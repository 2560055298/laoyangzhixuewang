<template>
    <div class="app-container">
      <h2 style="text-align: center">发布新课程</h2>
  
      <el-steps
        :active="2"
        process-status="wait"
        align-center
        style="margin-bottom: 40px"
      >
        <el-step title="填写课程基本信息" />
  
        <el-step title="创建课程大纲" />
  
        <el-step title="提交审核" />
      </el-steps>
  
      <ul class="chanpterList">
        <li
            v-for="chapter in chapterList"
            :key="chapter.id">
            <p>
                {{ chapter.title }}
    
                <span class="acts">
                    <el-button type="text">添加课时</el-button>
                    <el-button style="" type="text">编辑</el-button>
                    <el-button type="text">删除</el-button>
                </span>
            </p>
    
            <!-- 视频 -->
            <ul class="chanpterList videoList">
                <li
                    v-for="video in chapter.children"
                    :key="video.id">
                    <p>{{ video.title }}
                        <span class="acts">
                            <el-button type="text">编辑</el-button>
                            <el-button type="text">删除</el-button>
                        </span>
                    </p>
                </li>
            </ul>
        </li>
    </ul>

      <el-form label-width="120px">

        <el-form-item>
          <el-button @click="previous">上一步</el-button>
  
          <el-button :disabled="saveBtnDisabled" type="primary" @click="next"
            >下一步</el-button
          >
        </el-form-item>
      </el-form>
    </div>



  </template>
  
  
  <script>

import chapter from '@/api/edu/chapter'
  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        chapterList: [],         //章节信息
        courseID:""
      };
    },
  
    created() {
      console.log("chapter created");
      
      if(this.$route.params && this.$route.params.id){
            this.courseID = this.$route.params.id
            this.getChapterList(this.$route.params.id);      //显示章节信息
      }
      
    },
  
    methods: {
      //上一步
      previous() {
        this.$router.push({ path: "/course/info/" + this.courseID});
      },
  
      //下一步
      next() {
        this.$router.push({ path: "/course/publish/1" });
      },

      getChapterList(courseId){
           chapter.getList(courseId)
                  .then(reponse =>{
                   console.log(reponse)
                   this.chapterList = reponse.data.list
          })
      }
    },
  };
  </script>
   <style scoped>
    .chanpterList{
        position: relative;
        list-style: none;
        margin: 0;
        padding: 0;
    }
    .chanpterList li{
      position: relative;
    }
    .chanpterList p{
      float: left;
      font-size: 20px;
      margin: 10px 0;
      padding: 10px;
      height: 70px;
      line-height: 50px;
      width: 100%;
      border: 1px solid #DDD;
    }
    .chanpterList .acts {
        float: right;
        font-size: 14px;
    }
    
    .videoList{
      padding-left: 50px;
    }
    .videoList p{
      float: left;
      font-size: 14px;
      margin: 10px 0;
      padding: 10px;
      height: 50px;
      line-height: 30px;
      width: 100%;
      border: 1px dotted #DDD;
    }
    
</style> 
