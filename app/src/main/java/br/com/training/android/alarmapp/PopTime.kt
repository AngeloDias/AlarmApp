package br.com.training.android.alarmapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

class PopTime: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.pop_time, container, false)
        val btnDone = myView.findViewById<Button>(R.id.btnDone)
        val timePicker = myView.findViewById<TimePicker>(R.id.timePicker)

        btnDone.setOnClickListener {
            val mainActivity = activity as MainActivity

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mainActivity.setTimeEvent(timePicker.hour, timePicker.minute)
            } else {
                mainActivity.setTimeEvent(timePicker.currentHour, timePicker.currentMinute)
            }

            this.dismiss()
        }

        return myView
    }

}