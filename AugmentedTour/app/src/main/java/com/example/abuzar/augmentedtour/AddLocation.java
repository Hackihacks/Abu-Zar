package com.example.abuzar.augmentedtour;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddLocation extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText name,altitude,longitude,latitude;
    Spinner cat;
    Button insertData;
    Button showdatabtn;
    Button getbtn;
    Button showdatabtn2;
    Button bckbtn;
    Button deletebtn,updateButton;
    //These all are  variables which i used
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        myDb=new DatabaseHelper(this);
        name=(EditText)findViewById(R.id.editTextlocaname);
        altitude=(EditText)findViewById(R.id.editTextlocaltitude);
        longitude=(EditText)findViewById(R.id.editTextlocllongitude);
        latitude=(EditText)findViewById(R.id.editTextloclatitude);
        cat=(Spinner) findViewById(R.id.cat);
        insertData=(Button)findViewById(R.id.buttonAddLoc);
        showdatabtn2=(Button)findViewById(R.id.showdata2) ;
        bckbtn=(Button)findViewById(R.id.backbtn2);
        deletebtn=(Button)findViewById(R.id.deletebtn);
        updateButton=(Button)findViewById(R.id.updatebtn);
        Spinner dropdown = findViewById(R.id.cat);
        String[] items = new String[]{"Resturant","Hotel", "CNG Station", "Hospital","Hostel","Guest House","Shopping","Mosque","Church","Bank","EasyPaisa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);




     //   showdatabtn=(Button)findViewById(R.id.showdata) ;
     //   getbtn=(Button)findViewById(R.id.getvalue);
        ADDDATA();
        showalldata();
        Deletedata();
        UpdateDate();
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //click listener for to go back
                Intent goback=new Intent(AddLocation.this, FirstActivity.class);
                startActivity(goback);

            }
        });
    }

    private void UpdateDate() {
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.updationData(name.getText().toString(),altitude.getText().toString(),latitude.getText().toString(),longitude.getText().toString(), (String) cat.getSelectedItem());
                if (isInserted == true) {

                    Toast.makeText(AddLocation.this, "Location Updated Successfully", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AddLocation.this, "Location Updatatiom Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void Deletedata() {
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Boolean isDel=myDb.DeleteData(name.getText().toString());

                if (
                        isDel==true) {

                    Toast.makeText(AddLocation.this, "Location Deleted Successfully", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AddLocation.this, "Location Deletion Failed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void showalldata(){  //click listener inside the Showal data method to disply registred locations
        showdatabtn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor ref=myDb.showdata2();   //calling method of showdata2 to get all registred locations
                        if(ref.getCount()==0)
                        {

                           Toast.makeText(AddLocation.this,"No Data Found",Toast.LENGTH_LONG).show();
                            showmsgdata("Error","No data Found");
                            return;
                        }

                        StringBuffer buffer=new StringBuffer();
                        int num=1;
                        while (ref.moveToNext())
                        {
                            buffer.append("Location: "+num+"\n");
                            buffer.append("LocationName:"+ref.getString(0)+"\n");
                            buffer.append("LocationLatitude:"+ref.getString(1)+"\n");
                            buffer.append("LocationLongitude:"+ref.getString(2)+"\n");
                            buffer.append("LocationAltitude:"+ref.getString(3)+"\n");
                            buffer.append("LocationCategory:"+ref.getString(4)+"\n\n");
                            num++;
                        }
                        showmsgdata("Registered Locations",buffer.toString());
                    }
                }


        );



    }



    public void showmsgdata(String title,String Message)  //this method used to display data as mesege Box
    {

        AlertDialog.Builder builder=new  AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }




    public void ADDDATA() {  //this method and its click lsitne used to add new locations
        insertData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(name.getText().toString(), altitude.getText().toString(), latitude.getText().toString(),longitude.getText().toString(), cat.getSelectedItem().toString());
                        if (isInserted == true) {

                            Toast.makeText(AddLocation.this, "Location inserted Successfully", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(AddLocation.this, "Location indert Failed", Toast.LENGTH_LONG).show();
                        }


                    }
                }


        );


    }


}
