package com.ej.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_table")
data class TextEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id :Int,

    @ColumnInfo(name = "text")
    var text : String,

    @ColumnInfo(name = "text2", defaultValue = "this is text2 default")
    var text2 : String
) {
}