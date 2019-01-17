<template>
    <div class="container">

        <div class="wrapper-text">
            <text class="text">常规设置</text>
        </div>
        <div>
            <div class="item" :src="myHeadPictStr" @click="pickAndUpload">
                <text class="content-left">更换头像</text>
                <image class="header-image" resize="cover" :src="myHeadPictStr | imageFilter" v-if="myHeadPictStr"></image>
                <text class="icon" style="font-size: 58px" v-else>&#xe6b0;</text>
            </div>
            <div class="spilt"></div>
            <div class="item" @click="setMyName">
                <text class="content-left">更换昵称</text>
                <text class="content-right">{{user.userNm}}</text>
            </div>
            <div class="spilt"></div>
            <div class="item" @click="setMySign">
                <text class="content-left">更换签名</text>
                <text class="content-right">{{user.userRemark}}</text>
            </div>
        </div>


        <div class="wrapper-text">
            <text class="text">绑定账号登录</text>
        </div>
        <div>
            <div class="wrapper-item">
                <text class="icon">&#xe61a;</text>
                <text class="content-left">未绑定</text>
            </div>
            <div class="spilt"></div>
            <div class="wrapper-item">
                <text class="icon">&#xe63c;</text>
                <text class="content-left">未绑定</text>
            </div>
            <div class="spilt"></div>
            <div class="wrapper-item">
                <text class="icon">&#xe80e;</text>
                <text class="content-left">未绑定</text>
            </div>
            <div class="spilt"></div>
        </div>

    </div>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    const modal = weex.requireModule('modal')
    import {imageFilter} from 'Utils/filter'
    import {getMemberInfo} from '../../../../utils/utils'
    import {Global} from "../../../../utils/global";

    export default {

        data () {
            return {
                myHeadPictStr: null,
                user: {
                    userNm: ''
                }
            };

        },
        bmRouter: {
            viewWillBackAppear (params, options) {
                this.init();
            },
        },
        beforeCreate () {

        },
        created () {
            this.init();
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
//                        this.$notice.alert({message: resData})
                    }, error => {

                    })
                })
            },

            pickAndUpload () {
                this.$image
                    .pickAndUpload({
                        url: '',
                        maxCount: 3,
                        imageWidth: 1000,
                        allowCrop: true,
                        params: {},
                        header: {}
                    })
                    .then(
                        resData => {
                            if (resData && resData[0]){
                                this.user.userHeadPictId = resData[0].fileInfId;
                                this.$notice.toast({message: this.myHeadPictStr})
                                this.$fetch({
                                    method: 'POST',
                                    name: 'updateUserHeadPict',
                                    data: this.user
                                }).then(resData => {
                                    this.myHeadPictStr = resData.userHeadPictStr;
                                });
                            } else
                                this.$notice.toast({message: '图片选取失败，请重新选取一下~'})
                        },
                        error => {
                            this.$notice.toast({
                                message: '上传失败:' + error.errorMsg
                            });
                        }
                    );
            },
            setMyName () {
                let self = this;
                modal.prompt({
                    message: '修改昵称',
                    duration: 0.3,
                    okTitle: '确认',
                    cancelTitle: '取消'
                }, function (value) {
                    self.user.userNm = value.data;

                    self.$fetch({
                        method: 'POST',
                        name: 'updateProfile',
                        data: self.user
                    }).then(resData => {

                    }, error => {

                    })
                })
            },
            setMySign () {
                this.$router.open({
                    name: 'signItem',
                    type: 'PUSH'
                })
            },

        }
    }
</script>

<style lang="sass" src='./profile.scss'></style>
