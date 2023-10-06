package com.example.myapplication

import ClassifiedSwipeClass
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import com.example.myapplication.databinding.ActivityScreenFirstEnter2Binding
import com.example.myapplication.databinding.ActivityScreenFirstEnter3Binding

class activity_screen_first_enter3 : AppCompatActivity(), OnGestureListener {

    private var gestureDetectorCompat: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityScreenFirstEnter3Binding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)
        gestureDetectorCompat = GestureDetectorCompat(this, this)
        binding.textView.setOnClickListener{
            val intent = Intent(this@activity_screen_first_enter3, LoginWindow::class.java)
            startActivity(intent)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event != null)
        {
            gestureDetectorCompat!!.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        when (ClassifiedSwipeClass(p0, p1, p2, p3).defineSwipe(150))
        {
            SwipeVariant.SwipeRight ->{
                val intent = Intent(this@activity_screen_first_enter3, activity_screen_first_enter2::class.java)
                startActivity(intent)
            }
            else ->
            {

            }
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return true
    }
}