// ExerciseAdapter.kt
package de.finsandcode.tauchtrainer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.finsandcode.tauchtrainer.R
import de.finsandcode.tauchtrainer.model.Exercise

class ExerciseAdapter(private val exercises: List<Exercise>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.nameTextView.text = exercise.name
        holder.descriptionTextView.text = exercise.description
        holder.durationTextView.text = "${exercise.duration} min"
    }

    override fun getItemCount(): Int = exercises.size

    // ViewHolder für die einzelnen Listenelemente
    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.exerciseName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.exerciseDescription)
        val durationTextView: TextView = itemView.findViewById(R.id.exerciseDuration)
    }
}
