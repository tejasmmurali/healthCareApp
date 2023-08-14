package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPack, tvCost;
    EditText multiLine;

    Button addToCart,btnBck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPack = findViewById(R.id.textLabDetPack);
        tvCost = findViewById(R.id.LDtotalCost);
        multiLine = findViewById(R.id.LDMultiLine);

        multiLine.setKeyListener(null);

        Intent intent = getIntent();
        tvPack.setText(intent.getStringExtra("text1"));
        multiLine.setText(intent.getStringExtra("text2"));
        tvCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/-");

        addToCart = findViewById(R.id.btnLDCart);
        btnBck = findViewById(R.id.btnLDBack);

        btnBck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPack.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product already Exist",Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"product has been added",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));

                }
            }
        });

    }
}