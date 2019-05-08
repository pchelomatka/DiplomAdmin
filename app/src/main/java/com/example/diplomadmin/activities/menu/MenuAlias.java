package com.example.diplomadmin.activities.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.aliases.AddAlias;
import com.example.diplomadmin.activities.aliases.DeleteAlias;
import com.example.diplomadmin.activities.aliases.UpdateAlias;
import com.example.diplomadmin.activities.points.AddPoint;
import com.example.diplomadmin.activities.points.DeletePoint;
import com.example.diplomadmin.activities.points.UpdatePoint;

public class MenuAlias extends AppCompatActivity implements View.OnClickListener{

    Button buttonAdd;
    Button buttonDelete;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alias);

        buttonAdd = findViewById(R.id.button18);
        buttonDelete = findViewById(R.id.button19);
        buttonUpdate = findViewById(R.id.button20);

        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button18:
                Intent intentAdd = new Intent(this, AddAlias.class);
                startActivity(intentAdd);
                break;
            case R.id.button19:
                Intent intentDelete = new Intent(this, DeleteAlias.class);
                startActivity(intentDelete);
                break;
            case R.id.button20:
                Intent intentUpdate = new Intent(this, UpdateAlias.class);
                startActivity(intentUpdate);
                break;
        }
    }
}
