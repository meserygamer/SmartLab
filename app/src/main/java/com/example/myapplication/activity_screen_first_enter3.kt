package com.example.myapplication

import ClassifiedSwipeClass
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import com.example.myapplication.databinding.ActivityScreenFirstEnter2Binding
import com.example.myapplication.databinding.ActivityScreenFirstEnter3Binding
import java.util.prefs.Preferences

class activity_screen_first_enter3 : AppCompatActivity(), OnGestureListener {

    private var gestureDetectorCompat: GestureDetectorCompat? = null

    private lateinit var prefs : SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityScreenFirstEnter3Binding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)
        gestureDetectorCompat = GestureDetectorCompat(this, this)
        binding.textView.setOnClickListener{
            prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
            val editor : SharedPreferences.Editor = prefs.edit()
            editor.putBoolean("FirstLookWasPassed", true).apply();
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