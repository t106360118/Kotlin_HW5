package com.example.kotlin_hw5_lab9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var flag=false;
    private val receiver=object :BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {
            val b:Bundle?=intent!!.extras
            tv_clock.text = String.format(
                "%02d:%02d:%02d", b!!.getInt("H"), b.getInt("M"), b.getInt("S")
            )
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(receiver, IntentFilter("MyMessage"))
        if (flag)
        {
            btn_start!!.text = "暫停"
        } else {
            btn_start!!.text = "開始"
        }
        btn_start.setOnClickListener {
            flag = !flag
            if (flag) {
                btn_start.text = "暫停"
                Toast.makeText(this, "計時開始", Toast.LENGTH_SHORT).show()
            } else {
                btn_start.text = "開始"
                Toast.makeText(this, "計時暫停", Toast.LENGTH_SHORT).show()
            }
            startService(Intent(this, MyService::class.java).putExtra("flag",flag))
        }
    }
    fun onDestory()
    {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
