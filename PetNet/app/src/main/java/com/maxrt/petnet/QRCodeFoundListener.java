package com.maxrt.petnet;

public interface QRCodeFoundListener {
    void onQRCodeFound(String qrCode);
    void onQRCodeNotFound();
}
