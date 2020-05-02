package br.com.training.android.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmBroadcastReceiver: BroadcastReceiver() {

    companion object {
        const val receiverActionName = "br.com.training.kotlin.alarmapp"
    }

    override fun onReceive(p0: Context?, p1: Intent?) {

        if(p1!!.action.equals(receiverActionName)) {
            Toast.makeText(p0, p1.extras!!.getString(SaveData.msgExtraAlarm), Toast.LENGTH_LONG).show()
        } else if(p1.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            val saveData = SaveData(p0!!)

            saveData.setAlarm()
        }
    }
}