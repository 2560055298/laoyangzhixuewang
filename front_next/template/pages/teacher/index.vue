<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 讲师列表 开始 -->
    <section class="container">

      <div style="text-align:center">
        <h2 style="font-size:30px;margin:30px;font-weight: 500;">全部讲师</h2>
      </div>

      <hr style="height:1px; background-color: rgb(8, 8, 8); margin-bottom: 40px;">
      <div/>

      <section class="c-sort-box unBr">
        <div>
          <!-- /无数据提示 开始-->
          <section v-if="data.total == 0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="i-teacher-list">
            <ul class="of" width="250px" height="300px">
              <li v-for="teacher in data.data.records" :key="teacher.id" >
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
        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a
              :class="{undisable: !data.data.hasPrevious}"
              href="#"
              title="首页"
              style="width: 40px;border-radius: 3px;"
              @click.prevent="gotoPage(1)">首页</a>
            <a
              :class="{undisable: !data.data.hasPrevious}"
              href="#"
              title="前一页"
              @click.prevent="gotoPage(data.data.current-1)">&lt;</a>
            <a
              v-for="page in data.data.pages"
              :key="page"
              :class="{current: data.data.current == page, undisable: data.current == page}"
              :title="'第'+page+'页'"
              href="#"
              @click.prevent="gotoPage(page)">{{ page }}</a>
            <a
              :class="{undisable: !data.data.hasNext}"
              href="#"
              title="后一页"
              @click.prevent="gotoPage(data.data.current+1)">&gt;</a>
            <a
              :class="{undisable: !data.data.hasNext}"
              href="#"
              title="尾页"
              @click.prevent="gotoPage(data.data.pages)">尾页</a>
            <div class="clear"/>
          </div>
        </div>
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /讲师列表 结束 -->
  </div>
</template>

<script>
import teacherAPI from '@/api/teacher'

export default {
  asyncData({ params, error }) {
    return teacherAPI.getPageTeacher(1, 8).then(response => {
      console.log(response.data.data)
      return { data: response.data.data }
    })
  },

  methods: {
    gotoPage(page) {
      if (page > this.data.data.pages) {
        page = this.data.data.pages
      }

      teacherAPI.getPageTeacher(page, 8).then(response => {
        this.data = response.data.data
      })
    }
  }
}

</script>
<style scoped>
  .active {
    background: #bdbdbd;
  }
  .hide {
    display: none;
  }
  .show {
    display: block;
  }
</style>
