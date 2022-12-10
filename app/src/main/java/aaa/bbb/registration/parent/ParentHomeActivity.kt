package aaa.bbb.registration.parent

import aaa.bbb.registration.fragments.CalendarFragment
import aaa.bbb.registration.R
import aaa.bbb.registration.databinding.ActivityParentHomeBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class ParentHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendarfragment = CalendarFragment()

        binding.bNav1.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.calendar -> switchFragments(calendarfragment)
            }
            true
        }
    }

    private fun switchFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}