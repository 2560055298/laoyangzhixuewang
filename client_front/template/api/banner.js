import request from '@/utils/request'
export default {
  getListBanner() {
    return request({
      url: `/cmsservice/client/banner/getListBanner`,
      method: 'get'
    })
  }
}
