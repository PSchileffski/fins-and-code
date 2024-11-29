package de.finsandcode.tauchtrainer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import de.finsandcode.tauchtrainer.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Hier kannst du deine Daten direkt festlegen oder verarbeiten
        val textView: TextView = binding.textHome
        textView.text = "Willkommen zu den Freiwasser Übungen!" // Beispieltext

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
