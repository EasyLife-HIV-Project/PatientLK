package aaa.bbb.registration.patient

import aaa.bbb.registration.fragments.CalendarFragment
import aaa.bbb.registration.R
import aaa.bbb.registration.databinding.ActivityHomeBinding
import aaa.bbb.registration.fragments.GameFragment
import aaa.bbb.registration.fragments.ProfileFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendarfragment = CalendarFragment()
        val gamegragment = GameFragment()
        val profilefragment = ProfileFragment()

        binding.bNav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.calendar -> switchFragments(calendarfragment)
                R.id.game -> switchFragments(gamegragment)
                R.id.profile -> switchFragments(profilefragment)
            }
            true
        }
    }
    private fun switchFragments(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}