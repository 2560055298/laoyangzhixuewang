<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程详情 开始 -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
        /
        <a href="#" title class="c-999 fsize14">课程列表</a>
        /
        <span class="c-333 fsize14">{{ course.title }}</span>
      </section>
      <div>
        <article class="c-v-pic-wrap" style="height: 357px;">
          <section id="videoPlay" class="p-h-video-box">
            <img :alt="course.title" :src="course.cover" height="357px" style="width: 640px; height: 357px;" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{ course.title }}</span>
            </h2>
            <section class="c-attr-jg">
              <span class="c-fff" style="font-size: 24px;">价格：</span>
              <b class="c-yellow" style="font-size:24px;">￥{{ course.price }}</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14" style="font-size: 24px;">主讲： {{ course.teacherName }}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section v-if="Number(course.price) === 0 || buyStatus === true" class="c-attr-mt">
              <a title="立即观看" class="comm-btn c-btn-3" href="#">立即观看</a>
            </section>
            <section v-else class="c-attr-mt">
              <a title="立即购买" class="comm-btn c-btn-3" @click="createOrder(course.id)">立即购买</a>
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">购买数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ course.buyCount }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">课时数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ course.lessonNum }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">浏览数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ course.viewCount }}</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"/>
      </div>
      <!-- /课程封面介绍 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <p v-html="course.description">{{ course.description }}</p>
                    </section>
                  </div>
                </div>
                <!-- /课程介绍 -->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>课程大纲</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 文件目录 -->
                          <li v-for="charpter in chapterAndVideoList" :key="charpter.id" class="lh-menu-stair">
                            <a href="javascript: void(0)" title="第一章" class="current-1">
                              <em class="lh-menu-i-1 icon18 mr10"/>{{ charpter.title }}
                            </a>
                            <ol class="lh-menu-ol" style="display: block;">
                              <li v-for="video in charpter.children" :key="video.id" class="lh-menu-second ml30">
                                <a
                                  v-if="Number(course.price) === 0 || buyStatus === true || video.isFree === 1"
                                  :href="'/player/'+video.videoSourceId"
                                  :title="video.title"
                                  target="_blank">
                                  <span v-if="video.isFree === 1" class="fr">
                                    <i class="free-icon vam mr10">免费试听</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                                </a>

                                <!--用户没有登录：则需要登录-->
                                <a
                                  v-else-if="loginInfo.id === ''"    
                                  :href="'/login'"
                                  :title="video.title">
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                                </a>

                                <!--登录了没有购买-->
                                <a
                                  v-else
                                  href="#"
                                  @click="open3()"
                                  :title="video.title">
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                               </a>
                              </li>
                            </ol>
                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- /课程大纲 -->
              </article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">{{ course.teacherName }}</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li>
                    <div class="u-face">
                      <a href="#">
                        <img :src="course.avatar" width="50" height="50" alt>
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#"/>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{ course.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <div class="clear"/>
      </div>
    </section>
    <!-- /课程详情 结束 -->
  </div>
</template>

<script>
import courseAPI from '@/api/course'
import orderAPI from '@/api/order'
import cookie from 'js-cookie'

export default {
  asyncData({ params, error }) {
    return {
      courseId: params.id // 课程信息
    }
  },

  data() {
    return {
      course: {},
      chapterAndVideoList: {},
      buyStatus: 0,
      loginInfo: {
        id: '',
        age: '',
        avatar: '',
        mobile: '',
        nickname: '',
        sex: ''
      },
    }
  },

  created() {
    this.init()
  },

  mounted(){
     var userStr = cookie.get('guli_ucenter')

      // 将字符串：转换为json对象
      if (userStr) {
        this.loginInfo = JSON.parse(userStr)
        console.log("id：" + this.loginInfo.id)
        console.log("昵称：" + this.loginInfo.nickname)
        console.log("头像：" + this.loginInfo.avatar)
      }
  },

  methods: {
    init() {
      courseAPI.getCourse(this.courseId).then(response => {
        this.course = response.data.data.course // 课程信息
        this.chapterAndVideoList = response.data.data.chapterAndVideoList // 章、节信息
        this.buyStatus = response.data.data.buyStatus
        console.log(response.data.data.buyStatus)
        console.log(this.course.price)
      })
    },

    // 根据课程id，调用接口方法生成订单
    createOrder(courseId) {
      orderAPI.createOrder(courseId).then(response => {
        console.log(response)
        if (response.data.sccuess) {
          if (response.data.data.orderNo === '') {
            window.location.href = '/login'
          } else {
            // 订单创建成功，跳转到订单页面
            this.$router.push({ path: '/order/' + response.data.data.orderNo })
          }
        }
      })
    },

    open3() {
        this.$message({
          showClose: true,
          message: '亲, 请先购买哦~~~',
          type: 'success',
          center: true
        });
      },
  }
}
</script>
