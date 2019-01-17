<template>
    <div class="status-bar" :style="{'height': statusBarHeight}"></div>
</template>

<script>
    export default {
        data () {
            return {
                statusBarHeight: weex.config.eros.statusBarHeight ? weex.config.eros.statusBarHeight : 40,
            };
        }
    }
</script>

<style scoped>
    .status-bar {
        width: 750px;
        height: 40px;
        background-color: #1da1f2;
    }
</style>
