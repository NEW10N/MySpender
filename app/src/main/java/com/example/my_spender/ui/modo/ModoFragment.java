package com.example.my_spender.ui.modo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.my_spender.R;

public class ModoFragment extends Fragment {

    private Button ModoInicio , ModoConsejo, ModoConectividad, ModoConfiguracion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modo, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ModoInicio = (Button) view.findViewById(R.id.ModoUsoInicio);
        ModoConsejo = (Button) view.findViewById(R.id.ModoUsoConsejos);
        ModoConectividad = (Button) view.findViewById(R.id.ModoUsoConectividad);
        ModoConfiguracion  = (Button) view.findViewById(R.id.ModoUsoConfiguracion);

        ModoInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Has elegido Inicio.", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_nav_modo_uso_to_modoUsoInicioFragment);
            }
        });

        ModoConsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Has elegido Consejo.", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_nav_modo_uso_to_modoUsoConsejosFragment);
            }
        });

        ModoConectividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Has elegido Conectividad.", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_nav_modo_uso_to_modoUsoConectividadFragment);
            }
        });

        ModoConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Has elegido Configuración.", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_nav_modo_uso_to_modoUsoConfiguracionFragment);
            }
        });
    }
}