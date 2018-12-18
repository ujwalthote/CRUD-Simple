package com.mobilesiri.sqliteexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView textView;
    private DBHandler db;
    private EditText rollNo,name,studclass;
    private Button create,update,delete,showAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = (Button) findViewById(R.id.create);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        showAll=(Button) findViewById(R.id.show);

        textView= (TextView) findViewById(R.id.information);
        rollNo = (EditText) findViewById(R.id.rollNo);
        name = (EditText) findViewById(R.id.name);
        studclass = (EditText) findViewById(R.id.studclass);

        db = new DBHandler(this);

        create.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        showAll.setOnClickListener(this);

    }


    public void updateStudent(){
//        try {
            Student student = new Student();
            student.setName(name.getText().toString());
            student.setRollno(Integer.parseInt(rollNo.getText().toString()));
            student.setStudClass(studclass.getText().toString());
            db.updateStudent(student);
            Toast.makeText(this, " updated", Toast.LENGTH_SHORT).show();
//        }catch (Exception e){
//            Toast.makeText(this, "Exception occurred", Toast.LENGTH_SHORT).show();
//        }
    }

    public void deleteStudent(){
        try {

            db.deleteStudent(Integer.parseInt(rollNo.getText().toString()));
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(this, "Exception occurred", Toast.LENGTH_SHORT).show();
        }
    }


    public void createStudent(){
        try {
            Student student = new Student();
            student.setName(name.getText().toString());
            student.setRollno(Integer.parseInt(rollNo.getText().toString()));
            student.setStudClass(studclass.getText().toString());
            db.addStudent(student);
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
            rollNo.setText("");
            name.setText("");
            studclass.setText("");
            rollNo.requestFocus();
        }catch (Exception e){
            Toast.makeText(this, "Exception occurred", Toast.LENGTH_SHORT).show();
        }
    }

    public void showAll(){
        textView.setText("");
        List<Student> students = db.getAllStudents();
        for (Student student : students) {
            String log = "Roll No: " + student.getRollno() + " ,Name: " + student.getName() + " ,Class: " + student.getStudClass();
            // Writing students  to log
            Log.d("Student: : ", log);
            textView.append(log + "\n");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create:
                createStudent();
                break;
            case R.id.update:
                updateStudent();
                break;
            case R.id.delete:
                deleteStudent();
                break;
            case R.id.show:
                showAll();
                break;
            default:
        }
    }
}
