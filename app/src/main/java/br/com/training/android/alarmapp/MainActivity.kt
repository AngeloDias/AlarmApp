package br.com.training.android.alarmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val _popTimeFragmentTag = "SelectTime"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveData = SaveData(applicationContext)
        textViewShowTime.text = "${saveData.getHour()}:${saveData.getMinute()}"

        btnSetTime.setOnClickListener {
            val popTime = PopTime()
            val fragManager = supportFragmentManager

            popTime.show(fragManager, _popTimeFragmentTag)
        }

    }

    fun setTimeEvent(hours: Int, minute: Int) {
        textViewShowTime.text = "${hours}:${minute}"
        val saveData = SaveData(applicationContext)

        saveData.saveData(hours, minute)
        saveData.setAlarm()
    }

}
