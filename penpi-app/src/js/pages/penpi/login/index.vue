<template>
    <div>
        <image class="image" resize="cover" src="bmlocal://assets/mitu4.jpg"></image>
        <div class="login mt40">
            <div class="label">
                <text class="icon">&#xe655;</text>
                <input class="input width550" type="number" maxlength="11" placeholder="手机号码" v-model="loginId"/>
            </div>
            <div class="label mt20">
                <text class="icon">&#xe644;</text>
                <input class="input" type="number" maxlength="6" placeholder="密码" v-model="identifyingCode"/>
                <div class="identifying-code" @click="getCode">
                    <text class="identifying-code-text" v-if="config.time === config.alltime">获取验证码</text>
                    <text class="identifying-code-text" v-else>{{config.time}} s</text>
                    <text class="identifying-code-icon" v-if="config.time === config.alltime">&#xe604;</text>
                </div>
            </div>
            <div class="button" @click="login">
                <text class="button-text">登陆/注册</text>
            </div>
        </div>
    </div>
</template>

<script>
    import {Global} from '../../../utils/global'

    export default {
        components: {},
        data () {
            return {
                config: {
                    alltime: 21,
                    time: 21,
                },
                identifyingCode: null,
                loginId: null
            };
        },
        created () {

        },
        methods: {
            login() {
                if (!this.identifyingCode) {
                    this.$notice.toast({message: '把信息都填完整吧~'})
                    return false;
                }
                this.$fetch({
                    method: 'GET',
                    name: 'memberLogin',
                    data: {
                        loginId: this.loginId,
                        validateCode: this.identifyingCode
                    }
                }).then(resData => {
                    this.$storage.set(Global.STORAGE_MEMBER, resData);
                    this.$router.open({
                        name: 'index',
                        type: 'PUSH'
                    });
                    this.$router.finish();
                }, error => {
                    this.$notice.toast({message: error})
                })
            },
            getCode() {
                if (!this.loginId) {
                    this.$notice.toast({message: '手机号码还为空哦~'})
                    return false;
                }
                if (this.loginId.length !== 11) {
                    this.$notice.toast({message: '手机号码不正确哦~'})
                    return false;
                }
                this.config.time = this.config.time - 1;
                this.backcount();
                this.$fetch({
                    method: 'GET',
                    name: 'getValidateCode',
                    data: {
                        loginId: this.loginId
                    }
                }).then(resData => {
                    this.identifyingCode = resData;
                    this.$notice.toast({message: '验证码为： ' + resData})
                }, error => {

                })
            },
            // 验证码倒数
            backcount() {
                setTimeout(() => {
                    if (this.config.time > 0) {
                        this.config.time = this.config.time - 1;
                        this.backcount();
                    } else {
                        this.config.time = this.config.alltime;
                    }
                }, 1000)
            }
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
