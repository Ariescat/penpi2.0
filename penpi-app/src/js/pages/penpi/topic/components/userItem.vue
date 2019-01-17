<template>
    <div class="container">
        <div class="content">
            <image class="image" resize="cover" :src="data.userHeadPictStr | imageFilter"></image>
            <div class="right">
                <text class="text1">{{data.userNm}}</text>
                <text class="text2">个人介绍</text>
            </div>
        </div>
        <div class="line"></div>
    </div>
</template>

<script>
    import * as Filter from 'Utils/filter'
    export default {
        props: {
            index: {
                type: Number,
                default: 0
            },
            data: {
                type: Object,
                default: {
                    userHeadPictStr: 'bmlocal://assets/beauty.jpg'
                }
            }
        },
        methods: {
            jumpToNote() {
                // this.$notice.toast({ message: `这里是标题${this.index}` })
            }
        }
    }
</script>

<style lang="sass" src='./userItem.scss'></style>
