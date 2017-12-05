package vn.quankundeptrai.tieqvietparser.Utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

/**
 * Created by TQN on 12/5/2017.
 */

public class MainUtils {
    public static String convertToTieqViet(String tiengViet, ArrayList<Pair<String,String>> dictionary){
        for(Pair<String,String> item : dictionary){
            tiengViet = tiengViet.replaceAll(item.first,item.second);
        }
        return tiengViet;
    }

    public static void startAnimation(Context context, View view, int anim) {
        Animation effect = AnimationUtils.loadAnimation(context, anim);
        view.startAnimation(effect);
    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public static void copy(Context context, String input){
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("tieqViet", input);
        clipboard.setPrimaryClip(clip);
    }
}
