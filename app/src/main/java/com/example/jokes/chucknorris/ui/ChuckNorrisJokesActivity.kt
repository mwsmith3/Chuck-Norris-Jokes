package com.example.jokes.chucknorris.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jokes.R

class ChuckNorrisJokesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_list)
    }
}