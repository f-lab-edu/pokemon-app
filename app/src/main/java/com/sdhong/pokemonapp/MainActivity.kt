package com.sdhong.pokemonapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sdhong.pokemonapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragments = listOf(AllPokemonFragment(), HistoryFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpView()
    }

    private fun setUpView() {
        binding.viewPager.adapter = ViewPagerAdapter(fragments, this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.all_pokemon_tab_title)
                1 -> getString(R.string.history_tab_title)
                else -> ""
            }
            tab.icon = when (position) {
                0 -> AppCompatResources.getDrawable(this, R.drawable.ic_pikachu)
                1 -> AppCompatResources.getDrawable(this, R.drawable.ic_pokeball)
                else -> null
            }
        }.attach()
    }
}