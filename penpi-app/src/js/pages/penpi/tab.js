export default {

    // 正常模式的tab title配置
    tabTitles: [
        {
            title: '抢单',
            icon: 'https://gw.alicdn.com/tfs/TB1MWXdSpXXXXcmXXXXXXXXXXXX-72-72.png',
            activeIcon: 'https://gw.alicdn.com/tfs/TB1kCk2SXXXXXXFXFXXXXXXXXXX-72-72.png'
        },
        {
            title: '话题推荐',
            icon: 'https://gw.alicdn.com/tfs/TB1ARoKSXXXXXc9XVXXXXXXXXXX-72-72.png',
            activeIcon: 'https://gw.alicdn.com/tfs/TB19Z72SXXXXXamXFXXXXXXXXXX-72-72.png'
        },
        {
            title: '消息中心',
            icon: 'https://gw.alicdn.com/tfs/TB1VKMISXXXXXbyaXXXXXXXXXXX-72-72.png',
            activeIcon: 'https://gw.alicdn.com/tfs/TB1aTgZSXXXXXazXFXXXXXXXXXX-72-72.png',
            badge: 5
        },
        {
            title: '我的主页',
            icon: 'https://gw.alicdn.com/tfs/TB1Do3tSXXXXXXMaFXXXXXXXXXX-72-72.png',
            activeIcon: 'https://gw.alicdn.com/tfs/TB1LiNhSpXXXXaWXXXXXXXXXXXX-72-72.png',
            dot: true
        }
    ],
    tabStyles: {
        bgColor: '#FFFFFF',
        titleColor: '#666666',
        activeTitleColor: '#3D3D3D',
        activeBgColor: '#FFFFFF',
        isActiveTitleBold: true,
        iconWidth: 70,
        iconHeight: 70,
        width: 160,
        height: 120,
        fontSize: 24,
        textPaddingLeft: 10,
        textPaddingRight: 10
    },

    // 使用 iconFont 模式的tab title配置
    tabIconFontTitles: [
        {
            title: '首页',
            codePoint: '\ue61d'
        },
        {
            title: '话题推荐',
            codePoint: '\ue702',
            dot: true
        },
        {
            title: '消息中心',
            codePoint: '\ue614',
            badge: 5
        },
        {
            title: '我的主页',
            codePoint: '\ue635'
        }
    ],
    tabIconFontStyles: {
        bgColor: '#eff3f4',
        titleColor: '#666666',
        activeTitleColor: '#1da1f2',
        activeBgColor: '#eff3f4',
        isActiveTitleBold: true,
        width: 160,
        height: 120,
        fontSize: 24,
        textPaddingLeft: 10,
        textPaddingRight: 10,
        iconFontSize: 50,
        iconFontColor: '#666666',
        activeIconFontColor: '#1da1f2',
        iconFontUrl: '//at.alicdn.com/t/font_501019_mauqv15evc1pp66r.ttf'
    }
}
