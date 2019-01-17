package com.benmu.wx;

import android.app.Application;

import com.benmu.framework.BMWXApplication;
import com.benmu.wx.ppapi.PenpiWXEngine;

/**
 * Created by Carry on 2017/8/23.
 */

public class App extends BMWXApplication {

    public Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        PenpiWXEngine.initialize();
    }

}
