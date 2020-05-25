package com.example.weatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.model.Article

/*
* Database class for room is always to be abstract class
* To tell room that this is the database class we have to use the @Database annotation
*
* */

@Database (
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase(){

    //Here we are creating a abstract function which will return article Dao

    abstract fun getArticleDao():ArticleDao

    companion object{

        // Here we are making instance with @Volatile so that other threads can see when a thread changes this instance
        @Volatile
        private var instance:ArticleDatabase?=null

        //Used to synchronize the setting that instance so that only single instance of our database at once
        private val LOCK=Any()

        // This function is to be called whenever we call the constructor of Database Class
        // That is whe
        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            //If instance is null then we are creating synchronized block

            instance?:createDatabase(context).also{instance=it}
        }

        fun createDatabase(context: Context)=

            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()


    }

}