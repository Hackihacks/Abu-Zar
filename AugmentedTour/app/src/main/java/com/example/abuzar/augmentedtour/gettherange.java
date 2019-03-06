package com.example.abuzar.augmentedtour;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class gettherange extends AppCompatActivity {
Button returnvalue;
    DatabaseHelper myDb=new DatabaseHelper(this);
    String n,lat,longg,alti;
    String [][] points=new String[15][4]; //15 locations data can enter if need more you may change its size








    EditText rangevalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gettherange);

        returnvalue=(Button)findViewById(R.id.rangebtnretun);
        rangevalue=(EditText) findViewById(R.id.rangevaluetext);

        returnvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String con=getIntent().getStringExtra("condition");
                Cursor ref=myDb.showdata(con);
                if(ref.getCount()==0)
                {


                }
                StringBuffer buffer=new StringBuffer();
                int i=0;
                while (ref.moveToNext())
                {
                    n=(ref.getString(0));
                    lat=(ref.getString(1));
                    longg=(ref.getString(2));
                    alti=(ref.getString(3));
                    points[i][0]=n;
                    points[i][1]=lat;
                    points[i][2]=longg;
                    points[i][3]=alti;
                    i++;

                }

                Intent myintent = new Intent(gettherange.this, ARActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("pointslist",  points);
                myintent.putExtras(mBundle);
                myintent.putExtra("range",rangevalue.getText().toString());
                startActivity(myintent);
            }
        });









    }
}
