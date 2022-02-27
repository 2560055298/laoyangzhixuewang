import request from '@/utils/request'

export default {
  // 1、创建订单
  createOrder(courseId) {
    return request({
      url: `/eduorder/order/createOrder/${courseId}`,
      method: 'post'
    })
  },

  // 2、根据(订单ID)获取订单
  getOrderById(orderNo) {
    return request({
      url: `/eduorder/order/getOrderById/${orderNo}`,
      method: 'get'
    })
  },

  // 3、生成：微信支付（二维码）
  createQrCodePayment(orderNo) {
    return request({
      url: `/eduorder/payLog/createQrCodePayment/${orderNo}`,
      method: 'get'
    })
  },

  // 4、通过orderNo查询支付状态
  queryPayStatus(orderNo) {
    return request({
      url: `/eduorder/payLog/queryPayStatus/${orderNo}`,
      method: 'get'
    })
  }
}
