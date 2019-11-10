package com.example.my_spender.ui.conectividad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_spender.R;
import com.example.my_spender.home;

public class ConectividadEstadoFragment extends Fragment {

    private ProgressBar miProgress;
    Button btncon;
    //Estado fragment = new HoraFragment();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_conectividadestado, container, false);

        return root;
    }
}