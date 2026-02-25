package com.mobile.lab2mobile

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val artworks = ArtworkCollection.artworks
    private var currentIndex = 0

    companion object {
        private const val KEY_CURRENT_INDEX = "CURRENT_INDEX"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX, 0)
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val buttonPrev = findViewById<Button>(R.id.btn_prev)
        val buttonNext = findViewById<Button>(R.id.btn_next)
        val imageView = findViewById<ImageView>(R.id.iv_pic)
        val textView = findViewById<TextView>(R.id.tv_namePic)


        imageView.setImageResource(artworks[currentIndex].pictureResId)
        imageView.contentDescription = getString(artworks[currentIndex].titleResId)
        textView.setText(artworks[currentIndex].titleResId)

        buttonPrev.isEnabled = currentIndex > 0
        buttonNext.isEnabled = currentIndex < artworks.size - 1


        buttonNext.setOnClickListener {
            if (currentIndex < artworks.size - 1) {
                currentIndex++
                imageView.setImageResource(artworks[currentIndex].pictureResId)
                imageView.contentDescription = getString(artworks[currentIndex].titleResId)
                textView.setText(artworks[currentIndex].titleResId)

                if(currentIndex == artworks.size - 1){
                    buttonNext.isEnabled = false
                }else{
                    buttonPrev.isEnabled = true
                }
            }
        }

        buttonPrev.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                imageView.setImageResource(artworks[currentIndex].pictureResId)
                imageView.contentDescription = getString(artworks[currentIndex].titleResId)
                textView.setText(artworks[currentIndex].titleResId)

                if(currentIndex == 0){
                    buttonPrev.isEnabled = false
                }else{
                    buttonNext.isEnabled = true
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CURRENT_INDEX, currentIndex)
    }
}