package com.ej.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ej.db.dao.TextDao
import com.ej.db.entity.TextEntity

@Database(entities = [TextEntity::class], version = 3,
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]
)
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

                    .build()
                INSTANCE  = instance
                instance
            }
        }
    }
}