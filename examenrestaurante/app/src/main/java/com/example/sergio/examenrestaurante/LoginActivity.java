package com.example.sergio.examenrestaurante;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btnlogin;
    private EditText editTextcorreo;
    private EditText editTextcontrasena;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        firebaseAuth= FirebaseAuth.getInstance();

       /* if(firebaseAuth.getCurrentUser() != null){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(new Intent(LoginActivity.this,MainActivity.class) );
        }*/
        editTextcorreo = (EditText)  findViewById(R.id.editTextcorreo);
        editTextcontrasena = (EditText)  findViewById(R.id.editTextcontrasena);
        btnlogin = (Button) findViewById(R.id.btnlogin);

        progressDialog = new ProgressDialog(LoginActivity.this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }
    private  void userLogin() {
        final String correo = editTextcorreo.getText().toString().trim();
        String contrasena = editTextcontrasena.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {

            Toast.makeText(LoginActivity.this, "por favor escribe un correo", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contrasena)) {

            Toast.makeText(LoginActivity.this, "por favor escribe un correo", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Iniciando sesión");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener((Activity) LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // finish();
                            //startActivity(new Intent(getApplicationContext(),InicioFragment.class));
                            progressDialog.setMessage("Sesion iniciada con "+ correo);
                            progressDialog.show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);

                        }
                    }
                });


    }

}

