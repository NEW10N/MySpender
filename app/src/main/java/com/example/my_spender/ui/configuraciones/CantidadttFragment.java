package com.example.my_spender.ui.configuraciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.my_spender.R;

public class CantidadttFragment extends Fragment {

    private Button shora;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_cantidadantes, container, false);
        shora = (Button) view.findViewById(R.id.btnshora);

        shora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Going to Fragment")
                Navigation.findNavController(getView()).navigate(R.id.action_cantidadttFragment_to_conHora);
            }
        });
        return view;
    }
}
