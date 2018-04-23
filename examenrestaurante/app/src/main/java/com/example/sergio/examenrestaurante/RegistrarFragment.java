package com.example.sergio.examenrestaurante;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RegistrarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarFragment newInstance(String param1, String param2) {
        RegistrarFragment fragment = new RegistrarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private Button btnregistrar;
    private EditText editTextCorreo;
    private EditText editTextcontrasena;
    private TextView textviewlogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_registrar, container, false);

        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getContext());
        btnregistrar = (Button) view.findViewById(R.id.btnregistrar);
        editTextCorreo = (EditText)  view.findViewById(R.id. editTextCorreo);
        editTextcontrasena = (EditText)  view.findViewById(R.id. editTextcontrasena);
        textviewlogin = (TextView)  view.findViewById(R.id. textviewlogin);
        btnregistrar.setOnClickListener(this);
        textviewlogin.setOnClickListener(this);

        return view;
    }



    private void registrarusuario(){
        String correo = editTextCorreo.getText().toString().trim();
        String contrasena = editTextcontrasena.getText().toString().trim();

        if(TextUtils.isEmpty(correo)){

            Toast.makeText(getContext(), "por favor escribe un correo", Toast.LENGTH_SHORT).show();
          // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(contrasena)){

            Toast.makeText(getContext(), "por favor escribe una contraseña", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("registrar usuario");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(correo,contrasena )
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(getContext(), "usuario no registrado intente nuevamente por favor", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onClick(View view) {
     if(view ==btnregistrar){
         registrarusuario();
     }

        if(view ==textviewlogin){


            LoginFragment fragment= new LoginFragment();

         FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
           fragmenttransaction.replace(R.id.main_content,fragment);
           fragmenttransaction.addToBackStack(null);
           fragmenttransaction.commit();
            //FragmentManager fragmentManager;
           //FragmentTransaction fragmentTransaction;
           // fragmentManager = getSupportFragmentManager();
           //fragmentTransaction = fragmentManager.beginTransaction();
            //LoginFragment loginFragment = new LoginFragment();
            //fragmentTransaction.replace(R.id.main_content,loginFragment);
            //fragmentTransaction.commit();

        }
    }
}
