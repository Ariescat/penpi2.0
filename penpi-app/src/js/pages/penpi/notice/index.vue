<template>
    <div class="container">
        <div class="tab">
            <notice-tab :list="tab" type="centre" iwidth="180" @select="select"></notice-tab>
        </div>
        <list>
            <cell>
                <div class="mt20"></div>
                <div class="topic-item">
                    <div class="topic-item-head">
                        <image class="topic-item-head" src="bmlocal://assets/demo.jpg"></image>
                    </div>
                    <div class="topic-item-content">
                        <div class="name-date-wrapper">
                            <text class="name-text">用户名</text>
                            <text class="content-text">2014-3-16</text>
                        </div>

                        <text class="content-text">楼主您好！我的网盘*****，发一份资料给我。谢谢哈</text>
                    </div>

                </div>
                <div class="bottom-line"></div>
            </cell>
        </list>
    </div>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    import NoticeTab from './components/index'

    export default {
        components: { NoticeTab },
        data () {
            return {
                tab: [{text: '私信', check: true}, {text: '评论', check: false}, {text: '@我', check: false}, {
                    text: '通知',
                    check: false
                }]
            };
        },
        beforeCreate () {

        },
        created () {

        },
        methods: {
            select(item) {
                this.$notice.toast({message: item})
            },
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
