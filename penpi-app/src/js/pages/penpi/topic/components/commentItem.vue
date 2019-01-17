<template>
    <div class="container">
        <div class="comment-head">
            <image class="comment-head-image" :src="data.userHeadPictStr | imageFilter"></image>
            <div class="person-name">
                <text class="person-name-text">{{data.userNm}}</text>
                <text class="time-text">{{formatTime(data.createDate)}}</text>
            </div>
        </div>
        <div class="comment-content">
            <text class="comment-content-text">{{data.cont}}</text>
        </div>
        <div class="reply-content" v-if="data.commentReplies.length > 0" v-for="(item, index) in data.commentReplies">
            <text class="reply-text base-color" :value="item.commentUserNm"></text>
            <text class="reply-text" value=" 回复 " v-if="item.atUserNm"></text>
            <text class="reply-text base-color" :value="`@${item.atUserNm}`" v-if="item.atUserNm"></text>
            <text class="reply-text" value="： "></text>
            <text class="reply-text" :value="item.cont"></text>
        </div>
    </div>
</template>

<script>
    import {imageFilter} from 'Utils/filter'
    import {diffTime} from 'Utils/utils'
    export default {
        props: {
            index: {
                type: Number,
                default: 1
            },
            data: {
                type: Object,
                default: {
                    userHeadPictStr: 'bmlocal://assets/beauty.jpg',
                    userNm: '小小小白池',
                    cont: '哈哈哈哈哈哈',
                    createDate: '2018-04-27',
                    commentReplies: []
                }
            }
        },
        methods: {
            jumpToNote() {
                // this.$notice.toast({ message: `这里是标题${this.index}` })
                this.$router.open({
                    name: 'note',
                    navShow: false,
                    params: this.index
                })
            },
            formatTime(tiem) {
                return diffTime(tiem);
            },
        }
    }
</script>

<style lang="sass" src='./commentItem.scss'></style>
