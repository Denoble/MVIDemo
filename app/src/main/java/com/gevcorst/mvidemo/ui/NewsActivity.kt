package com.gevcorst.mvidemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gevcorst.mvidemo.BuildConfig
import com.gevcorst.mvidemo.R

class NewsActivity : AppCompatActivity() {
    val key = BuildConfig.NEWS_API_KEY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}