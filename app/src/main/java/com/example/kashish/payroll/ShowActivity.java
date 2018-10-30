package com.example.kashish.payroll;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    PayrollOpenHelper payrollOpenHelper;
    SQLiteDatabase database;
ImageView imageView;
    String string="";
    Button edit, salary;
    Cursor cursor;
    TextView name, dept, email, contact , address, desig,genger ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);



        payrollOpenHelper= new PayrollOpenHelper(this);
        database=payrollOpenHelper.getReadableDatabase();
        String args[]={getIntent().getStringExtra(Contract.NAME)};
         cursor = database.query(Contract.TABLE_NAME,null,Contract.NAME+" LIKE ?",args,null,null,null);

//        if(cursor.moveToFirst())
//        s=cursor.getString(cursor.getColumnIndex(Contract.DESIG));
//        s+="       "+cursor.getString(cursor.getColumnIndex(Contract.GENDER));
//        Log.d("lul",s);

imageView=findViewById(R.id.i);
        name= findViewById(R.id.names);
        dept= findViewById(R.id.departs);
       genger=findViewById(R.id.genders);
        email= findViewById(R.id.emails);
        desig= findViewById(R.id.desigs);
        address= findViewById(R.id.adds);
        contact=findViewById(R.id.contacts);
    edit=findViewById(R.id.editb);
    salary=findViewById(R.id.salaryb);




        if(cursor.moveToFirst()){
            name.setText(cursor.getString(cursor.getColumnIndex(Contract.NAME)));
            dept.setText(cursor.getString(cursor.getColumnIndex(Contract.DEPT)));
            genger.setText(cursor.getString(cursor.getColumnIndex(Contract.GENDER)));
            email.setText(cursor.getString(cursor.getColumnIndex(Contract.EMAIL)));
            desig.setText(cursor.getString(cursor.getColumnIndex(Contract.DESIG)));
            address.setText(cursor.getString(cursor.getColumnIndex(Contract.ADDRESS)));
            contact.setText(cursor.getString(cursor.getColumnIndex(Contract.CONTACT)));
            string=cursor.getString(cursor.getColumnIndex(Contract.ID));

        }else {
            setContentView(R.layout.show_layout_2);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle= new Bundle();
                bundle.putString(Contract.NAME,name.getText().toString());
                bundle.putString(Contract.DEPT,dept.getText().toString());
                bundle.putString(Contract.DESIG,desig.getText().toString());
                bundle.putString(Contract.ADDRESS,address.getText().toString());
                bundle.putString(Contract.CONTACT, contact.getText().toString());
                bundle.putString(Contract.EMAIL,email.getText().toString());
                bundle.putString(Contract.GENDER,genger.getText().toString());
                bundle.putString(Contract.ID,string);

                Intent intent= new Intent(ShowActivity.this,EmployeeDetailsAct.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle= new Bundle();
                bundle.putString(Contract.NAME,name.getText().toString());
                bundle.putString(Contract.DEPT,dept.getText().toString());
                bundle.putString(Contract.DESIG,desig.getText().toString());
                bundle.putString(Contract.BSAL,cursor.getString(cursor.getColumnIndex(Contract.BSAL)));
                Intent intent= new Intent(ShowActivity.this,SalaryAcy.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        if(genger.getText().equals("male"))
            imageView.setImageResource(R.drawable.male);
        else
            imageView.setImageResource(R.drawable.feamle);
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.show_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        PayrollOpenHelper openHelper= new PayrollOpenHelper(this);
        final SQLiteDatabase database=openHelper.getWritableDatabase();
        final int position;
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("confirm delete");
        builder.setMessage("do you want to delete this item");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String[] s= {string};
                database.delete(Contract.TABLE_NAME,Contract.ID+" = ?",s);
                Intent intent= new Intent(ShowActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }

        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();
        return  true;
    }
}
