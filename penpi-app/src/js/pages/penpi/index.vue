<template>
    <div style="flex-direction: column">
        <pp-status-bar></pp-status-bar>
        <div :style="tabStyle" v-on:swipe="onSwipe($event)">
            <wxc-tab-bar :tab-titles="tabTitles"
                         :tab-styles="tabStyles"
                         title-type="iconFont"
                         ref="wxc-tab-bar"
                         @wxcTabBarCurrentTabSelected="wxcTabBarCurrentTabSelected">
                <deliver :style="contentStyle"></deliver>
                <topic :style="contentStyle"></topic>
                <notice :style="contentStyle"></notice>
                <me :style="contentStyle"></me>
            </wxc-tab-bar>
        </div>
    </div>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config');
// config
import TabConfig from './tab'
import { Global } from '../../utils/global'
// components
import { WxcButton } from 'weex-ui'
import { WxcTabBar,PpStatusBar } from 'PenpiComponents'
import Deliver from './deliver/index'
import Topic from './topic/index'
import Me from './me/index'
import Notice from './notice/index'

export default {
    components: { PpStatusBar, Deliver, Topic, Me, Notice, WxcButton, WxcTabBar },
    data () {
        return {
            curHomeBackTriggerTimes: 1,
            maxHomeBackTriggerTimes: 2,

            tabTitles: TabConfig.tabIconFontTitles,
            tabStyles: TabConfig.tabIconFontStyles,

            tabPage: 0,
        };
    },
    beforeCreate () {

    },
    created () {
        // 安卓自定义退出 app
        this.androidFinishApp()

        const realDeviceHeight = weex.config.eros.realDeviceHeight
        const statusBarHeight = weex.config.eros.statusBarHeight ? weex.config.eros.statusBarHeight : 40

        this.tabStyle = { height: (realDeviceHeight - statusBarHeight) + 'px' };
        this.contentStyle = { height: (realDeviceHeight - this.tabStyles.height - statusBarHeight) + 'px' };
    },
    mounted: function () {
        this.$nextTick(function () {
            this.$event.on(Global.EVENT_SLIDER_CHOOSE_TAB, params => {
                this.switchTab(params)
            });
        })
    },
    methods: {
        androidFinishApp () {
            const globalEvent = weex.requireModule('globalEvent')
            globalEvent.addEventListener('homeBack', options => {
                (this.curHomeBackTriggerTimes === this.maxHomeBackTriggerTimes) && this.$router.finish()
                this.curHomeBackTriggerTimes++
                // this.$notice.toast({ message: `点击返回${this.maxHomeBackTriggerTimes}次之后，会关闭应用，当前点击第${this.curHomeBackTriggerTimes - 1}次` })
                this.$notice.toast({ message: '再按一次退出'})
                setTimeout(() => {
                    this.curHomeBackTriggerTimes = 1;
                }, 1500)
            })
        },

        wxcTabBarCurrentTabSelected (e) {
            this.tabPage = e.page;
            this.$event.emit(Global.EVENT_CHOOSE_TAB + (e.page + 1));
        },

        onSwipe (event) {
            this.switchTab(event.direction)
        },
        switchTab (direction) {
            if (direction && direction === 'left') {
                this.tabPage++
                if (this.tabPage > 3) this.tabPage = 3
                this.$refs['wxc-tab-bar'].setPage(this.tabPage)
            }
            if (direction && direction === 'right') {
                this.tabPage--
                if (this.tabPage < 0) this.tabPage = 0
                this.$refs['wxc-tab-bar'].setPage(this.tabPage)
            }
        }
    }
}
</script>

<style lang="sass" src='./index.scss'></style>
