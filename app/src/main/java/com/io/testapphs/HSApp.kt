package com.io.testapphs

import android.app.Application
import androidx.room.Room
import com.io.data.country.db.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HSApp : Application()