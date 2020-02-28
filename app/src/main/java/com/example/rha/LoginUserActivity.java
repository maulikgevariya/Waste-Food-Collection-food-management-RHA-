package com.example.rha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserActivity extends AppCompatActivity {
    private EditText email_login,password_login;
    private Button login_bt;
    private ProgressDialog loading_bar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        getSupportActionBar().hide();

        initialization();
        mAuth=FirebaseAuth.getInstance();

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


    }

    private void userLogin() {
        String email=email_login.getText().toString();
        String password = password_login.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "please enter required field", Toast.LENGTH_SHORT).show();
        }
        else{
            loading_bar.setTitle("Sign in...");
            loading_bar.setMessage("Please wait...");
            loading_bar.setCanceledOnTouchOutside(true);
            loading_bar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        sendUserToUserMainActivity();
                        Toast.makeText(LoginUserActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        loading_bar.dismiss();
                    }
                    else {
                        String msg= task.getException().toString();
                        Toast.makeText(LoginUserActivity.this,"Error: "+msg, Toast.LENGTH_SHORT).show();
                        loading_bar.dismiss();
                    }
                }
            });
        }
    }

    private void sendUserToUserMainActivity() {
        Intent intent =new Intent(LoginUserActivity.this,UserMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void initialization() {

        email_login=findViewById(R.id.login_email_et);
        password_login=findViewById(R.id.login_password_et);
        login_bt=findViewById(R.id.login_bt);
        loading_bar=new ProgressDialog(this);
    }
}
