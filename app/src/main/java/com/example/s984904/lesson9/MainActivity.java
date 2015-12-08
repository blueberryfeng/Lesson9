package com.example.s984904.lesson9;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {

    //    int color = Color.GREEN;
    int[] colors = {Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN,
            Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW};
    Bitmap bitmap, bitmap1;
    ImageView imageView, imageView1;
    TextView textView;
    int index1, index2;
    boolean touched1, touched2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.match);
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.colors);
        imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener(this);


        imageView1 = new ImageView(this);
        imageView1.setImageBitmap(bitmap1);
        imageView1.setOnTouchListener(this);

        index1 = (int) (Math.random() * (colors.length - 1));
        index2 = (int) (Math.random() * (colors.length - 1));

        textView = new TextView(this);
        textView.setText("You win");
        textView.setTextColor(0);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100);

        touched1 = false;
        touched2 = false;

        layout.addView(imageView);
        layout.addView(imageView1);
        layout.addView(textView);
        setContentView(layout);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Bitmap temp = null;
        int index = 0;
        if (v == imageView) {
            temp = bitmap;
            index = index1;
            index1 = ++index1 % colors.length;
            touched1 = true;

        } else if (v == imageView1) {
            temp = bitmap1;
            index = index2;
            index2 = ++index2 % colors.length;
            touched2 = true;
        }
        if (temp != null) {
            int bitmap_w = temp.getWidth();
            int bitmap_h = temp.getHeight();
            int[] arrayColor = new int[bitmap_w * bitmap_h];
            int count = 0;

            for (int i = 0; i < bitmap_h; i++) {
                for (int j = 0; j < bitmap_w; j++) {
                    int color1 = temp.getPixel(j, i);
                    if (color1 != 0) {
                        color1 = colors[index];
                    } else {
                    }
                    arrayColor[count] = color1;
                    count++;
                }
            }

            temp = Bitmap.createBitmap(arrayColor, bitmap_w, bitmap_h, Bitmap.Config.ARGB_4444);
            ((ImageView) v).setImageBitmap(temp);
            win();
        }
        return false;
    }

    private void win() {
        if (index2 == index1 && touched1 && touched2) {
            textView.setTextColor(0xFFA59263);
        } else {
            textView.setTextColor(0);
        }
    }
}
