package com.example.appnerea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnerea.TaskApplication.Companion.prefs
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    lateinit var btnAdd:Button
    lateinit var etTask:EditText
    lateinit var rvTasks:RecyclerView
    lateinit var adapter:TaskAdapter

    var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        initUi()
        prefs
    }
    private fun initUi(){
        initView()
        initListeners()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        tasks = prefs.getTasks()
        rvTasks.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(tasks) {deleteTask(it)}
        rvTasks.adapter = adapter
    }

    private fun deleteTask(position:Int){
        tasks.removeAt(position)
        adapter.notifyDataSetChanged()
        prefs.saveTask(tasks)
    }

    private fun initListeners(){
        btnAdd.setOnClickListener { addTask() }
    }

    private fun addTask() {
        val taskToAdd: String = etTask.text.toString()
        tasks.add(taskToAdd)
        adapter.notifyDataSetChanged()
        etTask.setText("")
        prefs.saveTask(tasks)
        //rvTasks = findViewById(R.id.rvTasks)
    }

    private fun initView(){
        btnAdd = findViewById(R.id.btnAdd)
        etTask = findViewById(R.id.etTask)
        rvTasks = findViewById(R.id.rvTasks)
    }
}