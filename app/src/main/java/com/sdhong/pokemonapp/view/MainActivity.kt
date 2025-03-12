package com.sdhong.pokemonapp.view

import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.tabs.TabLayoutMediator
import com.sdhong.pokemonapp.base.BaseActivity
import com.sdhong.pokemonapp.databinding.ActivityMainBinding
import com.sdhong.pokemonapp.local.MainTab
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    bindingFactory = ActivityMainBinding::inflate
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpView()
    }

    private fun setUpView() {
        val fragments = listOf(AllPokemonFragment(), HistoryFragment())
        binding.viewPager.adapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)

        val mainTabs = MainTab.entries.toTypedArray()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(mainTabs[position].titleId)
            tab.icon = AppCompatResources.getDrawable(this, mainTabs[position].iconId)
        }.attach()
    }
}