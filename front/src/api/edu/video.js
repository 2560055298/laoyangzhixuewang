import request from '@/utils/request'
export default {
    //1、添加：小节信息
    addVideo(video) {
        return request({
            url: `/eduservice/video/addVideo`,
            method: 'post',
            data: video
          })
    },
    
    //2、查询：小节信息
    selVideo(videoId) {
        return request({
            url: `/eduservice/video/selVideo/${videoId}`,
            method: 'get'
            })
    },

    //3、修改：小节信息
    updVideo(video) {
        return request({
            url: `/eduservice/video/updVideo`,
            method: 'post',
            data: video
            })
    },

    //4、删除：小节信息
    delVideo(videoId) {
        return request({
            url: `/eduservice/video/delVideo/${videoId}`,
            method: 'get'
            })
    },
}
