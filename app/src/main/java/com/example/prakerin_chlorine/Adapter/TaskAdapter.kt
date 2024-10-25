package com.example.prakerin_chlorine.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.example.prakerin_chlorine.Model.TaskModel
import com.example.prakerin_chlorine.R
import com.example.prakerin_chlorine.Response.Task

class TaskAdapter(var context: Context, var data: MutableList<Task>): BaseAdapter() {

    var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView ?: inflater.inflate(R.layout.taskitem, null, false)

        var item = view.findViewById<LinearLayout>(R.id.task_item)
        var layer = view.findViewById<LinearLayout>(R.id.task_layer)
        var title = view.findViewById<TextView>(R.id.title_task)
        var date = view.findViewById<TextView>(R.id.date_text)
        var status = view.findViewById<TextView>(R.id.status_task_text)

        var task = getItem(position) as Task

        title.text = task.name
//        date.text = task.created_at
        status.text = if (task.is_done == 0) {
            "Selesai"
        }else{
            "Belum Selesai"
        }

        if (task.is_done == 1) {
            item.backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
        }

        return view
    }
}