package com.example.tasklite.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tasklite.model.Task
import kotlinx.coroutines.flow.StateFlow

class DesafioTaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks
    private val _filter = MutableStateFlow(TaskFilter .ALL)
    val filteredTasks : StateFlow <List<Task>> = combine(_tasks, _filter) { tasks, filter ->
            when (filter) {
                TaskFilter .ALL -> tasks
                TaskFilter .COMPLETED -> tasks.filter { it.isDone
                }
                TaskFilter .PENDING -> tasks.filter { !it.isDone }
            }
        }.stateIn(
            scope =
                kotlinx.coroutines. CoroutineScope(kotlinx.coroutines. Dispatc
                        hers.Default),
            started = SharingStarted .Eagerly,
            initialValue = emptyList()
        )
    private var nextId = 1
    fun onAddTask(title: String) {
        if (title.isNotBlank()) {
            val newTask = Task(id = nextId++, title = title, isDone =
                false)
            _tasks.value = _tasks.value + newTask
        }
    }
    fun onTaskChecked(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
    }
    fun onDeleteTask(id: Int) {
        _tasks.value = _tasks.value.filterNot { it.id == id }
    }
    fun onFilterSelected(filter: TaskFilter) {
        _filter.value = filter
    }
}