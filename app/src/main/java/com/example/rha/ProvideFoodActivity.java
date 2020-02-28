 package com.example.rha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

 public class ProvideFoodActivity extends AppCompatActivity {

     private EditText name,phone,city,description;
     private TextView location;
     private Button provide_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_food);

        getSupportActionBar().setTitle("Provide Food");

        initialization();


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

     private void initialization() {
        name=findViewById(R.id.pf_name);
         phone=findViewById(R.id.pf_number);
         city=findViewById(R.id.pf_city);
         description=findViewById(R.id.pf_food_description);
         location=findViewById(R.id.pf_location);
         provide_button=findViewById(R.id.pf_bt_provide_food);

     }
 }
