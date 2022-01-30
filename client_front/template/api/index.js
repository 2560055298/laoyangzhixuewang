import request from '@/utils/request'
export default {
  getListCourse() {
    return request({
      url: `/eduservice/client/course/getListCourse`,
      method: 'get'
    })
  },

  getListTeacher() {
    return request({
      url: `/eduservice/client/teacher/getListTeacher`,
      method: 'get'
    })
  }
}
