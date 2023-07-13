package com.example.workwithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    lateinit var Txt_login: TextView
    lateinit var Edtregemail: EditText
    lateinit var Edtregpass: EditText
    lateinit var Edtconfpass: EditText
    lateinit var Btnreg: Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        Txt_login=findViewById(R.id.tvlogin)
        Edtregemail=findViewById(R.id.edt_RegEmail)
        Edtregpass=findViewById(R.id.edt_RegPassword)
        Edtconfpass=findViewById(R.id.edt_confirmPassword)
        Btnreg=findViewById(R.id.btn_register)
        auth= Firebase.auth

        Txt_login.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        Btnreg.setOnClickListener {
            Signupuser()
        }


    }
    private fun Signupuser(){
        val email=Edtregemail.text.toString()
        val pass=Edtregpass.text.toString()
        val confirmpass=Edtconfpass.text.toString()

        if (email.isBlank()||pass.isBlank()||confirmpass.isBlank()){
            Toast.makeText(this,"Password and email can't be blank!", Toast.LENGTH_LONG).show()
            return
        }else if (pass != confirmpass) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show()
        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener (this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to create user", Toast.LENGTH_LONG).show()
            }
        }
    }
}

