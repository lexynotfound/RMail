package com.raihanardila.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raihanardila.myapplication.databinding.ActivityMainBinding
import com.raihanardila.myapplication.R
import com.raihanardila.myapplication.Mail


class MainActivity : AppCompatActivity() {
    //Membuat mainbainding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //memanggil fungsi loadfragment() dengan fragment pertama kali
        loadFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.profile -> {
                    loadFragment(AccountFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    // Memindahkan fungsi loadFragment() ke dalam kelas MainActivity
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}
