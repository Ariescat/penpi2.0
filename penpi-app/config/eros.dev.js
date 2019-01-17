// appBoard，mediator 不建议进行修改 如果修改了 也请对应修改
module.exports = {
    'exports': [
        // appBoard
        'js/config/index.js',
        // mediator
        'js/mediator/index.vue',
        // home
        'js/pages/demo/index.vue',
        'js/pages/demo/lifecycle/index.vue',
        'js/pages/demo/assets/index.vue',
        'js/pages/demo/globalAttr/index.vue',
        'js/pages/demo/inputExtend/index.vue',
        'js/pages/demo/refresh/index.vue',
        'js/pages/demo/storage/index.vue',
        'js/pages/demo/navigator/index.vue',
        'js/pages/demo/tools/index.vue',
        'js/pages/demo/router/index.vue',
        'js/pages/demo/router/home.vue',
        'js/pages/demo/event/a.vue',
        'js/pages/demo/event/b.vue',
        'js/pages/demo/notice/index.vue',
        'js/pages/demo/font/index.vue',
        'js/pages/demo/coms/index.vue',
        'js/pages/demo/image/index.vue',
        'js/pages/demo/bmchart/index.vue',
        'js/pages/demo/bmrichtext/index.vue',
        'js/pages/demo/bmcalendar/index.vue',
        'js/pages/demo/other/waterfall.vue',
        'js/pages/demo/other/weex-ui/tab-page/index.vue',

        // PenPi
        'js/pages/penpi/index.vue',
        'js/pages/penpi/login/index.vue',
        'js/pages/penpi/deliver/index.vue',
        'js/pages/penpi/deliver/sendOrder/index.vue',
        'js/pages/penpi/deliver/selectPlace/index.vue',
        'js/pages/penpi/topic/index.vue',
        'js/pages/penpi/topic/search/index.vue',
        'js/pages/penpi/topic/createTopic/index.vue',
        'js/pages/penpi/topic/createNote/index.vue',
        'js/pages/penpi/topic/topicDetail/index.vue',
        'js/pages/penpi/topic/noteDetail/index.vue',
        'js/pages/penpi/topic/commentDetail/index.vue',
        'js/pages/penpi/notice/index.vue',
        'js/pages/penpi/me/index.vue',
        'js/pages/penpi/me/pickItem/pickOrder.vue',
        'js/pages/penpi/me/publishItem/publishOrder.vue',
        'js/pages/penpi/me/setItem/setting.vue',
        'js/pages/penpi/me/profileItem/profile.vue',
        'js/pages/penpi/me/logisticsItem/logistics.vue',
        'js/pages/penpi/me/topicItem/isConcernedTopic.vue',
        'js/pages/penpi/me/signItem/sign.vue',
        'js/pages/penpi/me/noteItem/note.vue',
        'js/pages/penpi/me/historyItem/history.vue',
        'js/pages/penpi/me/payItem/pay.vue',

        // Test
        'js/pages/test/map-test.vue',
        'js/pages/test/weex-ui.vue',
    ],
    'alias': {
        'Components': 'js/components',
        'Common': 'js/common',
        'Config': 'js/config',
        'Widget': 'js/widget',
        'Pages': 'js/pages',
        'Utils': 'js/utils',
        'PenpiComponents': 'js/components/components'
    },
    'eslint': false,
    'diff': {
        'pwd': '/Users/yangmingzhe/Work/opensource/eros-diff-folder',
        'proxy': 'https://app.weex-eros.com/source'
    },
    'server': {
        'path': './',
        'port': 8889
    },
    'mockServer': {
        'port': 52077,
        'mockDir': './dist/mock'
    }
}
