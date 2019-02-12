package com.ratulbintazul.www.rkeyboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton keyboardBgImage;
    Bitmap selectedImage;
    SharedPreferences imgSharedPreferences;
    private static int IMG_PICKER_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        selectedImage = decodeBase64(getPreferences(Context.MODE_PRIVATE).getString("selectedImg",null));

        keyboardBgImage = (ImageButton)findViewById(R.id.imgPicker);
        if(selectedImage!=null)
            keyboardBgImage.setImageBitmap(selectedImage);

        keyboardBgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, IMG_PICKER_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==IMG_PICKER_CODE) {
            if(resultCode==RESULT_OK) {

                try {
                    Uri imageUri = data.getData();
                    InputStream imageStream = null;
                    imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);

                    keyboardBgImage.setImageBitmap(selectedImage);
                    imgSharedPreferences = getSharedPreferences("imgSharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = imgSharedPreferences.edit();
                    editor.putString("selectedImg", encodeTobase64(selectedImage));
                    editor.apply();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input) {
        if(input==null)
            return null;
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
