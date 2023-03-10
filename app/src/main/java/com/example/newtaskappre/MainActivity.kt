package com.example.newtaskappre

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newtaskappre.databinding.ActivityMainBinding
import com.example.newtaskappre.utils.Preferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //this.setSupportActionBar(binding.toolbar)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.newTaskFragment,
                R.id.onBoardFragment,
                R.id.authFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        lifecycleScope.launch {
            val session = MainApplication.appDataBase?.sessionDao?.getSession()
            if (session == null) {
                navController.navigate(R.id.authFragment)
            } else {
                if (Preferences(this@MainActivity).getHaveSeenBoarding()) {
                    navController.navigate(
                        R.id.navigation_home
                    )
                } else {
                    navController.navigate(
                        R.id.onBoardFragment
                    )
                }
            }

        }

        navController.addOnDestinationChangedListener { _, dest, _ ->
            navView.visibility =
                if (dest.id == R.id.newTaskFragment || dest.id == R.id.onBoardFragment || dest.id == R.id.authFragment) View.GONE else View.VISIBLE
        }
    }
}