package goope.rimu.caloriefittrainer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import goope.rimu.caloriefittrainer.R
import goope.rimu.caloriefittrainer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        navController.navigate(R.id.firstFragment)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.firstFragment -> {
                    navController.popBackStack()
                    navController.navigate(R.id.firstFragment)
                }

                R.id.profileFragment -> {
                    navController.popBackStack()
                    navController.navigate(R.id.profileFragment)
                }
            }
            true
        }
    }
}