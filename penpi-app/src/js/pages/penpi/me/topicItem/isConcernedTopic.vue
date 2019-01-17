<template>

    <div class="container">
        <div class="mt20"></div>
        <list>
            <cell v-for="(topic, index) in topicsList">
                <div class="topic-item">
                    <image class="topic-item-head" resize="cover" src="bmlocal://assets/beauty.jpg"></image>
                    <div class="topic-item-content">
                        <text class="header-text">{{topic.topicName}}</text>
                        <text class="content-text" style="padding-top: 10px">话题·{{topic.passageNum}}篇文章·{{topic.peopleNum}}人关注</text>
                    </div>
                    <div class="topic-item-num">
                        <text class="num-text">已关注</text>
                    </div>
                </div>
                <div class="bottom-line"></div>
                <div class="mt40"></div>
            </cell>
        </list>
    </div>

</template>

<script>
    export default {

        data () {
            return{
                topicsList: [
                    {
                        topicName: '校园',
                        passageNum: 1456,
                        peopleNum: 5216
                    },
                    {
                        topicName: '动漫',
                        passageNum: 230,
                        peopleNum: 523
                    },
                    {
                        topicName: '科技',
                        passageNum: 620,
                        peopleNum: 782
                    },
                    {
                        topicName: '情感',
                        passageNum: 965,
                        peopleNum: 320
                    },
                    {
                        topicName: '娱乐',
                        passageNum: 1951,
                        peopleNum: 6985
                    }
                ]
            }
        },
        beforeCreate () {

        },
        created () {

        },
        methods: {}
    }
</script>

<style lang="sass" src='./isConcernedTopic.scss'></style>
