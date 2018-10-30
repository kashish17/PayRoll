package com.example.kashish.payroll;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PayrollOpenHelper extends SQLiteOpenHelper {

    public static int VERSION=1;
    public PayrollOpenHelper(Context context) {
        super(context, Contract.TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String esql="CREATE TABLE "+Contract.TABLE_NAME +
                " ( "+Contract.ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+ Contract.NAME +
                " TEXT , "+ Contract.DEPT + " TEXT , "+ Contract.DESIG + " TEXT , "+ Contract.CONTACT +

                " TEXT , "+ Contract.ADDRESS + " TEXT , "+ Contract.BSAL + " INTEGER , "+ Contract.EMAIL +" TEXT , "+ Contract.GENDER +" TEXT );";

        sqLiteDatabase.execSQL(esql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
