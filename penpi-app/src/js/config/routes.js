// 这里的后缀都是 .js 因为打包出来的都是js文件，而不是.vue文件，我们要告诉客户端跳转那个js

//  这里的路径和dev.json路径的区别
//  pages里面配置的是路由跳转的地址，也就是dev生成的 dist文件夹下的js bundle路径 从dist/js开始
//  dev.json 的 exports 需要打包的js地址，填入src的需要被打包成js bundle的地址   从src开始

export default {

    // Penpi
    // 不设置title自动隐藏导航
    'index':        {url: '/pages/penpi/index.js'},
    'login':        {title: '登陆/注册', url: '/pages/penpi/login/index.js'},

    'sendOrder':    {title: '发布订单', url: '/pages/penpi/deliver/sendOrder/index.js'},
    'selectPlace':  {title: '地址填写', url: '/pages/penpi/deliver/selectPlace/index.js'},

    'search':       {title: '搜索话题/帖子/用户', url: '/pages/penpi/topic/search/index.js'},
    'createTopic':  {title: '话题创建', url: '/pages/penpi/topic/createTopic/index.js'},
    'createNote':   {title: '新建帖子', url: '/pages/penpi/topic/createNote/index.js'},
    'topicDetail':  {url: '/pages/penpi/topic/topicDetail/index.js'},
    'noteDetail':   {title: '帖子详情', url: '/pages/penpi/topic/noteDetail/index.js'},
    'commentDetail':{title: '评论详情', url: '/pages/penpi/topic/commentDetail/index.js'},

    'pickOrder':    {title: '抢单详情', url: '/pages/penpi/me/pickItem/pickOrder.js'},
    'publishOrder': {title: '发单详情', url: '/pages/penpi/me/publishItem/publishOrder.js'},
    'setting':      {title: '设置', url: '/pages/penpi/me/setItem/setting.js'},
    'profile':      {title: '编辑个人资料', url: '/pages/penpi/me/profileItem/profile.js'},
    'logistics':    {title: '查看物流', url: '/pages/penpi/me/logisticsItem/logistics.js'},
    'mypage':       {title: '', url: '/pages/penpi/me/index.js'},
    'topicItem':    {title: '关注的话题', url: '/pages/penpi/me/topicItem/isConcernedTopic.js'},
    'signItem':     {title: '', url: '/pages/penpi/me/signItem/sign.js'},
    'noteItem':     {title: '发布的帖子', url: '/pages/penpi/me/noteItem/note.js'},
    'historyItem':  {title: '浏览历史', url: '/pages/penpi/me/historyItem/history.js'},
    'payItem':      {title: '确认交易', url: '/pages/penpi/me/payItem/pay.js'},

    // Test
    'map-test':    {title: 'penpi.test', url: '/pages/test/map-test.js'},
    'weex-ui': {title: 'penpi.weex-ui', url: '/pages/test/weex-ui.js'},

    // Demo
    'demo': {
        title: 'weex-eros demo',
        url: '/pages/demo/index.js'
    },
    'demo.lifecycle': {
        title: '生命周期',
        url: '/pages/demo/lifecycle/index.js'
    },
    'demo.assets': {
        title: '本地资源',
        url: '/pages/demo/assets/index.js'
    },
    'demo.globalAttr': {
        title: '全局属性',
        url: '/pages/demo/globalAttr/index.js'
    },
    'demo.inputExtend': {
        title: '文本框扩展',
        url: '/pages/demo/inputExtend/index.js'
    },
    'demo.refresh': {
        title: '下拉刷新',
        url: '/pages/demo/refresh/index.js'
    },
    'demo.storage': {
        title: '本地化存储',
        url: '/pages/demo/storage/index.js'
    },
    'demo.navigator': {
        title: '导航条操作',
        url: '/pages/demo/navigator/index.js'
    },
    'demo.tools': {
        title: '工具',
        url: '/pages/demo/tools/index.js'
    },
    'demo.router': {
        title: '路由',
        url: '/pages/demo/router/index.js'
    },
    'demo.event.a': {
        title: '发布订阅-a页面',
        url: '/pages/demo/event/a.js'
    },
    'demo.event.b': {
        title: '发布订阅-b页面',
        url: '/pages/demo/event/b.js'
    },
    'demo.notice': {
        title: '弹窗通知',
        url: '/pages/demo/notice/index.js'
    },
    'demo.font': {
        title: '字体',
        url: '/pages/demo/font/index.js'
    },
    'demo.coms': {
        title: '联系功能',
        url: '/pages/demo/coms/index.js'
    },
    'demo.image': {
        title: '图片',
        url: '/pages/demo/image/index.js'
    },
    'demo.bmchart': {
        title: '图表',
        url: '/pages/demo/bmchart/index.js'
    },
    'demo.bmrichtext': {
        title: '富文本',
        url: '/pages/demo/bmrichtext/index.js'
    },
    'demo.bmcalendar': {
        title: '日期',
        url: '/pages/demo/bmcalendar/index.js'
    },
    'demo.other.waterfall': {
        title: 'weex 瀑布流',
        url: '/pages/demo/other/waterfall.js'
    },
    'demo.other.weex-ui': {
        title: 'weex-ui',
        url: '/pages/demo/other/weex-ui/tab-page/index.js'
    }
}
