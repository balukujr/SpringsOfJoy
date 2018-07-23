package com.example.challenger.springs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends Activity {

 DbHandler db;
    int b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DbHandler(this);

        new MyTask().execute();
            }


protected class MyTask extends AsyncTask<Void, Integer, Void>
{ 


    @Override
    protected Void doInBackground(Void... params) {
        b= db.doesSongExist();
        if(b<1){
        MyList object =new MyList();
        object.mysongs_into_main_activity(db);
       }
        else{
        }
        return null;
    }

    // -- gets called just before thread begins
     @Override 
     protected void onPreExecute() 
     {

        // u may show ur progressbar or spalsh screen

       // super.onPreExecute();

     } 


     // -- called as soon as doInBackground method completes 
     @Override 
     protected void onPostExecute(Void result)
     { 

      // Intent openMain = new Intent("com.nepways.MAIN"); 
            Intent menu = new Intent(MainActivity.this, MainActivity1.class);
                startActivity(menu);
     } 
  } 

}