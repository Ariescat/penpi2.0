<template>

    <scroller>
        <div class="container">

            <div class="wrapper-text">
                <text class="text">消息推送</text>
            </div>

            <div>
                <div class="item">
                    <text class="content-text">话题更新推送</text>
                    <wxc-cell :has-top-border="false"
                              :auto-accessible="false"
                              title="取消按钮">
                        <switch :checked="!single"
                                slot="value"
                                @change="single= !single"></switch>
                    </wxc-cell>
                </div>
                <div class="spilt"></div>
                <div class="item">
                    <text class="content-text">新消息推送</text>
                    <wxc-cell :has-top-border="false"
                              :auto-accessible="false"
                              title="取消按钮">
                        <switch :checked="!single"
                                slot="value"
                                @change="single= !single"></switch>
                    </wxc-cell>
                </div>
            </div>

            <div class="wrapper-text">
                <text class="text">通用设置</text>
            </div>

            <div>
                <div class="item" @click="editMyProfile">
                    <text class="content-text">编辑个人资料</text>
                </div>
                <div class="spilt"></div>
                <div class="item">
                    <text class="content-text">黑名单设置</text>
                </div>
                <div class="spilt"></div>
                <div class="item">
                    <text class="content-text">隐私设置</text>
                </div>
                <div class="spilt"></div>
                <div class="item">
                    <text class="content-text">移动网络下加载图片</text>
                    <wxc-cell :has-top-border="false"
                              :auto-accessible="false"
                              title="取消按钮">
                        <switch :checked="!single"
                                slot="value"
                                @change="single= !single"></switch>
                    </wxc-cell>
                </div>
            </div>

            <div class="wrapper-text">
                <text class="text">其他</text>
            </div>

            <div>
                <div class="item">
                    <text class="content-text">版本更新</text>
                </div>
                <div class="spilt"></div>
                <div class="item">
                    <text class="content-text">关于我们</text>
                </div>
                <div class="spilt"></div>
            </div>

            <div class="wrapper">
                <div class="button" @click="logout">
                    <text class="button-text">退出当前账号</text>
                </div>
            </div>

        </div>
    </scroller>

</template>
<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    export default {
        data () {

        },
        beforeCreate () {

        },
        created () {

        },
        methods: {
            editMyProfile() {
                this.$router.open({
                    name: 'profile',
                    type: 'PUSH'
                })
            },
            logout() {
                this.$router.open({
                    name: 'login',
                    type: 'PUSH'
                })
            }
        }
    }
</script>

<style lang="sass" src='./setting.scss'></style>
