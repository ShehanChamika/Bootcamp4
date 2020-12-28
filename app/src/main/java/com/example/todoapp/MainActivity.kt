package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

 private lateinit var todoItemRecyclerView:RecyclerView
 private lateinit var recyclerAdapter: TodoItemsAdapter
 private lateinit var recyclerLayouManager: RecyclerView.LayoutManager

 var todoItemsList =ArrayList<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbo = DatabaseOperations(this)
        val cursor = dbo.getAllItems(dbo)
        with(cursor){
            while (moveToNext()){
                val itemName = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME))
                val itemUrgency = getInt(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_URGENCY))
                val isUrgent = if (itemUrgency == 0) false else true
                todoItemsList.add(TodoItem(itemName, isUrgent))
            }
        }



       // todoItemsList.add(TodoItem("Buy groceries"))
       // todoItemsList.add(TodoItem("Do laundry",true))
       // todoItemsList.add(TodoItem("Play guitar",false))

        todoItemRecyclerView=findViewById(R.id.todo_item_recycler_view)

        recyclerLayouManager=LinearLayoutManager(this)
        recyclerAdapter= TodoItemsAdapter(todoItemsList , this)

        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager=recyclerLayouManager
            adapter=recyclerAdapter
        }
    }
    public fun naveToAddItemAction(view:View){
        val intent:Intent= Intent(this,AddItemActivity::class.java)
        startActivity(intent)
        }

}