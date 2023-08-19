package com.ej.db

import android.content.Context
import androidx.room.*
import com.ej.db.dao.TextDao
import com.ej.db.entity.MyConverters
import com.ej.db.entity.TextEntity

@Database(entities = [TextEntity::class], version = 1)
@TypeConverters(MyConverters::class)
abstract class TextDatabase  : RoomDatabase(){
    abstract fun textDao() :TextDao


    companion object{

        @Volatile
        private var INSTANCE : TextDatabase? = null

        fun getDatabase(context: Context) : TextDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDatabase::class.java,
                    "text_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE  = instance
                instance
            }
        }
    }
}