package com.example.todo3.screen.parts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo3.model.Task

@Composable
fun FinishedTasksList(tasks: List<Task>, modifier: Modifier = Modifier) {
    if (tasks.isEmpty()) {
        Text("Tidak ada tugas yang telah selesai.", modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(modifier = modifier) {
            items(tasks) { task ->
                FinishedTaskItem(task)
            }
        }
    }
}