package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages= {
            {"package 1 :Full Body Checkup","","","","999"},
            {"package 2 :Blood Glucose Fasting","","","","299"},
            {"package 3 :COVID 19 Antibody","","","","899"},
            {"package 4 :Thyroid Checkup","","","","499"},
            {"package 5 :Immunity Checkup","","","","799"},
    };

    private String[] pkg_details = {
            "Blood Glucose Fasting\n"+
                    "-Complete Hemogram\n" +
                    "-Iron Studies\n"+
                    "-Kidney Function Text\n",
            "Blood Glucose Fasting",
            "Complete Homogram\n"+
                    "CRP\n"+
                    "Iron Studies\n"+
                    "Lipid Profile",
            "Blood Glucose Fasting\n"+
                    "-Complete Hemogram\n",
            "Blood Pressure Fasting"

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoto, btnBack;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoto = findViewById(R.id.btnLTGoToCart);
        btnBack=findViewById(R.id.btnLTBack);
        lv=findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list=new ArrayList();
        for( int i=0;i<packages.length;i++){
            item = new HashMap<>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetailsActivity.class );
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",pkg_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

        btnGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });

    }
}