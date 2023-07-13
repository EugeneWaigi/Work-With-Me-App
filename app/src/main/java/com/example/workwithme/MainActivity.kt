package com.example.workwithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(){
    lateinit var Txt_gotoreg: TextView
    lateinit var Edtlogemail: EditText
    lateinit var Edtlogpass: EditText
    lateinit var Btn_login: Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Txt_gotoreg=findViewById(R.id.tvreg)
        Edtlogemail=findViewById(R.id.edt_logEmail)
        Edtlogpass=findViewById(R.id.edt_logPass)
        Btn_login=findViewById(R.id.btn_login)
        auth= FirebaseAuth.getInstance()

        Txt_gotoreg.setOnClickListener{
            val intent2 = Intent(this,RegistrationActivity::class.java)
            startActivity(intent2)
        }
        Btn_login.setOnClickListener {
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()


        }

    }
    private fun Login(){
        val email=Edtlogemail.text.toString()
        val password=Edtlogpass.text.toString()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Login failed",Toast.LENGTH_LONG).show()
            }

        }
    }
}





