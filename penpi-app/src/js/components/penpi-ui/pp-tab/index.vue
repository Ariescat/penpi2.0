<!--

type: left or centre

-->

<template>
    <div>
        <scroller class="scroller" :style="{width: swidth}" :class="[type === 'centre' ? 'centre' : '']" scroll-direction="horizontal">
            <div class="warpper" :style="{width: iwidth}" v-for="(item, index) in list" :ref="'item'+index" @click="select(item)">
                <text class="text" :class="[item.check ? 'active' : '']" :ref="'text'+index">{{item.text}}</text>
                <div class="line" :class="[item.check ? 'active' : '']"></div>
            </div>
        </scroller>
    </div>
</template>

<script>
    export default {
        props: {
            list: [
                {text: 'text1', check: true},
                {text: 'text2', check: false},
                {text: 'text3', check: false}
            ],
            type: {
                type: String,
                default: 'left'
            },
            // 组件总长度
            swidth: {
                type: Number,
                default: 750
            },
            // item长度
            iwidth: {
                type: Number,
                default: 130
            }
        },
        methods: {
            select(data) {
                this.list.forEach(item => {
                    item.check = item.text == data.text;
                });
                this.$emit('select', data);
            }
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
