package br.com.training.android.alarmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val _popTimeFragmentTag = "SelectTime"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSetTime.setOnClickListener {
            val popTime = PopTime()
            val fragManager = supportFragmentManager

            popTime.show(fragManager, _popTimeFragmentTag)
        }

    }

    fun setTimeEvent(hours: Int, minute: Int) {
        textViewShowTime.text = "${hours}:${minute}"
        val saveData = SaveData(applicationContext)

        saveData.setAlarm(hours, minute)
    }

}
