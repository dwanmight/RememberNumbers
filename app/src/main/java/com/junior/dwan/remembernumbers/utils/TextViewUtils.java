package com.junior.dwan.remembernumbers.utils;

import android.support.annotation.Nullable;
import android.widget.TextView;

public class TextViewUtils {

    public static Boolean isEmpty(@Nullable TextView tv) {
        return tv == null ||
                tv.getText().toString().trim().length() == 0;
    }

    public static Boolean isNotEmpty(@Nullable TextView tv) {
        return !isEmpty(tv);
    }

    public static String getText(@Nullable TextView tv) {
        if (tv == null) return "";
        return tv.getText().toString().trim();
    }

}
