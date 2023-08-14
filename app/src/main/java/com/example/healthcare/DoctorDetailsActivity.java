package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doc_det1={
            {"Doctor Name: Madhur", "Hospital Address: Bengaluru","Exp: 5 years","mobile No: 4844778923","1000/-"},
            {"Doctor Name: Raghu", "Hospital Address: Hyderabad","Exp: 3 years","mobile No: 9484948","800/-"},
            {"Doctor Name: Micheal", "Hospital Address: Chennai","Exp: 4 years","mobile No: 9581252","900/-"},
            {"Doctor Name: Harish", "Hospital Address: Pakistan","Exp: 1 years","mobile No: 4781849","12/-"},
            {"Doctor Name: Michel", "Hospital Address: Miami","Exp: 25 years","mobile No: 95584","9000/-"}

    };

    private String[][] doc_det2={
            {"Doctor Name: Madhur", "Hospital Address: Bengaluru","Exp: 5 years","mobile No: 4844778923","1000/-"},
            {"Doctor Name: Raghu", "Hospital Address: Hyderabad","Exp: 3 years","mobile No: 9484948","800/-"},
            {"Doctor Name: Micheal", "Hospital Address: Chennai","Exp: 4 years","mobile No: 9581252","900/-"},
            {"Doctor Name: Harish", "Hospital Address: Pakistan","Exp: 1 years","mobile No: 4781849","12/-"},
            {"Doctor Name: Michel", "Hospital Address: Miami","Exp: 25 years","mobile No: 95584","9000/-"}

    };

    private String[][] doc_det3={
            {"Doctor Name: Madhur", "Hospital Address: Bengaluru","Exp: 5 years","mobile No: 4844778923","1000/-"},
            {"Doctor Name: Raghu", "Hospital Address: Hyderabad","Exp: 3 years","mobile No: 9484948","800/-"},
            {"Doctor Name: Micheal", "Hospital Address: Chennai","Exp: 4 years","mobile No: 9581252","900/-"},
            {"Doctor Name: Harish", "Hospital Address: Pakistan","Exp: 1 years","mobile No: 4781849","12/-"},
            {"Doctor Name: Michel", "Hospital Address: Miami","Exp: 25 years","mobile No: 95584","9000/-"}

    };

    private String[][] doc_det4={
            {"Doctor Name: Madhur", "Hospital Address: Bengaluru","Exp: 5 years","mobile No: 4844778923","1000/-"},
            {"Doctor Name: Raghu", "Hospital Address: Hyderabad","Exp: 3 years","mobile No: 9484948","800/-"},
            {"Doctor Name: Micheal", "Hospital Address: Chennai","Exp: 4 years","mobile No: 9581252","900/-"},
            {"Doctor Name: Harish", "Hospital Address: Pakistan","Exp: 1 years","mobile No: 4781849","12/-"},
            {"Doctor Name: Michel", "Hospital Address: Miami","Exp: 25 years","mobile No: 95584","9000/-"}

    };

    private String[][] doc_det5={
            {"Doctor Name: Madhur", "Hospital Address: Bengaluru","Exp: 5 years","Mobile No: 4844778923","1000/-"},
            {"Doctor Name: Raghu", "Hospital Address: Hyderabad","Exp: 3 years","mobile No: 9484948","800/-"},
            {"Doctor Name: Micheal", "Hospital Address: Chennai","Exp: 4 years","mobile No: 9581252","900/-"},
            {"Doctor Name: Harish", "Hospital Address: Pakistan","Exp: 1 years","mobile No: 4781849","12/-"},
            {"Doctor Name: Michel", "Hospital Address: Miami","Exp: 25 years","mobile No: 95584","9000/-"}

    };
    TextView tv;
    Button btn;
    String[][] doc_det={};
    HashMap<String,String > item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv= findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.DDBack);
        Intent it=getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0){
            doc_det = doc_det1;
        }else if(title.compareTo("Dietician")==0){
            doc_det = doc_det2;
        }else if(title.compareTo("Cardiologist")==0){
            doc_det = doc_det3;
        }else if(title.compareTo("Surgeon")==0){
            doc_det = doc_det4;
        }else {
            doc_det = doc_det5;
        }


            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<doc_det.length;i++){
            item = new HashMap<>();
            item.put("line1",doc_det[i][0]);
            item.put("line2",doc_det[i][1]);
            item.put("line3",doc_det[i][2]);
            item.put("line4",doc_det[i][3]);
            item.put("line5","Cons fees"+doc_det[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doc_det[i][0]);
                it.putExtra("text3",doc_det[i][1]);
                it.putExtra("text4",doc_det[i][3]);
                it.putExtra("text5",doc_det[i][4]);
                startActivity(it);

            }
        });
    }
}