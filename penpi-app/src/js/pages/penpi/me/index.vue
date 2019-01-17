<template>
    <div class="container" v-if="config.isOverLoadData">
        <image class="header-backgroud-image" resize="cover" src="bmlocal://assets/scenery.jpg"></image>
        <div class="masking" @click="toLogin"></div>
        <div class="header-warpper">
            <div class="header">
                <div>
                    <image class="header-image" resize="cover" :src="myHeadPictStr | imageFilter" v-if="myHeadPictStr"></image>
                    <image class="header-image" resize="cover" src="bmlocal://assets/beauty.jpg"  v-else></image>
                </div>
                <div class="name" @click="editMyProfile">
                    <div style="width: 660px; flex-direction: row">
                        <text style="color: white; font-size: 34px" v-if="user.userNm">{{user.userNm}}</text>
                        <text style="color: white; font-size: 34px" v-else>请登录</text>
                        <text class="icon-header">&#xe643;</text>
                    </div>
                    <text class="icon" style="color: white;">&#xe604;</text>
                </div>
                <div class="sign">
                    <text class="sign-text">{{user.userRemark}}</text>
                </div>
            </div>
        </div>

        <scroller>
            <div class="external-wrapper">
                <div class="internal-wrapper">
                    <div class="wrapper">
                        <text class="wrapper-content">{{num.publishOrderNum}}</text>
                        <text class="wrapper-header">发单</text>
                    </div>
                    <div class="wrapper">
                        <text class="wrapper-content">{{num.pickOrderNum}}</text>
                        <text class="wrapper-header">抢单</text>
                    </div>
                    <div class="wrapper">
                        <text class="wrapper-content">{{num.orderPrice}}</text>
                        <text class="wrapper-header">金额</text>
                    </div>
                </div>

                <div class="internal-wrapper" style="margin-top: 20px">
                    <div class="wrapper">
                        <text class="wrapper-content">{{num.topicNum}}</text>
                        <text class="wrapper-header">帖子</text>
                    </div>
                    <div class="wrapper">
                        <text class="wrapper-content">{{num.focusNum}}</text>
                        <text class="wrapper-header">关注</text>
                    </div>
                    <div class="wrapper">
                        <text class="wrapper-content">{{num.fansNum}}</text>
                        <text class="wrapper-header">粉丝</text>
                    </div>
                </div>
            </div>

            <div class="split-line"></div>
            <div class="content">
                <div class="item" @click="publishMyOrder">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe7a1;</text>
                        <text class="text">我的发单</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>
                <div class="spilt"></div>

                <div class="item" @click="pickMyOrder">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe6cb;</text>
                        <text class="text">我的抢单</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>
                <div class="spilt"></div>
                <div class="split-line"></div>

                <div class="item" @click="pickMyNote">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe619;</text>
                        <text class="text">帖子</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>
                <div class="spilt"></div>

                <div class="item" @click="selectTopic">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe702;</text>
                        <text class="text">关注话题</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>
                <div class="spilt"></div>

                <div class="item">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe640;</text>
                        <text class="text">我赞过的</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>
                <div class="spilt"></div>

                <div class="item" @click="selectHistory">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe62c;</text>
                        <text class="text">浏览历史</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>
                <div class="spilt"></div>

                <div class="split-line"></div>
                <div class="item" @click="selectSet">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe65b;</text>
                        <text class="text">设置</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>
                <div class="spilt"></div>
                <div class="item">
                    <div class="item-icon">
                        <text class="icon" style="width: 50px">&#xe60f;</text>
                        <text class="text">反馈意见</text>
                    </div>
                    <text class="icon">&#xe604;</text>
                </div>

            </div>
        </scroller>

    </div>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    import {BaseIP} from 'Config/IP'
    import {getMemberInfo} from '../../../utils/utils'
    import {imageFilter} from 'Utils/filter'
    import {Global} from "../../../utils/global";

    export default {
        components: {},
        data () {
            return {
                config: {
                    isOverLoadData: false
                },
                user: {},
                num: {
                    publishOrderNum: 0,
                    pickOrderNum: 0,
                    orderPrice: 0,
                    topicNum: 0,
                    focusNum: 0,
                    fansNum: 0
                },
                myHeadPictStr: '',
            };
        },
        bmRouter: {
            viewWillBackAppear (params, options) {
                this.config.isOverLoadData && this.init();
            },
        },
        beforeCreate () {

        },
        created () {

        },
        mounted: function () {
            this.$nextTick(function () {
                this.$event.on(Global.EVENT_CHOOSE_TAB_4, params => {
                    this.config.isOverLoadData = true;
                    this.init();
                })
            })
        },
        methods: {

            init() {
                getMemberInfo(member => {
                    this.$fetch({
                        method: 'GET',
                        name: 'getUserProfile',
                        data: {
                            userId: member.userId
                        }
                    }).then(resData => {
                        this.user = resData;
                        this.myHeadPictStr = resData.userHeadPictStr;
                    }, error => {

                    })
                })
            },

            toLogin() {
                this.$router.open({
                    name: 'login',
                    type: 'PUSH'
                })
                this.$router.finish();
            },
            pickMyOrder() {
                this.$router.open({
                    name: 'pickOrder',
                    type: 'PUSH'
                })
            },
            publishMyOrder() {
                this.$router.open({
                    name: 'publishOrder',
                    type: 'PUSH'
                })
            },
            selectSet() {
                this.$router.open({
                    name: 'setting',
                    type: 'PUSH'
                })
            },
            editMyProfile() {
                this.$router.open({
                    name: 'profile',
                    type: 'PUSH'
                })
            },
            selectTopic() {
                this.$router.open({
                    name: 'topicItem',
                    type: 'PUSH'
                })
            },
            pickMyNote() {
                this.$router.open({
                    name: 'noteItem',
                    type: 'PUSH'
                })
            },
            selectHistory() {
                this.$router.open({
                    name: 'historyItem',
                    type: 'PUSH'
                })
            }
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
