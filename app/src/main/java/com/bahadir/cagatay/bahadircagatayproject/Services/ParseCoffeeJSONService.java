package com.bahadir.cagatay.bahadircagatayproject.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bahadir.cagatay.bahadircagatayproject.Coffee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ParseCoffeeJSONService extends IntentService{
    JSONArray coffees;
    ArrayList<Coffee> coffeeList;
    private static final String TAG_COFFEES = "coffees";
    private static final String TAG_COFFEENAME = "coffeeName";
    private static final String TAG_PRICE = "price";


    public ParseCoffeeJSONService(){
        super("MyServiceCoffee");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        coffeeList = new  ArrayList<Coffee>();

        String filename = intent.getStringExtra("filename");

        String jsonfileContent = loadFileFromAsset(filename);

        Log.d("Response: ", "> " + jsonfileContent);

        if (jsonfileContent != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonfileContent);

                // Getting JSON Array node
                coffees = jsonObj.getJSONArray(TAG_COFFEES);

                // looping through all Contacts
                for (int i = 0; i < coffees.length(); i++) {
                    JSONObject coffee = coffees.getJSONObject(i);

                    String coffeeName = coffee.getString(TAG_COFFEENAME);
                    String price = coffee.getString(TAG_PRICE);


                  Coffee a =new Coffee(coffeeName,price);
                    coffeeList.add(a);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Intent broascastIntent = new Intent();

        Bundle b=new Bundle();
        b.putParcelableArrayList("coffeelist", coffeeList);
        broascastIntent.putExtras(b);
        broascastIntent.setAction("COFFEE_JSON_PARSE_ACTION");
        getBaseContext().sendBroadcast(broascastIntent);

        Log.d("Service",":service END" );


    }
    private String loadFileFromAsset(String fileName) {
        String jsonfileContent = null;
        try {

            InputStream is = getBaseContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            jsonfileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonfileContent;
    }

}
