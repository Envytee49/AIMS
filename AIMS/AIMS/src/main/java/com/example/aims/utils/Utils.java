package com.example.aims.utils;

import com.example.aims.exception.RuntimeException;
import javafx.scene.control.Alert;
import org.apache.commons.validator.routines.EmailValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Utils {
    public static void processUserInfo(String name,
                                       String email,
                                       String address,
                                       String phone) throws RuntimeException {
        if ((name == null || name.trim().isEmpty()) ||
                (address == null || address.trim().isEmpty()) ||
                (phone == null || phone.trim().isEmpty())) {
            throw new RuntimeException("You need to input all information");
        }

        if (!phone.matches("0\\d{9}")) {
            throw new RuntimeException("You need to enter phone number with 10 digits, start with 0");
        }

        if ( !EmailValidator.getInstance().isValid(email)) {
            throw new RuntimeException("Your email must follow example@demo.com");
        }

    }
    public static void processUserInfo(String name,
                                       String email,
                                       String address,
                                       String phone,
                                       String username,
                                       String password) throws RuntimeException {
        processUserInfo(name, email, address, phone);
        if ((username.isBlank() || password.isBlank())) {
            throw new RuntimeException("You need to input all information");
        }
    }
    public static String convertPaymentTimeFormat(String input) {
        try {
            // Định dạng của đầu vào
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            // Định dạng của đầu ra
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            // Chuyển đổi từ chuỗi sang đối tượng Date
            Date date = inputFormat.parse(input);

            // Chuyển đổi từ Date sang chuỗi theo định dạng mong muốn
            return outputFormat.format(date);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi khi chuyển đổi
            System.err.println("Error converting date: " + e.getMessage());
            return null;
        }
    }
    public static Map<String, String> parseQueryString(String query) {

        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    public static void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
