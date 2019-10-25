package com.example.my_spender.ui.conectividad;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConectividadViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConectividadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el area de conectividad");
    }

    public LiveData<String> getText() {
        return mText;
    }
}