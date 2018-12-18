package com.mobilesiri.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    public DBHandler(Context context) {
        super(context, "studentsInfo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS students(rollNo INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, studclass TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);
        // Creating tables again
        onCreate(db);
    }

    // Adding new student
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rollNo",student.getRollno());
        values.put("name", student.getName()); // Student Name
        values.put("studclass", student.getStudClass()); // Student class

        // Inserting Row
        db.insert("students", null, values);
        db.close(); // Closing database connection
    }

    // Getting All Students
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM students";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setRollno(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setStudClass(cursor.getString(2));
                // Adding student to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        // return student list
        return studentList;
    }

    // Updating a student
    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",student.getName()); //These Fields should be your String values of actual column names
        cv.put("studclass",student.getStudClass());
        db.update("students", cv, "rollNo="+student.getRollno(), null);
    }

    // Deleting a student
    public void deleteStudent(int value) {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from students where rollNo="+value);
        db.close();
    }
}
