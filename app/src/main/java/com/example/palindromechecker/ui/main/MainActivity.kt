package com.example.palindromechecker.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.palindromechecker.R
import com.example.palindromechecker.data.pref.UserPreferences
import com.example.palindromechecker.databinding.ActivityMainBinding
import com.example.palindromechecker.ui.palindrome.PalindromeActivity
import com.example.palindromechecker.ui.user.ListUserActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var pref: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = UserPreferences(this)

        val name = intent.getStringExtra(EXTRA_NAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val imgUrl = intent.getStringExtra(EXTRA_IMG)
        val userr = pref.getName()

        if (userr!!.isEmpty()){
            binding.name.text = getString(R.string.guest)

        }else{
            if (userr == null){
                binding.name.text = getString(R.string.guest)

            } else {
                binding.name.text =userr
            }
        }

        if (name == null && email == null && imgUrl == null){
            binding.tvNamemain.text = getString(R.string.empty_username)

        }else{

            Glide.with(binding.root.context)
                .load(imgUrl)
                .centerCrop()
                .into(binding.ivMain)

            binding.tvNamemain.text = name
            binding.tvEmaimain.text = email

        }

        binding.btnChooseuser.setOnClickListener {
            startActivity(Intent(this@MainActivity,ListUserActivity::class.java))
        }

        binding.btnBackpal.setOnClickListener {
            startActivity(Intent(this@MainActivity,PalindromeActivity::class.java))
            pref.clearName()
        }


    }

    companion object {
        val EXTRA_NAME = "extra_name"
        val EXTRA_EMAIL = "extra_email"
        val EXTRA_IMG = "extra_img"
    }
}