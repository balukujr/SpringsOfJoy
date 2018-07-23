package com.example.challenger.springs;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class SongView extends AppCompatActivity {
    String value;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getActionBar().setBackgroundDrawable(new ColorDrawable(0x33FF99));
        TextView bb  = (TextView) findViewById(R.id.textv);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        value = extras.getString("the_data");
            title=extras.getString("the_title");
        }
        bb.setText(value);
        setTitle(title);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
