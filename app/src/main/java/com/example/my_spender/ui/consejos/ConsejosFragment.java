package com.example.my_spender.ui.consejos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.my_spender.R;

public class ConsejosFragment extends Fragment {

    private ConsejosViewModel consejosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consejosViewModel =
                ViewModelProviders.of(this).get(ConsejosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_consejos, container, false);

        final TextView textView = root.findViewById(R.id.consejos);
        consejosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}