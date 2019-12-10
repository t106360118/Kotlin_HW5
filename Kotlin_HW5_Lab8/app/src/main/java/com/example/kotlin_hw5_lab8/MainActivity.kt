package com.example.kotlin_hw5_lab8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(View.OnClickListener {
                val intent = Intent(this,MyService::class.java)
                startService(intent)
                Toast.makeText(this,"啟動service",Toast.LENGTH_LONG).show()
                finish()

        })
    }
}
