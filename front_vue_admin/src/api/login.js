import request from '@/utils/request'

export function login(userInfo) {
  return request({
    url: '/eduservice/user/login',
    method: 'post',
    data: userInfo
  })
}

export function getInfo(token) {
  return request({
    url: '/eduservice/user/info',
    method: 'get',
    params: { token }
  })
}

