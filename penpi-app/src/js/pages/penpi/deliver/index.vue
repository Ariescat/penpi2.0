<template>
    <div class="container">
        <wxc-minibar title="互助快递池"
                     background-color="#1da1f2"
                     text-color="#FFFFFF"
                     right-text="发单"
                     left-button=""
                     left-text=""
                     @wxcMinibarRightButtonClicked="showSendOrderView"></wxc-minibar>
        <div class="function">
            <div class="function ml25" @click="showMultipleSort">
                <text class="text">{{config.orderNm}}</text>
                <text class="icon">&#xe62b;</text>
            </div>
            <text class="text font-size28 ml25" :class="[config.isOrderByTime ? 'weight-bold' : '']" @click="orderListByTime(true)">时间</text>
            <text class="text font-size28 ml25" :class="[!config.isOrderByTime ? 'weight-bold' : '']" @click="orderListByTime(false)">距离</text>
            <div class="function" style="margin-left: 270px" @click="showOrderFilterView">
                <text class="icon">&#xe649;</text>
                <text class="text">过滤</text>
            </div>
        </div>
        <div v-if="config.isOverLoadData" class="home" :style="contentStyle">
            <list ref="list" :show-scrollbar="true" :showRefresh="true" @refresh="fetch" v-on:swipe="onSwipe($event)">
                <cell v-for="(item, index) in orders">
                    <deliver-item :index="index" :item="item" :latlng="params" @grab="grabOrder"></deliver-item>
                </cell>
            </list>
        </div>
        <wxc-loading :show="!config.isOverLoadData" type="trip" need-mask="true"></wxc-loading>
        <wxc-popup :show="config.isShowSendOrderView"
                   pos="top"
                   height="900"
                   @wxcPopupOverlayClicked="closeSendOrderView">
            <send-order @sendOrder="sendOrder"></send-order>
        </wxc-popup>
        <wxc-popup :show="config.isShowOrderFilterView"
                   pos="top"
                   height="270"
                   @wxcPopupOverlayClicked="closeOrderFilterView">
            <order-filter @filter="orderFilter"></order-filter>
        </wxc-popup>
        <wxc-popover ref="wxc-popover"
                    :buttons="config.popoverConfig.btns"
                    :position="config.popoverConfig.popoverPosition"
                    :arrowPosition="config.popoverConfig.popoverArrowPosition"
                    @wxcPopoverButtonClicked="multipleSort"></wxc-popover>
    </div>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config');
import {Global} from '../../../utils/global'
import WxcPopoverConfig from './popover'
import {WxcMinibar, WxcLoading, WxcPopover} from 'PenpiComponents'
import {WxcButton, WxcPopup} from 'weex-ui';
import DeliverItem from './components/deliverItem'
import OrderFilter from './components/orderFilter'
import SendOrder from './sendOrder/index'

export default {
    components: { WxcButton, WxcMinibar, WxcLoading, WxcPopover, WxcPopup,
        DeliverItem, SendOrder, OrderFilter },
    data () {
        return {
            config: {
                isOverLoadData: false,
                isShowSendOrderView: false,
                isShowOrderFilterView: false,
                isOrderByTime: true,
                orderNm: '综合排序',
                popoverConfig: {
                    btns: WxcPopoverConfig.btns,
                    popoverPosition: WxcPopoverConfig.popoverPosition,
                    popoverArrowPosition: WxcPopoverConfig.popoverArrowPosition,
                }
            },
            params: {
                locationLng: Global.DEFAULT_LNG,
                locationLat: Global.DEFAULT_LAT,
                // 排序类型
                orderType: null,
                // 过滤
                userSexCode: null,
                fromTime: null,
                toTime: null,
                distance: null
            },
            orders: [],
        };
    },
    beforeCreate: function () {

    },
    created () {
        const realDeviceHeight = weex.config.eros.realDeviceHeight
        const statusBarHeight = weex.config.eros.statusBarHeight
        // 减 状态栏 底部导航栏 顶部导航栏 功能区
        this.contentStyle = { height: (realDeviceHeight - statusBarHeight - 120 - 90 - 70) + 'px' };

        // this.fetch();
        this.init();
    },
    mounted: function () {
        this.$nextTick(function () {
            this.$event.on(Global.EVENT_CHOOSE_TAB_1, params => {
                // this.fetch();
                this.init();
            })
        })
    },
    methods: {
        init () {
            // --test
            // this.$fetch({
            //     method: 'GET',
            //     name: 'test.clear'
            // })
            // --test
            this.timeout();
            this.$notice.toast({ message: '获取定位中...' })
            this.$geo.get().then(data => {
                this.$notice.toast({ message: '定位获取成功' })
                this.params.locationLng = data.locationLng;
                this.params.locationLat = data.locationLat;
                this.fetch();
            }, error => {
                this.$notice.toast({ message: '定位获取失败' })
            })
        },
        fetch() {
            this.$fetch({
                method: 'POST',
                name: 'getOrdersByParams',
                data: this.params
            }).then(resData => {
                this.orders = resData;
                this.config.isOverLoadData = true;
                this.$refs['list'].refreshEnd()
            }, error => {
                this.$notice.toast({ message: error})
            })
        },
        timeout() {
            setTimeout(() => {
                this.config.isOverLoadData = true;
            }, 3000)
        },

        onSwipe: function(event) {
            if (event.direction) {
                this.$event.emit(Global.EVENT_SLIDER_CHOOSE_TAB, event.direction)
            }
        },

        /**
         * 订单排序
         */
        orderListByTime(isOrderByTime) {
            this.config.isOrderByTime = isOrderByTime;
            this.params.orderType = isOrderByTime ? 'createDate' : 'distance';
            this.orders.sort(function (a, b) {
                return isOrderByTime ? (b.createDate - a.createDate) : (a.distance - b.distance);
            })
        },
        showMultipleSort() {
            this.$refs['wxc-popover'].wxcPopoverShow();
        },
        multipleSort(e) {
            this.config.orderNm = e.key.text;
            this.orders.sort(function (a, b) {
                switch (e.index) {
                    case 0:
                        return b.createDate - a.createDate;
                    case 1:
                        return b.userSexCode - a.userSexCode;
                    case 2:
                        return b.orderFee - a.orderFee;
                    case 3:
                        return a.orderFee - b.orderFee;
                }
            })
        },

        /**
         * 订单过滤
         */
        showOrderFilterView () {
            this.config.isShowOrderFilterView = true;
        },
        closeOrderFilterView() {
            this.$tools.resignKeyboard(); //收起键盘
            this.config.isShowOrderFilterView = false;
        },
        orderFilter(e) {
            if (e.userSexCode != undefined) {
                this.params.userSexCode = (e.userSexCode == '2') ? null : e.userSexCode;
            }
            if (e.distance != undefined) {
                this.params.distance = (e.distance == 0) ? null : e.distance;
            }
            this.fetch();
        },

        /**
         * 发布订单
         */
        showSendOrderView () {
            this.config.isShowSendOrderView = true;
            // this.$router.open({
            //     name: 'sendOrder',
            //     type: 'PUSH'
            // })
        },
        closeSendOrderView () {
            this.$tools.resignKeyboard(); //收起键盘
            this.config.isShowSendOrderView = false;
        },
        sendOrder(order) {
            this.$fetch({
                method: 'POST',
                name: 'sendOrder',
                data: order
            }).then(resData => {
                this.closeSendOrderView();
                this.init();
                this.$notice.toast({ message: '发布成功 (ฅ´ω`ฅ)' })
            }, error => {
                this.$notice.toast({ message: '发布失败 (ฅ´ω`ฅ)' })
            })
        },

        grabOrder(e) {
            let type = e.type;
            if (type === Global.Success) {
                this.orders.splice(e.index, 1);
                this.$notice.toast({message: `抢单成功~ 订单编号为${e.data.orderId}`})
            } else if (type === Global.Failure) {
                this.$notice.toast({message: `已抢过该订单啦~`})
            }
        }

    }
}
</script>

<style lang="sass" src='./index.scss'></style>
