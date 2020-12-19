package com.maxrt.petnet.ui.qr_code_scan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QRCodeScanViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QRCodeScanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}