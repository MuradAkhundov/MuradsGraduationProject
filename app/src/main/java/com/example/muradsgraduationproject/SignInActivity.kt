package com.example.muradsgraduationproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.muradsgraduationproject.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySignInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this@SignInActivity , SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener{
            when{
                TextUtils.isEmpty(binding.etLoginEmail.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(
                        this@SignInActivity,
                        "Please enter email",
                        Toast.LENGTH_SHORT

                    ).show()

                }
                TextUtils.isEmpty(binding.etLoginPassword.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(
                        this@SignInActivity,
                        "Please enter password",
                        Toast.LENGTH_SHORT

                    ).show()
                }
                else ->{
                    val  email : String = binding.etLoginEmail.text.toString().trim() {it <= ' '}
                    val password : String = binding.etLoginPassword.text.toString().trim() {it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener({
                                task ->
                            if(task.isSuccessful){

                                val firebaseUser : FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@SignInActivity
                                    ,"You Logged in succesfully",
                                    Toast.LENGTH_SHORT
                                ).show()


                                val intent = Intent(this@SignInActivity , MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id" ,
                                    FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(
                                    this@SignInActivity,
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