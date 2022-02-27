<template>
    <div class="app-container">
      <h2 style="text-align: center">发布新课程</h2>
  
      <el-steps
        :active="1"
        process-status="wait"
        align-center
        style="margin-bottom: 40px"
      >
        <el-step title="填写课程基本信息" />
  
        <el-step title="创建课程大纲" />
  
        <el-step title="提交审核" />
      </el-steps>

      <el-form label-width="120px">
            <el-form-item label="课程标题">	
                <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课"/>
            </el-form-item>


            <!-- 所属分类：级联下拉列表 -->
            <!-- 一级分类 -->
            <el-form-item label="课程类别">
                <el-select
                  v-model="courseInfo.subjectParentId"
                  placeholder="一级分类" @change="subjectLevelOneChanged">
                  <el-option
                  v-for="subject in subjectOneList"
                  :key="subject.id"
                  :label="subject.title"
                  :value="subject.id"/>
                </el-select>


                <!-- 二级分类 -->
                <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
                  <el-option
                  v-for="subject in subjectTwoList"
                  :key="subject.id"
                  :label="subject.title"
                  :value="subject.id"/>
                  </el-select>
             </el-form-item>
            
            
            <!-- 课程讲师 -->
            <el-form-item label="课程讲师">
              <el-select v-model="courseInfo.teacherId" placeholder="请选择">
                <el-option
                  v-for="teacher in teacherList"
                  :key="teacher.id"
                  :label="teacher.name"
                  :value="teacher.id"
                />
              </el-select>
            </el-form-item>

            <el-form-item label="总课时">
                <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-				position="right"/>
            </el-form-item>
            
            
            <!-- 课程简介 TODO -->
            <!-- 课程简介-->
            <el-form-item label="课程简介">
              <tinymce :height="300" v-model="courseInfo.description"/>
            </el-form-item>
            

             <!-- 课程封面-->
            <el-form-item label="课程封面">
              <el-upload
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :action="BASE_API + '/eduoss/fileoss/uploadAvartar'"
                class="avatar-uploader"
              >
                <img :src="courseInfo.cover" width="150px" height="150px"/>
              </el-upload>
            </el-form-item>

            <!-- <img :src="https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/01/12/a80f9ea5a6fd44a5aca6c515dc6634d7file.png"> -->
   


            <el-form-item label="课程价格">
                <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" />
            </el-form-item>
            
            <el-form-item>
                <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button >
            </el-form-item>
        </el-form>    
    </div>
  </template>
  


<script>

import course from '@/api/edu/course'
import subject from '@/api/edu/subject'
import teacher from '@/api/edu/teacher'
import Tinymce from '@/components/Tinymce'

  export default {
    components: { Tinymce },
    data() {
      return {
        courseInfo:{
          title: "",            //课程名称
          teacherId: "",        //所属讲师ID
          subjectParentId: "",  //所属分类：父级ID
          subjectId : "",       //所属分类：子级ID
          price: 0,             //课程价格
          lessonNum: 0,         //课时数
          description: "",      //课程简介
          cover: "https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/01/12/a80f9ea5a6fd44a5aca6c515dc6634d7file.png",            //课程封面图片路径
        },  


        saveBtnDisabled: false,  //保存按钮是否禁用
        teacherList : {},        //讲师列表
        subjectOneList :{},      //（1级）分类列表
        subjectTwoList:{},       //（2级）分类列表
        BASE_API: process.env.BASE_API,
        courseID:""              //课程ID
      };
    },
  
    created() {
      //存在：courseID (为修改)
      if(this.$route.params && this.$route.params.id){
          this.courseID = this.$route.params.id
          this.getCourseInfo()   //查询courseInfo信息

      }else{ //不存在：courseID (为添加)
          //获取父级：分类列表
          this.subjectOneListInfo()
      }

      //获取讲师列表
      this.getTeacherList()    
    },
  
    methods: {
      //1、下一步
      next() {  
        this.addOrUpdate()     //添加课程（或）修改课程
      },

      //2、获取讲师列表
      getTeacherList(){   
        teacher.getTeacherListInfo()
               .then(response=>{
                  this.teacherList = response.data.items
               })
      },


      //3、获取父级：分类列表
      subjectOneListInfo(){
         subject.getSubjectList()
                .then(response=>{
                  this.subjectOneList = response.data.list
                })
      },


      //4、二级联动
      subjectLevelOneChanged(value){
         this.courseInfo.subjectId = ''   //清空2级分类：上一次的选择

          //遍历：1级标题  ===>>> 选出选中的（1级标题）,  打印（2级标题）
          for(let i = 0; i < this.subjectOneList.length; i++){
             if(this.subjectOneList[i].id == value){
               this.subjectTwoList = this.subjectOneList[i].children
             }
          }
      },

      //5、上传封面成功调用的方法
      handleAvatarSuccess(res, file) {
        this.courseInfo.cover = res.data.url;
      },

      //6、上传之前调用的方法
      beforeAvatarUpload(file) {
        const isJPG = file.type === "image/jpeg";
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error("上传头像图片只能是 JPG 格式!");
        }
        if (!isLt2M) {
          this.$message.error("上传头像图片大小不能超过 2MB!");
        }
        return isJPG && isLt2M;
      },


      //7、通过courseID查询：courseInfo的信息
      getCourseInfo(){
        course.getCourseInfoById(this.courseID)
              .then(response=>{
                this.courseInfo = response.data.courseInfoVo    //回显课程信息

                //为：分类（ID 对应名称）
                subject.getSubjectList()    //查询出所有的：1级，2级分类
                       .then(response =>{
                          this.subjectOneList = response.data.list  //获取到1级分类

                          for(var i = 0; i < this.subjectOneList.length; i++){
                            if(this.courseInfo.subjectParentId === this.subjectOneList[i].id){
                               this.subjectTwoList = this.subjectOneList[i].children
                            }
                          }
                       })
              })
      },

      //8、添加课程 （或）修改课程
      addOrUpdate(){
        if(this.courseID){    //修改课程
          course.updCourse(this.courseInfo)
                .then(response=>{
                  this.$message({       //提示信息
                            type: 'success',
                            message: '课程修改成功!'
                        });
                  this.$router.push({ path: "/course/chapter/" + this.courseID});
                })
        }else{  //添加：课程
          course.addCourseInfo(this.courseInfo)
              .then(response=>{
                this.$message({       //提示信息
                            type: 'success',
                            message: '课程添加成功!'
                        });
                  this.$router.push({ path: "/course/chapter/" + response.data.courseId});
              })
        }
      },
    },
  };
  </script>

<style scoped>
  .tinymce-container {
  line-height: 29px;
  }
</style>
  