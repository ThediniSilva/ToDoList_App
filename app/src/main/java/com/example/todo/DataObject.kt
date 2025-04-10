package com.example.todo

import kotlinx.android.synthetic.main.activity_create_card.*


object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(id: Int, title: String, priority: String, description: String, deadline: String) {
        listdata.add(CardInfo(id, title, priority, description, deadline))
    }

    fun updateData(pos: Int, id: Int, title: String, priority: String, description: String, deadline: String) {
        listdata[pos].id = id
        listdata[pos].title = title
        listdata[pos].priority = priority
        listdata[pos].description = description
        listdata[pos].deadline = deadline
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }



}