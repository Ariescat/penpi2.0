<template>
    <div class="content">
        <text class="icon" @click="voice">&#xe646;</text>
        <input class="input" v-model="text" :placeholder="placeholder"/>
        <text class="icon" @click="image">&#xe6a5;</text>
        <div class="reply" @click="send">
            <text class="reply-text">{{reply}}</text>
        </div>
    </div>
</template>

<script>
    export default {
        data () {
            return {

            }
        },
        props: {
            text: {
                type: String,
                default: ''
            },
            placeholder: {
                type: String,
                default: '输入点想说的话吧~'
            },
            reply: {
                type: String,
                default: '回复'
            }
        },
        methods: {
            voice() {
                this.$emit('sendVoice');
            },
            image() {
                this.$emit('sendImage');
            },
            send() {
                this.$emit('reply', this.text);
            }
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
