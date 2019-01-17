<template>
    <div class="container" v-if="config.isOverLoad">
        <div class="mt10"></div>
        <list ref="list" :show-scrollbar="true" :showRefresh="true" @refresh="onrefresh">
            <cell v-for="(order, index) in orders">
                <div class="deliver-item">
                    <div class="left-body">
                        <text class="order-text">性别：{{order.userSexCode | sexFilter}}</text>
                        <text class="order-text">快递点： {{order.startPlace}}</text>
                        <text class="order-text">送货点： {{order.endPlace}}</text>
                        <text class="order-text">金额(元)： {{order.orderFee}}</text>
                    </div>
                    <div class="right-body">
                        <div class="botton">
                            <div class="botton-item">
                                <text class="botton-text">确认</text>
                            </div>
                            <div class="botton-item">
                                <text class="botton-text">送达</text>
                            </div>
                        </div>
                        <div class="mt20"></div>
                        <div class="time">
                            <text class="time-text">{{formatTime(order.createDate)}}</text>
                        </div>
                    </div>
                </div>
                <div class="mb20"></div>
            </cell>
        </list>
    </div>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    const moment = require('moment');
    import * as filter from '../../../../utils/filter'
    import * as Utils from '../../../../utils/utils'

    export default {
        data () {
            return {
                config: {
                    isOverLoad: false,
                    isBottomShow: false,
                },
                orders: [],
            }
        },
        beforeCreate () {

        },
        created () {
            this.init();
        },
        methods: {

            init () {
                this.timeout();
                this.$fetch({
                    method: 'GET',
                    name: 'getOrders',
                }).then(resData => {
                    this.orders = resData;
                    this.config.isOverLoad = true;
                    this.$refs['list'].refreshEnd()
                    this.$notice.toast({message: '请求数据成功'})
                }, error => {

                })
            },
            timeout() {
                setTimeout(() => {
                    this.config.isOverLoad = true;
                }, 3000)
            },

            onrefresh() {
                this.init();
            },

            formatTime(e) {
                // 可以用
                // return Utils.computeRemainingTime(e);

                // 这个也可以用
                return moment(e).format('MM-DD HH:MM')
            }

        }
    }
</script>

<style lang="sass" src='./pickOrder.scss'></style>
