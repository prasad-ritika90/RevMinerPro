package com.undecided.proj;

import com.phonegap.*;
import android.os.Bundle;

public class UndecidedActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //KeyBoard keyboard = new KeyBoard(this, appView);
        //appView.addJavascriptInterface(keyboard, "KeyBoard");
        super.loadUrl("file:///android_asset/www/main.html");
    }
}