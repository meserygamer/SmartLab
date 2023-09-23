package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.myapplication.databinding.ActivityScreenFirstEnter1Binding

class screen_first_enter : AppCompatActivity(), GestureDetector.OnGestureListener {
    private var gestureDetectorCompat: GestureDetectorCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityScreenFirstEnter1Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_screen_first_enter1)
        gestureDetectorCompat = GestureDetectorCompat(this, this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event != null)
        {
            gestureDetectorCompat!!.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }


    override fun onDown(p0: MotionEvent): Boolean {
        Toast.makeText(this, "OnDown", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        Toast.makeText(this, "onShowPress", Toast.LENGTH_SHORT).show()
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        Toast.makeText(this, "onScroll", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        Toast.makeText(this, "onLongPress", Toast.LENGTH_SHORT).show()
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        Toast.makeText(this, "onFling", Toast.LENGTH_SHORT).show()
        return true
    }
}