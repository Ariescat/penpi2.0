package com.benmu.wx.ppapi.module;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.utils.JsPoster;
import com.benmu.wx.ppapi.manager.POIManager;
import com.benmu.wx.ppapi.model.POIResultBean;
import com.squareup.otto.Subscribe;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * Created by drugbean on 2018/4/21.
 */

public class POIMoudule extends WXModule {

    private JSCallback mCallback;
    private POIManager poiManager;

    @JSMethod(uiThread = true)
    public void getPOIList(POIManager.SearchParams params, JSCallback callback) {
        mCallback = callback;
        poiManager = ManagerFactory.getManagerService(POIManager.class);
        poiManager.search(mWXSDKInstance.getContext(), params);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }

    @Subscribe
    public void onSearch(POIResultBean bean) {
        if (bean.resCode == 0) {
            //搜索成功
            JsPoster.postSuccess(bean, mCallback);
        } else {
            //搜索失败
            JsPoster.postFailed(mCallback);
        }
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
    }
}
