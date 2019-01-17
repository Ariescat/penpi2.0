<template>
    <div class="container">
        <div class="topic-item" @click="jumpToNote()">
            <image class="topic-item-head" resize="cover" :src="pictUrl"></image>
            <div class="topic-item-content">
                <text class="content-text">{{topic.topicNm}}</text>
                <!--<text>{{pictUrl}}</text>-->
            </div>
            <div class="topic-item-num">
                <text class="num-text">99+</text>
            </div>
        </div>
        <div class="bottom-line"></div>
    </div>
</template>

<script>
    import {BaseIP} from 'Config/IP'
    export default {
        props: {
            index: {
                type: Number,
                default: 1
            },
            topic: {
                type: Object,
                default: {
                    topicId: null,
                    topicCategoryId: null,
                    topicHeadPictStr: 'bmlocal://assets/demo.jpg',
                    topicNm: '这里是话题',
                    createDate: null
                }
            }
        },
        computed: {
            pictUrl: function() {
                return BaseIP + this.topic.topicHeadPictStr;
            }
        },
        methods: {
            jumpToNote() {
                // this.$notice.toast({ message: `这里是标题${this.index}` })
                this.$router.open({
                    name: 'topicDetail',
                    navShow: false,
                    params: this.topic
                })
            }
        }
    }
</script>

<style lang="sass" src='./topicItem.scss'></style>
