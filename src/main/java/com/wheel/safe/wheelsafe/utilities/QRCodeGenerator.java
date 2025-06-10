package com.wheel.safe.wheelsafe.utilities;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleDTO;

public class QRCodeGenerator {
    private static final int DEFAULT_QR_CODE_WIDTH = 300;
    private static final int DEFAULT_QR_CODE_HEIGHT = 300;
    private static final String QR_PREFIX = "data:image/png;base64,";

    public static String generateQRCode(BicycleDTO data) throws WriterException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data.toString(), BarcodeFormat.QR_CODE, DEFAULT_QR_CODE_WIDTH,
                DEFAULT_QR_CODE_HEIGHT);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return QR_PREFIX + generateQRCodeBase64(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }

    public static String generateQRCodeBase64(byte[] data) throws WriterException {
        return Base64.getEncoder().encodeToString(data);
    }

}
