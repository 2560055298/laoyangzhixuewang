import request from '@/utils/request'
export default {
    //1、获取章节信息
    getList(courseId) {
        return request({
            url: `/eduservice/chapter/getList/${courseId}`,
            method: 'get'
          })
    },

    //2、添加章节
    addChapter(chapter) {
        return request({
            url: `/eduservice/chapter/addChapter`,
            method: 'post',
            data: chapter
          })
    },

    //3、查询：章信息
    selChapter(chapterId) {
        return request({
            url: `/eduservice/chapter/selChapter/${chapterId}`,
            method: 'get'
          })
    },

    //4、修改章节
    updChapter(chapter) {
        return request({
            url: `/eduservice/chapter/updChapter`,
            method: 'post',
            data: chapter
            })
    },

    //5、删除章节
    delChapter(chapterId) {
        return request({
            url: `/eduservice/chapter/delChapter/${chapterId}`,
            method: 'get'
            })
    },
}
