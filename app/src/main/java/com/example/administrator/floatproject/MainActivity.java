package com.example.administrator.floatproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.showfloat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatPresentImpl imple = FloatPresentImpl.getInstance();
                imple.showFloatBtn(MainActivity.this);
            }
        });
    }
}
