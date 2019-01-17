package com.benmu.wx.ppapi.model;

import com.benmu.framework.model.BaseResultBean;

import java.util.List;
import java.util.Set;

/**
 * Created by drugbean on 2018/4/21.
 */

public class POIResultBean extends BaseResultBean {

    private Set<POIResult> data;

    public Set<POIResult> getData() {
        return data;
    }

    public void setData(Set<POIResult> data) {
        this.data = data;
    }

    public class POIResult {
        double lng; // 经度
        double lat; // 维度
        String title; // 标题
        String snippet; // 内容

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            POIResult poiResult = (POIResult) o;

            return title != null ? title.equals(poiResult.title) : poiResult.title == null;
        }

        @Override
        public int hashCode() {
            return title != null ? title.hashCode() : 0;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSnippet() {
            return snippet;
        }

        public void setSnippet(String snippet) {
            this.snippet = snippet;
        }
    }

}
