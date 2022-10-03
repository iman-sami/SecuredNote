package com.imanp.securenote.Helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class DbHelper(
context: Context?,
name: String?,
factory: SQLiteDatabase.CursorFactory?,
version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    private val hp: HashMap<String,String>? = null

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table Notes"+"(id integer primary key , title text,note text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS Notes");
        onCreate(db);
    }
    fun  insertValue(title:String,Notes:String):Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title",title)
        contentValues.put("note",Notes)
        db.insert("Notes",null,contentValues)
        return true
    }

    @SuppressLint("Range", "Recycle")
    fun listNotes():ArrayList<String>{
        val array_list = ArrayList<String>()
        val db = this.readableDatabase
        val res: Cursor = db.rawQuery("select * from Notes", null)
        res.moveToFirst()

        while (!res.isAfterLast) {
            array_list.add(res.getString(res.getColumnIndex("title")))
            res.moveToNext()
        }
        return array_list
    }

    @SuppressLint("Recycle")
    fun getNote(id: Int): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("select * from contacts where id=$id", null)
    }

}