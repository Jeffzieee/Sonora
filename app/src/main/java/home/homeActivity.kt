package home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.codered.sonora.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class homeActivity : AppCompatActivity() {

    private val homeFragment = home.homeFragment()
    private val libraryFragment = home.libraryFragment()
    private val searchFragment = home.searchFragment()
    private val upgradeFragment = home.upgradeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navDock = findViewById<BottomNavigationView>(R.id.naviDock)

        replaceFragment(homeFragment)

        navDock.setOnItemSelectedListener {
            when(it.itemId){
                  R.id.home->replaceFragment(homeFragment)
                  R.id.search->replaceFragment(searchFragment)
                  R.id.library->replaceFragment(libraryFragment)
                  R.id.upgrade->replaceFragment(upgradeFragment)
            }
            true
        }


    }
    private fun replaceFragment(fragment : Fragment){
        if(fragment != null){
           val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_Container, fragment)
            transaction.commit()
        }
    }
}