package com.example.kashish.payroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SalaryAcy extends AppCompatActivity {

    TextView name, dept, email, contact , address, desig ,basic,da,hra,pf,tds,gt;
    String bsal;
    Double basSal;

    double d,h,p,t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_acy);
        name= findViewById(R.id.namea);
        dept= findViewById(R.id.depta);
        desig= findViewById(R.id.desiga);
        basic= findViewById(R.id.bsal);
        da= findViewById(R.id.da);
        hra= findViewById(R.id.hra);
        pf= findViewById(R.id.pf);
        tds= findViewById(R.id.tds);
        gt= findViewById(R.id.gt);


        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();

        name.setText(bundle.getString(Contract.NAME));
        desig.setText(bundle.getString(Contract.DESIG));
        dept.setText(bundle.getString(Contract.DEPT));
        bsal=bundle.getString(Contract.BSAL);
        basSal=Double.parseDouble(bsal);

        if(basSal==20000){
            d=5000;
            h= 0.4*basSal;
            t=0;
            p= 0.12*basSal;
        }
        else if(basSal== 80000){
            d=15000;
            h=0.4*basSal;
            t=0.10*basSal;
            p= 0.12*basSal;
        }
        else if(basSal==150000){

            d=25000;
            h=0.4*basSal;
            t=0.20*basSal;
            p= 0.12*basSal;

        }

        t=(basSal+d+h)-(t+p);

        basic.setText(basSal+" Rs");
        da.setText(d+" Rs");
        hra.setText(h+" Rs");
        tds.setText(t+" Rs");
        pf.setText(p+" Rs");

        gt.setText(t+" Rs");

    }
}
