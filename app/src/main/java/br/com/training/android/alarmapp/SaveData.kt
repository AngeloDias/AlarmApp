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

    init {
        sharedPreferences = context!!.getSharedPreferences("myRef", Context.MODE_PRIVATE)
    }

    companion object {
        const val msgExtraAlarm = "message"
    }

    fun setAlarm(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)

        intent.putExtra(msgExtraAlarm, "Alarm time")

        intent.action = AlarmBroadcastReceiver.receiverActionName
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent)
    }
}