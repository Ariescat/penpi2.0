<template>
    <div>
        <wxc-loading :show="!config.isOverLoad" type="trip" need-mask="true"></wxc-loading>
        <scroller class="container" :style="{height: config.height}">
            <div class="head" @click="noAtUser">
                <div class="comment-head">
                    <image class="comment-head-image" :src="noteComment.userHeadPictStr | imageFilter"></image>
                    <div class="person-name">
                        <text class="person-name-text">{{noteComment.userNm}}</text>
                        <text class="time-text">{{formatTime(noteComment.createDate)}}</text>
                    </div>
                </div>
                <div class="comment-content">
                    <text class="comment-content-text">{{noteComment.cont}}</text>
                </div>
            </div>
            <div class="head reply"
                 v-if="noteComment.commentReplies.length > 0"
                 v-for="(item, index) in noteComment.commentReplies"
                 @click="atUser(item)">
                <div class="comment-head">
                    <image class="comment-head-image" :src="item.commentUserHeadPictStr | imageFilter"></image>
                    <div class="person-name">
                        <text class="person-name-text">{{item.commentUserNm}}</text>
                        <text class="time-text">{{formatTime(item.createDate)}}</text>
                    </div>
                </div>
                <div class="reply-content">
                    <text class="reply-text" v-if="item.atUserId">回复 </text>
                    <text class="reply-text base-color" v-if="item.atUserId">{{item.atUserNm}}</text>
                    <text class="reply-text" v-if="item.atUserId">： </text>
                    <text class="reply-text">{{item.cont}}</text>
                </div>
            </div>
        </scroller>
        <pp-rely :text="config.commentText" :placeholder="config.placeholder"
                 @sendVoice="sendVoice" @sendImage="sendImage" @reply="comment"></pp-rely>
    </div>
</template>

<script>
    import {imageFilter} from 'Utils/filter'
    import {diffTime} from 'Utils/utils'
    import CommentItem from '../components/commentItem'
    import {WxcLoading, PpRely} from 'PenpiComponents'
    export default {
        components: {CommentItem, WxcLoading, PpRely},
        data () {
            return {
                config: {
                    isOverLoad: false,
                    height: 1500,
                    commentText: '',
                    placeholder: '输入点想说的话吧~'
                },
                noteComment: {},
                noteCommentReply: {
                    commentUserId: 50002,
                    atUserId: null,
                }
            };
        },
        beforeCreate: function () {

        },
        created () {
            const realDeviceHeight = weex.config.eros.realDeviceHeight
            const statusBarHeight = weex.config.eros.statusBarHeight ? weex.config.eros.statusBarHeight : 40

            this.config.height = realDeviceHeight - statusBarHeight - 100 - 90

            this.$router.getParams().then(resData => {
                this.noteComment = resData;
                this.config.isOverLoad = true;
            })
        },
        methods: {
            formatTime(tiem) {
                return diffTime(tiem);
            },
            atUser(e) {
                this.noteCommentReply.atUserId = e.commentUserId;
                this.config.placeholder = `回复 @${e.commentUserNm}：`;
            },
            noAtUser() {
                this.noteCommentReply.atUserId = null;
                this.config.placeholder = '输入点想说的话吧~';
            },
            comment(e) {
                this.config.isOverLoad = false;
                this.$fetch({
                    method: 'POST',
                    name: 'reply',
                    data: {
                        noteCommentId: this.noteComment.noteCommentId,
                        commentUserId: this.noteCommentReply.commentUserId,
                        atUserId: this.noteCommentReply.atUserId,
                        cont: e,
                    }
                }).then(resData => {
                    this.$notice.toast({ message: '评论成功啦~'})
                    this.$tools.resignKeyboard();
                    this.config.commentText = '';
                    this.noteComment.commentReplies.push(resData);
                    this.config.isOverLoad = true;
                }, error => {
                })
            }
        }
    }
</script>


<style lang="sass" src='./index.scss'></style>
