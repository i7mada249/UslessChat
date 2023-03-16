package com.example.uslesschat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btSign: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDb: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.etEmail)
        etName = findViewById(R.id.etName)
        etPass = findViewById(R.id.etPassword)
        btSign = findViewById(R.id.btSign)

        btSign.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPass.text.toString()

            signUp(name, email, password)
        }
    }

    private fun signUp(name: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                } else {

                    Toast.makeText(this@SignUp, "some error occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDb = FirebaseDatabase.getInstance().getReference()
        mDb.child("user").child(uid).setValue(User(name, email, uid))
    }

}