package com.example.rha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText fullname,email,phone,password,confirm_password;
    private Button registration;
    private ProgressDialog loading_bar;
    private FirebaseAuth mAuth;
    private DatabaseReference rootRef;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        initialization();

        mAuth=FirebaseAuth.getInstance();
        rootRef= FirebaseDatabase.getInstance().getReference();

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });



    }

    private void createNewAccount() {
        String fullname_string=fullname.getText().toString();
        String email_string=email.getText().toString();
        String pass_string=password.getText().toString();
        String confirm_pass_string=confirm_password.getText().toString();

        if(fullname_string.isEmpty() || email_string.isEmpty() || pass_string.isEmpty() || confirm_pass_string.isEmpty()){
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show();
        }
        else if(!pass_string.equals(confirm_pass_string)){
            Toast.makeText(this, "confirm password is not matched", Toast.LENGTH_SHORT).show();
        }
        else {
            loading_bar.setTitle("Create New Account");
            loading_bar.setMessage("Please wait, until it complete successfully");
            loading_bar.setCanceledOnTouchOutside(true);
            loading_bar.show();

            mAuth.createUserWithEmailAndPassword(email_string,pass_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        currentUserID=mAuth.getCurrentUser().getUid();
                        rootRef.child("Users").child(currentUserID).setValue("");
                        sendUserToUserMainActivity();
                        Toast.makeText(RegistrationActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        loading_bar.dismiss();
                    }
                    else {
                        String msg= task.getException().toString();
                        Toast.makeText(RegistrationActivity.this,"Error: "+msg, Toast.LENGTH_SHORT).show();
                        loading_bar.dismiss();
                    }
                }
            });
        }

    }

    private void sendUserToUserMainActivity() {
        Intent intent =new Intent(RegistrationActivity.this,UserMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void initialization() {

        fullname=findViewById(R.id.reg_fullname);
        email=findViewById(R.id.reg_email);
        phone=findViewById(R.id.reg_phone);
        password=findViewById(R.id.reg_pass);
        confirm_password=findViewById(R.id.reg_conf_pass);
        registration=findViewById(R.id.register_bt);
        loading_bar=new ProgressDialog(this);
    }
}
