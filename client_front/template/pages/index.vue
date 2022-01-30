<template>
  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div v-for="banner in bannerList" :key="banner.id" class="swiper-slide" style="background: #040B1B;">
          <a :href="banner.linkUrl" target="_blank">
            <img
              :src="banner.imageUrl"
              :alt="banner.title"
              width="100%">
          </a>
        </div>

      </div>
      <div class="swiper-pagination swiper-pagination-white"/>
      <div slot="button-prev" class="swiper-button-prev swiper-button-white"/>
      <div slot="button-next" class="swiper-button-next swiper-button-white"/>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门课程</span>
            </h2>
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
                      >
                      <div class="cc-mask">
                        <a href="#" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" title="听力口语" class="course-title fsize18 c-333">{{ course.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span v-if="Number(course.price) === 0" class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">免费</i>
                      </span>
                      <span v-else class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA"> ￥{{ course.price }}</i>
                      </span>

                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ course.buyCount }} 人学习</i>
                        |
                        <i class="c-999 f-fA">{{ course.viewCount }} 人访问</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"/>
            </article>

            <section class="tac pt20">
              <a href="#" title="全部课程" class="comm-btn c-btn-2">全部课程</a>
            </section>
          </div>

        </section>
      </div>
      <!-- /网校课程 结束 -->
      <!-- 网校名师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">名师大咖</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="teacher in teacherList" :key="teacher.id">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a :title="teacher.name" href="/teacher/1">
                        <img :src="teacher.avatar" :alt="teacher.name">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a :title="teacher.name" href="/teacher/1" class="fsize18 c-666">{{ teacher.name }}</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{ teacher.level }}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p
                        class="c-999 f-fA"
                      >{{ teacher.intro }}</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"/>
            </article>
            <section class="tac pt20">
              <a href="#" title="全部讲师" class="comm-btn c-btn-2">全部讲师</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<!-- 幻灯片 开始 -->
<div v-swiper:mySwiper="swiperOption">
  <div class="swiper-wrapper">
      <div class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" href="/">
              <img src="~/assets/photo/banner/1525939573202.jpg" alt="首页banner">
          </a>
      </div>
      <div class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" href="/">
              <img src="~/assets/photo/banner/1525939573202.jpg" alt="首页banner">
          </a>
      </div>
  </div>
  <div class="swiper-pagination swiper-pagination-white"></div>
  <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
  <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
</div>
<!-- 幻灯片 结束 -->

<script>
  import bannerApi from "@/api/banner"
  import indexApi from "@/api/index"

  export default {
    data () {
      return {
        swiperOption: {
          //配置分页
          pagination: {
            el: '.swiper-pagination'//分页的dom节点
          },
          //配置导航
          navigation: {
            nextEl: '.swiper-button-next',//下一页dom节点
            prevEl: '.swiper-button-prev'//前一页dom节点
          }
        },

        bannerList : {},      //首图集合
        courseList : {},      //课程集合
        teacherList : {},     //讲师集合
      }
    },

    //页面预加载
    created() {
      this.initDataBanner()   //初始化：首图
      this.initDataCourse()   //初始化：课程
      this.initDataTeacher()  //初始化：讲师
    },

    methods:{
      //1、初始化：首图
      initDataBanner() {
        bannerApi.getListBanner().then(response => {
          console.log(response)
          this.bannerList = response.data.data.items
        })
      },

      //2、初始化：课程
      initDataCourse() {
        indexApi.getListCourse().then(response => {
          console.log(response)
          this.courseList = response.data.data.items
        })
      },

      //3、初始化：讲师
      initDataTeacher() {
        indexApi.getListTeacher().then(response => {
          console.log(response)
          this.teacherList = response.data.data.items
        })
      },
    }

  }
  </script>
