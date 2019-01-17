<template>
    <div class="container">
        <pp-status-bar></pp-status-bar>
        <div class="item">
            <text class="label">性别：</text>
            <div class="width550">
                <wxc-grid-select single="true" cols="3" :customStyles="customStyles" :list="sexCode"
                    @select="onSelect">
                </wxc-grid-select>
            </div>
        </div>
        <div class="item">
            <text class="label">范围：</text>
            <input class="input" maxlength="3" @input="onInput"/>
            <text class="label width70">Km</text>
        </div>
    </div>
</template>

<script>
    import {PpStatusBar, WxcPageCalendar} from 'PenpiComponents'
    import {WxcGridSelect} from 'weex-ui';
    export default {
        components: { PpStatusBar, WxcGridSelect},
        data () {
            return {
                customStyles: {
                    lineSpacing: '15px',
                    icon: 'http://drugbean.xyz/penpi/initImage/default/choose.png',
                    checkedBorderColor: '#1da1f2',
                    backgroundColor: '#eff3f4',
                },
                sexCode: [{title: '男'}, {title: '女'}, {title: 'BOTH'}],
            };
        },
        methods: {
            onSelect(e) {
                this.$emit('filter', { userSexCode: e.selectIndex });
            },
            onInput(e) {
                this.$emit('filter', { distance: e.value });
            }
        }
    }
</script>

<style lang="sass" src='./orderFilter.scss'></style>
