package com.example.cookbook

import androidx.room.Dao
import androidx.room.Query

//DAO is used to get data from database
//in this file we write queries and functions
@Dao
interface Dao {
    @Query("SELECT * FROM recipe")
    fun getAll():List<Recipe?>?
}