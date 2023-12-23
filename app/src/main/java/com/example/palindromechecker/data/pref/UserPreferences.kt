package com.example.palindromechecker.data.pref

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private var pref :SharedPreferences = context.getSharedPreferences(PREFENCES_NAME,Context.MODE_PRIVATE)


    fun saveName(name:String){
        val editor = pref.edit()
        editor.putString(NAME_USER,name)
        editor.apply()
    }


    fun getName():String?{
        return  pref.getString(NAME_USER,null)

    }

    fun clearName(){
        val editor = pref.edit()
        editor.remove(NAME_USER)
        editor.apply()
    }

    companion object{
        val PREFENCES_NAME = "user_pref"
        val NAME_USER = "user_name"

    }
}