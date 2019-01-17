<template>
    <div class="container">
        <list>
            <cell v-for="note in notes">
                <div>
                    <div class="external-wrapper">
                        <div class="left-wrapper">
                            <image class="note-image" src="bmlocal://assets/beauty.jpg"></image>
                        </div>
                        <div class="right-wrapper">
                            <div class="top-wrapper">
                                <text class="top-text">{{note.noteCont}}</text>
                            </div>
                            <div class="buttom-wrapper">
                                <text class="buttom-text">4回复</text>
                            </div>
                        </div>
                    </div>
                    <div class="mt20"></div>
                </div>
            </cell>
        </list>

    </div>
</template>

<script>

    if (process.env.NODE_ENV === 'development') require('Config');
    import {imageFilter} from 'Utils/filter'
    import {getMemberInfo} from '../../../../utils/utils'
    import {Global} from "../../../../utils/global";
    export default {

        data () {
            return {
                notes: []
            }

        },
        beforeCreate () {

        },
        created () {
            this.init();
        },
        methods: {
            init() {
                getMemberInfo(member => {
                    this.$fetch({
                        method: 'GET',
                        name: 'queryNoteById',
                        data: {
//                            userId: member.userId
                            userId: '50004'
                        }
                    }).then(resData => {
                        this.notes = resData;
                    }, error => {

                    })
                })
            },

        }
    }
</script>

<style lang="sass" src='./note.scss'></style>
