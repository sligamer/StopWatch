package com.lab62.sligamer.stopwatch

/**
 * Created by Justin Freres on 3/30/2018.
 * Lab 6-2 Stop Watch
 * Plugin Support with kotlin_version = '1.2.31'
 */
class WatchTime {

    // DECLARE TIME ELEMENTS
    private var mStartTime: Long = 0L
    private var mTimeUpdate: Long = 0L
    private var mStoredTime: Long = 0L

    constructor()
    {
        mStartTime = 0L
        mTimeUpdate = 0L
        mStoredTime = 0L
    }

    fun resetWatchTime()
    {
        mStartTime = 0L
        mTimeUpdate = 0L
        mStoredTime = 0L
    }

    fun setStartTime(startTime: Long)
    {
        mStartTime = startTime
    }

    fun getStartTime()
            : Long
    {
        return mStartTime
    }

    fun setTimeUpdate(timeUpdate: Long)
    {
        mTimeUpdate = timeUpdate
    }

    fun getTimeUpdate()
            : Long
    {
        return mTimeUpdate
    }

    fun addStoredTime(timeInMilliseconds: Long)
    {
        mStoredTime += timeInMilliseconds
    }

    fun getStoredTime()
            : Long {
        return mStoredTime
    }





}