import request from '@/utils/request'
export default {
    //1、查询：发布课程信息
    getCoursePublishVoById(courseId) {
        return request({
            url: `/eduservice/course/getCoursePublishVoById/${courseId}`,
            method: 'get'
          })
    },

    //2、确认发布
    confirmCourse(courseId) {
        return request({
            url: `/eduservice/course/confirmCourse/${courseId}`,
            method: 'get'
          })
    }
}
