<template>
  <div class="main">
    <div class="title">
      <a href="/login" style="text-decoration: none;">登录</a>
      <span>·</span>
      <a class="active" href="/register" style="text-decoration: none;">注册</a>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="params">

        <el-form-item :rules="[{ required: true, message: '请输入你的昵称', trigger: 'blur' }]" class="input-prepend restyle" prop="nickname">
          <div>
            <el-input v-model="params.nickname" type="text" placeholder="你的昵称"/>
            <i class="iconfont icon-user"/>
          </div>
        </el-form-item>

        <el-form-item :rules="[{ required: true, message: '请输入手机号码', trigger: 'blur' },{validator: checkPhone, trigger: 'blur'}]" class="input-prepend restyle no-radius" prop="mobile">
          <div>
            <el-input v-model="params.mobile" type="text" placeholder="手机号"/>
            <i class="iconfont icon-phone"/>
          </div>
        </el-form-item>

        <el-form-item :rules="[{ required: true, message: '请输入验证码', trigger: 'blur' }]" class="input-prepend restyle no-radius" prop="code">
          <div style="width: 100%;display: block;float: left;position: relative">
            <el-input v-model="params.code" type="text" placeholder="验证码"/>
            <i class="iconfont icon-phone"/>
          </div>
          <div class="btn" style="position:absolute;right: 0;top: 6px;width: 40%;">
            <a :value="codeTest" href="javascript:" type="button" style="border: none;background-color: none; color: #ffaa0f;text-decoration: none;" @click="getCodeFun()">{{ codeTest }}</a>
          </div>
        </el-form-item>

        <el-form-item :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]" class="input-prepend" prop="password">
          <div >
            <el-input v-model="params.password" type="password" placeholder="设置密码"/>
            <i class="iconfont icon-password" />
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-up-button" value="注册" style="background-color: #36b9fc;" @click="submitRegister()">
        </div>

        <p class="sign-up-msg" style="margin-top: 20px;">
          点击 “注册” 即表示您同意并愿意遵守简书
          <br>
          <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">用户协议</a>
          和
          <a target="_blank" href="http://www.jianshu.com/p/2ov8x3">隐私政策</a> 。
        </p>
      </el-form>
    </div>
  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'

import registerApi from '@/api/register'

export default {
  layout: 'sign',
  data() {
    return {
      params: {
        mobile: '',
        code: '',
        nickname: '',
        password: ''
      },
      sending: true, // 是否发送验证码
      second: 60, // 倒计时间
      codeTest: '获取验证码'
    }
  },
  methods: {
    getCodeFun() {
      // sending = false
      // his.sending原为true,请求成功，!this.sending == true，主要是防止有人把disabled属性去掉，多次点击；
      if (!this.sending) { return }

      // debugger
      // prop 换成你想监听的prop字段
      this.$refs.userForm.validateField('mobile', (errMsg) => {
        if (errMsg === '') {
          registerApi.getMobile(this.params.mobile).then(res => {
            this.sending = false
            this.timeDown()
          })
        }
      })
    },

    timeDown() {
      const result = setInterval(() => {
        --this.second
        this.codeTest = this.second
        if (this.second < 1) {
          clearInterval(result)
          this.sending = true
          // this.disabled = false;
          this.second = 60
          this.codeTest = '获取验证码'
        }
      }, 1000)
    },

    submitRegister() {
      this.$refs['userForm'].validate((valid) => {
        // if (valid) {
        //   registerApi.submitRegister(this.params).then(response => {
        //     console.log('状态：' + response)

        //     // 提示注册成功
        //     this.$message({
        //       type: 'success',
        //       message: '注册成功'
        //     })
        //     this.$router.push({ path: '/login' })
        //   }).catch((err) => {
        //     // 提示注册成功
        //     this.$message({
        //       type: 'error',
        //       message: err
        //     })
        //     this.$router.push({ path: '/register' })
        //   })
        // }

        if (valid) {
          registerApi.submitRegister(this.params)
            .then(response => {
              if (response.data.code === 20000) {
                // 注册成功
                this.$message({
                  type: 'success',
                  message: '注册成功'
                })
                this.$router.push({ path: '/login' })
              } else if (response.data.code === 20001) {
                console.log(response.data.message)
                this.$message({
                  type: 'error',
                  message: response.data.message
                })
              }
            })
        }
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
    background-image: url("../assets/img/08.jpg");
    background-repeat: no-repeat;
    height: 100%;
    width: 100%;
    position: fixed;
    background-size: 100% 100%;
  }
</style>
