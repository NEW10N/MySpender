package com.example.my_spender.ui.configuraciones;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.my_spender.AdminSQLiteOpenHelper;
import com.example.my_spender.R;
import com.example.my_spender.SistemaDifusoCachorro;

import org.w3c.dom.Text;

public class ConfiguracionesFragment extends Fragment {

    @Nullable

    Button btnComenzar, btnEditar;
    TextView text;
    RadioGroup radioGroup;
    RadioButton radioButton;
    SistemaDifusoCachorro sistemaDifusoCachorro;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "animal", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery
                ("select nombre from mascota where llave =" + 1, null );
        View root;

        if(fila.moveToFirst()){
            root = inflater.inflate(R.layout.fragment_editar_configuracion, container, false);
            btnEditar = (Button) root.findViewById(R.id.btnEditar);
            text = root.findViewById(R.id.ConfiguracionLista);
            addListenerOnButton(text);

        }else{
            root = inflater.inflate(R.layout.fragment_configuraciones, container, false);

            radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup);
            btnComenzar = (Button) root.findViewById(R.id.buttonComenzar);
            addListenerOnButton();

        }
        BaseDeDatos.close();
        fila.close();
        return root;
    }

    public void addListenerOnButton(TextView mensaje){
        btnEditar.setOnClickListener(new View.OnClickListener() {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "animal", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            @Override
            public void onClick(View v) {
                BaseDeDatos.delete("mascota", "llave ="+1, null);
                text.setText("¡Datos de la mascota eliminados! Puede regresar a Home");
                btnEditar.setVisibility(View.GONE);
            }
        });
    }

    public void addListenerOnButton(){

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get selected radio button from radioGroup
                int selectedID = radioGroup.getCheckedRadioButtonId();

                //find the radiobutton by returned id
                radioButton = (RadioButton) getActivity().findViewById(selectedID);
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(), "Elegir una opción.", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (radioButton.getText().toString().trim().equalsIgnoreCase("Cachorro")) {
                        Toast.makeText(getContext(), "Has elegido cachorro.", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(getView()).navigate(R.id.action_nav_configuracion_perfil_to_sistemaDifusoCachorro);


                    }
                    if (radioButton.getText().toString().trim().equalsIgnoreCase("Adulto")) {
                        Toast.makeText(getContext(), "Has elegido adulto.", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(getView()).navigate(R.id.action_nav_configuracion_perfil_to_sistemaDifusoAdulto);
                    }
                }
            }
        });

    }
}