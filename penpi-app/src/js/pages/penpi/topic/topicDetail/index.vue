<template>
    <div class="container">
        <pp-status-bar></pp-status-bar>
        <div class="nav">
            <div @click="onNavBack">
                <text class="nav-icon return-icon" style="margin-left: 25px">&#xe605;</text>
            </div>
            <div @click="openCreateNote">
                <text class="nav-icon" style="margin-left: 530px">&#xe60d;</text>
            </div>
            <div @click="onShare">
                <text class="nav-icon" style="margin-left: 30px">&#xe637;</text>
            </div>
        </div>
        <wxc-loading :show="!config.isOverLoad" type="trip" need-mask="true"></wxc-loading>

        <scroller v-if="config.isOverLoad">
            <div class="head mt20">
                <div class="head-left mt10">
                    <text class="head-left-text1">{{topicDetail.topicNm}}</text>
                    <text class="head-left-text2 ml2">{{topicDetail.followerAmount}}位{{topicDetail.followerNm}}</text>
                    <text class="head-left-text3 ml2">{{topicDetail.topicDescr}}</text>
                </div>
                <image class="head-right" resize="cover" :src="topicDetail.topicHeadPictStr | imageFilter" @click="showBigImage"></image>
            </div>
            <div class="head">
                <div class="head-left follow-person-warpper">
                    <div v-for="(item, index) in topicDetail.followers">
                        <image class="follow-person" resize="cover" :src="item.userHeadPictStr | imageFilter" v-if="index < 6"></image>
                    </div>
                    <div class="follow-person" v-if="topicDetail.followers.length >= 6">
                        <text class="follow-text base-gray">&#xe637;</text>
                    </div>
                </div>
                <div class="follow" :class="[topicDetail.ifFollow ? 'hasfollow' : '']" @click="follow(topicDetail.ifFollow)">
                    <text class="follow-text" v-if="!topicDetail.ifFollow">+ 关注</text>
                    <text class="follow-text" v-else>√ 已关注</text>
                </div>
            </div>
            <div class="tip-warpper mb10">
                <div class="tip">
                    <!--<text class="follow-text">看一下友情提示mua~</text>-->
                    <image class="tip-image" resize="cover" src="bmlocal://assets/scenery.jpg"></image>
                </div>
                <div class="tip-text-warpper">
                    <text style="color: white; font-size: 34px">各位发帖的{{topicDetail.followerNm}}们请看一下规则</text>
                </div>
            </div>
            <div class="split mb10"></div>

            <pp-tab :list="tab" type="centre" iwidth="300" @select="select"></pp-tab>
            <pp-no-data v-if="topicDetail.notes.length === 0"></pp-no-data>
            <div class="note-body" v-else v-for="(note,index) in topicDetail.notes">
                <div class="split" v-if="index != 0"></div>
                <note-item :index="index" :data="note" canClick="true"></note-item>
            </div>
        </scroller>
    </div>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config');
import {PpStatusBar, PpTab, PpNoData, WxcLoading, WxcPopover} from 'PenpiComponents'
import NoteItem from '../components/noteItem'
import {imageFilter} from 'Utils/filter'
import {getMemberInfo} from "Utils/utils";
import {BaseIP} from 'Config/IP'

export default {
    components: { PpStatusBar, NoteItem, PpTab, PpNoData, WxcLoading, WxcPopover },
    data () {
        return {
            tab: [{text: '新帖', check: true}, {text: '推荐', check: false}],
            config: {
                isOverLoad: false
            },
            topicDetail: {
                topicNm: '话题',
                followerAmount: 0,
                followerNm: '关注者',
                topicDescr: '话题描述',
                topicHeadPictStr: null,
                followers: [],
                notes: []
            }
        };
    },
    bmRouter: {
        viewWillBackAppear (params, options) {
            this.init(this.topicDetail.topicId);
        }
    },
    beforeCreate () {
        this.$navigator.setNavigationInfo({
            statusBarStyle: 'LightContent',
            navShow: false,
        });
    },
    created () {
        this.$router.getParams().then(resData => {
            this.init(resData.topicId);
        })
    },
    methods: {
        init(topicId) {
            getMemberInfo(member => {
                this.$fetch({
                    method: 'GET',
                    name: 'getTopicDetail',
                    data: {
                        userId: member.userId,
                        topicId: topicId
                    }
                }).then(resData => {
                    this.topicDetail = resData;
                    this.config.isOverLoad = true;
                }, error => {
                    this.$notice.toast({ message: error })
                })
            });
        },

        //导航功能区
        onNavBack() {
            this.$router.back({
                length: 1,
                type: 'PUSH'
            })
        },
        openCreateNote() {
            this.$router.open({
                name: 'createNote',
                type: 'PUSH',
                params: this.topicDetail
            })
        },

        follow(ifFollow) {
            getMemberInfo(member => {
                this.$fetch({
                    method: 'GET',
                    name: 'topicFollower',
                    data: {
                        userId: member.userId,
                        topicId: this.topicDetail.topicId,
                        ifFollow: !ifFollow
                    }
                }).then(resData => {
                    this.topicDetail.ifFollow = !this.topicDetail.ifFollow
                    this.$notice.toast({ message: this.topicDetail.ifFollow ? '关注成功' : '取消关注成功'})
                }, error => {
                    this.$notice.toast({ message: error })
                })
            })
        },

        select(item) {
            this.$notice.toast({ message: item })
        },

        onShare() {
            this.$share({
                title:'',                                     // 分享出去的 title
                content:'',                                   // 内容
                url: '',                                      // 用户点击后跳转 url
                image: '',                                    // 图片url
                platforms: ['Pasteboard','WechatSession','WechatTimeLine'] // 选填，不传此属性默认全部，目前支持朋友圈、朋友、复制链接
            }).then(resData => {
                // 成功的回调
            }, error => {
                // 失败的回调
            })
        },
        showBigImage() {
            if (!this.topicDetail.topicId) return;
            this.$fetch({
                method: 'GET',
                name: 'getTopicBigImage',
                data: {
                    topicId: this.topicDetail.topicId
                }
            }).then(resData => {
                this.$image.preview({
                    index: 0,
                    images: [BaseIP + resData.url]
                })
            }, error => {

            })
        }
    }
}
</script>

<style lang="sass" src='./index.scss'></style>
