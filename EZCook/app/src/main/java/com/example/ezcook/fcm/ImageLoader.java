package com.example.ezcook.fcm;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageLoader {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static void loadImage(Context context, ImageView imageView, String imagePath) {
        Uri uri = Uri.parse(imagePath);

        try {
            ContentResolver resolver = context.getContentResolver();
            ParcelFileDescriptor parcelFileDescriptor = resolver.openFileDescriptor(uri, "r");
            if (parcelFileDescriptor != null) {
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromFileDescriptor(fileDescriptor);

                Glide.with(context)
                        .load(bitmap)
                        .into(imageView);
//                Picasso.get().lo(bitmap).into(imageView);
                parcelFileDescriptor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

