package com.example.diplomadmin.activities.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.bluetooth.BluetoothActivity;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    Button buttonPoint;
    Button buttonVector;
    Button buttonAlias;
    Button buttonBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonPoint = findViewById(R.id.button2);
        buttonVector = findViewById(R.id.button3);
        buttonAlias = findViewById(R.id.button4);
        buttonBluetooth = findViewById(R.id.button14);

        buttonPoint.setOnClickListener(this);
        buttonVector.setOnClickListener(this);
        buttonAlias.setOnClickListener(this);
        buttonBluetooth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Intent intentPoint = new Intent(this, MenuPoint.class);
                startActivity(intentPoint);
                break;
            case R.id.button3:
                Intent intentVector = new Intent(this, MenuVector.class);
                startActivity(intentVector);
                break;
            case R.id.button4:
                Intent intentAlias = new Intent(this, MenuAlias.class);
                startActivity(intentAlias);
                break;
            case R.id.button14:
                Intent intentBluetooth = new Intent(this, BluetoothActivity.class);
                startActivity(intentBluetooth);
                break;
        }
    }
}
