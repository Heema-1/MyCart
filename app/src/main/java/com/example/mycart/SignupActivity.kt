package com.example.mycart


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val button3 = findViewById(R.id.button3 ) as Button
        val editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2) as TextView
        val editTextTextPassword2 = findViewById(R.id.editTextTextPassword2) as TextView
        button3.setOnClickListener{
            when{
                TextUtils.isEmpty(editTextTextEmailAddress2.text.toString().trim { it <= ' ' }) ->{
                    Toast.makeText(
                        this@SignupActivity,
                        "Enter Email",
                    Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editTextTextPassword2.text.toString().trim { it <= ' ' }) ->{
                    Toast.makeText(
                        this@SignupActivity,
                        "Enter Password",
                    Toast.LENGTH_SHORT
                    ).show()
                }
                else ->{

                    val email: String =  editTextTextEmailAddress2.text.toString().trim { it <= ' '}
                    val password: String = editTextTextPassword2.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> {task ->
                                if (task.isSuccessful)
                                {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@SignupActivity,
                                         "Account Created!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this@SignupActivity, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()

                                }else {
                                    Toast.makeText(
                                        this@SignupActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT

                                    ).show()

                                }
                            }

                        )
                }

            }

        }
    }
}

