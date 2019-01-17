<template>
    <div class="wrapper">
        <div class="btn yellow  btn-margin" @click="openBottomPopup">
            <text class="btn-txt">点击弹出底部面板</text>
        </div>
        <wxc-popup popup-color="rgb(92, 184, 92)"
                   :show="isBottomShow"
                   @wxcPopupOverlayClicked="popupOverlayBottomClick"
                   pos="bottom"
                   height="400">
            <div class="demo-content">
                <image src="https://img.alicdn.com/tfs/TB1ojYvOXXXXXaOXFXXXXXXXXXX-180-41.png" class="demo-image"></image>
                <text>与 Web App、HTML5 App 或 hybrid App 不同，您可以使用 Weex 构建一个真正的原生应用。更贴心的是你的代码只需使用 HTML、CSS、JavaScript 可以构建原生应用，上手非常简单。
                </text>
            </div>
        </wxc-popup>
    </div>
</template>

<script>
    import { WxcPopup } from 'weex-ui';
    export default {
        components: { WxcPopup },
        data: () => ({
            isBottomShow: false,
            height: 400
        }),
        methods: {
            openBottomPopup () {
                this.isBottomShow = true;
            },
            //非状态组件，需要在这里关闭
            popupOverlayBottomClick () {
                this.isBottomShow = false;
            }
        }
    };
</script>
