<template>
    <div class="container">
        <list>
            <cell v-for="note in noteLists">
                <div class="external-wrapper">
                    <div class="top-wrapper">
                        <image class="note-image" src="bmlocal://assets/beauty.jpg"></image>
                        <div class="right-wrapper">
                            <text class="right-text">{{note.content}}</text>
                        </div>
                    </div>

                    <div class="buttom-wrapper">
                        <text class="buttom-text">{{note.deliverTime}}</text>
                        <text class="buttom-text" style="margin-left: 20px">来自{{note.author}}</text>
                    </div>
                </div>
                <div class="mt20"></div>
            </cell>
        </list>

    </div>
</template>

<script>
    export default {
        data () {
            return {
                noteLists: [
                    {
                        deliverTime: '28分钟前',
                        author: '第四个火枪手',
                        content: '布朗：绿军打得很好，但这不是我们真正的水平'
                    },
                    {
                        deliverTime: '1小时前',
                        author: 'Allen',
                        content: '幸福都是奋斗出来的'
                    },
                    {
                        deliverTime: '2小时前',
                        author: '马库斯-莫里斯',
                        content: '我们会努力在第六场解决战斗'
                    },
                    {
                        deliverTime: '3小时前',
                        author: '杜兰特',
                        content: '要尽快摆脱这场失利，为下一场做好准备'
                    },
                    {
                        deliverTime: '3小时前',
                        author: '孙悦',
                        content: '目前最大的心愿是以生涯最佳状态回归球场'
                    },
                    {
                        deliverTime: '3小时前',
                        author: '德安东尼',
                        content: '保罗说他没事，会延用上一场的轮换阵容 '
                    },
                    {
                        deliverTime: '4小时前',
                        author: '虎扑步行街',
                        content: 'ZT:这个香港美女长得这么美也要搬砖？被网友笑称“搬砖界的劳拉”！'
                    },
                    {
                        deliverTime: '一天前',
                        author: '虎扑步行街',
                        content: ' ZT “暴走漫画”涉嫌戏谑英烈遭声讨 创始人到烈士陵园道歉'
                    },
                    {
                        deliverTime: '一天前',
                        author: '虎扑步行街',
                        content: 'MSI季中冠军赛数据出炉：Karsa夺KDA之冠，Uzi获得击杀数第一'
                    },
                    {
                        deliverTime: '一天前',
                        author: '虎扑步行街',
                        content: '大学里同学背后传谣言，我这波操作什么水平？'
                    }
                ]
            }

        },
        beforeCreate () {

        },
        created () {

        },
        methods: {

        }
    }
</script>

<style lang="sass" src='./history.scss'></style>
