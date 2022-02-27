<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 讲师介绍 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac" >
          <span class="c-333">讲师介绍</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
        <!-- 讲师基本信息 -->
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="teacher.avatar" style="width:200px; height:200px">
            </section>
            <h3 class="hLh30">
              <span class="fsize24 c-333">姓名：{{ teacher.name }}</span>
            </h3>
            <section v-if="teacher.level === 2" class="mt10">
              <el-tag >首席讲师</el-tag>
            </section>

            <section v-else class="mt10">
              <el-tag >高级讲师</el-tag>
            </section>

            <hr style="height:1px; background-color:#c3bfbf; margin-top:20px">

            <section class="t-infor-txt">
              <p
                class="mt20"
              >{{ teacher.intro }}</p>
            </section>
            <div class="clear"/>
          </div>
        </section>
        <div class="clear"/>
      </div>
      <section class="mt30">
        <div>
          <header class="comm-title all-teacher-title c-course-content">
            <h2 class="fl tac">
              <span class="c-333">主讲课程</span>
            </h2>
            <section class="c-tab-title">
              <a href="javascript: void(0)">&nbsp;</a>
            </section>
          </header>
          <!-- /无数据提示 开始-->
          <section v-if="length==0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list">
            <ul class="of">
              <li v-for="(course, index) in courses" :key="index">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="course.cover" :alt="course.title" style="width:267px;height:150px" class="img-responsive" >
                    <div class="cc-mask">
                      <a :title="course.title" :href="'/course/'+course.id" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :title="course.title" href="'/course/'+course.id" class="course-title fsize18 c-333">{{ course.title }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span v-if="Number(course.price) === 0" class="fr jgTag bg-blu" style="border-radius: 5px;">
                      <i class="c-fff fsize12 f-fA" style="color: #46a1ff;font-size: 18px;"> 免费</i>

                    </span>
                    <span v-else class="fr jgTag bg-red" style="border-radius: 5px;">
                      <i class="c-fff fsize12 f-fA" style="color: #f56c6c; font-size: 18px;"> ￥{{ course.price }}</i>
                    </span>

                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{ course.view_count }}人学习</i>
                      |
                      <i class="c-999 f-fA">9634评论</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>
        </div>
      </section>
    </section>
    <!-- /讲师介绍 结束 -->
  </div>
</template>
<script>

import teacherAPI from '@/api/teacher'

export default {
  asyncData({ params, error }) {
    return teacherAPI.selTeacherById(params.id).then(response => {
      console.log(response)
      console.log('课程：' + response.data.data.courses[0])
      return {
        teacher: response.data.data.teacher,
        courses: response.data.data.courses,
        length: response.data.data.length
      }
    })
  }
}
</script>
