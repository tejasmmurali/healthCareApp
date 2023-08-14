package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirm;
    Button signUp;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextSignUserName);
        edEmail = findViewById(R.id.editTextSignEmail);
        edPassword = findViewById(R.id.editTextSignPassword);
        edConfirm = findViewById(R.id.editTextSignConfirmPassword);
        signUp = findViewById(R.id.buttonSignUp);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(userName.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill in details",Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            db.register(userName,email,password);
                            Toast.makeText(getApplicationContext(),"Sign up complete",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        }else{
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 letters, an alphabet, a digit, a special character",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Passwords does not match",Toast.LENGTH_SHORT).show();
                    }
                }





            }
        });





    }

    public static Boolean isValid(String pass){
        int f1=0,f2=0,f3=0;
        if(pass.length()<8){
            return false;
        }else{
            for(int p=0;p<pass.length();p++){
                if(Character.isLetter(pass.charAt(p))){
                    f1=1;
                }
            }
            for(int q=0;q<pass.length();q++){
                if(Character.isDigit(pass.charAt(q))){
                    f2=1;
                }
            }
            for(int r=0;r<pass.length();r++){
                char c = pass.charAt(r);
                if(c>=33 && c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2 ==1 && f3 ==1){
                return true;
            }
            return false;

        }
    }

}