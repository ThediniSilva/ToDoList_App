package com.example.todo

data class CardInfo(
    var id: Int, // Add this field
    var title: String,
    var priority: String,
    var description: String,
    var deadline: String,
)
