package com.example.adminprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.adminprofile.databinding.ActivitySliderBinding
import com.google.android.material.button.MaterialButton

class SliderActivity : AppCompatActivity() {

    lateinit var myMaterialButton: MaterialButton

    private val introSliderAdapter = introSlideAdapter(
        listOf(
            introSlide(
                "Society",
                "Smart Society App",
                R.drawable.societyimg
            ),

            introSlide(
                "Driver",
                "Drivers are Available",
                R.drawable.driverimg
            ),
            introSlide(
                "HoseHelper",
                "House Helper are Available",
                R.drawable.househelperimg
            ),
            introSlide(
                "Grocery Store",
                "Grocery Store is Available",
                R.drawable.grocerystoreimg
            ),
            introSlide(
                "Maid",
                "Mayank Sharma is Maid",
                R.drawable.maisimg
            ),
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        val introSliderViewPager: ViewPager2 = findViewById(R.id.introSliderPager1)

        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        myMaterialButton = findViewById(R.id.buttonNext)

        myMaterialButton.setOnClickListener {

            val intent = Intent(this@SliderActivity,MainActivity::class.java)
            startActivity(intent)

        }


    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        val indicatorsContainer = findViewById<LinearLayout>(R.id.indicatorsContainer)

        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index : Int){
        val indicatorsContainer = findViewById<LinearLayout>(R.id.indicatorsContainer)
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i==index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

}