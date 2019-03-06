package com.example.abuzar.augmentedtour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Location;
import android.media.Image;
import android.opengl.Matrix;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;
import com.example.abuzar.augmentedtour.LocationHelper;
import com.example.abuzar.augmentedtour.ARPoint;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ${ABUZAR} on ${10/21/2017}.
 */
public class AROverlayView extends View  {


    Context context;
    private float[] rotatedProjectionMatrix = new float[16];
    private Location currentLocation;
    private List<ARPoint> arPoints;
    static String name;
    double latitude,longitude,altitude;
    double a, b;
    int rangeofloc;  //variaable to set range of locations

  /*  // Get Params from intent
    Intent it = new Intent();
if (it != null)
    {
        Bundle params = it.getExtras();
        if  (params != null)
        {
            a = params.getDouble("doubleA");
            b = params.getDouble("doubleB");
        }
    }*/
 /* Intent intent = ((Activity) context).getIntent();
  Bundle b = getIntent().getExtras();
    double result = b.getDouble("key");*/

 /*   public  void  setlat(double lat,double longi ){


     latitude=lat;
     longitude=longi;

    }
*/




    public AROverlayView(Context context, final String getpoints[][],int range) { //in constructor we re getting POI lists as array and also range values
        super(context);

        this.context=context;
        this.rangeofloc=range; //assigning values to range variable
        arPoints = new ArrayList<ARPoint>() {{

         /*   add(new ARPoint("My Pak Palace",73.229837 ,73.229837, 0));
            add(new ARPoint("Your Horizon",34.191087 ,73.237245, 0));
            add(new ARPoint("Our Lalazar",34.201572 ,73.238554, 0));
            add(new ARPoint("His PTCL",34.162262 ,73.200445, 0));
            add(new ARPoint("Hotel One",34.215324 ,73.243076, 0));*/
         for(int i=0;getpoints[i][0]!=null;i++) //Passing All POIs from DataBase
            add(new ARPoint(getpoints[i][0],Double.parseDouble(getpoints[i][1]),Double.parseDouble(getpoints[i][2]),Double.parseDouble(getpoints[i][3])));

        }};


        //Demo points


    }

    public void updateRotatedProjectionMatrix(float[] rotatedProjectionMatrix) {
        this.rotatedProjectionMatrix = rotatedProjectionMatrix;
        this.invalidate();
    }

    public void updateCurrentLocation(Location currentLocation){
        this.currentLocation = currentLocation;
        //this.invalidate();
    }



  //  this methods used to calculate the distance
    public double CalculationByDistance(LatLng StartP,LatLng EndP ) { //geting start and ending point of locations as parameter to measure to distance between them
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
       // double lat2=endplat;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
      //  double lon2=endplong;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }






















    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paintCompass = new Paint(Paint.ANTI_ALIAS_FLAG); //paint object for compass
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); //paint object for locations circles
        int x2 = getWidth();
        int y2 = getHeight();
        int radius2;
        radius2 = 150;
        paintCompass.setStyle(Paint.Style.FILL);
        paintCompass.setColor(Color.TRANSPARENT);
        canvas.drawPaint(paintCompass);
        // Use Color.parseColor to define HTML colors
        paintCompass.setColor(Color.parseColor("#f57f17"));


        Paint compasscircletext;
        compasscircletext = new Paint(); //paint object for text inside circle
        compasscircletext.setColor(Color.WHITE);
        compasscircletext.setTextSize(50f); //size of text inside circle
        compasscircletext.setAntiAlias(true);

        compasscircletext.setTextAlign(Paint.Align.CENTER);
        Rect b = new Rect();
        compasscircletext.getTextBounds("Km", 0,2 , b);


        paintCompass.setAntiAlias(true);
        canvas.drawCircle(x2 / x2+150, y2 / y2+150,radius2,paintCompass);




      //  canvas.drawCircle(x2 / x2+150, y2 / y2+150, radius2, paint);
       // canvas.drawCircle(x2 / x2+150, y2 / y2+150,radius2,paint);
       // canvas.drawText(String.valueOf(arPoints.size()), x2 / x2+150, y2 / y2+150, compasscircletext);
     //   canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        //canvas.drawText("Compass", x2 - 30, y2 - 80, paint);

       // String s=String.valueOf(arPoints.size());
       // canvas.drawCircle(x2/2, y2/2, 50, paint);
       // canvas.drawText(s, x2 - (30 * arPoints.get(2).getName().length() / 2), y2 - 80, paint);


        if (currentLocation == null) {
            return;
        }

        final int radius = 30;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(60);
        int totalloc=0;
        for (int i = 0; i < arPoints.size(); i ++) {
            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);

            float[] cameraCoordinateVector = new float[4];
            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);

            canvas.drawCircle((getWidth()/getWidth()+50)+i*10, (getHeight()/getHeight()+150)+i*30, 20, paint);

            // cameraCoordinateVector[2] is z, that always less than 0 to display on right position
            // if z > 0, the point will display on the opposite
            if (cameraCoordinateVector[2] < 0) {
                float x  = (0.5f + cameraCoordinateVector[0]/cameraCoordinateVector[3]) * canvas.getWidth();
                float y = (0.5f - cameraCoordinateVector[1]/cameraCoordinateVector[3]) * canvas.getHeight();


              LatLng startlatlng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
              LatLng endlatlng = new LatLng(arPoints.get(i).getLocation().getLatitude(), arPoints.get(i).getLocation().getLongitude());

                double distance = CalculationByDistance(startlatlng,endlatlng);
                distance=distance * 1000;
if((int)distance<=rangeofloc) //in this condition checking range of locations then displaying location as per range demand

{
    totalloc++;
    String text=String.valueOf(totalloc);
    canvas.drawText(text, x2 / x2+150, y2 / y2+150, compasscircletext);  //String.valueOf(totalloc)
    Paint circletext;
    circletext = new Paint(); //paint object for text inside circle
    circletext.setColor(Color.WHITE);
    circletext.setTextSize(30f); //size of text inside circle
    circletext.setAntiAlias(true);
    circletext.setTextAlign(Paint.Align.CENTER);
    Rect bounds = new Rect();
    circletext.getTextBounds(arPoints.get(i).getName() + "Distance =" + new DecimalFormat("##.##").format(distance) + "m", 0, 8, bounds);
    paint.setAntiAlias(true);
    canvas.drawCircle(x, y - (bounds.height() / 2), bounds.width() + 5, paint);
    //  canvas.drawText(arPoints.get(i).getName()+"Distance ="+new DecimalFormat("##.##").format(distance)+"Km", x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);
    canvas.drawText(arPoints.get(i).getName(), x, y, circletext);
    canvas.drawText(new DecimalFormat("##.##").format(distance) + "m", x, y + 50, circletext);

}





            }
        }
    }



}
