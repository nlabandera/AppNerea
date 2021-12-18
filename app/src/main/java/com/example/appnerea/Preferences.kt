package com.example.appnerea

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    //Constantes siempre en MAYUS
    companion object{
        const val PREFS_NAME = "myDataBase"
        const val TASKS = "task_value"
    }


    private val prefs:SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun saveTask(task:List<String>){
        prefs.edit().putStringSet(TASKS, task.toSet()).apply()
    }

    fun getTasks():MutableList<String>{
        return prefs.getStringSet(TASKS, emptySet<String>())?.toMutableList() ?: mutableListOf()
    }


}