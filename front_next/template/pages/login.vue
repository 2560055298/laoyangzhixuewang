<template >
  <div class="main">
    <div class="title1">
      <a class="active1" href="/login" style="text-decoration: none;">登录</a>
      <span>·</span>
      <a href="/register" style="text-decoration: none;">注册</a>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">

        <el-form-item :rules="[{ required: true, message: '请输入手机号码', trigger: 'blur' },{validator: checkPhone, trigger: 'blur'}]" class="input-prepend restyle" prop="mobile">
          <div >
            <el-input v-model="user.mobile" type="text" placeholder="手机号"/>
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>

        <el-form-item :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]" class="input-prepend" prop="password">
          <div>
            <el-input v-model="user.password" type="password" placeholder="密码"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="submitLogin()">
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" href="#" style="text-decoration: none;cursor: not-allowed"><i class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" href="#" style="text-decoration: none;cursor: not-allowed;"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import loginApi from '@/api/login'
import cookie from 'js-cookie'

export default {
  layout: 'sign',

  data() {
    return {
      user: {
        mobile: '',
        password: ''
      },

      loginInfo: {} // 用户信息
    }
  },

  methods: {
    // 登录
    submitLogin() {
      loginApi.memberLogin(this.user)
        // 第一步：登录返回token值
        .then(response => {
          // 第二步：将token放入cookie中
          // cookie.set('guli_token', response.data.data.token, { domain: 'localhost' })   //windows
          cookie.set('guli_token', response.data.data.token, { domain: 'www.yangzaikongzhongfei.com' })   //linux

          // 第三步：使用拦截器, 将token放入header中(写在：request.js中)

          // 第四步：根据header获取到memberInfo
          loginApi.getMemberInfo()
            .then(response => {
              this.loginInfo = response.data.data.memberInfo

              // 第五步：将memberInfo放入cookie中
              // cookie.set('guli_ucenter', JSON.stringify(this.loginInfo), { domain: 'localhost' })   //windows
              cookie.set('guli_ucenter', JSON.stringify(this.loginInfo), { domain: 'www.yangzaikongzhongfei.com' })   //linux

              // 第六步：跳转到主页
              window.location.href = '/'
            })
        })
    },

    checkPhone(rule, value, callback) {
      // debugger
      if (!(/^1[34578]\d{9}$/.test(value))) {
        return callback(new Error('手机号码格式不正确'))
      }
      return callback()
    }
  }
}
</script>
<style>
  .el-form-item__error{
    z-index: 9999999;
  }

  body{
    background-image: url("../assets/img/10.jpg");
    background-repeat: no-repeat;
    height: 100%;
    width: 100%;
    position: fixed;
    background-size: 100% 100%;
  }
</style>
