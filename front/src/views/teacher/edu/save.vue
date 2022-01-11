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

    export default {
        data(){
            return{
                teacher:{
                    name: "",       //讲师名称
                    sort: 0,        //讲师排序
                    level: "",      //讲师头衔
                    career:"",      //讲师资历
                    intro:"",        //讲师简介
                },
                saveBtnDisabled: false  //默认不可以提交
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
            }
        }
    }
</script>