package com.example.admin.loginmovie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrationactivity extends AppCompatActivity {

    private EditText username,userpassword,useremail,umur;
    private Button Regist;
    private ImageView userFoto;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    String email,name,umurr,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //Upload data base
                    String user_email = useremail.getText().toString().trim();
                    String user_password = userpassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                              sendEmailVerification();
                            }
                            else {
                                Toast.makeText(Registrationactivity.this, "Registrasi gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrationactivity.this,LoginActivity.class));
            }
        });
    }
    private void setupUIViews () {
        username = (EditText)findViewById(R.id.et_uname);
        userpassword=(EditText)findViewById(R.id.et_upass);
        useremail=(EditText)findViewById(R.id.et_email);
        Regist=(Button)findViewById(R.id.b_regis);
        userLogin=(TextView)findViewById(R.id.tv_ulogin);
        umur=(EditText)findViewById(R.id.et_umur);
        userFoto=(ImageView)findViewById(R.id.iv_foto);

    }

    private Boolean validate(){
        Boolean result = false ;

        name = username.getText().toString();
        password= userpassword.getText().toString();
        email = useremail.getText().toString();
        umurr=umur.getText().toString();

        if(name.isEmpty() && password.isEmpty() && email.isEmpty() && umurr.isEmpty()){
            Toast.makeText(this,"isi semua terlebih dahulu!",Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
        }
        return result;
    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        senduserdata();
                        Toast.makeText(Registrationactivity.this,"Registrasi berhasil",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();//agar harus login terlebih dahulu
                        finish();
                        startActivity(new Intent(Registrationactivity.this,LoginActivity.class));
                    }
                    else {
                        Toast.makeText(Registrationactivity.this,"email belum diverifikasi",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void senduserdata() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getUid()); //setiap user punya id unik
        UserProfile userProfile = new UserProfile(umurr,email,name);
        reference.setValue(userProfile);

    }

}
