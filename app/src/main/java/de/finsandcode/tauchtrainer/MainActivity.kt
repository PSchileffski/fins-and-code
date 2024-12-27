package de.finsandcode.tauchtrainer

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import de.finsandcode.tauchtrainer.adapter.ExerciseAdapter
import de.finsandcode.tauchtrainer.data.AppDatabase
import de.finsandcode.tauchtrainer.databinding.ActivityMainBinding
import de.finsandcode.tauchtrainer.model.Exercise
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding initialisieren
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar einrichten
        setSupportActionBar(binding.appBarMain.toolbar)

        // DrawerLayout und Navigation View einrichten
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_openwater,
                R.id.nav_pool,
                R.id.nav_settings
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Drawer-Toggle einrichten
        val toggle = androidx.appcompat.app.ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // RecyclerView einrichten
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Daten aus der Datenbank laden
        loadExercises()

        // Floating Action Button (optional)
        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }

    private fun loadExercises() {
        lifecycleScope.launch {
            try {
                // Datenbank initialisieren und Übungen laden
                val database = AppDatabase.getDatabase(applicationContext)
                val exercises: List<Exercise> = database.exerciseDao().getAllExercises()

                // Log für Debug-Zwecke
                exercises.forEach { exercise ->
                    Log.d("DatabaseTest", "Name: ${exercise.name}, Dauer: ${exercise.duration}")
                }

                // Adapter initialisieren und RecyclerView setzen
                exerciseAdapter = ExerciseAdapter(exercises)
                recyclerView.adapter = exerciseAdapter
            } catch (e: Exception) {
                Log.e("DatabaseError", "Fehler beim Laden der Übungen: ${e.message}")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return when (item.itemId) {
            R.id.nav_pool -> {
                navController.navigate(R.id.nav_pool)
                true
            }
            R.id.nav_openwater -> {
                navController.navigate(R.id.nav_openwater)
                true
            }
            R.id.nav_settings -> {
                navController.navigate(R.id.nav_settings)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
