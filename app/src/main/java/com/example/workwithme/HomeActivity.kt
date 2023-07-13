package com.example.workwithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar

class HomeActivity : AppCompatActivity() {
    lateinit var Btnjobpost:Button
    lateinit var Btnpostyourjobs:Button
    lateinit var toolbar:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Btnjobpost=findViewById(R.id.btn_jobposts)
        Btnpostyourjobs=findViewById(R.id.btn_postyourjob)
        setTitle("Work With Me App")



        Btnjobpost.setOnClickListener {
            val intent2 =Intent(this,JobsPostedActivity:: class.java)
            startActivity(intent2)

        }
        Btnpostyourjobs.setOnClickListener {
            val intent2 = Intent(this, PostJobActivity::class.java)
            startActivity(intent2)

        }

        }

        }

