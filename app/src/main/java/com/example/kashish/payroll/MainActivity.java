package com.example.kashish.payroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    Timer timer;
    ImageView imageView;
    EditText editText;
    Integer photo[]= {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};

Button go;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText= findViewById(R.id.edit);
        go =findViewById(R.id.go);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(MainActivity.this,EmployeeDetailsAct.class);
             startActivity(intent);
            }
        });



            viewPager = (ViewPager) findViewById(R.id.viewPager);



            CustomPager adapter = new CustomPager(MainActivity.this,photo);




            viewPager.setAdapter(adapter);

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    viewPager.post(new Runnable(){

                        @Override
                        public void run() {
                            viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%photo.length);
                        }
                    });
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 3000, 3000);

            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(editText.getText().toString()!=null) {
                        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                        intent.putExtra(Contract.NAME, editText.getText().toString());
                        startActivity(intent);
                    }
                }
            });

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
       Intent intent= new Intent(MainActivity.this,AboutActivity.class);
       startActivity(intent);
        return true;
    }
}
