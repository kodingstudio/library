package com.haris.ecommerce.presentationutil.ui.customutil;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.haris.ecommerce.R;


@GlideModule
public class MyGlideApp extends AppGlideModule {


    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);

        builder.setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_logo));

    }


}
