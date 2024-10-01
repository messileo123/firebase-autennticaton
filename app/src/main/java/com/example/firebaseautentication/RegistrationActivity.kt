package com.example.firebaseautentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseautentication.databinding.ActivityRegistrationBinding
import com.example.firebaseautentication.viewodel.AuthViewModel

class RegistrationActivity : AppCompatActivity() {
    private lateinit var viewModel:AuthViewModel
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegistrationBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.registerBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val conPass = binding.conPassEt.toString()

            if (email.isEmpty() || password.isEmpty() || conPass.isEmpty()){
                Toast.makeText(this@RegistrationActivity, "Please Fill All Fields",
                    Toast.LENGTH_SHORT).show()
            }else if (!password.equals(conPass)){
                Toast.makeText(this@RegistrationActivity, "Password does not match ",
                    Toast.LENGTH_SHORT).show()
            }else{
                viewModel.signup(email,conPass).observe(this,{result->
                    Toast.makeText(this@RegistrationActivity,result,
                        Toast.LENGTH_SHORT).show()

                    if (result.equals("signUp Successful")){
                        startActivity(Intent(this@RegistrationActivity, HomeActivity::class.java))
                    }
                })
            }
        }
        binding.loginTxt.setOnClickListener{
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
        }

    }
}