package com.dangjian.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片进行压缩
 * Created by sg-pc on 2016/10/17.
 */
public class ImageUtil {
    public static String zipImage(String path) {
        Bitmap bitmap;
        double SCALE;
        File f = new File(path);
        if (!f.exists()) {
            return "";
        }
        // 将保存在本地的图片取出并缩小后显示在界面上
        bitmap = BitmapFactory.decodeFile(path);
        if (bitmap.getHeight() > 500) {
            SCALE = bitmap.getHeight() / 500.00;
        } else {
            SCALE = 1;
        }
        int tmpWidth = new Double(bitmap.getWidth() / SCALE).intValue();
        int tmpHeight = new Double(bitmap.getHeight() / SCALE).intValue();
        Bitmap newBitmap = zoomBitmap(bitmap, tmpWidth, tmpHeight);
        saveMyBitmap(newBitmap, path + "_zip.jpg");
        return path + "_zip.jpg";
    }

    private static void saveMyBitmap(Bitmap mBitmap, String newPath) {
        File f = new File(newPath);
        FileOutputStream fOut = null;
        try {
            f.createNewFile();
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }
}
