package com.massky.greenlandvland.util;

import android.content.Context;
import android.content.Intent;

import com.massky.greenlandvland.R;

import androidx.annotation.NonNull;


/**
 * Created by chenxz on 2017/12/20.
 */

public class ShareUtil {

    public static void send(@NonNull Context context, @NonNull String shareText) {
        Intent shareIntent = new Intent()
                .setAction(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, shareText);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.action_share)));
    }

}
