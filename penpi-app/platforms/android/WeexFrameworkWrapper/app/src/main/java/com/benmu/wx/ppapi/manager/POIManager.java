package com.benmu.wx.ppapi.manager;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.wx.ppapi.model.POIResultBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by drugbean on 2018/4/21.
 */

public class POIManager extends Manager implements PoiSearch.OnPoiSearchListener, Inputtips.InputtipsListener {

    private static final long SEARCH_INTERVAL = 2000;

    /**
     * POI搜索
     */
    public void search(Context context, SearchParams params) {
        //POI搜索条件
        PoiSearch.Query query = new PoiSearch.Query(params.getKey(), params.getCtgr(), params.getCity());
        PoiSearch mSearch = new PoiSearch(context, query);
        //设置异步监听
        mSearch.setOnPoiSearchListener(this);
        //查询POI异步接口
        mSearch.searchPOIAsyn();
    }

    /**
     * 输入内容自动提示
     */
    public void searchInputtips(Context context, SearchParams params) {
        //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
        InputtipsQuery inputquery = new InputtipsQuery(params.getKey(), params.getCity());
        //限制在当前城市
        inputquery.setCityLimit(true);

        Inputtips inputTips = new Inputtips(context, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int code) {
        POIResultBean result = new POIResultBean();
        if (code == 1000 && poiResult != null && poiResult.getPois().size() > 0) {
            Set<POIResultBean.POIResult> list = new HashSet<>();
            for (PoiItem poiItem : poiResult.getPois()) {
                POIResultBean.POIResult bean = result.new POIResult();
                //获取经纬度对象
                LatLonPoint llp = poiItem.getLatLonPoint();
                bean.setLng(llp.getLongitude());
                bean.setLat(llp.getLatitude());
                //获取标题
                bean.setTitle(poiItem.getTitle());
                //获取内容
                bean.setSnippet(poiItem.getSnippet());
                list.add(bean);
            }
            result.setData(list);
            result.resCode = 0;
        } else {
            //locate failed
            result.msg = "搜索失败";
            result.resCode = 9;
        }
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(result);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int code) {

    }

    @Override
    public void onGetInputtips(List<Tip> list, int code) {
        POIResultBean result = new POIResultBean();
        if (code == 1000 && list != null && list.size() > 0) {
            Set<POIResultBean.POIResult> set = new HashSet<>();
            for (Tip tip : list) {
                POIResultBean.POIResult bean = result.new POIResult();
                //获取经纬度对象
                LatLonPoint llp = tip.getPoint();
                bean.setLng(llp.getLongitude());
                bean.setLat(llp.getLatitude());
                //获取标题
                bean.setTitle(tip.getName());
                //获取内容
                bean.setSnippet(tip.getAddress());
                set.add(bean);
            }
            result.setData(set);
            result.resCode = 0;
        } else {
            //locate failed
            result.msg = "搜索失败";
            result.resCode = 9;
        }
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(result);
    }

    /**
     * key  - 关键字
     * ctgr - POI 类型的组合，比如定义如下组合：餐馆|电影院|景点
     * city - 待查询城市（地区）的城市编码 citycode、城市名称（中文或中文全拼）、行政区划代码adcode
     */
    public static class SearchParams {
        private String key;
        private String ctgr;
        private String city;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getCtgr() {
            return ctgr;
        }

        public void setCtgr(String ctgr) {
            this.ctgr = ctgr;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
