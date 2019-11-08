package com.example.my_spender.ui.configuraciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.my_spender.R;

public class CantidadttFragment extends Fragment {

    private Button shora;
    private TextView infoCroquetas;
    private int cantidad;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            cantidad = getArguments().getInt("Croquetas");
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
                HoraFragment fragment = new HoraFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Cantidadtt, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}

