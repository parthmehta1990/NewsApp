package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp.model.Article


/*
* Dao stands for Database Access object which will be use to get and store data in database
*
* */

@Dao
interface ArticleDao {

    //Make sure all the functions in the Dao are suspend function to achieve async functionality

    //Here we using the conflict strategy so that if record is not available then insert or if found then replace

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article:Article):Long

    //Here we are not maing function as suspend function because it will return Live data so that doesn't work with suspend function
    @Query("SELECT * FROM articles")
    fun getAllArticles():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}