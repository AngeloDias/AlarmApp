package br.com.training.android.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

class SaveData(ctx: Context) {
    private var context: Context? = ctx
    private var sharedPreferences: SharedPreferences? = null
    private var _hourKeySharedPref: String? = null
    private var _minuteKeySharedPref: String? = null

    init {
        // shared preferences is a good tool to save information that isn't so important because the file "myRef" is available for anyone
        this.sharedPreferences = context!!.getSharedPreferences("myRef", Context.MODE_PRIVATE)
        this._hourKeySharedPref = "hourKey"
        this._minuteKeySharedPref = "minuteKey"
    }

    companion object {
        const val msgExtraAlarm = "message"
    }

    fun saveData(hour: Int, minute: Int) {
        val editor = sharedPreferences!!.edit()

        editor.putInt(_hourKeySharedPref, hour)
        editor.putInt(_minuteKeySharedPref, minute)
        editor.apply()
    }

    fun getHour(): Int {
        return sharedPreferences!!.getInt(_hourKeySharedPref, 0)
    }

    fun getMinute(): Int {
        return sharedPreferences!!.getInt(_minuteKeySharedPref, 0)
    }

    fun setAlarm() {
        val calendar = Calendar.getInstance()
        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)

        intent.putExtra(msgExtraAlarm, "Alarm time")

        intent.action = AlarmBroadcastReceiver.receiverActionName
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        calendar.set(Calendar.HOUR_OF_DAY, getHour())
        calendar.set(Calendar.MINUTE, getMinute())
        calendar.set(Calendar.SECOND, 0)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent)
    }
}