package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.cookbook.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecipeBinding
    var imgCrop = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
        binding.tittle.text = intent.getStringExtra("tittle")
        binding.ingText.text = intent.getStringExtra("ing")
        binding.stepData.text = intent.getStringExtra("des")

        binding.fullScreen.setOnClickListener {
            if (imgCrop) {
                binding.itemImg.scaleType = ImageView.ScaleType.FIT_CENTER
                binding.shadow.visibility= View.GONE
                imgCrop = !imgCrop
            } else {
                binding.itemImg.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.shadow.visibility= View.GONE
                imgCrop = !imgCrop
            }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}