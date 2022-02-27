import request from '@/utils/request'

export default {
    //1、根据某一天（yyyy-MM-dd） 添加注册人数
    addStatisics(day) {
    return request({
        url: `/statservice/statisticsDaily/addStatisics/${day}`,
        method: 'get'
    })
    },

    //2、根据：字段类型、开始时间、截止时间 -->>>查询statisics表
    queryStatisics(searchObj) {
        return request({
            url: `/statservice/statisticsDaily/queryStatisics/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
            method: 'get'
        })
    }
}