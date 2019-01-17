<template>
    <div>
        <div class="note-cont mb20">
            <textarea class="input" maxlength="200" rows="7" placeholder="发表帖子 200个字以内" v-model="note.noteCont"/>
            <div class="note-label">
                <text class="note-label-text">{{note.topicNm}}</text>
            </div>
        </div>
        <div class="note-file" v-if="noteFiles.length > 0">
            <div v-for="(item,index) in noteFiles">
                <image class="note-image" resize="cover" :src="item.fileUrl | imageFilter" @click="showBigImage(index)"></image>
            </div>
        </div>
        <div class="attachment" @click="upload">
            <text class="attachment-icon">&#xe62a;</text>
            <text class="attachment-text">照片/视频</text>
        </div>
    </div>
</template>

<script>
    if (process.env.NODE_ENV === 'development') require('Config');
    import {imageFilter} from 'Utils/filter'
    import {BaseIP} from 'Config/IP'
    export default {
        components: {},
        data () {
            return {
                note: {
                    topicId: null,
                    topicNm: null,
                    sendNoteUserId: 50001,
                    noteCont: null
                },
                noteFiles: []
            };
        },
        beforeCreate: function () {

        },
        created () {
            this.$navigator.setRightItem({
                text: '发表',
                fontSize: '32',
                fontWeight: 'normal'
            }, () => {
                this.createNote();
            });
            this.$router.getParams().then(resData => {
                this.note.topicId = resData.topicId;
                this.note.topicNm = resData.topicNm;
            })
        },
        methods: {
            createNote() {
                Vue.set(this.note, 'noteFiles', this.noteFiles);
                this.$fetch({
                    method: 'POST',
                    name: 'createNote',
                    data: this.note
                }).then(resData => {
                    this.$notice.toast({message: '发布成功啦~'})
                    this.$router.back({
                        length: 1,
                        type: 'PUSH'
                    });
                }, error => {
                    this.$notice.toast({message: error})
                })
            },
            upload() {
                if (this.noteFiles.length < 6)
                    this.$image.pickAndUpload({
                        maxCount: (6 - this.noteFiles.length)
                    }).then(resData => {
                        resData.forEach(item => {
                            this.noteFiles.push(item);
                        })
                    }, error => {
                        this.$notice.toast({message: error})
                    });
                else {
                    this.$notice.toast({message: '最多只能上传6张图片啦~'})
                }
            },
            showBigImage(index) {
                let urls = [];
                this.noteFiles.forEach(item => {
                    urls.push(BaseIP + item.fileUrl);
                });
                this.$image.preview({
                    index: index,
                    images: urls
                })
            }
        }
    }
</script>

<style lang="sass" src='./index.scss'></style>
