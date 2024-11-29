package com.example.todo3.model


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.todo3.model.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ListenerRegistration

class TaskViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val realtimeDatabase = FirebaseDatabase.getInstance("https://todo3e-4d6d5-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks
    private var tasksListener: ListenerRegistration? = null

    init {
        fetchTasks()
    }

    override fun onCleared() {
        super.onCleared()
        tasksListener?.remove()
    }

    fun addTask(title: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val taskId = firestore.collection("users").document(userId).collection("tasks").document().id
            val task = Task(id = taskId, title = title, completed = false)

            // Add to Firestore
            firestore.collection("users")
                .document(userId)
                .collection("tasks")
                .document(taskId)
                .set(task)
                .addOnSuccessListener {
                    // Task added successfully to Firestore
                }
                .addOnFailureListener {
                    Log.e("TaskViewModel", "Error adding task to Firestore", it)
                }

            // Add to Realtime Database
            val taskRef = realtimeDatabase.getReference("users/$userId/tasks/$taskId")
            taskRef.setValue(task)
                .addOnSuccessListener {
                    // Task added successfully to Realtime Database
                }
                .addOnFailureListener {
                    Log.e("TaskViewModel", "Error adding task to Realtime Database", it)
                }
        }
    }

    fun deleteTask(taskId: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            // Delete from Firestore
            firestore.collection("users")
                .document(userId)
                .collection("tasks")
                .document(taskId)
                .delete()
                .addOnSuccessListener {
                    // Task deleted successfully from Firestore
                }
                .addOnFailureListener {
                    Log.e("TaskViewModel", "Error deleting task from Firestore", it)
                }

            // Delete from Realtime Database
            val taskRef = realtimeDatabase.getReference("users/$userId/tasks/$taskId")
            taskRef.removeValue()
                .addOnSuccessListener {
                    // Task deleted successfully from Realtime Database
                }
                .addOnFailureListener {
                    Log.e("TaskViewModel", "Error deleting task from Realtime Database", it)
                }
        }
    }

    fun fetchTasks() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            tasksListener?.remove() // Remove any existing listener to avoid multiple listeners
            tasksListener = firestore.collection("users")
                .document(userId)
                .collection("tasks")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.e("TaskViewModel", "Error fetching tasks", e)
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val taskList = snapshot.documents.map { document ->
                            document.toObject(Task::class.java)!!
                        }
                        _tasks.value = taskList
                    }
                }
        }
    }

    fun updateTaskCompletion(taskId: String, isCompleted: Boolean) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("tasks")
                .document(taskId)
                .update("completed", isCompleted)
                .addOnFailureListener { e ->
                    Log.e("TaskViewModel", "Failed to update task completion status: ${e.message}")
                }

            // Update in Realtime Database
            val taskRef = realtimeDatabase.getReference("users/$userId/tasks/$taskId")
            taskRef.updateChildren(mapOf("completed" to isCompleted))
                .addOnFailureListener { e ->
                    Log.e("TaskViewModel", "Failed to update task completion status in Realtime Database: ${e.message}")
                }
        }
    }

    fun completeAllTasks() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("tasks")
                .get()
                .addOnSuccessListener { snapshot ->
                    val batch = firestore.batch()
                    snapshot.documents.forEach { document ->
                        val taskRef = document.reference
                        batch.update(taskRef, "completed", true)
                    }
                    batch.commit().addOnSuccessListener {
                        // All tasks marked as completed successfully
                    }.addOnFailureListener { e ->
                        Log.e("TaskViewModel", "Failed to complete all tasks: ${e.message}")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("TaskViewModel", "Failed to fetch tasks for completing all: ${e.message}")
                }

            // Mark all tasks as completed in Realtime Database
            val tasksRef = realtimeDatabase.getReference("users/$userId/tasks")
            tasksRef.get().addOnSuccessListener { dataSnapshot ->
                val batch = realtimeDatabase.reference
                dataSnapshot.children.forEach { taskSnapshot ->
                    val taskId = taskSnapshot.key ?: return@forEach
                    batch.child("users/$userId/tasks/$taskId/completed").setValue(true)
                }
            }.addOnFailureListener { e ->
                Log.e("TaskViewModel", "Failed to fetch tasks for completing all in Realtime Database: ${e.message}")
            }
        }
    }

    fun getFinishedTasks(): LiveData<List<Task>> {
        return tasks.map { tasks -> tasks.filter { it.completed } }
    }
}

