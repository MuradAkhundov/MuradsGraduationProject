package com.example.muradsgraduationproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.muradsgraduationproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {

   private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener{
            onBackPressed()
        }
        binding.btnRegister.setOnClickListener{
            when{
                TextUtils.isEmpty(binding.etRegisterEmail.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(
                        this@SignUpActivity,
                    "Please enter email",
                        Toast.LENGTH_SHORT

                    ).show()

                }
                TextUtils.isEmpty(binding.etRegisterPassword.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(
                        this@SignUpActivity,
                        "Please enter password",
                        Toast.LENGTH_SHORT

                    ).show()
            }
                else ->{
                val  email : String = binding.etRegisterEmail.text.toString().trim() {it <= ' '}
                val password : String = binding.etRegisterPassword.text.toString().trim() {it <= ' '}

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener({
                        task ->
                        if(task.isSuccessful){

                            val firebaseUser : FirebaseUser = task.result!!.user!!

                            Toast.makeText(
                                this@SignUpActivity
                            ,"You are registered succesfully",
                                Toast.LENGTH_SHORT
                            ).show()


                            val intent = Intent(this@SignUpActivity , SignInActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id" , firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(
                                this@SignUpActivity,
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