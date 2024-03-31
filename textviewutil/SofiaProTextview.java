package com.haris.ecommerce.presentationutil.ui.textviewutil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.haris.ecommerce.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class SofiaProTextview extends AppCompatTextView {

    public SofiaProTextview(@NonNull Context context) {
        super(context);
        init(context, null, android.R.attr.textViewStyle);
    }

    public SofiaProTextview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, android.R.attr.textViewStyle);
    }

    public SofiaProTextview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void init(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.SofiaProTextview, defStyleAttr, 0);

        int fontOrdinal = typedArray.getInt(R.styleable.SofiaProTextview_sofiaProFontFamily, 0);
        ///int trimmingLength = typedArray.getInt(R.styleable.SofiaProTextview_trimLength, 0);
        typedArray.recycle();

        setFont(context, fontOrdinal);


    }

    public void setFont(Context context, int fontOrdinal) {

        Typeface font;
        switch (fontOrdinal) {
            case 1:
                font = getFontTypeface(context,"SofiaProUltraLightAz.otf");
                break;

            case 2:
                font = getFontTypeface(context,"SofiaProExtraLightAz.otf");
                break;

            case 3:
                font = getFontTypeface(context,"SofiaProLightAz.otf");
                break;

            case 4:
                font = getFontTypeface(context,"SofiaProRegularAz.otf");
                break;

            case 5:
                font = getFontTypeface(context,"SofiaProMediumAz.otf");
                break;

            case 6:
                font = getFontTypeface(context,"SofiaProBoldAz.otf");
                break;

            case 7:
                font = getFontTypeface(context,"SofiaProSemiBoldAz.otf");
                break;

            case 8:
                font = getFontTypeface(context,"SofiaProBlackAz.otf");
                break;

            default:
                font = null;
                break;

        }

        if (font!=null){
            setTypeface(font);
        }


    }

    public Typeface getFontTypeface(Context context, String fontName){
        String fontBasePath = "fonts/sofiaprofonts";
        return Typeface.createFromAsset(context.getResources().getAssets(), fontBasePath+"/"+fontName);
    }

}
