import axios from 'axios'
//请求封装
export function request(config) {

    const instance = axios.create({
        baseURL: '/api',
    })
    // 拦截器
    instance.interceptors.request.use(config => {
        return config
    }, err => {
        console.log(err);
    });
    // 响应拦截
    instance.interceptors.response.use(res => {
        // 做数据处理 返回data数据
        return res
    }, err => {
        console.log(
            err
        );
    }
    );
    // 返回实例 类型为promise
    return instance(config)
}