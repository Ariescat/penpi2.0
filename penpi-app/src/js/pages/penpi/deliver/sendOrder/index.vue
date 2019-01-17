<template>
    <div class="container">
        <pp-status-bar></pp-status-bar>
        <wxc-loading :show="isSending" type="trip" need-mask="true"></wxc-loading>
        <weex-amap class="map">
            <weex-amap-marker position="[113.36967,23.132384]"></weex-amap-marker>
            <weex-amap-marker position="[113.37367,23.133384]"></weex-amap-marker>
            <weex-amap-marker position="[113.37167,23.131384]"></weex-amap-marker>
        </weex-amap>
        <div class="item" @click="chooseAddress('start')">
            <text class="label">起始点：</text>
            <text class="input placeholder">{{order.startPlace}}</text>
        </div>
        <div class="item" @click="chooseAddress('end')">
            <text class="label">派送点：</text>
            <text class="input placeholder">{{order.endPlace}}</text>
        </div>
        <div class="item mb10">
            <text class="label">费用（元）：</text>
            <input class="input" type="number" maxlength="2" placeholder="给派单者打赏点小费吧(●'◡'●)" v-model="order.orderFee"/>
        </div>
        <div class="item">
            <div class="ensure" @click="sendOrder()">
                <text class="ensure-text">确定发布</text>
            </div>
        </div>
    </div>
</template>

<script>
    import {PpStatusBar, WxcLoading} from 'PenpiComponents'
    import {Global} from '../../../../utils/global'
    import {getMemberInfo} from "../../../../utils/utils";

    export default {
        components: { WxcLoading, PpStatusBar },
        data () {
            return {
                isSending: false,
                order: {
                    sendUserId: '50001',
                    startPlace: '标注个“拿单点”吧',
                    endPlace: '标注个“派送点”吧',
                    startPlaceLng: null,
                    startPlaceLat: null,
                    orderFee: null,
                }
            };
        },
        created () {
            this.init();
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$event.on(Global.EVENT_ADDRESS_START, params => {
                    if (params) this.order.startPlace = params;
                })
                this.$event.on(Global.EVENT_ADDRESS_END, params => {
                    if (params) this.order.endPlace = params;
                })
            })
        },
        methods: {
            init() {
                this.$geo.get().then(data => {
                    this.order.startPlaceLng = data.locationLng;
                    this.order.startPlaceLat = data.locationLat;
                }, error => {
                    this.$notice.toast({ message: error })
                })
            },
            chooseAddress(type) {
                this.$router.open({
                    name: 'selectPlace',
                    params: type
                })
            },
            sendOrder() {
                this.$tools.resignKeyboard(); //收起键盘

                getMemberInfo(member => {

                    this.order.sendUserId = member.userId;

                    if (this.order.startPlace === '标注个“拿单点”吧' || this.order.endPlace === '标注个“派送点”吧') {
                        this.$notice.toast({message: '请把信息补充完整吧 (ฅ´ω`ฅ)'})
                    } else if (!this.order.orderFee || this.order.orderFee === 0) {
                        this.$notice.toast({message: '给派单者打赏点小费吧(●\'◡\'●)'})
                    } else {
                        this.$notice.toast({message: '发布中... (ฅ´ω`ฅ)'})
                        this.isSending = true;
                        this.$emit('sendOrder', this.order)
                    }
                })
            }
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
