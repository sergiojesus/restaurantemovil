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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReservarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReservarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ReservarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReservarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReservarFragment newInstance(String param1, String param2) {
        ReservarFragment fragment = new ReservarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText editnombre;
    private EditText editFecha;
    private TimePicker timePicker;
    private RadioButton radio1;
    private RadioButton radio2;
    private Button btnreservar;
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
        View  view = inflater.inflate(R.layout.fragment_reservar, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getContext());
        editnombre = (EditText) view.findViewById(R.id.editNombre);
        editFecha = (EditText) view.findViewById(R.id.editFecha);
       // timePicker = (TimePicker) view.findViewById(R.id.tiempo);
        radio1 = (RadioButton) view.findViewById(R.id.hora);
        radio2 = (RadioButton) view.findViewById(R.id.mediahora);
        btnreservar = (Button) view.findViewById(R.id.BtnReservar);
        return view;
    }
    private void registrarusuario(){
        String nombre = editnombre.getText().toString().trim();
        String fecha = editFecha.getText().toString().trim();
        String tiempo ="";
        if(radio1.isChecked()==true){
            tiempo = "1 hora";
            radio2.setChecked(false);
        }
        else if (radio2.isChecked()==true){
            tiempo = "30 minutos";
            radio1.setChecked(false);
        }

        if(TextUtils.isEmpty(nombre)){

            Toast.makeText(getContext(), "por favor escribe un nombre", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(fecha)){

            Toast.makeText(getContext(), "por favor escribe una fecha", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(tiempo)){

            Toast.makeText(getContext(), "por favor seleccione una tiempo", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "jhj",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Reservando mesa");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword (nombre,fecha)
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

}
