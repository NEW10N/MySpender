package com.example.my_spender.ui.modo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ModoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Apartado de modo de uso");
    }

    public LiveData<String> getText() {
        return mText;
    }
}