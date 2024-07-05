package com.example.cookbook

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
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
        binding.stepData.text = intent.getStringExtra("des")

        var ing =intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()
        binding.time.text = ing?.get(0)

        for (i in 1 until ing!!.size) {
            binding.ingText.text=
                """${binding.ingText.text} 🟢 ${ing[i]}
                    
                """.trimIndent()
        }

        binding.steps.background=null
        binding.steps.setTextColor(getColor(R.color.black))

        binding.steps.setOnClickListener{
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.setTextColor(getColor(R.color.white))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.background=null
            binding.stepScrollview.visibility=View.VISIBLE
            binding.ingScrollview.visibility=View.GONE
        }

        binding.ing.setOnClickListener{
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.setTextColor(getColor(R.color.black))
            binding.ing.setTextColor(getColor(R.color.white))
            binding.steps.background=null
            binding.stepScrollview.visibility=View.GONE
            binding.ingScrollview.visibility=View.VISIBLE
        }

        binding.fullScreen.setOnClickListener {
            if (imgCrop) {
                binding.itemImg.scaleType = ImageView.ScaleType.FIT_CENTER
                binding.shadow.visibility = View.GONE
                binding.fullScreen.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_ATOP)
                imgCrop = !imgCrop
            } else {
                binding.itemImg.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.shadow.visibility = View.VISIBLE
                binding.fullScreen.setColorFilter(null)
                imgCrop = !imgCrop
            }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}