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
import androidx.fragment.app.FragmentTransaction;

import com.example.my_spender.R;

public class CantidadDespuesFragment extends Fragment {

    private Button btn;
    private TextView infoCroquetas, infoCantidadDias;
    private int cantidad, tamaño, peso, edad, actividadFisica, daysNumber, hora1, hora2, hora3;
    private boolean am1, am2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            cantidad = getArguments().getInt("Croquetas");
            tamaño = getArguments().getInt("Tamaño");
            peso = getArguments().getInt("Peso");
            edad = getArguments().getInt("Edad", 0);
            actividadFisica = getArguments().getInt("Actividad", 0);
            daysNumber = getArguments().getInt("Dias");
            hora1 = getArguments().getInt("Hora1");
            hora2 = getArguments().getInt("Hora2", 0);
            hora3 = getArguments().getInt("Hora3", 0);
            am1 = getArguments().getBoolean("Am1");
            am2 = getArguments().getBoolean("Am2", true);

        }
    }




    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cantidaddespues, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = (Button) view.findViewById(R.id.buttonComenzarCantidad);
        infoCroquetas = view.findViewById(R.id.textPorcionkg);
        infoCantidadDias = view.findViewById(R.id.textcanveces);

        int porciones = cantidad/daysNumber;

        infoCroquetas.setText(porciones + " gramos");
        infoCantidadDias.setText(daysNumber + "");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Going to Fragment")
                btn.setY(-150);
                //((ViewGroup)shora.getParent()).removeView(shora);
                datosFinalesConfActivity fragment = new datosFinalesConfActivity();

                //Se crea un paquete donde se almacena el dato de croquetas
                Bundle info = new Bundle();
                info.putInt("Croquetas", cantidad);
                info.putInt("Tamaño", tamaño);
                info.putInt("Peso", peso);
                info.putInt("Edad", edad);
                info.putInt("Actividad", actividadFisica);
                info.putInt("Dias", daysNumber);
                info.putInt("Hora1", hora1);
                info.putInt("Hora2", hora2);
                info.putInt("Hora3", hora3);
                info.putBoolean("Am1", am1);
                info.putBoolean("Am2", am2);
                //Se envia el paquete a la siguiente fragment
                fragment.setArguments(info);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.CantidadAntes, fragment);
                transaction.commit();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Cantidadtt, fragment).commit();
                //getActivity().getSupportFragmentManager().beginTransaction()
            }
        });
    }


}

