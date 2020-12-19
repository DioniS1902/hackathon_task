package com.maxrt.petnet.ui.qr_code_scan;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.util.concurrent.ListenableFuture;
import com.maxrt.petnet.MainActivity;
import com.maxrt.petnet.R;

public class QRCodeScanFragment extends Fragment {

    private QRCodeScanViewModel QRCodeScanViewModel;

    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        QRCodeScanViewModel = new ViewModelProvider(this).get(QRCodeScanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qr_core_scan, container, false);

        previewView = root.findViewById(R.id.camera_previewView);
        cameraProviderFuture = ProcessCameraProvider.getInstance(getActivity());

        ((MainActivity)getActivity()).requestCamera();

        return root;
    }

    /*@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
    }*/
}