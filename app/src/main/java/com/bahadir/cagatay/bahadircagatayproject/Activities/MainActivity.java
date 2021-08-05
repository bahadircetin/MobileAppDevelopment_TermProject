package com.bahadir.cagatay.bahadircagatayproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bahadir.cagatay.bahadircagatayproject.Coffee;
import com.bahadir.cagatay.bahadircagatayproject.CoffeeBrand;
import com.bahadir.cagatay.bahadircagatayproject.MyRecyclerViewAdapter;
import com.bahadir.cagatay.bahadircagatayproject.Services.ParseCoffeeJSONService;
import com.bahadir.cagatay.bahadircagatayproject.R;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerCoffee;
    LinearLayoutManager layoutManager;
    MyRecyclerViewAdapter adapter;
    ArrayList<Coffee> coffeeList;
    MediaPlayer mediaPlayer;
    IntentFilter mIntentFilter;
    TextView c1,c2,c3,c4;
    TextView[] textViewArray;
    boolean selection=true;
    String deliverCoffee, brandd;
    int position=-1;

    CoffeeBrand a =null;

    private ArrayList<CoffeeBrand> recyclervalues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Locking the orientation to Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        prepareData();
        //for the audio file
        mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.piano);
        mediaPlayer.start();

        c1=findViewById(R.id.tvCoffee1);
        c2=findViewById(R.id.tvCoffee2);
        c3=findViewById(R.id.tvCoffee3);
        c4=findViewById(R.id.tvCoffee4);
        textViewArray= new TextView[4];
        textViewArray[0]=c1;
        textViewArray[1]=c2;
        textViewArray[2]=c3;
        textViewArray[3]=c4;

        //recycler View
        recyclerCoffee = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCoffee.setLayoutManager(layoutManager);
        recyclerCoffee.hasFixedSize();
        adapter = new MyRecyclerViewAdapter(this, recyclervalues);
        recyclerCoffee.setAdapter(adapter);


        //Service Starts
        Intent intent = new Intent(this, ParseCoffeeJSONService.class);
        intent.putExtra("filename", "coffees.json");
        startService(intent);
        Log.d("Burda",":intentFilter" );
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("COFFEE_JSON_PARSE_ACTION");


        // Register the receiver
        registerReceiver(mIntentReceiver, mIntentFilter);



        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selection == true){
                    c1.setBackgroundResource(R.drawable.rounded_corners2);
                    selection = false;
                    deliverCoffee=c1.getText().toString();
                }
               else {
                    c1.setBackgroundResource(R.drawable.rounded_corners);
                    selection=true;
                    deliverCoffee="";
               }

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selection == true){
                    c2.setBackgroundResource(R.drawable.rounded_corners2);
                    selection = false;
                    deliverCoffee=c2.getText().toString();

                }
                else {
                    c2.setBackgroundResource(R.drawable.rounded_corners);
                    selection=true;
                    deliverCoffee="";
                }

            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selection == true){
                    c3.setBackgroundResource(R.drawable.rounded_corners2);
                    selection = false;
                    deliverCoffee=c3.getText().toString();

                }
                else {
                    c3.setBackgroundResource(R.drawable.rounded_corners);
                    selection=true;
                    deliverCoffee="";
                }

            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selection == true){
                    c4.setBackgroundResource(R.drawable.rounded_corners2);
                    selection = false;
                    deliverCoffee=c4.getText().toString();
                }
                else {
                    c4.setBackgroundResource(R.drawable.rounded_corners);
                    selection=true;
                    deliverCoffee="";
                }

            }
        });



    }
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Storing the received data into a Bundle

            Log.d("Service","Broadcast message taken" );
            Bundle b=intent.getExtras();
            coffeeList=b.getParcelableArrayList("coffeelist");
            fillCoffees();
        }
    };
    void fillCoffees() {

            for (int i=0;i<4;i++)
            {   Coffee cof = coffeeList.get(i);

                textViewArray[i].setText(cof.getCoffeeName()+"\n"+cof.getPrice());

            }

    }

    private void prepareData() {
        Collections.addAll(recyclervalues, new CoffeeBrand("Starbucks", R.drawable.starbuckscup), new CoffeeBrand("Caribou", R.drawable.cariboucup),
                new CoffeeBrand("Gloria Jeans", R.drawable.gloriacup));
        
    }

    public void onClick(View view){
        if(view.getId()==R.id.btnAdd)
        {
            Intent intent2 = new Intent(this, SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("br",brandd);
            bundle.putString("cf",deliverCoffee);
            intent2.putExtras(bundle);

            startActivity(intent2);
        }
    }
    public void getDeliverInfo(String brand){
        brandd=brand;

    }

}
