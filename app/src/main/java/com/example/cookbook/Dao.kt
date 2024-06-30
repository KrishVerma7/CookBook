package com.example.cookbook

import androidx.room.Query

//DAO is used to get data from database
//in this file we write queries and functions
interface Dao {
    @get:Query("SELECT*FROM recipe")
    var all:List<Recipe?>?
}