package de.home24.home24task.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtil {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    //changing the format of the date
    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String receivedDate) {
        String formattedDate = "";
        Date date = null;

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");
        try {
            date = input.parse(receivedDate);
            formattedDate = output.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String day = outFormat.format(date);

        return formattedDate + "\n" + day;
    }
}
