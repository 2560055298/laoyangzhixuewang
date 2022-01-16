import request from '@/utils/request'
export default {
    //获取章节信息
    getList(courseId) {
        return request({
            url: `/eduservice/chapter/getList/${courseId}`,
            method: 'get'
          })
    },
}
