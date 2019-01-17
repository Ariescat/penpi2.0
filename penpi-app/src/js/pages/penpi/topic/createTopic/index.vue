<template>
    <div class="container">
        <div class="header">
            <div class="topic-image" @click="upload()">
                <image class="topic-image" resize="cover" :src="topicHeadPictStr" v-if="topicHeadPictStr"></image>
                <text class="topic-image-icon" v-else>&#xe62a;</text>
            </div>
            <div class="topic-name">
                <text class="label">话题名称</text>
                <input class="input" maxlength="10" placeholder="给话题起个响亮的名字吧" v-model="topic.topicNm"/>
            </div>
        </div>
        <div class="follower">
            <text class="label mb20">话题类型</text>
            <wxc-grid-select
                single="true"
                cols="4"
                :customStyles="customStyles"
                :list="categorys"
                @select="onSelect">
            </wxc-grid-select>
        </div>
        <div class="follower">
            <text class="label">关注者</text>
            <input class="input" maxlength="10" placeholder="给关注者起个称号吧" v-model="topic.followerNm"/>
        </div>
        <div class="follower">
            <text class="label">话题描述</text>
            <textarea class="input" maxlength="200" rows="5" placeholder="200个字以内" v-model="topic.topicDescr"/>
        </div>
        <div class="btnStyle mt10" @click="create">
            <text class="btnStyle-text">创建</text>
        </div>
    </div>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config');
import {WxcGridSelect} from 'weex-ui';
import {BaseIP} from 'Config/IP'
export default {
    components: { WxcGridSelect },
    data () {
        return {
            customStyles: {
                lineSpacing: '15px',
                icon: 'http://drugbean.xyz/penpi/initImage/default/choose.png',
                checkedBorderColor: '#1da1f2',
                backgroundColor: '#eff3f4',
            },
            categorys: [],
            topic: {
                topicHeadPictId: null,
                topicCategoryId: null,
                topicNm: null,
                followerNm: null,
                topicDescr: null
            },
            topicHeadPictStr: null
        };
    },
    beforeCreate: function () {

    },
    created () {
        this.$router.getParams().then(resData => {
            this.categorys = resData;
            this.categorys.forEach(item => {
                Vue.set(item, 'title', item.topicCategoryNm);
            });
            this.categorys.splice(0, 2);
        })
    },
    methods: {
        onSelect (e) {
            this.topic.topicCategoryId = e.checkedList[0].topicCategoryId
        },
        upload() {
            this.$image.pickAndUpload({
                maxCount: 1
            }).then(resData => {
                if (resData && resData[0]){
                    this.topicHeadPictStr = BaseIP + resData[0].fileUrl;
                    this.topic.topicHeadPictId = resData[0].fileInfId;
                } else
                    this.$notice.toast({message: '图片选取失败，请重新选取一下~'})
            }, error => {
                this.$notice.toast({message: error})
            })
        },
        create() {
            if (!this.topic.topicHeadPictId) {
                this.$notice.toast({message: '上传一张图片吧~'})
                return
            }
            if (!this.topic.topicNm) {
                this.$notice.toast({message: '还没给话题起名哦~'})
                return
            }
            if (!this.topic.topicCategoryId) {
                this.$notice.toast({message: '还没给话题分类哦~'})
                return
            }
            if (!this.topic.followerNm) {
                this.$notice.toast({message: '给关注者起个称号吧~'})
                return
            }
            if (!this.topic.topicDescr) {
                this.$notice.toast({message: '描述一下该话题吧~'})
                return
            }
            this.$fetch({
                method: 'POST',
                name: 'createTopic',
                data: this.topic
            }).then(resData => {
                if (resData) {
                    this.$router.back({
                        length: 1,
                        type: 'PUSH'
                    })
                    this.$notice.toast({message: '创建成功'})
                }
            }, error => {
                this.$notice.toast({message: error})
            })
        }
    }
}
</script>

<style lang="sass" src='./index.scss'></style>
