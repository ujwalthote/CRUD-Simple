package com.mobilesiri.sqliteexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= (TextView) findViewById(R.id.information);

        DBHandler db = new DBHandler(this);

        // Inserting Student/Rows
        Log.d("Insert: ", "Inserting ..");
        db.addShop(new Student(1,"abc","1st"));
        db.addShop(new Student(2,"xyz","2st"));
        db.addShop(new Student(3,"pqr","3st"));

        // Reading all students
        Log.d("Reading: ", "Reading all students..");
        List<Student> students = db.getAllStudents();

        for (Student student : students) {
            String log = "Id: " + student.getRollno() + " ,Name: " + student.getName() + " ,Address: " + student.getStudClass();
            // Writing students  to log
            Log.d("Student: : ", log);
            textView.append(log + "\n");
        }
    }
}
