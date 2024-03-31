package com.haris.arbnb.edittextutil.sofiaproutil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.haris.arbnb.R;
import com.haris.arbnb.fontutil.Font;

public class SofiaProEdittext extends androidx.appcompat.widget.AppCompatEditText {
    public SofiaProEdittext(Context context) {
        super(context);
        init(context,null,android.R.attr.textViewStyle);
    }

    public SofiaProEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,android.R.attr.textViewStyle);
    }

    public SofiaProEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // Load attributes
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SofiaProEdittext, defStyleAttr, 0);
        int fontOrdinal = a.getInt(R.styleable.SofiaProEdittext_sofiaProFontFamily, 0);
        a.recycle();

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

