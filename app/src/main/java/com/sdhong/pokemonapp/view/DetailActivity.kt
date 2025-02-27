package com.sdhong.pokemonapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sdhong.pokemonapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailUrl = intent.getStringExtra(DETAIL_URL)
    }

    companion object {
        private const val DETAIL_URL = "DETAIL_URL"

        fun newIntent(context: Context, detailUrl: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_URL, detailUrl)
            return intent
        }
    }
}