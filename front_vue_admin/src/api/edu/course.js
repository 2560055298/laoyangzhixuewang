import request from '@/utils/request'
export default {
    //1 添加课程信息
    addCourseInfo(courseInfo) {
        return request({
            url: '/eduservice/course/addCourse',
            method: 'post',
            data:courseInfo
          })
    },

    //2、通过courseID查询：courseInfo的信息
    getCourseInfoById(courseID){
        return request({
            url: `/eduservice/course/getCourse/${courseID}`,
            method: 'get'
          })
    },

    //3、修改课程信息
    updCourse(courseInfo) {
        return request({
            url: '/eduservice/course/updCourse',
            method: 'post',
            data:courseInfo
            })
    },


    //4、获取所有的：课程信息
    getListCourse(currentPage, limit, courseQuery) {
        return request({
                url: `/eduservice/course/getCourseList/${currentPage}/${limit}`,
                method: 'post',
                data:courseQuery
            })
    },

    //4、根据课程Id：删除课程
    delCourse(courseId) {
        return request({
            url: `/eduservice/course/delCourse/${courseId}`,
            method: 'get'
            })
    },

}
