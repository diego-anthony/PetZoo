package com.petzoo.petzoo.helpers;

import android.content.Context;
import android.widget.Toast;

public class Msg {
    public static void Show(Context ctx, String message)
    {
        Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();
    }
}
