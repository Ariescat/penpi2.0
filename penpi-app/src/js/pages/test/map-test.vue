<template>
    <scroller>
        <weex-amap class="box"  :compass="compass" :geolocation="geolocation">
            <weex-amap-marker title="111" :position="position1"></weex-amap-marker>
            <weex-amap-marker title="222" :position="position2"></weex-amap-marker>
            <weex-amap-polyline :path="path" strokeWidth="10" strokeColor="#666666"></weex-amap-polyline>
        </weex-amap>

        <!--<weex-amap-marker></weex-amap-marker>-->
        <!--<weex-amap-info-window></weex-amap-info-window>-->
        <!--<weex-amap-circle></weex-amap-circle>-->
        <!--<weex-amap-polyline></weex-amap-polyline>-->
        <!--<weex-amap-polygon></weex-amap-polygon>-->
    </scroller>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    export default {
        data () {
            return {
                //AMap
                sdkKey: {android:'7330cea212832913b887f4a71c3294e0'},
                //设置地图当前定位
                position1: [113.36967,23.132384],
                position2: [113.37367,23.133384],
                //返回地图是否显示指南针，默认false
                compass: true,
                geolocation: true,

                //Marker
                path: [[113.36967,23.132384],[113.37367,23.133384]],
            };
        },
        methods: {

        }
    }
</script>

<style scoped>
    .box {
        width: 750px;
        height: 1000px;
    }
</style>
