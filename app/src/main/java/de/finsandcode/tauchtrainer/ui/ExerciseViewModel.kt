package de.finsandcode.tauchtrainer.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import de.finsandcode.tauchtrainer.data.AppDatabase
import de.finsandcode.tauchtrainer.model.Exercise

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application) // Hole die Datenbank
    private val exerciseDao = db.exerciseDao() // Hole den Dao für Übungen
    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAllExercises()


    // Funktion zum Hinzufügen einer Übung
    fun addExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.insert(exercise)
        }
    }

}