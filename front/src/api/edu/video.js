import request from '@/utils/request'
export default {
    //1、添加：小节信息
    getList(courseId) {
        return request({
            url: `/eduservice/chapter/getList/${courseId}`,
            method: 'get'
          })
    },


}
