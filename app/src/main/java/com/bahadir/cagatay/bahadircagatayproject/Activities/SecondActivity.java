package com.bahadir.cagatay.bahadircagatayproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bahadir.cagatay.bahadircagatayproject.Coffee;
import com.bahadir.cagatay.bahadircagatayproject.CoffeeBrand;
import com.bahadir.cagatay.bahadircagatayproject.DataBase.CoffeeDB;
import com.bahadir.cagatay.bahadircagatayproject.DataBase.DatabaseHelper;
import com.bahadir.cagatay.bahadircagatayproject.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Intent recievedIntent;
    Bundle recievedBundle;
    TextView tvBrand, tvCoffee, tvRating;
    Button btnDeliver,btnSave,btnDelete,btnCancel;
    DatabaseHelper databaseHelper;
    Dialog customDialog;
    EditText et1,et2,et3,et4, etAddress, etNumber;
    TextView tv1;
    RadioGroup rg;
    String payment;
    String  dbStr1, dbStr2, dbStr3, strVal;
    CoffeeBrand cbb;
    Coffee ccc;
    RatingBar ratingBar;

    //Gesture
    private GestureDetectorCompat mDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);

        // Make sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Locking the orientation to Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        tvBrand=findViewById(R.id.tvBrand);
        tvCoffee=findViewById(R.id.tvCoffee);
        btnDeliver=findViewById(R.id.btnDeliver);
        etAddress=findViewById(R.id.etAddress);
        etNumber=findViewById(R.id.etPhone);
        ratingBar=findViewById(R.id.ratingBar);
        tvRating=findViewById(R.id.tvGesRate);
        ratingBar.setVisibility(View.INVISIBLE);
        tvRating.setVisibility(View.INVISIBLE);

        recievedIntent = getIntent();
        recievedBundle = recievedIntent.getExtras();
        databaseHelper= new DatabaseHelper(this);
        tvBrand.setText(recievedBundle.getString("br"));
        tvCoffee.setText(recievedBundle.getString("cf"));

        rg = (RadioGroup) findViewById(R.id.radioGroup);
         cbb=new CoffeeBrand(tvBrand.getText().toString());
       ccc=new Coffee(tvCoffee.getText().toString());


        btnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ArrayList<Coffee> carsinCarTable = CoffeeDB.getAllCars(databaseHelper);
                final String value =
                        ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                                .getText().toString();

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
                    }
                });
                strVal=value;
                displayDialog(ccc,cbb);
            }
        });
    }

    public void displayDialog(Coffee cof, CoffeeBrand cb ){

        final Coffee selectedcoffee = cof;
        final CoffeeBrand cofBrand=cb;

        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.dialog);
        customDialog.setTitle("Custom Dialog");

        et1 = (EditText) customDialog.findViewById(R.id.coffeeBrandEt);
        et2 = (EditText)  customDialog.findViewById(R.id.cofNameEt);
        et3 = (EditText)  customDialog.findViewById(R.id.addressEt);
        et4 = (EditText)  customDialog.findViewById(R.id.numEt);
        tv1=customDialog.findViewById(R.id.tvPaymentType);


        et1.setText(cb.getCoffeeBrand());
        et2.setText(cof.getCoffeeName());
        et3.setText( etAddress.getText().toString());
        et4.setText(etNumber.getText().toString());
        tv1.setText(strVal);







        btnSave = (Button) customDialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dbStr1=et1.getText().toString();
                dbStr2=et2.getText().toString();
                dbStr3=et3.getText().toString();
                new InsertAsynTask().execute(selectedcoffee);
                customDialog.dismiss();
            }
        });


        btnCancel = (Button) customDialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }

    class InsertAsynTask extends AsyncTask<Coffee, Void, String> {

        @Override
        protected String doInBackground(Coffee... coffee) {
            Coffee selectedCar = coffee[0];
            CoffeeDB.insertCar(databaseHelper, dbStr1, dbStr2);
            return null;
        }

        @Override
        protected void onPostExecute(String msg) {
            super.onPostExecute(msg);
            FancyToast.makeText(SecondActivity.this, "Coffee added",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();

        }
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        public void onLongPress(MotionEvent motionEvent) {

            ratingBar.setVisibility(View.VISIBLE);
            tvRating.setVisibility(View.VISIBLE);
        }


    }
}
