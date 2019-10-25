package com.example.my_spender.ui.consejos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsejosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsejosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esta es la página de consejos.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}