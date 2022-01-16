import request from '@/utils/request'

export default{
            //获取课程分类信息（树型结构）
            getSubjectList() {
                return request({
                    url: `/eduservice/subject/getSubjectList`,
                    method: 'get'
            })
      }
}

