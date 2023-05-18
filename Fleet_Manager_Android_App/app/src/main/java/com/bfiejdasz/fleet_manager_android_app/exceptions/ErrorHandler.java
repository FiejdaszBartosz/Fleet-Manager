package com.bfiejdasz.fleet_manager_android_app.exceptions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bfiejdasz.fleet_manager_android_app.api.CustomHttpException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.ApiException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.BadRequestException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.ForbiddenException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.NetworkException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.NotFoundException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.ServerErrorException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.UnauthorizedException;

import java.io.IOException;

public class ErrorHandler {

    public static void handleException(Throwable t) throws
            BadRequestException,
            UnauthorizedException,
            ForbiddenException,
            NotFoundException,
            ServerErrorException,
            UnexpectedErrorException,
            NetworkException
    {
        if (t instanceof CustomHttpException) {
            int statusCode = ((CustomHttpException) t).getStatusCode();
            String errorMessage = ((CustomHttpException) t).getErrorMessage();
            switch (statusCode) {
                case 400:
                    throw new BadRequestException("Nieprawidłowe żądanie: " + errorMessage);
                case 401:
                    throw new UnauthorizedException("Nieprawidłowe dane logowania");
                case 403:
                    throw new ForbiddenException("Brak uprawnień: " + errorMessage);
                case 404:
                    throw new NotFoundException("Nie znaleziono zasobu: " + errorMessage);
                case 500:
                case 502:
                case 503:
                case 504:
                    throw new ServerErrorException("Wystąpił błąd serwera");
                default:
                    throw new UnexpectedErrorException("Nieoczekiwany błąd: " + errorMessage);
            }
        } else if (t instanceof IOException) {
            throw new NetworkException("Brak połączenia z internetem");
        } else {
            throw new UnexpectedErrorException("Nieoczekiwany błąd: " + t.getMessage());
        }
    }

    public static void logErrors(Throwable t) {
        if (t instanceof ApiException)
            Log.e("API_EXCEPTION", t.getMessage());
        else if (t instanceof UnexpectedErrorException)
            Log.e("UNEXPECTED_ERROR_EXCEPTION", t.getMessage());
        else
            Log.e("EXCEPTION", t.getMessage());
    }

    public static void logWithToastErrors(Context context, Throwable t) {
        if (t instanceof UnauthorizedException || t instanceof ServerErrorException || t instanceof NetworkException)
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
        else if (t instanceof ApiException)
            Log.e("API_EXCEPTION", t.getMessage());
        else if (t instanceof UnexpectedErrorException)
            Log.e("UNEXPECTED_ERROR_EXCEPTION", t.getMessage());
        else
            Log.e("EXCEPTION", t.getMessage());
    }
}


