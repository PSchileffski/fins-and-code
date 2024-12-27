package de.finsandcode.tauchtrainer.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.finsandcode.tauchtrainer.data.AppDatabase
import de.finsandcode.tauchtrainer.data.ExerciseRepository
import de.finsandcode.tauchtrainer.model.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExerciseRepository

    // MutableStateFlow für eine direkte Liste der Übungen
    private val _allExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val allExercises: StateFlow<List<Exercise>> = _allExercises

    init {
        val database = AppDatabase.getDatabase(application) // Zugriff auf die Datenbank
        repository = ExerciseRepository(database)

        // Lade Daten aus dem Repository in einer Coroutine
        viewModelScope.launch {
            val exercises = repository.getAllExercises()
            _allExercises.value = exercises
        }
    }
}
