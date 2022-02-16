<template>
  <div class="cart py-container">
    <!--主内容-->
    <div class="checkout py-container pay">
      <div class="checkout-tit">
        <h4 class="fl tit-txt"><span class="success-icon"/><span class="success-info">订单提交成功，请您及时付款！订单号：{{ payInfo.out_trade_no }}</span>
        </h4>
        <span class="fr"><em class="sui-lead">应付金额：</em><em class="orange money">￥{{ payInfo.total_fee }}</em></span>
        <div class="clearfix"/>
      </div>
      <div class="checkout-steps">
        <div class="fl weixin">微信支付</div>
        <div class="fl sao">
          <p class="red">请使用微信扫一扫。</p>
          <div class="fl code">
            <!-- <img id="qrious" src="~/assets/img/erweima.png" alt=""> -->
            <!-- <qriously value="weixin://wxpay/bizpayurl?pr=R7tnDpZ" :size="338"/> -->
            <qriously :value="payInfo.code_url" :size="338"/>
            <div class="saosao">
              <p>请使用微信扫一扫</p>
              <p>扫描二维码支付</p>
            </div>

          </div>

        </div>
        <div class="clearfix"/>
        <!-- <p><a href="pay.html" target="_blank">> 其他支付方式</a></p> -->

      </div>
    </div>
  </div>
</template>

<script>
import orderApi from '@/api/order'

export default {
  // 根据订单id生成微信支付二维码
  asyncData({ params, error }) {
    return orderApi.createQrCodePayment(params.pid).then(response => {
      console.log('******************', response.data.data)
      return {
        payInfo: response.data.data.map
      }
    })
  },
  data() {
    return {
      timer: null, // 定时器名称
      initQCode: '',
      timer1: ''
    }
  },
  mounted() {
    // 在页面渲染之后执行
    // 每隔三秒，去查询一次支付状态
    this.timer1 = setInterval(() => {
      this.queryPayStatus(this.payInfo.out_trade_no)
    }, 3000)
  },
  methods: {
    // 查询支付状态的方法
    queryPayStatus(out_trade_no) {
      orderApi.queryPayStatus(out_trade_no).then(response => {
        console.log('##############################', response.data)
        if (response.data.data.trade_state === 'true') {
          // 如果支付成功，清除定时器
          clearInterval(this.timer1)
          this.$message({
            type: 'success',
            message: '支付成功!'
          })
          // 跳转到课程详情页面观看视频
          this.$router.push({ path: '/course/' + this.payInfo.course_id })
        }
      })
    }
  }
}
</script>
