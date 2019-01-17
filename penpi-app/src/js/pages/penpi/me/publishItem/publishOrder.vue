<template>

    <div class="container">
        <div class="mt10"></div>
        <list>
            <cell v-for="(order, index) in orders">
                <div class="deliver-item">
                    <div class="left-body">
                        <text class="order-text">发布时间： {{formatTime(order.createDate)}}</text>
                        <text class="order-text">快递点： {{order.startPlace}}</text>
                        <text class="order-text">送货点： {{order.endPlace}}</text>
                    </div>
                </div>
                <div class="wrapper-text">
                    <text class="text">共1件包裹   配送金额：¥ {{order.orderFee}}元</text>
                </div>
                <div class="spilt"></div>
                <div class="wrapper-text" style="height: 110px">
                    <div class="button" style="margin-right: 10px">
                        <text class="button-text">取消订单</text>
                    </div>
                    <div class="button" style="margin-right: 10px" @click="confirmPay">
                        <text class="button-text">确认付款</text>
                    </div>
                    <div class="button" @click="logisticsQuery">
                        <text class="button-text">查看物流</text>
                    </div>
                </div>
                <div class="split-line"></div>
            </cell>
        </list>
    </div>

</template>
<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    const moment = require('moment');
    import {imageFilter} from 'Utils/filter'
    import {getMemberInfo} from '../../../../utils/utils'
    import {Global} from "../../../../utils/global";

    export default {
        data () {
            return {
                orders: []
            }
        },
        beforeCreate () {

        },
        created () {
            this.init();
        },
        methods: {
            init() {
                getMemberInfo(member => {
                    this.$fetch({
                        method: 'GET',
                        name: 'queryOrdersById',
                        data: {
//                            userId: member.userId
                            userId: '50002'
                        }
                    }).then(resData => {
                        this.orders = resData;
                    }, error => {

                    })
                })
            },

            logisticsQuery() {
                this.$router.open({
                    name: 'logistics',
                    type: 'PUSH'
                })
            },
            confirmPay() {
                this.$router.open({
                    name: 'payItem',
                    type: 'PUSH'
                })
            },
            formatTime(e) {
                return moment(e).format('MM-DD HH:MM')
            }
        }
    }
</script>

<style lang="sass" src='./publishOrder.scss'></style>
