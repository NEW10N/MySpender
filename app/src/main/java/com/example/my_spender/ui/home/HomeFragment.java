package com.example.my_spender.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.my_spender.AdminSQLiteOpenHelper;
import com.example.my_spender.R;
public class HomeFragment extends Fragment {

    private TextView infoNombre, infoTamaño, infoPesoIdeal, infoAdditional, infoCantidadCroquetas, infoCantidadVeces, infoCantidadPorciones,horarios;
    private int cantidad, tamaño, peso, edad, actividadFisica, veces, hora1, hora2, hora3;

    private Button btnconfig;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "animal", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery
                ("select tipo, nombre, tamaño, peso, atributo from mascota where llave =" + 1, null );
        View root;

        if(fila.moveToFirst()){
            if(fila.getInt(0) == 0){
                root = inflater.inflate(R.layout.fragment_perfiladulto, container, false);
                Toast.makeText(getContext(), "Entre 1", Toast.LENGTH_SHORT);
                infoNombre = root.findViewById(R.id.textNameInformationAdulto);
                infoTamaño = root.findViewById(R.id.textSizeInfomationAdulto);
                infoPesoIdeal = root.findViewById(R.id.textWeightInfomationAdulto);
                infoAdditional = root.findViewById(R.id.textAdditionalInfomationAdulto);
                infoNombre.setText(fila.getString(1));
                infoTamaño.setText("Tamaño: " + fila.getInt(2) + " cm");
                infoPesoIdeal.setText("Peso ideal: " + fila.getInt(3) + " kg");
                String actividad;
                actividadFisica = fila.getInt(4);
                if (actividadFisica <= 3){
                    actividad = "Baja";
                }else if (actividadFisica <= 6){
                    actividad = "Normal";
                }else {
                    actividad = "Alta";
                }
                infoAdditional.setText("Actividad fisica: " + actividad);
            }else{
                root = inflater.inflate(R.layout.fragment_perfilcachorro, container, false);
                infoNombre = root.findViewById(R.id.textNameInformationCachorro);
                infoTamaño = root.findViewById(R.id.textSizeInfomationCachorro);
                infoPesoIdeal = root.findViewById(R.id.textWeightInfomationCachorro);
                infoAdditional = root.findViewById(R.id.textAdditionalInfomationCachorro);
                infoNombre.setText(fila.getString(1));
                infoTamaño.setText("Tamaño: " + fila.getInt(2) + " cm");
                infoPesoIdeal.setText("Peso ideal: " + fila.getInt(3) + " kg");
                infoAdditional.setText("Edad: " + fila.getInt(4) + " meses");
            }
            BaseDeDatos.close();
        }else{
            root = inflater.inflate(R.layout.fragment_perfilvacio, container, false);
            Toast.makeText(getContext(), "Entre 2", Toast.LENGTH_SHORT);
            BaseDeDatos.close();
        }
        fila.close();

        return root;
    }

}