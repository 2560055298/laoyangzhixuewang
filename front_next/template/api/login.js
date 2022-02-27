import request from '@/utils/request'

export default {
  // 登录
  memberLogin(loginVo) {
    return request({
      url: `api/ucenter/member/memberLogin`,
      method: 'post',
      data: loginVo
    })
  },

  // 根据token值：拿到顾客信息
  getMemberInfo() {
    return request({
      url: `api/ucenter/member/getMemberInfo`,
      method: 'get'
    })
  }
}
