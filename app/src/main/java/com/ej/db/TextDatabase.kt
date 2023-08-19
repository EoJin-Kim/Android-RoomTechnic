package com.ej.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ej.db.dao.TextDao
import com.ej.db.dao.TextDao2
import com.ej.db.entity.TextEntity
import com.ej.db.entity.TextEntity2

@Database(
    entities = [TextEntity::class, TextEntity2::class],
    version = 2
)
abstract class TextDatabase  : RoomDatabase(){
    abstract fun textDao() :TextDao
    abstract fun textDao2() : TextDao2


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