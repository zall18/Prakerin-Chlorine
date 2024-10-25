package com.example.prakerin_chlorine.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.prakerin_chlorine.Model.Task
import com.example.prakerin_chlorine.R

class taskAdapter(var context: Context, var data: MutableList<Task>): BaseAdapter() {

    var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = p1 ?: inflater.inflate(R.layout.task_item, null, false)

//        var title = view.findViewById<TextView>(R.id.name_task)
//        var date = view.findViewById<TextView>(R.id.date_text)
//        var layer = view.findViewById<LinearLayout>(R.id.task_layer)
//        var item = view.findViewById<LinearLayout>(R.id.taskItem)
//
//        var task = getItem(p0) as Task
//
//        title.text = task.name
//        date.text = task.created_at
//        if(task.is_done == 1){
//            item.resources.getColor(R.color.green)
//            layer.resources.getColor(R.color.green_history)
//        }else{
//            item.resources.getColor(R.color.red)
//            layer.resources.getColor(R.color.red_history)
//        }


        return view
    }
}