package de.finsandcode.tauchtrainer.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.finsandcode.tauchtrainer.model.Exercise
import java.io.FileOutputStream
import java.io.IOException

@Database(entities = [Exercise::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                // Stelle sicher, dass die Datenbank aus den Assets kopiert wird, wenn sie noch nicht existiert
                copyDatabaseFromAssets(context, "exercise-database.db")

                INSTANCE = instance
                instance
            }
        }

        private fun copyDatabaseFromAssets(context: Context, databaseName: String) {
            val dbFile = context.getDatabasePath(databaseName)
            if (!dbFile.exists()) {
                try {
                    val inputStream = context.assets.open(databaseName)
                    val outputStream = FileOutputStream(dbFile)
                    val buffer = ByteArray(1024)
                    var length: Int
                    while (inputStream.read(buffer).also { length = it } > 0) {
                        outputStream.write(buffer, 0, length)
                    }
                    outputStream.flush()
                    outputStream.close()
                    inputStream.close()
                } catch (e: IOException) {
                    Log.e("DatabaseHelper", "Error copying database", e)
                }
            }
        }
    }
}
