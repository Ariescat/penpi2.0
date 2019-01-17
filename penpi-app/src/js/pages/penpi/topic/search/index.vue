<template>
    <div class="container">
        <div class="search">
            <wxc-searchbar ref="wxc-searchbar"
                           :always-show-cancel="true"
                           placeholder="搜索话题/帖子/用户"
                           @wxcSearchbarInputOnInput="onInput"></wxc-searchbar>
            <div class="ensure" @click="ensure()">
                <text class="ensure-text">&#xe641;</text>
            </div>
        </div>
        <pp-tab :list="tab" type="centre" :iwidth="220" @select="select"></pp-tab>
        <list>
            <cell v-for="(item, index) in topicExlist" v-if="type === '话题'">
                <topic-item :index="index" :topic="item"></topic-item>
            </cell>
            <cell v-for="(item, index) in noteExlist" v-if="type === '帖子'">
                <div class="split" v-if="index != 0"></div>
                <note-item :index="index" :data="item"></note-item>
            </cell>
            <cell v-for="(item, index) in userExlist" v-if="type === '用户'">
                <user-item :index="index" :data="item"></user-item>
            </cell>
        </list>
    </div>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    import {WxcSearchbar, PpTab} from 'PenpiComponents'
    import UserItem from '../components/userItem'
    import TopicItem from '../components/topicItem'
    import NoteItem from '../components/noteItem'

    export default {
        components: {WxcSearchbar, PpTab, UserItem, NoteItem, TopicItem},
        data() {
            return {
                tab: [{text: '话题', check: true}, {text: '帖子', check: false}, {text: '用户', check: false}],
                inputText: null,
                type: '话题',
                userExlist: [],
                noteExlist: [],
                topicExlist: [],
            };
        },
        beforeCreate: function () {
            this.$navigator.setNavigationInfo({
                statusBarStyle: 'LightContent',
                navShow: false,
            });
        },
        created() {

        },
        methods: {
            select(e) {
                this.type = e.text;
                if (this.type && this.inputText) this.search();
            },
            onInput(e) {
                this.inputText = e.value;
                if (this.type && this.inputText) this.search();
            },
            search() {
                this.$fetch({
                    method: 'GET',
                    name: 'search',
                    data: {
                        type: this.type,
                        param: this.inputText
                    }
                }).then(resData => {
                    if (resData && resData.type === '话题') {
                        this.topicExlist = resData.topicExlist;
                    } else if (resData && resData.type === '帖子') {
                        this.noteExlist = resData.noteExlist;
                    } else if (resData && resData.type === '用户') {
                        this.userExlist = resData.userExlist;
                    }
                }, error => {

                })
            },
            ensure () {
                if (this.type && this.inputText) this.search();
            }
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
