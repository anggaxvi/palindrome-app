package com.example.palindromechecker.ui.palindrome

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.palindromechecker.R
import com.example.palindromechecker.data.pref.UserPreferences
import com.example.palindromechecker.databinding.ActivityPalindromeBinding
import com.example.palindromechecker.ui.main.MainActivity
import com.example.palindromechecker.ui.user.ListUserActivity

class PalindromeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPalindromeBinding
    private lateinit var pref : UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPalindromeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = UserPreferences(this)

        setupView()


        val edtPalindrome = binding.edtPalindrome.text
        val edtName = binding.edtName.text
        val btnCheck = binding.btnCheck
        val btnNext = binding.btnNext



        btnCheck.setOnClickListener {
            if (edtPalindrome != null && edtPalindrome.isNotEmpty()){
                if (checkPalindrome(edtPalindrome.toString())){
                    dialogInfo(edtPalindrome.toString(),getString(R.string.palindrome))

                }else{
                    dialogInfo(edtPalindrome.toString(),getString(R.string.not_palindrome))

                }

            }
        }

        btnNext.setOnClickListener {
            pref.clearName()
            if (edtName != null ){
                startActivity(Intent(this@PalindromeActivity,MainActivity::class.java))
                pref.saveName(edtName.toString())

            }

        }

    }

    private fun checkPalindrome(text:String):Boolean{
        val resverseText = text.reversed()
        return text.equals(resverseText,ignoreCase = true)
    }

    private fun dialogInfo(word: String,msg:String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.palindrome_dialog)

        val close = dialog.findViewById<Button>(R.id.btn_close)
        val tittle = dialog.findViewById<TextView>(R.id.tv_resultpalindrom)
        val info = dialog.findViewById<TextView>(R.id.tv_wordsinput)

        tittle.text = msg
        info.text = "${word} ${msg}"


        close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}