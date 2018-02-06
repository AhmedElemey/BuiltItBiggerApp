package com.example.shatla.gradle_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.libjokes.Jokes;/*
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
*/
public class Main2Activity extends AppCompatActivity {

    Button welcome;
    String unitID="ca-app-pub-9252532724996427/1432646575";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Jokes jokes=new Jokes();
        /*MobileAds.initialize(this, "ca-app-pub-9252532724996427~577486104");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("");
*/
        welcome=findViewById(R.id.wlcome);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Main2Activity.this,,Toast.LENGTH_LONG).show();
                //Intent intent=new Intent(Main2Activity.this,AndroidActivity.class);
                //intent.putExtra("key",new JokesClass().getJokesLib());
                new MyAsyncTask(Main2Activity.this).execute();
            }
        });

    }
}
