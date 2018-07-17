package com.petzoo.petzoo.helpers;

import android.content.Context;
import android.graphics.Bitmap;

import com.petzoo.petzoo.constants.ImageConstants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageHelper {
    public static File bitMapToFile(Context context, Bitmap imageBitmap) throws IOException {
        //create a file to write bitmap data
        File file = new File(context.getCacheDir(), "Image");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, ImageConstants.MEDIUM_QUALITY_IMAGE /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
        return file;
    }
}
