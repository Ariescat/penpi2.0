<template>

    <div class="container">
        <div class="message">
            <text class="message-text">订单商品</text>
        </div>
        <div class="deliver-item" style="background-color: #f9f9f9">
            <div class="left-body">
                <text class="order-text">发布时间： 03-07 09:03</text>
                <text class="order-text">快递点： 广东技术师范学院美术学院</text>
                <text class="order-text">派送点： 广东技术师范学院教工宿舍</text>
            </div>
        </div>
        <div class="message">
            <text class="message-text">配送人员  {{name}}</text>
        </div>
        <div class="message">
            <text class="message-text">订单号码  {{oderNumber}}</text>
        </div>

        <div class="split-line"></div>

        <div class="deliver-item">
            <wxc-simple-flow :list="testData" :themeColor="themeColor"></wxc-simple-flow>
        </div>
        <div class="split-line"></div>

        <div class="bottom">
            <text class="bottom-text" @click="skipToMyPage">第四个火枪手</text>
            <text class="bottom-text" style="padding-left: 20px" @click="logout">退出</text>
        </div>
    </div>

</template>
<script>
    import {WxcSimpleFlow} from 'weex-ui';
    export default {
        components: {WxcSimpleFlow},
        data: () => ({
            themeColor: {
                lineColor: '#bf280b',
                pointInnerColor: '#b95048',
                pointBorderColor: '#bf280b',
                highlightTitleColor: '#bf280b',
                highlightPointInnerColor: '#bf280b',
                highlightPointBorderColor: '#d46262'
            },
            name: '李四',
            oderNumber: 48271893667,
            userName: '第四个火枪手',
            testData: [
                {
                    'date': '2017-05-24 19:54:28',
                    'highlight': true,
                    'title': '您已收取快件'
                },
                {
                    'date': '2017-05-24 19:54:28',
                    'title': '***同学已确认送达快件'
                },
                {
                    'date': '2017-05-24 19:50:21',
                    'title': '***同学正在给您派送快件'
                },
                {
                    'date': '2017-05-24 19:49:03',
                    'desc': '***同学会在2个工作小时内把快件送给您',
                    'title': '***同学已收取您的快件'
                }
            ]
        }),
        beforeCreate () {

        },
        created () {

        },
        methods: {
            skipToMyPage() {
                this.$router.open({
                    name: 'mypage',
                    type: 'PUSH'
                })
            }
        }
    }
</script>

<style lang="sass" src='./logistics.scss'></style>
