package com.example.mycart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val button2 = findViewById(R.id.button2) as Button
        button2.setOnClickListener{


            startActivity(Intent(this@SigninActivity, SignupActivity::class.java))

        }

        val button = findViewById(R.id.button) as Button
        val editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress) as TextView
        val editTextTextPassword = findViewById(R.id.editTextTextPassword) as TextView
        button.setOnClickListener{
            when{
                TextUtils.isEmpty(editTextTextEmailAddress.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(
                        this@SigninActivity,
                        "Enter Email",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                TextUtils.isEmpty(editTextTextPassword.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(
                        this@SigninActivity,
                        "Enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = editTextTextEmailAddress.text.toString().trim { it <= ' '}
                    val password: String = editTextTextPassword.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@SigninActivity,
                                    "You are logged in Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()


                                val intent = Intent(this@SigninActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()

                            }else {
                                Toast.makeText(
                                    this@SigninActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT

                                ).show()
                            }
                        }

                }


            }
        }
    }
}