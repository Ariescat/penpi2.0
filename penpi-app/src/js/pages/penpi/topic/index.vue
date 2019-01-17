<template>
    <div class="container">
        <div class="home" v-if="config.isOverLoadPage">
            <wxc-ep-slider ref="wxc-ep-slider"
                           :slider-id="slider.sliderId"
                           :card-length='slider.cardLength'
                           :card-s="slider.cardSize"
                           :select-index="slider.selectIndex"
                           @wxcEpSliderCurrentIndexSelected="sliderCurrentIndexSelected">

                <!--自动生成demo-->
                <div v-for="(v,index) in [1,2,3,4,5]" v-on:swipe="sliderTopic($event)"
                     :key="index"
                     :slot="`card${index}_${slider.sliderId}`"
                     :class="['slider',`slider${index}`]">
                    <!--<text>这里是第{{index + 1}}张图片</text>-->
                    <image class="slider-image" resize="cover" src="bmlocal://assets/mitu1.jpg" v-if="index == 0"></image>
                    <image class="slider-image" resize="cover" src="bmlocal://assets/mitu2.jpg" v-if="index == 1"></image>
                    <image class="slider-image" resize="cover" src="bmlocal://assets/mitu4.jpg" v-if="index == 2"></image>
                    <image class="slider-image" resize="cover" src="bmlocal://assets/mitu5.jpg" v-if="index == 3"></image>
                    <image class="slider-image" resize="cover" src="bmlocal://assets/mitu6.jpg" v-if="index == 4"></image>
                </div>
            </wxc-ep-slider>
            <div class="search-warpper search">
                <wxc-searchbar ref="wxc-searchbar"
                               :always-show-cancel="true"
                               placeholder="搜索话题/帖子/用户"
                               @wxcSearchbarInputOnInput="search"
                               @wxcSearchbarInputOnFocus="search"></wxc-searchbar>
                <div class="add" @click="createTopic()"><text class="add-text">+</text></div>
            </div>

            <wxc-loading :show="!config.isOverLoadCategory || !config.isOverLoadTopic" type="trip" need-mask="true"></wxc-loading>
            <div v-if="config.isOverLoadCategory">
                <pp-tab :list="topicCategorys" @select="chooseTopic"></pp-tab>
            </div>
            <div class="mt10"></div>
            <div :style="contentStyle" v-if="config.isOverLoadTopic">
                <list v-on:swipe="swipeTab($event)">
                    <cell v-for="(topic, index) in topics">
                        <topic-item :index="index" :topic="topic"></topic-item>
                    </cell>
                </list>
            </div>
        </div>
    </div>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config');
import {Global} from '../../../utils/global'
import {WxcSearchbar, WxcLoading, PpTab} from 'PenpiComponents'
import {WxcButton, WxcEpSlider} from 'weex-ui';
import TopicItem from './components/topicItem'
export default {
    components: { WxcButton, WxcSearchbar, WxcEpSlider, TopicItem, WxcLoading, PpTab },
    data () {
        return {
            slider: {
                sliderId: 1,
                cardLength: 5,
                cardSize: {
                    width: 750,
                    height: 350,
                    spacing: 0,
                    scale: 1
                },
                selectIndex: 0
            },
            config: {
                isOverLoadPage: false,
                isOverLoadCategory: false,
                isOverLoadTopic: false,
            },
            topicCategorys: [],
            topics: []
        };
    },
    // bmRouter: {
    //     viewWillBackAppear (params, options) {
    //         this.topicCategorys.forEach(item => {
    //             if (item.check === true) this.getTopics(item.topicCategoryId);
    //         });
    //     },
    // },
    beforeCreate: function () {

    },
    created () {
        const realDeviceHeight = weex.config.eros.realDeviceHeight
        const statusBarHeight = weex.config.eros.statusBarHeight
        // 减 状态栏 底部导航栏 顶部导航栏 轮滚图
        this.contentStyle = { height: (realDeviceHeight - statusBarHeight - 120 - 90 - 350) + 'px' };
    },
    mounted: function () {
        this.$nextTick(function () {
            this.$event.on(Global.EVENT_CHOOSE_TAB_2, params => {
                this.config.isOverLoadPage = true;
                this.getTopicCategorys();
            })
        })
    },
    methods: {
        // ---slider---
        sliderCurrentIndexSelected (e) {
            const index = e.currentIndex;
            console.log(index);
        },
        sliderTopic: function(event) {
            if (event.direction && event.direction === 'left') {
                if (this.slider.selectIndex == 4) {
                    this.$notice.toast({ message: '滑到尽头啦~'})
                    return
                }
                else this.setPage(++this.slider.selectIndex);
            }
            if (event.direction && event.direction === 'right') {
                if (this.slider.selectIndex == 0) {
                    this.$notice.toast({ message: '滑到尽头啦~'})
                    return
                }
                this.setPage(--this.slider.selectIndex);
            }
        },
        setPage(index) {
            this.$refs['wxc-ep-slider'].manualSetPage(index)
        },
        openNote(index) {
            // this.$router.open({
            //     name: 'noteDetail',
            //     params: {
            //         noteId: `5000${index}`
            //     }
            // })
        },
        // ---slider---

        // ---swipe---
        swipeTab: function(event) {
            if (event.direction) {
                this.$event.emit(Global.EVENT_SLIDER_CHOOSE_TAB, event.direction)
            }
        },
        // ---swipe---

        // ---connecting---
        getTopicCategorys () {
            this.timeout();
            this.$fetch({
                method: 'GET',
                name: 'getTopicCategorys'
            }).then(resData => {
                this.getTopics(50001);
                this.topicCategorys = resData;
                for (let i = 0; i < this.topicCategorys.length; i++) {
                    Vue.set(this.topicCategorys[i], 'text', this.topicCategorys[i].topicCategoryNm);
                    Vue.set(this.topicCategorys[i], 'check', (i === 0));
                }
                this.config.isOverLoadCategory = true;
            }, error => {
                this.$notice.toast({ message: error})
            })
        },
        getTopics (categoryId) {
            this.timeout();
            this.$fetch({
                method: 'GET',
                name: 'getTopics',
                data: {
                    categoryId: categoryId
                }
            }).then(resData => {
                this.topics = resData;
                this.config.isOverLoadTopic = true;
            }, error => {
                this.$notice.toast({ message: error})
            })
        },
        timeout() {
            setTimeout(() => {
                this.config.isOverLoadCategory = true;
                this.config.isOverLoadTopic = true;
            }, 3000)
        },
        // ---connecting---

        chooseTopic(category) {
            this.config.isOverLoadTopic = false;
            this.getTopics(category.topicCategoryId);
        },
        search() {
            this.$router.open({
                name: 'search'
            })
        },
        createTopic() {
            this.$router.open({
                name: 'createTopic',
                params: this.topicCategorys
            })
        }
    }
}
</script>

<style lang="sass" src='./index.scss'></style>
