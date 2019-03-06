package com.example.abuzar.augmentedtour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by ${ABUZAR} on ${10/21/2017}.
 */
public class MainActivity extends AppCompatActivity {

    String result;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult





    DatabaseHelper myDb=new DatabaseHelper(this);
    String n,lat,longg,alti;
    EditText etInput;
    TextView txtText;
    Button btnAdd, btnDelete, button1;
    ImageButton imageButton,imageButton2,imageButton4;
    Button getrangebtn;

    private Context Context;
    private List<ARPoint> arPoints;
    AROverlayView arOverlayVie;
    ARPoint arPoint;
    double Latitude;
    double Longitude;
    String name;
    ImageButton  hospitalbtn,Cngbtn,guesthouse,Hostel,Resturant,shopping,masjid,chruch,bank,easypaisa;
    Button showdatabtn;
     String [][] points=new String[20][4];


    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageButton = (ImageButton) findViewById(R.id.RESTURANTS1);
        hospitalbtn=(ImageButton)findViewById(R.id.hospital12);
        Cngbtn=(ImageButton)findViewById(R.id.fuel2);
        guesthouse=(ImageButton)findViewById(R.id.RESTURANTS1);
        Hostel=(ImageButton)findViewById(R.id.RESTURANTS2);
        Resturant=(ImageButton)findViewById(R.id.imageButton7);
        shopping=(ImageButton)findViewById(R.id.shopping1);
        masjid=(ImageButton)findViewById(R.id.mosque1);
        chruch=(ImageButton)findViewById(R.id.mosque2);
        bank=(ImageButton)findViewById(R.id.money1);
        easypaisa=(ImageButton)findViewById(R.id.money2);




        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Bank");
                startActivity(intent);
            }
        });


        easypaisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "EasyPaisa");
                startActivity(intent);
            }
        });

















        masjid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Mosque");
                startActivity(intent);
            }
        });


        chruch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Church");
                startActivity(intent);
            }
        });








        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Shopping");
                startActivity(intent);
            }
        });





        guesthouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Guest House");
                startActivity(intent);
            }
        });

        Hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Hostel");
                startActivity(intent);
            }
        });

        Resturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Resturant");
                startActivity(intent);
            }
        });



        Cngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "CNG Station");
                startActivity(intent);
            }
        });



        hospitalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Hospital");
                startActivity(intent);
            }
        });



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, gettherange.class);
                intent.putExtra("condition", "Hotel");
                startActivity(intent);

            }
        });

    }













































}











