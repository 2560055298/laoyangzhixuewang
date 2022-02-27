<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl style="border:0px;">
            <dt>
              <span class="c-999 fsize14">
                <el-button type="primary" disabled size="medium" style="border: 0px; font-size: 16px;font-weight: 500;" >类别</el-button>
              </span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li
                  v-for="(oneSubject, index) in oneSubjectList"
                  :key="index"
                >
                  <a :title="oneSubject.title" href="javascript:void(0);" @click="searchOne(oneSubject.id, index)"><el-button :class="{active1:oneIndex==index}" size="medium" plain>{{ oneSubject.title }}</el-button></a>
                </li>
              </ul>
            </dd>
          </dl>

          <dl style="border: 0px;">
            <dt >
              <span class="c-999 fsize14">
                <el-button type="warning" disabled size="medium" style="border: 0px; font-size: 16px;font-weight: 500;" >课程</el-button>
              </span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(twoSubject, index) in twoSubjectList" :key="index" >
                  <a :title="twoSubject.title" href="javascript:void(0);" @click="searchTwo(twoSubject.id, index)"><el-button :class="{active2:twoIndex==index}" type="danger" size="small" round plain>{{ twoSubject.title }}</el-button></a>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"/>
        </section>
        <div class="js-wrap" style="top: 16px;">

          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{'current my-color-blank':frontCourseVo.buyCountSort!=''}">
                <a title="销量" href="javascript:void(0);" @click="searchBuyCount()">销量
                  <span :class="{hide:frontCourseVo.buyCountSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current my-color-blank':frontCourseVo.gmtCreateSort!=''}">
                <a title="最新" href="javascript:void(0);" @click="searchGmtCreate()">最新
                  <span :class="{hide:frontCourseVo.gmtCreateSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current my-color-blank':frontCourseVo.priceSort!=''}">
                <a title="价格" href="javascript:void(0);" @click="searchPrice()">价格&nbsp;
                  <span :class="{hide:frontCourseVo.priceSort==''}">↓</span>
                </a>
              </li>
            </ol>
          </section>

        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section v-if="courseMap.total === 0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list">
            <ul id="bna" class="of">
              <li v-for="(course, index) in courseMap.records" :key="index">
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
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a
              :class="{undisable: !courseMap.hasPrevious}"
              href="#"
              title="首页"
              style="width: 40px;border-radius: 3px;"
              @click.prevent="gotoPage(1)">首页</a>
            <a
              :class="{undisable: !courseMap.hasPrevious}"
              href="#"
              title="前一页"
              @click.prevent="gotoPage(courseMap.current-1)">&lt;</a>
            <a
              v-for="page in courseMap.pages"
              :key="page"
              :class="{current: courseMap.current == page, undisable: courseMap.current == page}"
              :title="'第'+page+'页'"
              href="#"
              @click.prevent="gotoPage(page)">{{ page }}</a>

            <a
              :class="{undisable: !courseMap.hasNext}"
              href="#"
              title="前一页"
              @click.prevent="gotoPage(courseMap.current+1)">&gt;</a>
            <a
              :class="{undisable: !courseMap.hasNext}"
              hei
              href="#"
              title="尾页"
              style="width: 40px;border-radius: 3px;"
              @click.prevent="gotoPage(courseMap.pages)">尾页</a>
            <div class="clear"/>
          </div>
        </div>
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>

<script>
import courseApi from '@/api/course'

export default {
  data() {
    return {
      currentPage: '1',
      limit: 8,
      frontCourseVo: {
        title: '', // 课程名称
        teacherId: '', // 讲师id
        subjectParentId: '', // 一级类别id
        subjectId: '', // 二级类别id
        buyCountSort: '', // 销量排序
        gmtCreateSort: '', // 最新时间排序
        priceSort: '' // 价格排序
      },

      courseMap: {}, // 分页：课程map信息

      oneSubjectList: {}, // 一级分类
      oneIndex: '-1', // 一级分类（选中）当前索引

      twoSubjectList: {}, // 二级分类
      twoIndex: '-1' // 二级分类（选中）当前索引
    }
  },

  created() {
    this.initOneSubject() // 初始化：1级分类
    this.initCourseList() // 初始化：课程信息
  },

  methods: {
    // 1、初始化：一级课程分类
    initOneSubject() {
      courseApi.getSubjectList()
        .then(response => {
          console.log('1级分类：', response)
          this.oneSubjectList = response.data.data.oneAndTwoSubjects
          console.log('oneSubjectList ==>>', this.oneSubjectList)
        })
    },

    // 2、初始化：课程列表（初始 currentPage = 1,  limit = 8,  frontCourseVo = null）
    initCourseList() {
      courseApi.getPageCourse(1, 8, this.frontCourseVo)
        .then(response => {
          console.log('课程列表：', response)
          this.courseMap = response.data.data.courseMap

          console.log('map信息：', this.courseMap)
        })
    },

    // 3、跳转页面：gotoPage(1)
    gotoPage(page) {
      if (page > this.courseMap.pages) {
        page = this.courseMap.pages
      }
      console.log('当前页：', page)
      courseApi.getPageCourse(page, 8, this.frontCourseVo)
        .then(response => {
          this.courseMap = response.data.data.courseMap
          console.log('总页数：', this.courseMap.pages)
          console.log('是否可以下一页：', this.courseMap.hasNext)
        })
    },

    // 4、点击一级标题时
    searchOne(oneSubjectId, index) {
      this.frontCourseVo = {}

      console.log(this.oneSubjectList.length)
      console.log('一级标题ID：', oneSubjectId)
      for (let i = 0; i < this.oneSubjectList.length; i++) {
        if (this.oneSubjectList[i].id === oneSubjectId) {
          this.oneIndex = index // 赋值索引
          this.frontCourseVo.subjectParentId = this.oneSubjectList[i].id // 给一级标题搜索赋值
          this.twoSubjectList = this.oneSubjectList[i].children // 给二级分类赋值
        }
      }

      // 清空二级标题
      this.twoIndex = '-1' // 二级分类（选中）当前索引

      // 清空：（销量、最新时间、价格）
      this.frontCourseVo.buyCountSort = ''// 销量排序
      this.frontCourseVo.gmtCreateSort = ''// 最新时间排序
      this.frontCourseVo.priceSort = '' // 价格排序

      // 调用赋值
      courseApi.getPageCourse(1, 8, this.frontCourseVo)
        .then(response => {
          console.log('课程列表：', response)
          this.courseMap = response.data.data.courseMap

          console.log('map信息：', this.courseMap)
        })
    },

    // 5、点击二级级标题时
    searchTwo(twoSubjectId, index) {
      for (let i = 0; i < this.twoSubjectList.length; i++) {
        if (this.twoSubjectList[i].id === twoSubjectId) {
          this.twoIndex = index // 赋值索引
          this.frontCourseVo.subjectId = this.twoSubjectList[i].id // 给一级标题搜索赋值
        }
      }

      // 调用赋值
      courseApi.getPageCourse(1, 8, this.frontCourseVo)
        .then(response => {
          console.log('课程列表：', response)
          this.courseMap = response.data.data.courseMap

          console.log('map信息：', this.courseMap)
        })
    },

    // 6、 销量降序
    searchBuyCount() {
      this.frontCourseVo.buyCountSort = '1'// 销量排序
      this.frontCourseVo.gmtCreateSort = ''// 最新时间排序
      this.frontCourseVo.priceSort = '' // 价格排序

      // 调用赋值
      courseApi.getPageCourse(1, 8, this.frontCourseVo)
        .then(response => {
          console.log('课程列表：', response)
          this.courseMap = response.data.data.courseMap

          console.log('map信息：', this.courseMap)
        })
    },

    // 7、时间降序
    searchGmtCreate() {
      this.frontCourseVo.buyCountSort = '' // 销量排序
      this.frontCourseVo.gmtCreateSort = '1' // 最新时间排序
      this.frontCourseVo.priceSort = '' // 价格排序

      // 调用赋值
      courseApi.getPageCourse(1, 8, this.frontCourseVo)
        .then(response => {
          console.log('课程列表：', response)
          this.courseMap = response.data.data.courseMap

          console.log('map信息：', this.courseMap)
        })
    },

    // 8、价格降序
    searchPrice() {
      this.frontCourseVo.buyCountSort = ''// 销量排序
      this.frontCourseVo.gmtCreateSort = ''// 最新时间排序
      this.frontCourseVo.priceSort = '1' // 价格排序

      // 调用赋值
      courseApi.getPageCourse(1, 8, this.frontCourseVo)
        .then(response => {
          console.log('课程列表：', response)
          this.courseMap = response.data.data.courseMap

          console.log('map信息：', this.courseMap)
        })
    }
  }
}
</script>

<style scoped>
  .active1 {
    color: #46a1ff;
    border: #46a1ff solid 1px;
    font-weight: 500;
  }

  .active2 {
    font-weight: 500;
    color: #fff;
    background-color: #f56c6c;
  }

  .hide {
    display: none;
  }
  .show {
    display: block;
  }
</style>
