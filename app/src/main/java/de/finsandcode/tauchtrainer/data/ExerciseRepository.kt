package de.finsandcode.tauchtrainer.data

import androidx.lifecycle.LiveData
import de.finsandcode.tauchtrainer.model.Exercise

class ExerciseRepository(private val db: AppDatabase) {

    suspend fun getAllExercises(): List<Exercise> {
        return db.exerciseDao().getAllExercises() // Suspend-Methode aufrufen
    }
}
