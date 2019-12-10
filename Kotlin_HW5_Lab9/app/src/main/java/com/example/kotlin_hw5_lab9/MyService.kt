package com.example.kotlin_hw5_lab9

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder

class MyService : Service() {

    var flag:Boolean = false
    private var h=0
    private var m=0
    private var s=0
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        flag=intent!!.getBooleanExtra("flag",false)
        Thread(Runnable {
            while (flag)
            {
                try {
                    Thread.sleep(1000)
                }
                catch (e:InterruptedException)
                {
                    e.printStackTrace()
                }
                s++
                if (s >= 60) {
                    s = 0
                    m++
                    if (m >= 60) {
                        m = 0
                        h++
                    }
                }
                var intent=Intent("MyMessage")
                var bundle=Bundle()
                bundle.putInt("H",h)
                bundle.putInt("M",m)
                bundle.putInt("S",s)
                intent.putExtras(bundle)
                sendBroadcast(intent)

            }
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }
}
