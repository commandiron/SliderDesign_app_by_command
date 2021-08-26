package com.demirli.a28sliderdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewpager: ViewPager
    private lateinit var adapter: ImageAdapter

    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    private var flagForLoopRight = false
    private var flagForLoopLeft = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runnable = Runnable {}
        handler = Handler()

        setAdapter()

        autoSwipe()

        auto_swipe_btn.setOnClickListener {
            autoSwipe()
            auto_swipe_btn.visibility = View.INVISIBLE
        }

        slide_left.setOnClickListener {
            handler.removeCallbacks(runnable)
            swipeLeftLoop()
            auto_swipe_btn.visibility = View.VISIBLE
        }
        slide_right.setOnClickListener {
            handler.removeCallbacks(runnable)
            swipeRightLoop()
            auto_swipe_btn.visibility = View.VISIBLE
        }
    }

    fun setAdapter(){
        var imageList = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        viewpager = findViewById(R.id.viewPager)
        adapter = ImageAdapter(this,imageList)
        viewpager.adapter = adapter
    }

    fun swipeRightLoop(){
        if(flagForLoopRight == true){
            viewpager.setCurrentItem(0)
            flagForLoopRight = false
        }else{
            val currentItem = viewpager.currentItem
            viewpager.setCurrentItem(currentItem+1)
            if(currentItem == 1){
                flagForLoopRight = true
            }
        }
    }
    fun swipeLeftLoop(){
        if(flagForLoopLeft == true){
            viewpager.setCurrentItem(2)
            flagForLoopLeft = false
        }else{
            val currentItem = viewpager.currentItem
            viewpager.setCurrentItem(currentItem-1)
            if(currentItem == 1){
                flagForLoopLeft = true
            }
        }
    }

    fun autoSwipe(){
        runnable = object: Runnable{
            override fun run() {
                swipeRightLoop()
                handler.postDelayed(runnable,2000)
            }
        }
        handler.postDelayed(runnable,2000)
    }
}
