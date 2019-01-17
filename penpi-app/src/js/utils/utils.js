const moment = require('moment');

const bmModal = weex.requireModule('bmModal')
const bmStorage = weex.requireModule('bmStorage')
const bmRouter = weex.requireModule('bmRouter')

import _isFunction from 'lodash/isFunction'
import routes from '../../js/config/routes'
import {Global} from './global';

/**
 * 计算时间差
 * @param createDate
 * @returns {string}
 */
export function diffTime(createDate) {
    if (!createDate) return 'NULL';
    else {
        let remainingTime = moment().diff(moment(createDate), 'minutes');  //剩余分钟
        if (remainingTime < 0) {
            remainingTime = 1;
        } else if (remainingTime < 60) {  //不足一小时
            remainingTime = (remainingTime % 60) + '分钟'
        } else if (remainingTime / 60 < 24) { //不足一天
            remainingTime = Math.floor((remainingTime / 60)) + '小时' + (remainingTime % 60) + '分钟'  //floor小数向下取整
        } else {
            remainingTime = Math.ceil((remainingTime / (60 * 24))) + '天'   //ceil小数向上取整
        }
        return remainingTime + '前';
    }
}

export function alert(data, callback) {
    bmModal.alert({
        title: '提示',
        message: data,
        okTitle: '确定'
    }, function () {
        if (_isFunction(callback)) {
            callback.call();
        }
    });
}

export function toast(data) {
    bmModal.toast({ message: data })
}

/**
 * 检查是否登陆并返回登陆的用户信息
 */
export function getMemberInfo(callback) {
    bmStorage.getData(Global.STORAGE_MEMBER, ({ status, data, errorMsg }) => {
        if (status === 0) {
            // alert(data)
            _isFunction(callback) && callback(JSON.parse(data))
        } else {
            bmModal.confirm({
                title: '提示',
                message: '你还没登陆哦~',
                cancelTitle: '取消',
                okTitle: '登陆'
            }, (params) => { // cancel

            }, (params) => { // OK
                bmRouter.open({
                    url: routes.login.url,
                    type: 'PUSH',
                    params: {},
                    canBack: true,
                    navShow: true,
                    navTitle: routes.login.title,
                    statusBarStyle: 'Default'
                })
                bmRouter.finish();
            })
        }
    })
}
