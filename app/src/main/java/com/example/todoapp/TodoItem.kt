package com.example.todoapp

import java.util.*

class TodoItem(var name:String) {
    var isUrgent =false
    var date = Calendar.getInstance()

    constructor(name: String, isUrgent:Boolean):this(name){
        this.isUrgent=isUrgent
    }
    fun getDataAsString():String{
        val year=date.get(Calendar.YEAR).toString()
        val month=date.get(Calendar.MONTH).toString()
        val day=date.get(Calendar.DAY_OF_MONTH).toString()
        return "$year/$month/$day"
    }
}