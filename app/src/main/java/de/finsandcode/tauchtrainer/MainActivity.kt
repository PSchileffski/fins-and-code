package de.finsandcode.tauchtrainer

import android.os.Bundle
//import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import de.finsandcode.tauchtrainer.databinding.ActivityMainBinding
import androidx.appcompat.app.ActionBarDrawerToggle
import android.content.Context
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.room.Room
import de.finsandcode.tauchtrainer.adapter.ExerciseAdapter
import de.finsandcode.tauchtrainer.data.AppDatabase
import de.finsandcode.tauchtrainer.model.Exercise
import de.finsandcode.tauchtrainer.ui.ExerciseViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // ViewModel für die Datenbankoperationen
    private val exerciseViewModel: ExerciseViewModel by viewModels()

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

        // Navigation Controller
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Navigation mit AppBarConfiguration einrichten
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,  // Startpunkt
                R.id.nav_openwater,
                R.id.nav_pool,
                R.id.nav_settings
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Beobachte die Liste der Übungen im ViewModel
        exerciseViewModel.allExercises.observe(this, Observer { exercises ->
            // Hier kannst du mit der 'exercises' Liste weiterarbeiten
            // Zum Beispiel kannst du den RecyclerView hier mit dem Adapter verbinden
            val adapter = ExerciseAdapter(exercises)
            // recyclerView.adapter = adapter (Falls du ein RecyclerView hast)
        })

        // DrawerToggle einrichten
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Floating Action Button (optional)
        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Holen des NavController
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        return when (item.itemId) {
            R.id.nav_pool -> {
                // Navigation zu Hallenbad-Übungen
                navController.navigate(R.id.nav_pool) // id sollte mit der in der mobile_navigation.xml übereinstimmen
                true
            }
            R.id.nav_openwater -> {
                // Navigation zu Freiwasser-Übungen
                navController.navigate(R.id.nav_openwater) // id sollte mit der in der mobile_navigation.xml übereinstimmen
                true
            }
            R.id.nav_settings -> {
                // Navigation zu Einstellungen
                navController.navigate(R.id.nav_settings) // id sollte mit der in der mobile_navigation.xml übereinstimmen
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        // Navigation nach oben unterstützen
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
