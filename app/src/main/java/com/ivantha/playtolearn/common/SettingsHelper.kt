package com.ivantha.playtolearn.common

import android.content.Context
import com.ivantha.playtolearn.model.Settings
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SettingsHelper {

    fun newSettings() {
        Session.currentSettings = Settings()
    }

    fun loadSettings(context: Context) {
        try {
            val fileInputStream = context.openFileInput("settingsFile")
            val objectInputStream = ObjectInputStream(fileInputStream)

            Session.currentSettings = objectInputStream.readObject() as Settings

            objectInputStream.close()
            fileInputStream.close()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun saveSettings(context: Context) {
        try {
            val fileOutputStream = context.openFileOutput("settingsFile", Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            objectOutputStream.writeObject(Session.currentSettings)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun settingsExists(context: Context): Boolean {
        try {
            val fileInputStream = context.openFileInput("settingsFile")
            return fileInputStream != null
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        }

    }
}
