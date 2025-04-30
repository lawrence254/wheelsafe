package com.wheel.safe.wheelsafe.bicycle.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class QRCodeService {
    private static final int DEFAULT_QR_CODE_WIDTH = 300;
    private static final int DEFAULT_QR_CODE_HEIGHT = 300;
    public byte[] generateQrCode(
            BicycleDTO bicycleInfoDTO) throws WriterException, IOException {
        String bicycleInfo = bicycleInfoDTO.toString();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(bicycleInfo, BarcodeFormat.QR_CODE, DEFAULT_QR_CODE_WIDTH, DEFAULT_QR_CODE_HEIGHT);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public void generateQrCodeToFile(BicycleDTO bicycleInfoDTO, String filePath) throws WriterException, IOException {
        String bicycleInfo = bicycleInfoDTO.toString();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(bicycleInfo, BarcodeFormat.QR_CODE, DEFAULT_QR_CODE_WIDTH, DEFAULT_QR_CODE_HEIGHT);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
