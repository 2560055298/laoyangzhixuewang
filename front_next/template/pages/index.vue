<template>
  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div class="swiper-slide" style="background: #ffffff;max-width: 100%; max-height: 100%;">
          <img src="~/assets/photo/banner/gaozhong.png" alt="高中" style="max-width: 100%; max-height: 100%; height:100%; width: 100%;">
        </div>
        <div class="swiper-slide" style="background: #fdf3e8;">
          <img src="~/assets/photo/banner/daxue.png" alt="大学" width="100%" height="100%">
        </div>

        <div class="swiper-slide" style="background: #ffffff;">
          <img src="~/assets/photo/banner/shehui.jpg" alt="社会" width="100%" height="100%">
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-blanck"/>
      <div slot="button-prev" class="swiper-button-prev swiper-button-blanck"/>
      <div slot="button-next" class="swiper-button-next swiper-button-blanck"/>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <section class="tac pt20">
              <a :style="active" href="#" title="热门课程" style="color:#6b6b6b" class="comm-btn c-btn-2" @mouseover="mouseOver" @mouseleave="mouseLeave">热门课程</a>
            </section>
          </header>

          <div>
            <article class="comm-course-list">
              <ul id="bna" class="of">
                <li v-for="course in courseList" :key="course.id">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="course.cover"
                        :alt="course.title"
                        class="img-responsive"
                        style="width:267px;height:150px"
                      >
                      <div class="cc-mask">
                        <a :href="'/course/'+course.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a :title="course.title" href="#" style="text-decoration: none;" class="course-title fsize18 c-333">{{ course.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span v-if="Number(course.price) === 0" class="fr jgTag bg-blu" style="border-radius: 5px;">
                        <i class="c-fff fsize12 f-fA" style="color: #46a1ff;font-size: 18px;"> 免费</i>

                      </span>
                      <span v-else class="fr jgTag bg-red" style="border-radius: 5px;">
                        <i class="c-fff fsize12 f-fA" style="color: #f56c6c; font-size: 18px;"> ￥{{ course.price }}</i>
                      </span>

                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA" style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; font-size: 15px;">{{ course.buyCount }} 人学习</i>
                        |
                        <i class="c-999 f-fA">{{ course.viewCount }} 人访问</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"/>
            </article>

          </div>

          <el-divider/>
        </section>
      </div>

      <!-- /网校课程 结束 -->
      <!-- 网校名师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <section class="tac pt20">
                <a :style="active" href="#" style="color:#6b6b6b" title="优秀讲师" class="comm-btn c-btn-2" @mouseover="mouseOver" @mouseleave="mouseLeave">优秀讲师</a>
              </section>
            </h2>
          </header>

          <div>
            <article class="i-teacher-list">
              <ul class="of" width="250px" height="300px">
                <li v-for="teacher in teacherList" :key="teacher.id" >
                  <section class="i-teach-wrap" style="border: 1px solid #c1c0c0; border-radius: 5px;">
                    <div class="i-teach-pic">
                      <a :href="'/teacher/' + teacher.id" :title="teacher.name">
                        <img :src="teacher.avatar" width="140px" height="140px">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a :href="'/teacher/' + teacher.id" :title="teacher.name" class="fsize18 c-666">{{ teacher.name }}</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{ teacher.intro }}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p v-if="teacher.level === 2" style="color:#fed45e;font-size: 25px;" class="c-999 f-fA">★★★★★</p>
                      <p v-else class="c-999 f-fA" style="color:#fed45e;font-size: 25px;" >★★★★</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"/>
            </article>
          </div>
        </section>

      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
// import bannerApi from '@/api/banner'
import indexApi from '@/api/index'

export default {
  data() {
    return {
      swiperOption: {
        // 配置分页
        pagination: {
          el: '.swiper-pagination'// 分页的dom节点
        },
        // 配置导航
        navigation: {
          nextEl: '.swiper-button-next', // 下一页dom节点
          prevEl: '.swiper-button-prev'// 前一页dom节点
        }
      },
      courseList: {}, // 课程集合
      teacherList: {}, // 讲师集合
      active: ''
    }
  },

  // 页面预加载
  created() {
    this.initDataCourse() // 初始化：课程
    this.initDataTeacher() // 初始化：讲师
  },

  methods: {
    // 1、初始化：课程
    initDataCourse() {
      indexApi.getListCourse().then(response => {
        console.log(response)
        this.courseList = response.data.data.items
      })
    },

    // 2、初始化：讲师
    initDataTeacher() {
      indexApi.getListTeacher().then(response => {
        console.log(response)
        this.teacherList = response.data.data.items
      })
    },

    mouseOver: function() {
      this.active = 'color:#00a2e8'
    },
    mouseLeave: function() {
      this.active = ''
    }
  }

}
</script>
