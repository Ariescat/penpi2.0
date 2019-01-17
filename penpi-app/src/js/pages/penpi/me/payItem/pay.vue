<template>
    <div class="container">

        <div class="external-wrapper">
            <text class="header-text">订单编号{{payLists[0].payId}}</text>
            <text class="content-text">￥{{payLists[0].payPrice}}</text>

            <div class="wrapper">
                <text style="color: #666666;">收款方</text>
                <text>派派科技</text>

            </div>
            <div class="buttom">
                <text class="pay-text">立即支付</text>
            </div>

        </div>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                payLists: [
                    {
                        payId: '01',
                        payPrice: 0.02
                    }
                ]
            }

        },
        beforeCreate () {

        },
        created () {

        },
        methods: {}
    }
</script>

<style lang="sass" src='./pay.scss'></style>
