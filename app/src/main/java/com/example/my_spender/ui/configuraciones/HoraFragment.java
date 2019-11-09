package com.example.my_spender.ui.configuraciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_spender.R;

public class HoraFragment extends Fragment {
    private Button ctd;
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_confhora, container, false);

        ctd = (Button) view.findViewById(R.id.btnscf);

        ctd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Going to Fragment")
                Toast.makeText(getContext(), "Boton 2", Toast.LENGTH_SHORT).show();
                //HoraFragment fragment = new HoraFragment();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ConfHora, fragment).addToBackStack(null).commit();
            }
        });


        return view;
    }
}
