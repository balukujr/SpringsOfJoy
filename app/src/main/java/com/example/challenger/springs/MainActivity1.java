package com.example.challenger.springs;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {

    String songname11;
    String  body;
    Song bby;
    Song bby1;
    Button zz;
    DbHandler db;
    LinearLayout lay;
    ArrayList<Song> nu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        db = new DbHandler(this);
       setTitle("Springs of Joy");
       // setSubtitle("MUSDAA Hymnal");
        // DatabaseHandler db = new DatabaseHandler(this);
               // txtedt = (EditText) findViewById(R.id.textView);
        lay  = (LinearLayout) findViewById(R.id.lnrlayout);

        for(int vv=1;vv<=50;vv++){
            bby = db.getSongById(vv);

            final String ttle =bby.getSong_title();
            zz = new Button(this);
            zz.setId(bby.getId());
            zz.setText(ttle);
            zz.setTextColor(0xFF00FF66);
            zz.setAllCaps(false);
            zz.setBackgroundColor(0x00f5b041 );
            // zz.setTextColor(0x0000);

            final String   words = bby.getSong_body();

            zz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent fire =new Intent(MainActivity1.this,SongView.class);
                    fire.putExtra("the_data",words);
                    fire.putExtra("the_title",ttle);
                    startActivity(fire);
                }
            });
            lay.addView(zz);

        }


    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void my_method(ArrayList<Song> nu){
        for(int ruth=0; ruth<nu.size();ruth++){
            bby = nu.get(ruth);
            final String ttle =bby.getSong_title();
            zz = new Button(MainActivity1.this);
            zz.setId(bby.getId());
            zz.setText(ttle);
            zz.setAllCaps(false);
            zz.setTextColor(0xFF00FF66);

           // zz.setTextColor(0x0000);

            final String   words = bby.getSong_body();

            zz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // String cathyn = db.getSong((String) zz.getText());
                    Intent fire =new Intent(MainActivity1.this,SongView.class);
                    fire.putExtra("the_data",words);
                    fire.putExtra("the_title",ttle);
                    startActivity(fire);
                }
            });
            lay.addView(zz);


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
         SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                songname11=query;
                lay.removeAllViews();
                //  songname11= txtedt.getText().toString();

                nu = db.getAllSongs(songname11);
                my_method(nu);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       /* if(id == R.id.action_search){
           ray.setVisibility(View.VISIBLE);

        }  */
        return super.onOptionsItemSelected(item);
    }

    //code for changing keyboaard
    /*
    * if(txtedt.getInputType()==InputType.TYPE_CLASS_TEXT){
                txtedt.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
            else if(txtedt.getInputType()== InputType.TYPE_CLASS_NUMBER){
                txtedt.setInputType(InputType.TYPE_CLASS_TEXT);
                //change_key.setBackgroundResource(R.drawable.p123);
            }
    * */
}
