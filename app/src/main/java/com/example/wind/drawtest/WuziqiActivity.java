package com.example.wind.drawtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class WuziqiActivity extends AppCompatActivity {

        private WuziqiPanel wuziqiPanel;

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wuziqi_layout);
        wuziqiPanel = (WuziqiPanel) findViewById(R.id.id_wuziqi);
    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu)
        {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item)
        {
            int id = item.getItemId();

            if (id == R.id.restart) {
                wuziqiPanel.restart();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }

