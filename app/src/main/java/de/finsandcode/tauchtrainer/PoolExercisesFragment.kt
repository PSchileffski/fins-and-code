package de.finsandcode.tauchtrainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import de.finsandcode.tauchtrainer.databinding.FragmentPoolExercisesBinding
import de.finsandcode.tauchtrainer.ui.ExerciseViewModel
import de.finsandcode.tauchtrainer.adapter.ExerciseAdapter
import de.finsandcode.tauchtrainer.model.Exercise
import kotlinx.coroutines.launch

class PoolExercisesFragment : Fragment() {

    // ViewModel für die Datenbankoperationen
    private val exerciseViewModel: ExerciseViewModel by activityViewModels()

    // Binding-Objekt, um auf die UI-Elemente zuzugreifen (falls ViewBinding verwendet wird)
    private var _binding: FragmentPoolExercisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPoolExercisesBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // RecyclerView einrichten
        val recyclerView = binding.exerciseRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Beobachte die Liste der Übungen im ViewModel
        exerciseViewModel.allExercises.observe(viewLifecycleOwner, Observer { exercises ->
            // Wenn die Liste der Übungen aktualisiert wird, Adapter setzen
            val adapter = ExerciseAdapter(exercises)
            recyclerView.adapter = adapter
        })

        // Beispiel: Füge eine Übung in die Datenbank ein, sobald das Fragment erstellt wird
        val exercise = Exercise(name = "Kraulen", description = "Schwimmtechnik", duration = 60)

        // Die Methode aus dem ViewModel aufrufen, um die Übung hinzuzufügen
        lifecycleScope.launch {
            exerciseViewModel.addExercise(exercise)
        }

        // Beispiel: Setze eine Aktion, z.B. ein Button, um eine Übung hinzuzufügen
        binding.addExerciseButton.setOnClickListener {
            val newExercise = Exercise(name = "Brustschwimmen", description = "Schwimmtechnik", duration = 45)
            lifecycleScope.launch {
                exerciseViewModel.addExercise(newExercise)
            }
        }

        return rootView
    }

    // Stelle sicher, dass du das Binding freigibst, wenn das Fragment nicht mehr benötigt wird
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
