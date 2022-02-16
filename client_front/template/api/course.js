import request from '@/utils/request'

export default {
  // 1、根据FrontCourseVo （分页查询）课程信息
  getPageCourse(currentPage, limit, frontCourseVo) {
    return request({
      url: `/eduservice/client/course/getPageCourse/${currentPage}/${limit}`,
      method: 'post',
      data: frontCourseVo
    })
  },

  // 2、查询：分类（1、2级）
  getSubjectList() {
    return request({
      url: `/eduservice/client/subject/getSubjectList`,
      method: 'get'
    })
  },

  // 3、根据课程ID：查询课程
  getCourse(courseId) {
    return request({
      url: `/eduservice/client/course/getCourse/${courseId}`,
      method: 'get'
    })
  }

}
