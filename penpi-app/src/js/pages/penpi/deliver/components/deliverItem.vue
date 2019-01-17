<template>
    <div class="container">
        <div class="deliver-item">
            <div class="head">
                <div class="left-body">
                    <div class="sex">
                        <text class="icon-man" v-if="item.userSexCode === '0'">&#xe643;(男)</text>
                        <text class="icon-woman" v-else-if="item.userSexCode === '1'">&#xe647;(女)</text>
                        <text class="icon-woman" v-else>&#xe645; (保密)</text>
                    </div>
                    <text class="label">快递点： {{item.startPlace}}</text>
                    <text class="label">送货点： {{item.endPlace}}</text>
                </div>
                <div class="right-body">
                    <div class="mt10"></div>
                    <div class="grab" @click="grab">
                        <text class="grab-text">抢</text>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <text class="bottom-text width220">金额(元)： {{item.orderFee}}</text>
                <text class="bottom-text width220">距离(Km)： {{item.distance}}</text>
                <text class="bottom-text time-text">{{formatTime()}}</text>
            </div>
        </div>
    </div>
</template>

<script>
    import {sexFilter} from '../../../../utils/filter'
    import {diffTime, alert, getMemberInfo} from '../../../../utils/utils'
    import {Global} from "../../../../utils/global";

    export default {
        props: {
            index: {
                type: Number,
                default: 1
            },
            item: {
                type: Object,
                default: {
                    orderId: 0,
                    userSexCode: '0',
                    startPlace: '天河公园',
                    endPlace: '广东技术师范学院',
                    orderFee: 1.5,
                    createDate: '',
                    distance: 100
                }
            },
            latlng: {
                locationLng: 0,
                locationLat: 0,
            }
        },
        methods: {
            grab () {
                getMemberInfo(member => {
                    this.$fetch({
                        method: 'GET',
                        name: 'grabOrder',
                        data: {
                            userId: member.userId,
                            orderId: this.item.orderId,
                            endPlaceLng: this.latlng.locationLng,
                            endPlaceLat: this.latlng.locationLat
                        }
                    }).then(resData => {
                        if (resData) {
                            this.$emit('grab', {
                                type: Global.Success,
                                index: this.index,
                                data: resData
                            })
                        } else {
                            this.$emit('grab', {type: Global.Failure})
                        }
                    }, error => {
                        alert(error)
                    })
                });
            },
            formatTime () {
                return diffTime(this.item.createDate);
            }
        }
    }
</script>

<style lang="sass" src='./deliverItem.scss'></style>
