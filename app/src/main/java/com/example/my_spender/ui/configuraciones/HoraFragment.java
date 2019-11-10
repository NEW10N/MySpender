package com.example.my_spender.ui.configuraciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.my_spender.R;

public class HoraFragment extends Fragment {
    private Button ctd, btnMin, btnPlus;
    private int cantidad, tamaño, peso, edad, actividadFisica, auxNumber;
    private TextView days;
    private Spinner spinner;
    private ArrayAdapter <String> adapter;
    private boolean amPm1, amPm2;

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
        View view =inflater.inflate(R.layout.fragment_confhora, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnMin = view.findViewById(R.id.minus);
        btnPlus = view.findViewById(R.id.plus);
        days = view.findViewById(R.id.daysNumber);
        auxNumber = 1;
        days.setText(auxNumber + "");
        spinner = view.findViewById(R.id.spinnerHoras);
        amPm1 = true;
        amPm2 = true;

        final String [] OptionDays1 = {"Escoja un horario", "7am", "8am", "9am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm", "12am"};
        final String [] OptionDays2 = {"Escoja un horario", "7am - 3pm", "8am - 4pm", "9am - 5pm", "10am - 6pm", "11am - 7pm", "12pm - 8pm", "1pm - 9pm", "2pm - 10pm", "3pm - 11pm", "4pm - 12am"};
        final String [] OptionDays3 = {"Escoja un horario", "7am - 11am - 3pm", "8am - 12pm - 4pm", "9am - 1pm - 5pm", "10am - 2pm - 6pm", "11am - 3pm - 7pm", "12pm - 4pm - 8pm", "1pm - 5pm - 9pm", "2pm - 6pm - 10pm", "3pm - 7pm - 11pm", "4pm - 8pm - 12pm"};

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, OptionDays1);
        spinner.setAdapter(adapter);

        ctd = (Button) view.findViewById(R.id.btnscf);

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auxNumber > 1){
                    auxNumber--;
                    days.setText(auxNumber + "");
                }

                changeHour(OptionDays1,OptionDays2,OptionDays3);
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auxNumber < 3){
                    auxNumber++;
                    days.setText(auxNumber + "");
                }

                changeHour(OptionDays1,OptionDays2,OptionDays3);
            }
        });

        ctd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Going to Fragment")
                int hora = 0;
                int hora2 = 0;
                int hora3 = 0;
                switch (auxNumber){
                    case 1:
                        hora = spinner.getSelectedItemPosition();
                        if(hora <= 6 && hora != 0){
                            if (hora == 6){
                                amPm1 = false;
                            }else{
                                amPm1 = true;
                            }
                            hora += 6;
                        }else if(hora != 0){
                            hora -= 6;
                            amPm1 = false;
                        }
                        break;
                    case 2:
                        hora = spinner.getSelectedItemPosition();
                        hora2 = hora + 2;
                        amPm2 = false;
                        if(hora <= 6 && hora != 0){
                            if (hora == 6){
                                amPm1 = false;
                            }else{
                                amPm1 = true;
                            }
                            hora += 6;
                        }else if (hora != 0) {
                            hora -= 6;
                            amPm1 = false;
                        }

                        break;
                    case 3:
                        hora = spinner.getSelectedItemPosition();
                        hora3 = hora + 2;
                        if(hora < 3 && hora != 0){
                            if (hora == 2){
                                amPm2 = false;
                            }else{
                                amPm2 = true;
                            }
                            hora += 6;
                            amPm1 = true;
                            hora2 = hora + 4;
                        }else if(hora < 7 && hora != 0){
                            if (hora == 6){
                                amPm1 = false;
                            }else{
                                amPm1 = true;
                            }
                            hora2 = hora - 2;
                            amPm2 = false;
                            hora += 6;
                        }else if(hora != 0){
                            hora2 = hora - 2;
                            amPm2 = false;
                            hora -= 6;
                            amPm1 = false;
                        }
                        break;
                }
                if(hora != 0){
                    //Se envia la cantidad de veces y las horas
                    //auxNumber
                    //hora, hora2, hora3
                    CantidadDespuesFragment fragment = new CantidadDespuesFragment();

                    //Se crea un paquete donde se almacena el dato de croquetas
                    Bundle info = new Bundle();
                    info.putInt("Croquetas", cantidad);
                    info.putInt("Tamaño", tamaño);
                    info.putInt("Peso", peso);
                    info.putInt("Edad", edad);
                    info.putInt("Actividad", actividadFisica);
                    info.putInt("Dias", auxNumber);
                    info.putInt("Hora1", hora);
                    info.putInt("Hora2", hora2);
                    info.putInt("Hora3", hora3);
                    info.putBoolean("Am1", amPm1);
                    info.putBoolean("Am2", amPm2);

                    //Se envia el paquete a la siguiente fragment
                    fragment.setArguments(info);

                    //Aleja el boton
                    ctd.setY(-150);

                    //Se transfiere a otro fragment
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.ConfHora, fragment);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Escoja alguna hora", Toast.LENGTH_SHORT).show();
                }
                ctd.setVisibility(View.GONE);
                //HoraFragment fragment = new HoraFragment();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ConfHora, fragment).addToBackStack(null).commit();
            }
        });
    }

    //Permite cambiar las horas dependiendo de cuantas veces sera la alimentación
    public void changeHour(String [] dias1, String [] dias2, String [] dias3){

        switch (auxNumber) {
            case 1:
                adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dias1);
                spinner.setAdapter(adapter);
                break;
            case 2:
                adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dias2);
                spinner.setAdapter(adapter);
                break;
            case 3:
                adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dias3);
                spinner.setAdapter(adapter);
                break;
        }
    }
}
