package com.example.diplomadmin.activities.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.points.AddPoint;
import com.example.diplomadmin.activities.points.DeletePoint;
import com.example.diplomadmin.activities.points.UpdatePoint;

public class MenuPoint extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd;
    Button buttonDelete;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_point);

        buttonAdd = findViewById(R.id.button5);
        buttonDelete = findViewById(R.id.button6);
        buttonUpdate = findViewById(R.id.button7);

        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button5:
                Intent intentAdd = new Intent(this, AddPoint.class);
                startActivity(intentAdd);
                break;
            case R.id.button6:
                Intent intentDelete = new Intent(this, DeletePoint.class);
                startActivity(intentDelete);
                break;
            case R.id.button7:
                Intent intentUpdate = new Intent(this, UpdatePoint.class);
                startActivity(intentUpdate);
                break;
        }
    }
}

