<template>
    <div class="container" @click="jumpToNoteDetail()">
        <div class="note-item-head">
            <image class="note-item-head-image" resize="cover" :src="data.userHeadPictStr | imageFilter"></image>
            <div class="person-name">
                <text class="person-name-text">{{data.sendNoteUserNm}}</text>
                <text class="time-text">{{formatTime()}}</text>
            </div>
            <div class="follow" @click="follower"><text class="follow-text">+ 关注</text></div>
            <div class="operate" @click="onShare">
                <text class="operate-text">&#xe637;</text>
            </div>
        </div>
        <div class="note-item-content mt20 mb20">
            <text class="content-title">{{data.noteCont}}</text>
            <div class="content-images">
                <div v-for="(item,index) in data.noteFiles">
                    <image class="content-image" resize="cover" :src="item.fileInfStr | imageFilter" @click="showBigImage(index)"></image>
                </div>
            </div>
        </div>
        <div class="note-item-label">
            <text class="note-item-label-text">{{data.topicNm}}</text>
        </div>
        <div class="note-item-bottom">
            <div class="note-item-bottom width110" @click="onShare">
                <text class="bottom-icon">&#xe601;</text>
                <text class="bottom-text">分享</text>
            </div>
            <div class="note-item-bottom width100" style="margin-left: 60px" @click="jumpToNoteDetail">
                <text class="bottom-icon">&#xe600;</text>
                <text class="bottom-text">评论</text>
            </div>
            <div class="note-item-bottom width100">
                <text class="bottom-text">{{data.commentAmount}}</text>
            </div>
            <div class="note-item-bottom width140" style="margin-left: 190px" >
                <text class="bottom-icon" @click="up">&#xe750;</text>
                <text class="bottom-text" style="margin-left: 10px">{{data.noteHeat | numberFilter}}</text>
                <text class="bottom-icon" style="margin-left: 12px" @click="down">&#xe755;</text>
            </div>
        </div>
    </div>
</template>

<script>
    import {diffTime} from 'Utils/utils'
    import {imageFilter, numberFilter} from 'Utils/filter'
    import {BaseIP} from 'Config/IP'
    export default {
        props: {
            canClick: {
                type: Boolean,
                default: false
            },
            index: {
                type: Number,
                default: 1
            },
            data: {
                type: Object,
                default: {
                    topicId: null,
                    topicNm: '话题',
                    sendNoteUserId: null,
                    sendNoteUserNm: 'user',
                    userHeadPictStr: '',
                    noteCont: '',
                    noteStatCode: '',
                    commentAmount: 0,
                    createDate: null,
                    lastUpdTime: '2018-04-20 10:00:00',
                    noteFiles: [],
                }
            }
        },
        methods: {
            jumpToNoteDetail() {
                if (this.canClick) {
                    this.$router.open({
                        name: 'noteDetail',
                        params: this.data
                    })
                }
            },
            formatTime() {
                return diffTime(this.data.lastUpdTime);
            },
            follower() {
                this.$notice.toast({ message: '关注' })
            },
            onShare() {
                this.$share({
                    title:'',                                     // 分享出去的 title
                    content:'',                                   // 内容
                    url: '',                                      // 用户点击后跳转 url
                    image: '',                                    // 图片url
                    platforms: ['Pasteboard','WechatSession','WechatTimeLine']
                                                                  // 选填，不传此属性默认全部，目前支持朋友圈、朋友、复制链接
                }).then(resData => {
                    // 成功的回调
                }, error => {
                    // 失败的回调
                })
            },
            up() {
                this.$notice.toast({ message: 'up' })
            },
            down() {
                this.$notice.toast({ message: 'down' })
            },
            showBigImage(index) {
                if (this.data.noteFiles.length === 0) return;
                this.$fetch({
                    method: 'GET',
                    name: 'getNoteBigImage',
                    data: {
                        noteId: this.data.noteId
                    }
                }).then(resData => {
                    let urls = [];
                    resData.forEach(item => {
                        urls.push(BaseIP + item.url);
                    });
                    this.$image.preview({
                        index: index,
                        images: urls
                    })
                }, error => {

                })
            }
        }
    }
</script>

<style lang="sass" src='./noteItem.scss'></style>
