import request from '@/utils/request'

export default {
  getPlayAuth(videoSourceId) {
    return request({
      url: `service/vod/getVideoPlayAuth/${videoSourceId}`,
      method: 'get'
    })
  }
}
