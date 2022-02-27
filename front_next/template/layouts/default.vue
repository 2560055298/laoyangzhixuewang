<template>
  <div class="in-wrap">
    <!-- 公共头引入 -->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="老洋自学网">
            <img src="~/assets/img/logo.png" width="100%" alt="老洋自学网">
          </a>
        </h1>
        <div class="h-r-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>首页</a>
            </router-link>
            <router-link to="/course" tag="li" active-class="current">
              <a>课程</a>
            </router-link>
            <router-link to="/teacher" tag="li" active-class="current">
              <a>讲师</a>
            </router-link>
            <router-link to="/hope" tag="li" active-class="current">
              <a>网站愿景</a>
            </router-link>

            <li><a href="https://www.yangzaikongzhongfei.com/new" target="_blank">后台登录</a></li>
          </ul>
          <!-- / nav -->
          <ul class="h-r-login">
            <li v-show="!loginInfo.id" id="no-login">
              <a href="/login" title="登录">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5" style="font-size: 20px;">登录</span>
              </a>

              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <a href="/register" title="注册">
                <img src="~/assets/img/regi.png" style="width: 20px; vertical-align: middle;">
                <span class="vam ml5" style="font-size: 20px;">注册</span>
              </a>
            </li>
            
            <li v-if="loginInfo.id" id="is-login-one" class="mr10">
              <a id="headerMsgCountId" href="#" title="消息">
                <em class="icon13 ">&nbsp;</em>
              </a>
              <q class="red-point" style="display: none">&nbsp;</q>
            </li>

            <li v-if="loginInfo.id" id="is-login-two" class="h-r-user">
              <a href="/ucenter" title>
                <img
                  :src="loginInfo.avatar"
                  width="30"
                  height="30"
                  class="vam picImg"
                  alt
                >
                <span id="userName" class="vam disIb">{{ loginInfo.nickname }}</span>
              </a>
              <a href="javascript:void(0);" title="退出" class="ml5" @click="logout()">退出</a>
            </li>
            <!-- /未登录显示第1 li；登录后显示第2，3 li -->
          </ul>

        </div>
        <aside class="mw-nav-btn">
          <div class="mw-nav-icon"/>
        </aside>
        <div class="clear"/>
      </section>
    </header>
    <!-- /公共头引入 -->

    <nuxt/>

    <!-- 公共底引入 -->
    <footer id="footer" >
      <section class="container">
        <div class="b-foot">
          <section class="b-f-link mt20" style="font-size: 18px; text-align:center">
            <span>Copyright ©1997 - 2022 老洋自学网</span>
          </section>

          <section class="b-f-link mt20 " style="font-size: 18px; text-align:center">
            <img src="~/assets/img/gongan.jpg" style="width: 20px;">
            <a href="http://beian.miit.gov.cn" target="_blank" style="color: #6a6b6b;" @mouseover="mouseOver" @mouseleave="mouseLeave"><span :style="active"> 湘公网安备 43112502431137号 湘ICP备2021005178号-1</span></a>
          </section>
          <div class="clear"/>
        </div>
      </section>
    </footer>
    <!-- /公共底引入 -->
  </div>
</template>
<script>
import '~/assets/css/reset.css'
import '~/assets/css/theme.css'
import '~/assets/css/global.css'
import '~/assets/css/web.css'
import '~/assets/css/base.css'
import '~/assets/css/activity_tab.css'
import '~/assets/css/bottom_rec.css'
import '~/assets/css/nice_select.css'
import '~/assets/css/order.css'
import '~/assets/css/swiper-3.3.1.min.css'
import '~/assets/css/pages-weixinpay.css'
import cookie from 'js-cookie'
import loginApi from '@/api/login'

export default {
  data() {
    return {
      token: '',
      loginInfo: {
        id: '',
        age: '',
        avatar: '',
        mobile: '',
        nickname: '',
        sex: ''
      },
      active: ''
    }
  },

  created() {
    // this.token = this.$route.query.token

    // if (this.token) { // 存在token：说明进行了（微信登录）
    //   console.log("我有：token")
    //   cookie.set('guli_token', this.token, { domain: 'www.yangzaikongzhongfei.com' })
    //   this.showWxInfo() // 显示微信登录信息
    // } else {
    //   console.log("我没有：token")
    //   this.showInfo()
    // }
  },

  mounted(){
    this.token = this.$route.query.token

    if (this.token) { // 存在token：说明进行了（微信登录）
      console.log("我有：token")
      cookie.set('guli_token', this.token, { domain: 'www.yangzaikongzhongfei.com' })
      this.showWxInfo() // 显示微信登录信息
    } else {
      console.log("我没有：token")
      this.showInfo()
    }
  },

  methods: {
    // 显示登录信息
    showInfo() {
      var userStr = cookie.get('guli_ucenter')

      // 将字符串：转换为json对象
      if (userStr) {
        this.loginInfo = JSON.parse(userStr)
        console.log("用户信息：" + this.loginInfo.id)
        console.log("用户信息：" + this.loginInfo.nickname)
        console.log("用户信息：" + this.loginInfo.avatar)
      }
    },

    // 显示（微信用户）登录信息
    showWxInfo() {
      loginApi.getMemberInfo()
        .then(response => {
          this.loginInfo = response.data.data.memberInfo
        })
    },

    // 退出
    logout() {
      cookie.set('guli_ucenter', '', { domain: 'www.yangzaikongzhongfei.com' })
      cookie.set('guli_token', '', { domain: 'www.yangzaikongzhongfei.com' })
      window.location.href = '/'
    },

    mouseOver: function() {
      this.active = 'color:pink;'
    },
    mouseLeave: function() {
      this.active = ''
    }
  }
}
</script>
