import Widget from 'eros-widget'
import apis from './apis'
import routes from './routes'
import './push'

import {isDev, BaseIP, APP_NAME} from 'Config/IP'
import {alert, getMemberInfo} from "../utils/utils"
import {Global} from "../utils/global"

const bmAxios = weex.requireModule('bmAxios')
const bmStorage = weex.requireModule('bmStorage')

const domModule = weex.requireModule("dom");
domModule.addRule('fontFace', {
    'fontFamily': "iconfont-penpi",
    'src': "url('bmlocal://iconfont/iconfont.ttf')"
});

new Widget({
    router: {
        /**
         * 路由配置表
         */
        routes
    },
    ajax: {
        baseUrl: isDev ? BaseIP : (BaseIP + APP_NAME),

        /**
         * 接口别名
         */
        apis,
        // 接口超时时间
        timeout: 30000,

        /**
         * 请求发送统一拦截器 （可选）
         * options 你请求传入的所有参数和配置
         * next
         */
        requestHandler(options, next) {
            console.log('request-opts', options)

            if (apis[options.name]) {
                next()
            } else {
                alert(options.name + '还未定义呐')
            }
        },
        /**
         * 请求返回统一拦截器 （可选）
         * options 你请求传入的所有参数和配置
         * resData 服务器端返回的所有数据
         * resolve 请求成功请resolve你得结果，这样请求的.then中的成功回调就能拿到你resolve的数据
         * reject 请求成功请resolve你得结果，这样请求的.then中的失败回调就能拿到你reject的数据
         */
        responseHandler(options, resData, resolve, reject) {
            const {status, errorMsg, data} = resData
            if (status !== 200) {
                console.log(`invoke error status: ${status}`)
                console.log(`invoke error message: ${errorMsg}`)

                switch (status) {
                    case 502://bad gateway [没开服务器]
                    {
                        alert('服务器维护中...')
                        break;
                    }
                    case 403://服务端禁止请求，需重登录
                    {
                        // relogin(options, resolve, reject);
                        tologin();
                        break;
                    }
                    case 404://not found [bad request]
                    {
                        alert('[ ' + (apis[options.name] || options.url) + ' ] is 404 error');
                        break;
                    }
                    case undefined: {
                        alert('网络有点小问题哦，检查下网络吧~')
                        break;
                    }
                    default: {
                        alert(status + ' 错误')
                        break;
                    }
                }
                reject(data)
            } else {
                customHandler(options, data, resolve, reject);
            }
        }
    }
})

/**
 * 自定义请求响应逻辑
 */
function customHandler(options, data, resolve, reject) {
    // [errorObjectResp是服务器自定义的异常传输类]
    if (data.errorObjectResp) {
        reject(data.errorObjectResp.errorText)
    } else {
        resolve(data)
    }
}

/**
 * 跳转到登陆页面，先替代relogin
 */
export function tologin() {
    const bmRouter = weex.requireModule('bmRouter')
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
}

/**
 * 重登陆
 */
export function relogin(options, resolve, reject) {
    // let {status, data} = bmStorage.getDataSync(Global.STORAGE_MEMBER);
    // let member = JSON.parse(data);
    // alert(member)
    // bmAxios.fetch({
    //     method: 'GET',
    //     url: BaseIP + apis.relogin,
    //     data: {
    //         loginId: member.loginId,
    //     }
    // }, function (resData) {
    //     let {status, errorMsg, data} = resData
    //     if (data.errorObjectResp) {
    //         alert('relogin: ' + data.errorObjectResp.errorText + ' 错误')
    //     } else {
    //         bmStorage.setDataSync(Global.STORAGE_MEMBER, resData);
    //         reaxios(options, resolve, reject);
    //     }
    // })

    getMemberInfo(member => {
        bmAxios.fetch({
            method: 'GET',
            url: BaseIP + apis.relogin,
            data: {
                loginId: member.loginId,
            }
        }, resData => {
            let {status, errorMsg, data} = resData
            if (data.errorObjectResp) {
                alert('relogin: ' + data.errorObjectResp.errorText + ' 错误')
            } else {
                bmStorage.setDataSync(Global.STORAGE_MEMBER, data);
                reaxios(options, resolve, reject);
            }
        })
    })
}

/**
 * 重请求
 */
function reaxios(options, resolve, reject) {
    bmAxios.fetch({
        url: options.url || (BaseIP + apis[options.name]),
        data: options.data || {},
        method: options.method || 'GET',
        header: options.header || {},
        timeout: 15000
    }, (resData) => {
        if (resData.status === 403) {
            alert('reaxios: ' + resData.status + ' 错误')
        } else {
            customHandler(options, resData.data, resolve, reject)
        }
    })
}
