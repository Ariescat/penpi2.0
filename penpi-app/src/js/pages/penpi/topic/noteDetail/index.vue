<template>
    <div>
        <wxc-loading :show="!config.isOverLoad" type="trip" need-mask="true"></wxc-loading>
        <scroller class="container" v-if="config.isOverLoad" :style="{height: config.height}">
            <note-item :data="note"></note-item>

            <div class="split"></div>

            <div class="comment">
                <div>
                    <text class="comment-text">评论</text>
                </div>
                <div class="order" @click="commentOrder">
                    <text class="comment-text" v-if="config.isHottest">最热</text>
                    <text class="comment-text" v-else>最新</text>
                    <text class="order-icon" :class="[config.isHottest? 'isHottest' : '']">&#xe699;</text>
                </div>
            </div>

            <div v-for="(data, index) in noteComments">
                <div class="line"></div>
                <comment-item :data="data" :index="index" @click.native="openCommentDetail(data)"></comment-item>
            </div>

        </scroller>
        <pp-rely :text="commentText" @sendVoice="sendVoice" @sendImage="sendImage" @reply="comment"></pp-rely>
    </div>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config');
import NoteItem from '../components/noteItem'
import CommentItem from '../components/commentItem'
import {WxcLoading, PpRely} from 'PenpiComponents'
export default {
    components: { NoteItem, CommentItem, WxcLoading, PpRely },
    data () {
        return {
            config: {
                height: 1500,
                commentText: '',
                isHottest: true,
                isOverLoad: false
            },
            note: {
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
            },
            noteComments:[]
        };
    },
    bmRouter: {
        viewWillAppear (params, options) {
            if (params) {
                this.config.isOverLoad = true;
                this.note = params;
                this.init(this.note.noteId);
            } else {
                // TODO 如果是轮滚图点击进来的话要在这个请求下帖子的数据
            }
        },
        viewWillBackAppear (params, options) {
            this.init(this.note.noteId);
        }
    },
    beforeCreate: function () {

    },
    created () {
        const realDeviceHeight = weex.config.eros.realDeviceHeight
        const statusBarHeight = weex.config.eros.statusBarHeight ? weex.config.eros.statusBarHeight : 40

        this.config.height = realDeviceHeight - statusBarHeight - 100 - 90
    },
    methods: {
        init(noteId) {
            this.$fetch({
                method: 'GET',
                name: 'getNoteDetail',
                data: {
                    noteId: noteId
                }
            }).then(resData => {
                this.noteComments = resData.noteCommentExs;
                this.config.isOverLoad = true;
            }, error => {
            })
        },
        // 评论排序
        commentOrder() {
            this.config.isHottest = !this.config.isHottest;
        },
        openCommentDetail(e) {
            this.$router.open({
                name: 'commentDetail',
                type: 'PUSH',
                params: e
            })
        },
        sendVoice() {
            this.$notice.toast({ message: 'voice~'})
        },
        sendImage() {
            this.$notice.toast({ message: 'image~'})
        },
        comment(e) {
            this.config.isOverLoad = false;
            this.$fetch({
                method: 'POST',
                name: 'comment',
                data: {
                    noteId: this.note.noteId,
                    commentUserId: 50002,
                    cont: e,
                }
            }).then(resData => {
                this.$notice.toast({ message: '评论成功啦~'})
                this.$tools.resignKeyboard();
                this.commentText = '';
                this.note.commentAmount = this.note.commentAmount + 1;
                this.init(this.note.noteId);
            }, error => {
            })
        }
    }
}
</script>

<style lang="sass" src='./index.scss'></style>
