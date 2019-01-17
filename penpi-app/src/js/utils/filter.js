import {Global} from './global'
import {BaseIP} from "../config/IP";

Vue.filter('imageFilter', function (value) {
    if (!value) return 'bmlocal://assets/demo.jpg'
    else {
        return BaseIP + value
    }
})

Vue.filter('sexFilter', function (value) {
    if (!value) return '保密'
    if (value == '0') return Global.MAN
    if (value == '1') return Global.WOMAN
})

Vue.filter('numberFilter', function (value) {
    if (!value) return 0
    else value;
})
