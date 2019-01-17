<template>
    <div class="content" :style="{height: height}">
        <image class="image" resize="cover" src="bmlocal://assets/no-data.png"></image>
        <text class="text">暂无数据哦</text>
    </div>
</template>

<script>
    export default {
        props: {
            height: {
                type: Number,
                default: 500
            }
        },
    }
</script>

<style scoped>
    .content {
        justify-content: center;
        align-items: center;
    }

    .image {
        height: 300px;
        width: 300px;
    }

    .text {
        color: #666666;
    }
</style>
