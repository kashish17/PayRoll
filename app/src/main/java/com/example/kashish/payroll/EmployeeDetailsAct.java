package com.example.kashish.payroll;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeDetailsAct extends AppCompatActivity {


    EditText name, dept, email, contact , address, desig ;
    RadioButton male, female;
    boolean isedit;

    SQLiteDatabase database;
    long bsal;
    Button button;
     String gender;
     String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        name= findViewById(R.id.nameE);
        dept= findViewById(R.id.depte);
        male= findViewById(R.id.male);
        female= findViewById(R.id.female);
        email= findViewById(R.id.email);
        desig= findViewById(R.id.designation);
        address= findViewById(R.id.add);
        contact=findViewById(R.id.contact);
        button= findViewById(R.id.save);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender="male";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender="female";
            }
        });

        Intent intent= getIntent();
        if(intent!=null&& intent.getExtras()!=null){
            Bundle bundle= intent.getExtras();

            name.setText(bundle.getString(Contract.NAME));
            desig.setText(bundle.getString(Contract.DESIG));
            dept.setText(bundle.getString(Contract.DEPT));
            address.setText(bundle.getString(Contract.ADDRESS));
            contact.setText(bundle.getString(Contract.CONTACT));
            email.setText(bundle.getString(Contract.EMAIL));
            isedit=true;
            id =bundle.getString(Contract.ID);
        }

        PayrollOpenHelper openHelper= new PayrollOpenHelper(this);
        database=openHelper.getReadableDatabase();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues contentValues= new ContentValues();
                contentValues.put(Contract.NAME, name.getText().toString());
                contentValues.put(Contract.DEPT, dept.getText().toString());
                contentValues.put(Contract.GENDER, gender);
                contentValues.put(Contract.EMAIL, email.getText().toString());
                contentValues.put(Contract.DESIG, desig.getText().toString());
                contentValues.put(Contract.ADDRESS,address.getText().toString());
                contentValues.put(Contract.CONTACT,contact.getText().toString());


                if(desig.getText().toString().equals("intern"))
                    bsal=20000;
                else if(desig.getText().toString().equals("manager"))
                    bsal=80000;
                else if(desig.getText().toString().equals("director"))
                    bsal=150000;

                contentValues.put(Contract.BSAL,bsal);

                if(!isedit){
                long id= database.insert(Contract.TABLE_NAME,null,contentValues);
                Toast.makeText(EmployeeDetailsAct.this,"ADDED in database", Toast.LENGTH_LONG).show();

                Intent intent= new Intent(EmployeeDetailsAct.this,MainActivity.class);
                startActivity(intent);
                finish();
             }else{
                    String[] s= {id};
                    database.update(Contract.TABLE_NAME,contentValues,Contract.ID+" = ?", s);
                    Toast.makeText(EmployeeDetailsAct.this,"UPDATED in database", Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(EmployeeDetailsAct.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
