package com.lab62.sligamer.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * Created by Justin Freres on 3/30/2018.
 * Lab 6-2 Stop Watch
 * Plugin Support with kotlin_version = '1.2.31'
 */
class MainActivity : AppCompatActivity() {


    // UI: ELEMENTS: BUTTON TOGGLE
    private lateinit var timeDisplay: TextView
    private lateinit var startBtn: Button
    private lateinit var stopBtn: Button
    private lateinit var resetBtn: Button

    // TIME ELEMENTS
    private lateinit var mHandler: Handler
    private var timeInMilliseconds: Long = 0L

    // WATCH TIME CLASS
    private lateinit var watchTime: WatchTime


    override fun onCreate(savedInstanceState: Bundle?) {

        // TASK 1: ACTIVATE THE ACTIVITY AND THE LAYOUT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TASK 2: CREATE REFERENCES TO UI COMPONENTS
        timeDisplay = findViewById(R.id.timerView)
        startBtn = findViewById(R.id.startBtn)
        stopBtn = findViewById(R.id.stopBtn)
        resetBtn = findViewById(R.id.resetBtn)

        // TASK 3: HIDE THE STOP BUTTON
        stopBtn.isEnabled = false
        resetBtn.isEnabled = false

        // TASK 4: INSTANTIATE THE OBJECT THAT MODELS THE STOPWATCH TIME
        watchTime = WatchTime()

        // TASK 5:  INSTANTIATE A HANDLER TO RUN ON THE UI THREAD
        mHandler = Handler()
    }

    fun startTimer(view: View)
    {
        // TASK 1: SET THE START ADN RESET BUTTON TO INVISIBLE
        // AND THE STOP BUTTON TO VISIBLE
        stopBtn.isEnabled = true
        startBtn.isEnabled = false
        resetBtn.isEnabled = false

        // TASK 2: SET THE START TIME AND CALL THE CUSTOM HANDLER
        watchTime.setStartTime(SystemClock.uptimeMillis())
        mHandler.postDelayed(updateTimerRunnable,  20)

    }

    fun stopTimer(view: View)
    {
        // TASK 1: DISABLE THE START BUTTON
        // AND ENABLE STOP BUTTON
        stopBtn.isEnabled = false
        startBtn.isEnabled = true
        resetBtn.isEnabled = true

        // TASK 2: UPDATE THE STORED TIME VALUE
        watchTime.addStoredTime(timeInMilliseconds)

        // TASK 3: HANDLER CLEARS THE MESSAGE QUEUE
        mHandler.removeCallbacks(updateTimerRunnable)
    }

    fun resetTimer(view: View){
        // TASK 1: RESET THE WATCHTIME
        watchTime.resetWatchTime()

        // TASK 2: RESET VARIABLES TO 0
        timeInMilliseconds = 0L
        var minutes = 0
        var seconds= 0
        var milliseconds = 0

        // TASK 3: DISPLAY THE TIME IN THE TIMERVIEW
        timeDisplay.text = String.format("%02d", minutes) + ":" +
                String.format("%02d", seconds) + ":" +
                String.format("%02d", milliseconds)
    }

    private val updateTimerRunnable: Runnable = object: Runnable {
        override fun run() {
            // TASK 1: COMPUTE THE TIME DIFFERENCE
            timeInMilliseconds = (SystemClock.uptimeMillis() - watchTime.getStartTime())
            watchTime.setTimeUpdate(watchTime.getStoredTime() + timeInMilliseconds)
            var time = (watchTime.getTimeUpdate() / 1000)

            // TASK 2: COMPUTE MINUTES, SECONDS, AND MILLISECONDS
            var minutes = (time / 60)
            var seconds = (time % 60)
            var milliseconds = (watchTime.getTimeUpdate() % 1000)

            // TASK 3: DISPLAY THE TIME IN THE TIMERVIEW
            timeDisplay.text = String.format("%02d", minutes) + ":" +
                    String.format("%02d", seconds) + ":" +
                    String.format("%02d", milliseconds)

            // TASK 4: SPECIFY NO TIME LAPSE BETWEEN POSTING
            mHandler.postDelayed(this, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        if(id == R.string.action_settings){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
