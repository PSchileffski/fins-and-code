package de.finsandcode.tauchtrainer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.finsandcode.tauchtrainer.model.Exercise

@Dao
interface ExerciseDao {

    //@Insert
    //suspend fun insert(exercise: Exercise)

    @Query("SELECT * FROM exercise_table")
    suspend fun getAllExercises(): List<Exercise>  // Direkte Liste, kein LiveData / Suspend-Methode
    /*fun getAllExercises(): LiveData<List<Exercise>>  // Gibt LiveData zurück*/

}
