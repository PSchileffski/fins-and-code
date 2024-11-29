package de.finsandcode.tauchtrainer

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import de.finsandcode.tauchtrainer.databinding.ActivityMainBinding
import de.finsandcode.tauchtrainer.PoolExercisesFragment
import de.finsandcode.tauchtrainer.OpenWaterExercisesFragment
import de.finsandcode.tauchtrainer.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding initialisieren
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar einrichten
        setSupportActionBar(binding.appBarMain.toolbar)

        // Floating Action Button (falls benötigt)
        /*binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }*/

        // Drawer und Navigation einrichten
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Listener für Navigation Drawer-Menüpunkte
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_pool_exercises -> {
                    replaceFragment(PoolExercisesFragment(), "Hallenbad-Übungen")
                }
                R.id.nav_open_water_exercises -> {
                    replaceFragment(OpenWaterExercisesFragment(), "Freiwasser-Übungen")
                }
                R.id.nav_settings -> {
                    replaceFragment(SettingsFragment(), "Einstellungen")
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START) // Drawer nach Auswahl schließen
            true
        }
    }

    // Methode zum Wechseln von Fragments
    private fun replaceFragment(fragment: Fragment, title: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, fragment)
            .commit()
        supportActionBar?.title = title // Titel der Toolbar aktualisieren
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Menü der Toolbar laden
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        // Navigation nach oben unterstützen
        val drawerLayout: DrawerLayout = binding.drawerLayout
        return if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            super.onSupportNavigateUp()
        }
    }
}
