<template>
    <div class="app-container">
        <template>
            <div class="app-container">
              <el-form label-width="120px">
                <el-form-item label="讲师名称">
                  <el-input v-model="teacher.name" />
                </el-form-item>
                <el-form-item label="讲师排序">
                  <el-input-number
                    v-model="teacher.sort"
                    controls-position="right"
                    :min="0"
                  />
                </el-form-item>
                <el-form-item label="讲师头衔">
                  <el-select v-model="teacher.level" clearable placeholder="请选择">
                    <el-option :value="1" label="高级讲师" />
                    <el-option :value="2" label="首席讲师" />
                  </el-select>
                </el-form-item>
                <el-form-item label="讲师资历">
                  <el-input v-model="teacher.career" />
                </el-form-item>
                <el-form-item label="讲师简介">
                  <el-input v-model="teacher.intro" :rows="10" type="textarea" />
                </el-form-item>
          
                <!-- 讲师头像：TODO -->
                   <!-- 讲师头像 -->
                  <el-form-item label="讲师头像">
                    <!-- 头衔缩略图 -->
                    <pan-thumb :image="String(teacher.avatar)" />
                    <!-- 文件上传按钮 -->
                    <el-button
                      type="primary"
                      icon="el-icon-upload"
                      @click="imagecropperShow = true"
                      >更换头像
                    </el-button>

                    <!--
                  v-show：是否显示上传组件
                  :key：类似于id，如果一个页面多个图片上传控件，可以做区分
                  :url：后台上传的url地址
                  @close：关闭上传组件
                  @crop-upload-success：上传成功后的回调 
                  这里field的值必须和后端接口MultipartFile file的形参名相同
                    <input type="file" name="file"/>
                  -->
                    <image-cropper
                      v-show="imagecropperShow"
                      :width="300"
                      :height="300"
                      :key="imagecropperKey"
                      :url="BASE_API + '/eduoss/fileoss/uploadAvartar'"
                      field="file"
                      @close="close"
                      @crop-upload-success="cropSuccess"
                    />
                  </el-form-item>
          
                <el-form-item>
                  <el-button
                    :disabled="saveBtnDisabled"
                    type="primary"
                    @click="saveOrUpdate"
                    >保存</el-button
                  >
                </el-form-item>
              </el-form>
            </div>
          </template>
    </div>
  </template>
    
<script>
    import teacherApi from '@/api/edu/teacher'
    import ImageCropper from "@/components/ImageCropper";
    import PanThumb from "@/components/PanThumb";

    export default {
        components: { ImageCropper, PanThumb },

        data(){
            return{
                teacher:{
                    name: "",       //讲师名称
                    sort: 0,        //讲师排序
                    level: "",      //讲师头衔
                    career:"",      //讲师资历
                    intro:"",       //讲师简介
                    avatar: ""      //讲师头像
                },

                imagecropperShow: false,  //上传组件是否显示
                imagecropperKey: 0,       //图片的键值
                saveBtnDisabled: false,  //保存按钮（默认禁用）
                BASE_API: process.env.BASE_API //获取dev.env.js里面地址
            }
        },
        created(){
           this.init()
        },

        //监听路由
        watch:{
            $route(to, from){
              this.init()
            }
        },

        methods:{
            //1、保存 (或) 添加
            saveOrUpdate(){
                if(!this.teacher.id){
                  this.saveTeacher()      //添加
                }else{                   
                  this.updateTeacher()     //修改
                }
                
            },

            //2、添加讲师
            saveTeacher(){
                //添加讲师, 跳转并且提示
                teacherApi.addTeacher(this.teacher)
                          .then(response =>{
                                this.$message({                             //提示信息
                                    type: 'success',
                                    message: '添加成功!'
                                 });
                              this.$router.push({path:'/teacher/list'})    //跳转路由
                          })
            },
            //3、根据Id查询讲师信息（并进行回显）
            getInfo(id){
              teacherApi.getTeacher(id)
                        .then(response=>{
                          this.teacher = response.data.items
                        })
            },

            //4、根据teacher修改（teacher信息）
            updateTeacher(){
              teacherApi.updateTeacher(this.teacher)
                        .then(response=>{
                          this.$message({                                 //提示信息
                                    type: 'success',
                                    message: '修改成功!'
                                 });
                              this.$router.push({path:'/teacher/list'})    //跳转路由
                        })
            },

            //5、初始化
            init(){
                if(this.$route.params && this.$route.params.id){   //回显信息(携带了ID)
                  const id = this.$route.params.id
                  this.getInfo(id)  
              }else{   //不回显：没有携带ID
                this.teacher = {}
              }
            },

            //6、关闭上传的组件
            close(){
                //关闭上传弹框的办法
                this.imagecropperShow = false;
                // 上传失败后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
                this.imagecropperKey = this.imagecropperKey + 1;
            },

            //7、上传成功执行方法
            cropSuccess(data){
                 //这个方法封装好了返回值
                this.imagecropperShow = false;
                //上传之后接口返回图片地址
                this.teacher.avatar = data.url;
                this.imagecropperKey = this.imagecropperKey + 1;
            }
        }
    }
</script>