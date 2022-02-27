import request from '@/utils/request'

export default {
  // 1、分页查询：名师信息（每页8条）
  getPageTeacher(currentPage, limit) {
    return request({
      url: `/eduservice/client/teacher/getPageTeacher/${currentPage}/${limit}`,
      method: 'get'
    })
  },

  // 2、根据讲师Id：查询讲师, 课程信息
  selTeacherById(teacherId) {
    return request({
      url: `/eduservice/client/teacher/selTeacherById/${teacherId}`,
      method: 'get'
    })
  }
}
