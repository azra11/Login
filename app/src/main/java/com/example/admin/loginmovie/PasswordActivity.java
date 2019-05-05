package com.example.admin.loginmovie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordemail;
    Button resetpass;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordemail = (EditText)findViewById(R.id.et_p_email);
        resetpass = (Button)findViewById(R.id.b_resetpass);
        firebaseAuth=FirebaseAuth.getInstance();

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passwordemail.getText().toString().trim();

                if(useremail.equals("")) {
                    Toast.makeText(PasswordActivity.this,"Masukan email",Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(PasswordActivity.this,"Reset Password berhasil",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this,LoginActivity.class));
                            }
                            else {
                                Toast.makeText(PasswordActivity.this,"Reset password Gagal",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
}
