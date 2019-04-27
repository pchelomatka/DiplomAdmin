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
import com.example.diplomadmin.activities.vectors.AddVector;
import com.example.diplomadmin.activities.vectors.DeleteVector;
import com.example.diplomadmin.activities.vectors.UpdateVector;

public class MenuVector extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd;
    Button buttonDelete;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vector);

        buttonAdd = findViewById(R.id.button11);
        buttonDelete = findViewById(R.id.button12);
        buttonUpdate = findViewById(R.id.button16);

        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button11:
                Intent intentAdd = new Intent(this, AddVector.class);
                startActivity(intentAdd);
                break;
            case R.id.button12:
                Intent intentDelete = new Intent(this, DeleteVector.class);
                startActivity(intentDelete);
                break;
            case R.id.button16:
                Intent intentUpdate = new Intent(this, UpdateVector.class);
                startActivity(intentUpdate);
                break;
        }
    }
}
