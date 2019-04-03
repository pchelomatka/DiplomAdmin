package com.example.diplomadmin.activities.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diplomadmin.R;

public class MenuVector extends AppCompatActivity implements View.OnClickListener{

    Button buttonAdd;
    Button buttonDelete;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vector);

        buttonAdd = findViewById(R.id.button11);
        buttonDelete = findViewById(R.id.button12);
        buttonUpdate = findViewById(R.id.button13);

        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button11:
                Intent intentPoint = new Intent(this, AddVector.class);
                startActivity(intentPoint);
                break;
            case R.id.button12:
                Intent intentVector = new Intent(this, DeleteVector.class);
                startActivity(intentVector);
                break;
            case R.id.button13:
                Intent intentAlias = new Intent(this, UpdateVector.class);
                startActivity(intentAlias);
                break;
        }
    }
}
