package com.mobilesiri.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "studentsInfo";

    // Contacts table name
    private static final String STUDENTS = "Students";

    // Students Table Columns names
    private static final String ROLLNO = "rollno";
    private static final String NAME = "name";
    private static final String CLASS = "class";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + STUDENTS + "("
                + ROLLNO + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + CLASS + " TEXT" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS);
        // Creating tables again
        onCreate(db);
    }

    // Adding new student
    public void addShop(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, student.getName()); // Student Name
        values.put(CLASS, student.getStudClass()); // Student class

        // Inserting Row
        db.insert(STUDENTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one shop
    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(STUDENTS, new String[]{ROLLNO,
                        NAME, CLASS}, ROLLNO + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return shop
        return student;
    }

    // Getting All Students
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setRollno(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setStudClass(cursor.getString(2));
                // Adding student to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        // return student list
        return studentList;
    }

    // Getting Students Count
    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating a student
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(CLASS, student.getStudClass());

        // updating row
        return db.update(STUDENTS, values, ROLLNO + " = ?",
                new String[]{String.valueOf(student.getRollno())});
    }

    // Deleting a student
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENTS, ROLLNO + " = ?",
                new String[] { String.valueOf(student.getRollno()) });
        db.close();
    }
}

