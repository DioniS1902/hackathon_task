package com.maxrt.petnet.ui.qr_code_scan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.maxrt.petnet.R;

public class QRCodeScanFragment extends Fragment {

    private QRCodeScanViewModel QRCodeScanViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        QRCodeScanViewModel = new ViewModelProvider(this).get(QRCodeScanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qr_core_scan, container, false);
        return root;
    }
}