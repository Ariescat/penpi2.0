package com.benmu.wx.ppapi;

import com.benmu.wx.ppapi.module.POIMoudule;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * Created by drugbean on 2018/4/21.
 */

public class PenpiWXEngine {

    public static void initialize() {
        registerModules();
        initPOI();
    }

    private static void registerModules() {
        try {
            WXSDKEngine.registerModule("POISearch", POIMoudule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }

    private static void initPOI() {

    }
}
