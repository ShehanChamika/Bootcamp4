package com.example.todoapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TodoItemsAdapter(private val todoItemsList: ArrayList<TodoItem>, val activity: MainActivity):
    RecyclerView.Adapter<TodoItemsAdapter.ViewHolder>() {

    class ViewHolder(val constraintLayout: ConstraintLayout):RecyclerView.ViewHolder(constraintLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val constraintLayout = LayoutInflater.from(parent.context).inflate(
          R.layout.to_do_item_layout,parent,false)as ConstraintLayout

        constraintLayout.setOnClickListener(View.OnClickListener {
            val nameTextView =constraintLayout.getChildAt(0)as TextView
            val urgencyTextView =constraintLayout.getChildAt(1)as TextView
            val nameText = nameTextView.text
            val urgencyText = urgencyTextView.text
            val isItemUrgent = if (urgencyText == "!!")true else false

            val intent: Intent = Intent(parent.context,AddItemActivity::class.java)
            intent.putExtra("ITEM_NAME",nameText)
            intent.putExtra("ITEM_URGENCY",isItemUrgent)
            activity.startActivity(intent)
        })

        constraintLayout.setOnLongClickListener(View.OnLongClickListener{
            val position: Int = parent.indexOfChild(it)

            val todoItemToRemove = activity.todoItemsList[position]
            val dbo = DatabaseOperations (parent.context)
            dbo.deleteItem(dbo, todoItemToRemove)

            activity.todoItemsList.removeAt(position)
            notifyItemRemoved(position)
            true
        })

        return ViewHolder(constraintLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val constraintLayout = holder.constraintLayout
        val nameTextView =constraintLayout.getChildAt(0)as TextView
        val urgencyTextView =constraintLayout.getChildAt(1)as TextView
        nameTextView.text = todoItemsList[position].name
        urgencyTextView.text=if(todoItemsList[position].isUrgent)"!!" else ""
    }

    override fun getItemCount(): Int {
        return todoItemsList.size
    }
}