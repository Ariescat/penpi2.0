<template>
    <div class="container">
        <div class="search">
            <wxc-searchbar ref="wxc-searchbar"
                           :always-show-cancel="true"
                           placeholder="填写下下地址吧(●'◡'●)"
                           @wxcSearchbarInputOnInput="search"></wxc-searchbar>
            <div class="ensure" @click="ensureAddress()">
                <text class="ensure-text">&#xe641;</text>
            </div>
        </div>
        <list class="address" v-if="config.isOverLoad">
            <cell v-for="(item, index) in address" @click="selectAddress(item)">
                <div class="address-item">
                    <text class="address-title">{{item.title}}</text>
                    <text class="address-snippet">{{item.snippet}}</text>
                    <div class="spilt"></div>
                </div>
            </cell>
        </list>
        <wxc-loading :show="!config.isOverLoad" type="trip" need-mask="true"></wxc-loading>
        <wxc-city ref="wxcCity"
                  :normalList="city.normalList"
                  :hotListConfig="city.hotListConfig"
                  :cityLocationConfig="city.cityLocationConfig"
                  @wxcCityItemSelected="citySelect"></wxc-city>
    </div>
</template>

<script>
if (process.env.NODE_ENV === 'development') require('Config');
import {WxcSearchbar, WxcCity, WxcLoading} from 'PenpiComponents'
import {Global} from '../../../../utils/global'
import City from './city';
const POISearch = weex.requireModule("POISearch");

export default {
    components: { WxcSearchbar, WxcLoading, WxcCity},
    data () {
        return {
            type: 'start', // 判断是“拿单点”还是“派单点”
            config: {
                isOverLoad: false,
            },
            city: {
                normalList: City.normalList,
                hotListConfig: {
                    type: 'group',
                    title: '热门',
                    list: City.hotList
                },
                cityLocationConfig: {
                    type: 'group',
                    title: '定位',
                    list: City.locationList
                }
            },
            poiModel: {
                key: '广东技术师范学院',
                ctgr: '',
                city: '广州'
            },
            address: [],
        };
    },
    beforeCreate: function () {

    },
    created () {
        this.$navigator.setRightItem({
            text: '城市',
            fontSize: '30'
        }, () => {
            this.selectCity();
        });
        this.$router.getParams().then(resData => {
            this.type = resData;
        });
        this.$storage.get(Global.STORAGE_CITY).then(resData => {
            this.poiModel.city = resData;
            this.search(this.poiModel.key);
        }, error => {
            this.search(this.poiModel.key);
        })
    },
    methods: {
        search(key) {
            let self = this;
            self.poiModel.key = key;
            POISearch.getPOIList(this.poiModel, function (data) {
                self.config.isOverLoad = true;
                if (data.status === 0) {
                    self.address = data.data.data;
                } else {
                    self.address = [];
                    self.$notice.toast({ message: `查找不到 \" ${key.value} \" 的相关内容呀 (ฅ´ω\`ฅ) ` })
                }
            });
        },
        selectAddress(address) {
            this.$event.emit(this.type === 'start' ?
                Global.EVENT_ADDRESS_START : Global.EVENT_ADDRESS_END, address.title);
            this.$router.back({
                length: 1,
                type: 'PUSH'
            })
        },
        ensureAddress() {
            this.$event.emit(this.type === 'start' ?
                Global.EVENT_ADDRESS_START : Global.EVENT_ADDRESS_END, this.poiModel.key.value);
            this.$router.back({
                length: 1,
                type: 'PUSH'
            })
        },
        selectCity () {
            this.$refs['wxcCity'].show();
        },
        citySelect (e) {
            this.$navigator.setRightItem({
                text: e.item.name,
                fontSize: '30'
            }, () => {
                this.selectCity();
            })
            this.$storage.set(Global.STORAGE_CITY, e.item.name);
        },
    }
}
</script>

<style lang="sass" src='./index.scss'></style>
