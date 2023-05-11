package com.cem.sqlliteproject1

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            val dataBase=this.openOrCreateDatabase("firstDb",Context.MODE_PRIVATE,null)
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS products(id INTEGER PRIMARY KEY,name VARCHAR, price INT)")
            //dataBase.execSQL("INSERT INTO products (name,price) VALUES('Shoes',100)")
            //dataBase.execSQL("INSERT INTO products (name,price) VALUES('Pants',150)")
            //dataBase.execSQL("INSERT INTO products (name,price) VALUES('T-shirts',50)")

            val cursor=dataBase.rawQuery("SELECT * From products",null)

            val idColumnIndex=cursor.getColumnIndex("id")
            val priceColumnIndex=cursor.getColumnIndex("price")
            val nameColumnIndex=cursor.getColumnIndex("name")

            while (cursor.moveToNext()){
                println("ID: ${cursor.getInt(idColumnIndex)}")
                println("Name: ${cursor.getString(nameColumnIndex)}")
                println("Price: ${cursor.getInt(priceColumnIndex)}")

            }
            cursor.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

}