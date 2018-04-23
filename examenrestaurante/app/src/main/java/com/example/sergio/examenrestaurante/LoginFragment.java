package com.example.sergio.examenrestaurante;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.sergio.examenrestaurante.R.id.editTextcontrasena;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Button btnlogin;
    private EditText editTextcorreo;
    private EditText editTextcontrasena;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            int a= 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_login, container, false);


        firebaseAuth= FirebaseAuth.getInstance();

        /*if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getContext(), MainActivity.class));
        }*/
        editTextcorreo = (EditText)  view.findViewById(R.id.editTextcorreo);
        editTextcontrasena = (EditText)  view.findViewById(R.id.editTextcontrasena);
        btnlogin = (Button)  view.findViewById(R.id.btnlogin);

        progressDialog = new ProgressDialog(getContext());
        btnlogin.setOnClickListener(this);


        return view;

    }

    private  void userLogin(){
        String correo = editTextcorreo.getText().toString().trim();
        String contrasena = editTextcontrasena.getText().toString().trim();

        if(TextUtils.isEmpty(correo)){

            Toast.makeText(getContext(), "por favor escribe un correo", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(contrasena)){

            Toast.makeText(getContext(), "por favor escribe un correo", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Iniciando Sesion");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                           // finish();
                            //startActivity(new Intent(getApplicationContext(),InicioFragment.class));
                            progressDialog.setMessage("Sesion Iniciada");
                            progressDialog.show();

                            startActivity(new Intent(getContext(),MainActivity.class) );
                        }
                    }
                });


    }

    @Override
    public void onClick(View view) {
        if(view ==btnlogin){
            userLogin();
        }
    }
}
