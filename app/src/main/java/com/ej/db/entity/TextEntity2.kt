package com.ej.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_table2")
data class TextEntity2(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id :Int,

    @ColumnInfo(name = "text2")
    var text2 : String,

    @ColumnInfo(name = "newText")
    var newText : String
) {
}