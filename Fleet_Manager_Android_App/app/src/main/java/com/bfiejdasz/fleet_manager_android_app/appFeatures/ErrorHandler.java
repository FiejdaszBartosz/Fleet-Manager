package com.bfiejdasz.fleet_manager_android_app.appFeatures;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bfiejdasz.fleet_manager_android_app.api.CustomHttpException;

import java.io.IOException;

public class ErrorHandler {

    public static void handleException(Context context, Throwable t) {
        if (t instanceof CustomHttpException) {
            int statusCode = ((CustomHttpException) t).getStatusCode();
            String errorMessage = ((CustomHttpException) t).getErrorMessage();
            switch (statusCode) {
                case 400:
                    Log.e("TAG", "Nieprawidłowe żądanie: " + errorMessage);
                    break;
                case 401:
                    Toast.makeText(context, "Nieprawidłowe dane logowania", Toast.LENGTH_SHORT).show();
                    break;
                case 403:
                    Log.e("TAG", "Brak uprawnień: " + errorMessage);
                    break;
                case 404:
                    Log.e("TAG", "Nie znaleziono zasobu: " + errorMessage);
                    break;
                case 500:
                case 502:
                case 503:
                case 504:
                    Toast.makeText(context, "Wystąpił błąd serwera", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Log.e("TAG", "Nieoczekiwany błąd: " + errorMessage);
                    break;
            }
        } else if (t instanceof IOException) {
            Toast.makeText(context, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("TAG", "Nieoczekiwany błąd: " + t.getMessage());
        }
    }

}

