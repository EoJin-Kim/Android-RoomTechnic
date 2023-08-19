package com.ej.roomtechnic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.ej.db.TextDatabase
import com.ej.db.entity.TextEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = TextDatabase.getDatabase(this)

        val inputArea = findViewById<EditText>(R.id.textInputArea)

        val insertBtn = findViewById<Button>(R.id.insert)
        val getAllBtn = findViewById<Button>(R.id.getData)
        val deleteBtn = findViewById<Button>(R.id.delete)

        insertBtn.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                db.textDao().insert(TextEntity(0,inputArea.text.toString(), Calendar.getInstance().time ))
//                inputArea.setText("")
//            }
        }

        getAllBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("MAIN", db.textDao().getAllData().toString())
            }
        }

        deleteBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.textDao().deleteAllData()
            }
        }

        val bok1 = findViewById<ImageView>(R.id.bok)
        val setImage = findViewById<Button>(R.id.setImage)

        setImage.setOnClickListener {
            val drawable = bok1.drawable

            val bitmap = drawable.toBitmap()

            CoroutineScope(Dispatchers.IO).launch {
                                db.textDao().insert(TextEntity(0,"tempText", Calendar.getInstance().time , bitmap))
            }
        }

        val bok2 = findViewById<ImageView>(R.id.bok2)
        val getImage = findViewById<Button>(R.id.getImage)
        getImage.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = db.textDao().getAllData()

                withContext(Dispatchers.Main){
                    bok2.setImageBitmap(result[0].myPhoto)
                }
            }
        }
    }
}