package com.ratulbintazul.www.rkeyboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import java.util.List;

/**
 * Created by SAMSUNG on 9/7/2017.
 */

public class CustomKeyboardView extends KeyboardView {

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {

//                Log.e("KEY", "Drawing key with code " + key.codes[0]);
//                Drawable dr = (Drawable) getResources().getDrawable(R.drawable.btn_keyboard_key_fulltrans_normal);
//                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
//                dr.draw(canvas);

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.btn_default_transparent_normal);
            ShadowImage image = new ShadowImage(getResources(), bmp);
            ShadowImage image2 = new ShadowImage(getResources(), bmp, Color.WHITE);


            NinePatchDrawable npd
                    = (NinePatchDrawable) getResources().getDrawable(R.drawable.btn_default_transparent_normal);
            npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
            npd.draw(canvas);


//            Paint paint = new Paint();
//            paint.setTextAlign(Paint.Align.CENTER);
//            paint.setTextSize(48);
//            paint.setColor(Color.GRAY);
//
//            if (key.label != null) {
//                canvas.drawText(key.label.toString(), key.x + (key.width / 2),
//                        key.y + (key.height / 2), paint);
//            } else {
//                key.icon.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
//                key.icon.draw(canvas);
//            }

        }
    }

    public void showWithAnimation(Animation animation) {
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.VISIBLE);
            }
        });

        setAnimation(animation);
    }

}