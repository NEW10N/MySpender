package com.example.my_spender.ui.configuraciones;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_spender.AdminSQLiteOpenHelper;
import com.example.my_spender.R;


public class datosFinalesConfFragment extends Fragment {

    private Button btn;
    private TextView infoTamaño, infoPesoIdeal, infoAdditional, infoCantidadCroquetas, infoCantidadDias, infoCantidadPorciones,horarios;
    private int cantidad, tamaño, peso, edad, actividadFisica, daysNumber, hora1, hora2, hora3;
    private boolean am1, am2;
    private EditText name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_finales_conf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = (Button) view.findViewById(R.id.btnConfirmar);
        infoTamaño = view.findViewById(R.id.textSizeInfomation);
        infoPesoIdeal = view.findViewById(R.id.textWeightInfomation);
        infoAdditional = view.findViewById(R.id.textAdditionalInfomation);
        infoCantidadCroquetas = view.findViewById(R.id.datoCantidadFinal);
        infoCantidadDias = view.findViewById(R.id.datoDiasFinal);
        infoCantidadPorciones = view.findViewById(R.id.datoPorcionesFinal);
        horarios = view.findViewById(R.id.horarios);
        name = view.findViewById(R.id.editTextName);


        infoTamaño.setText("Tamaño: " + tamaño + " cm");
        infoPesoIdeal.setText("Peso ideal: " + peso + " kg");

        if (actividadFisica == 0){
            infoAdditional.setText("Edad: " + edad + " meses");
        }else{
            String actividad;
            if (actividadFisica <= 3){
                actividad = "Baja";
            }else if (actividadFisica <= 6){
                actividad = "Normal";
            }else {
                actividad = "Alta";
            }
            infoAdditional.setText("Actividad fisica: " + actividad);
        }
        infoCantidadCroquetas.setText(cantidad + " gramos");
        infoCantidadDias.setText(daysNumber + " veces");
        final int porciones = cantidad/daysNumber;
        infoCantidadPorciones.setText(porciones + " gramos");

        //HORARIOS
        if(hora2 == 0){
            if (am1){
                horarios.setText(hora1 + "am");
            }else {
                horarios.setText(hora1 + "pm");
            }
        }else if(hora3 == 0){
            if(am1 && am2){
                horarios.setText(hora1 + "am - " + hora2 + "am");
            }else if(am1 && !am2){
                horarios.setText(hora1 + "am - " + hora2 + "pm");
            }else if(!am1 && am2){
                horarios.setText(hora1 + "pm - " + hora2 + "am");
            }else{
                horarios.setText(hora1 + "pm - " + hora2 + "pm");
            }
        }else{
            if(am1 && am2){
                horarios.setText(hora1 + "am - " + hora2 + "am - " + hora3 + "pm");
            }else if(am1 && !am2){
                horarios.setText(hora1 + "am - " + hora2 + "pm - " + hora3 + "pm");
            }else if(!am1 && am2){
                horarios.setText(hora1 + "pm - " + hora2 + "am - " + hora3 + "pm");
            }else{
                horarios.setText(hora1 + "pm - " + hora2 + "pm - " + hora3 + "pm");
            }

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "administracion", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                ContentValues registro = new ContentValues();

                if(actividadFisica == 0){
                    registro.put("tipo", true);
                }else {
                    registro.put("tipo", false);
                }
                String nombre = name.getText().toString();
                registro.put("nombre", nombre);
                registro.put("tamaño", tamaño);
                registro.put("peso", peso);

                //enviar atributo
                if(edad!=0){
                    registro.put("atributo", edad);
                }else{
                    registro.put("atributo", actividadFisica);
                }
                registro.put("atributo", edad);
                registro.put("cantidadD", daysNumber);
                registro.put("cantidadP", porciones);

                //Enviar horas transformado a 24horas
                if(hora2 == 0){
                    if(am1){
                        registro.put("hora1", hora1);
                    }else{
                        if(hora1 == 12){
                            registro.put("hora1", hora1);
                        }else{
                            hora1 = hora1 + 12;
                            registro.put("hora1",hora1);
                        }
                    }
                }else if(hora3 == 0) {
                    //Si existe hora 2
                    if (am1) {
                        registro.put("hora1", hora1);
                    } else {
                        if (hora1 == 12) {
                            registro.put("hora1", hora1);
                        } else {
                            hora1 = hora1 + 12;
                            registro.put("hora1", hora1);
                        }
                    }
                    //
                    if (am2) {
                        registro.put("hora2", hora2);
                    } else {
                        if (hora2 == 12) {
                            registro.put("hora2", hora2);
                        } else {
                            hora1 = hora1 + 12;
                            registro.put("hora2", hora2);
                        }
                    }
                }else {
                    if (am1) {
                        registro.put("hora1", hora1);
                    } else {
                        if (hora1 == 12) {
                            registro.put("hora1", hora1);
                        } else {
                            hora1 = hora1 + 12;
                            registro.put("hora1", hora1);
                        }
                    }
                    //
                    if (am2) {
                        registro.put("hora2", hora2);
                    } else {
                        if (hora2 == 12) {
                            registro.put("hora2", hora2);
                        } else {
                            hora1 = hora1 + 12;
                            registro.put("hora2", hora2);
                        }
                    }
                    hora3 = hora3 + 12;
                    registro.put("hora3", hora3);
                }
                BaseDeDatos.insert("mascota", null, registro);
                BaseDeDatos.close();

                EditarConfiguracionFragment fragment = new EditarConfiguracionFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.DatosFinalesConfg, fragment);
                transaction.commit();
                btn.setVisibility(View.GONE);
            }
        });
    }
}
