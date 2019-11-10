package com.example.my_spender.ui.configuraciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.my_spender.R;

public class CantidadttFragment extends Fragment {

    private Button shora;
    private TextView infoCroquetas;
    private int cantidad, tamaño, peso, edad, actividadFisica;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            cantidad = getArguments().getInt("Croquetas");
            tamaño = getArguments().getInt("Tamaño");
            peso = getArguments().getInt("Peso");
            edad = getArguments().getInt("Edad", 0);
            actividadFisica = getArguments().getInt("Actividad", 0);
        }
    }




    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_cantidadantes, container, false);
        shora = (Button) view.findViewById(R.id.btnshora);
        infoCroquetas = view.findViewById(R.id.textPorciontotal);

        infoCroquetas.setText(cantidad + " gramos");


        shora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Going to Fragment")
                shora.setY(-150);
                //((ViewGroup)shora.getParent()).removeView(shora);
                HoraFragment fragment = new HoraFragment();

                //Se crea un paquete donde se almacena el dato de croquetas
                Bundle info = new Bundle();
                info.putInt("Croquetas", cantidad);
                info.putInt("Tamaño", tamaño);
                info.putInt("Peso", peso);
                info.putInt("Edad", edad);
                info.putInt("Actividad", actividadFisica);

                //Se envia el paquete a la siguiente fragment
                fragment.setArguments(info);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.Cantidadtt, fragment);
                transaction.commit();
                shora.setVisibility(View.GONE);
            }
        });
        return view;
    }


}

