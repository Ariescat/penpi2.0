<template>
    <div class="container">

        <div class="bar">
            <wxc-minibar title="设置签名"
                         background-color=white
                             text-color=$font-color
                             :left-button="leftButton"
                         @wxcMinibarLeftButtonClicked="minibarLeftButtonClick"
                         @wxcMinibarRightButtonClicked="minibarRightButtonClick"
                         right-text="确认"></wxc-minibar>
        </div>
        <div class="spilt"></div>

        <div>
            <input type="text" placeholder="用一句话介绍一下自己吧(建议30字以内)" class="input" singleline="false" :autofocus=true
                   @input="oninput"/>
        </div>


    </div>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    import {WxcMinibar} from 'weex-ui';
    import {getMemberInfo} from '../../../../utils/utils'
    import {Global} from "../../../../utils/global";
    const modal = weex.requireModule('modal')


    export default {
        components: {WxcMinibar},
        data () {
            return {
                user: {}
            }

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
                    }, error => {

                    })
                })
            },

            oninput: function (event) {
                this.user.userRemark = event.value;
            },

            minibarLeftButtonClick () {
            },
            minibarRightButtonClick () {

                this.$fetch({
                    method: 'POST',
                    name: 'updateProfile',
                    data: this.user
                }).then(resData => {
                    this.$notice.toast({message: '设置成功'})
                    this.$router.back({
                        length: 1,
                        type: 'PUSH'
                    })
                }, error => {

                })
            }
        }
    }
</script>

<style lang="sass" src='./sign.scss'></style>
