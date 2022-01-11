import request from '@/utils/request'

export default{
            //从后端获取：带条件的（讲师分页信息）
            getTeacherPageInfo(current, limit, teacherQuery) {
                return request({
                    url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
                    method: 'post',
                    data: teacherQuery
            })
          },
    
        //通过Id逻辑删除讲师
        deleteTeacherById(id) {
            return request({
                url: `/eduservice/teacher/${id}`,
                method: 'delete'
            })
        },

        //保存OR添加讲师
        addTeacher(teacher) {
            return request({
                url: `/eduservice/teacher/addTeacher`,
                method: 'post',
                data: teacher
            })
        },

        //根据id查询：讲师信息
        getTeacher(id){
            return request({
                url: `/eduservice/teacher/getTeacher/${id}`,
                method: 'get'
            })
        },

        //修改teacher信息
        updateTeacher(teacher){
            return request({
                url: `/eduservice/teacher/updateTeacher`,
                method: 'post',
                data: teacher
            })     
        }
}

