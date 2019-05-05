package com.example.admin.loginmovie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText username1,password1;
    private Button login1;
    private TextView Registerr,lupapasword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1 =(EditText)findViewById(R.id.et_user);
        password1=(EditText)findViewById(R.id.et_pass);
        login1 = (Button)findViewById(R.id.b_login);
        Registerr = (TextView)findViewById(R.id.tv_register);
        lupapasword=(TextView)findViewById(R.id.tv_lupa);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user !=null){ //apakah user udah login
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));

        }

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username1.getText().toString(),password1.getText().toString());
            }
        });
        Registerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Registrationactivity.class));
            }
        });

        lupapasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,PasswordActivity.class));

            }
        });

    }
    private void validate(String userNAME, String PAssword) {



        firebaseAuth.signInWithEmailAndPassword(userNAME, PAssword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //Toast.makeText(LoginActivity.this,"Login berhasil",Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Login gagal!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkEmailVerification () { //untuk cek verif email
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent (LoginActivity.this,MainActivity.class));

  //      if(emailflag) {
    //        finish();
      //      startActivity(new Intent (LoginActivity.this,MainActivity.class));
        //}
       // else {
         //   Toast.makeText(LoginActivity.this,"Verifikasi email terlebih dahulu !",Toast.LENGTH_SHORT).show();
           // firebaseAuth.signOut();
        //}
    }
}
