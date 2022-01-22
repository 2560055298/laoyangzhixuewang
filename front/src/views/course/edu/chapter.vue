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

      <el-button type="text" @click="openChapterDialog()">添加章节</el-button>

    <!-- 章节 -->
    <ul class="chanpterList">
        <li
            v-for="chapter in chapterList"
            :key="chapter.id">
            <p>
                {{ chapter.title }}

                <span class="acts">
                    <el-button style="" type="text" @click="openVideo(chapter.id)">添加小节</el-button>
                    <el-button style="" type="text" @click="openEditChatper(chapter.id)">编辑</el-button>
                    <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
                </span>
            </p>

            <!-- 小节（视频） -->
            <ul class="chanpterList videoList">
                <li
                    v-for="video in chapter.children"
                    :key="video.id">
                    <p>{{ video.title }}

                <span class="acts">
                    <el-button style="" type="text" @click="openEditVideo(video.id)">编辑</el-button>
                    <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                </span>
                    </p>
                </li>
            </ul>
        </li>
    </ul>
    <div>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>


      <!-- 3、添加和修改章节表单 -->
      <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
        <el-form :model="chapter" label-width="120px">
            <el-form-item label="章节标题">
                <el-input v-model="chapter.title"/>
            </el-form-item>
            <el-form-item label="章节排序">
                <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
        </div>
    </el-dialog>

    <!-- 4、添加和修改（课时）表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="1">免费</el-radio>
            <el-radio :label="0">收费</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- TODO -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>

  </div>

  </template>
  
  
  <script>

import chapterApi from '@/api/edu/chapter'
import videoApi from '@/api/edu/video'
  export default {
    data() {
      return {
        chapterList: [],              //章节信息
        videoList:[],                 //小节信息
        courseID:"",
        dialogChapterFormVisible: false, //是否显示：章表单
        dialogVideoFormVisible: false,   //是否显示：小节表单
        saveBtnDisabled: false,       // （章）保存按钮是否禁用
        saveVideoBtnDisabled: false,      //(小节)保存按钮是否禁用

        // 章节对象
        chapter: {    
          courseId:"", 
          title: '',
          sort: 0
        },

        //课时对象
        video: {     
          id:"",          //视频ID
          courseId: "",   //课程ID
          chapterId: "",  //章节ID
          title: '',      //节点名称
          sort: 0,        //排序字段
          isFree: 1,      //是否可以试听： (1)免费  (0) 收费 
          videoSourceId: '' //云端视频资源
        },


      };
    },

    //页面前：加载
    created() {      
      if(this.$route.params && this.$route.params.id){
            this.courseID = this.$route.params.id
            this.getChapterList(this.$route.params.id);      //显示章节信息
      }
      
    },
  
    methods: {
      //1、上一步
      previous() {
        this.$router.push({ path: "/course/info/" + this.courseID});
      },
  
      //2、下一步
      next() {
        this.$router.push({ path: "/course/publish/" + this.courseID});
      },



      /*=======================================添加（章）===========================================*/
      //3、获取：章节列表
      getChapterList(courseId){
        chapterApi.getList(courseId)
                  .then(reponse =>{
                   console.log(reponse)
                   this.chapterList = reponse.data.list
          })
      },

      //4、保存（或）修改：章节
      saveOrUpdate(){
        //点击确定：若存在chapter.id那么进行修改, 否则进行保存
        if(this.chapter.id){  //修改
            console.log(this.chapter.id)
            this.updChapter()
        }else{                //保存
          this.saveChapter(this.chapter)
        }
      },

      //5、保存：章信息
      saveChapter(chapter){
          this.chapter.courseId = this.courseID   //设置：课程ID
          chapterApi.addChapter(chapter)
                    .then(response=>{
                      //关闭弹窗
                      this.dialogChapterFormVisible = false

                      //显示提交成功
                      this.$message({       //提示信息
                            type: 'success',
                            message: '章信息：添加成功!'
                        });

                      //刷新页面
                      this.getChapterList(this.courseID)
                    })
      },

      //6、点击添加按钮：调用方法（清空上一次内容，并弹出）
      openChapterDialog(){
        this.chapter = {}    //清空：弹出框内容
        this.dialogChapterFormVisible = true   //让弹框：出现
      },

      //7、编辑：章
      openEditChatper(chapterId){
        //根据chapterId查询出：章信息（回显数据）
        chapterApi.selChapter(chapterId)
                  .then(response=>{
                    this.chapter = response.data.chapter    //回显
                  })

        //弹出对话框
        this.dialogChapterFormVisible = true          //显示对话框
      },

      //8、正式修改
      updChapter(){
        chapterApi.updChapter(this.chapter)   //修改
                  .then(response=>{
                    this.dialogChapterFormVisible = false //关闭对话框
                     //显示提交成功
                     this.$message({       //提示信息
                            type: 'success',
                            message: '章信息：修改成功!'
                        });

                      //刷新页面
                      this.getChapterList(this.courseID)
                  })
      },
      //9、删除：章
      removeChapter(chapterId){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {  //点击确认后
                      chapterApi.delChapter(chapterId)      //删除
                        .then(response=>{
                            //显示提交成功
                            this.$message({ //提示信息
                                    type: 'success',
                                    message: '章信息：删除成功!'
                                });

                              //刷新页面
                            this.getChapterList(this.courseID)
                        })
                })
    },
    /*=======================================添加（章）===========================================*/


    /*=======================================添加（小节）===========================================*/
    
      //1、打开：小节（表单）
      openVideo(chapterId){
          this.video.chapterId = chapterId      //设置：章节id
          this.dialogVideoFormVisible = true    //显示小节表单
      },

      //2、保存 Or 更新 (小节)
      saveOrUpdateVideo(){
          if(this.video.id){      
            this.updVideoInfo()   //更新：小节信息
          }else{                  
            this.addVideoInfo()   //添加：小节信息
          }
      },

      //3、添加：小节信息
      addVideoInfo(){
        //第一步：video赋值
        this.video.courseId = this.courseID

        //第二步：将video对象传入
        videoApi.addVideo(this.video)
                .then(response=>{
                    //第三步：关闭弹窗
                    this.dialogVideoFormVisible = false

                    //第四步：提示（添加小节成功）
                    this.$message({ //提示信息
                                    type: 'success',
                                    message: '添加：小节成功!'
                                });

                    //第五步：刷新
                    this.getChapterList(this.courseID)
                })
      },

      //4、修改：小节信息
      updVideoInfo(){
        //第一步：修改信息
        videoApi.updVideo(this.video)   
                .then(response=>{
                     //第二步：关闭弹窗
                     this.dialogVideoFormVisible = false

                    //第三步：提示（添加小节成功）
                    this.$message({ //提示信息
                                    type: 'success',
                                    message: '修改：小节成功!'
                                });

                    //第四步：刷新
                    this.getChapterList(this.courseID)
                })  
      },

      //5、弹出：编辑小节（弹框）
      openEditVideo(videoId){
          //第一步：根据videoId查询出(video信息), 回显（弹框）
          videoApi.selVideo(videoId)
                  .then(response=>{
                    this.video = response.data.eduVideo

                    //第二步：弹出弹框
                    this.dialogVideoFormVisible = true
                  })
      },

      //6、删除：小节
      removeVideo(videoId){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {  //点击确认后
                       //第一步：删除
                      videoApi.delVideo(videoId)   
                              .then(response=>{
                                  //第二步：提示信息
                                  this.$message({ 
                                                  type: 'success',
                                                  message: '删除：小节成功!'
                                              });

                                  //第三步：刷新
                                  this.getChapterList(this.courseID)
                              })  
                })
      },

    /*=======================================添加（小节）==========================================*/
      

  },
};
</script>
<style scoped>
  .chanpterList {
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
  }
  .chanpterList li {
    position: relative;
  }
  .chanpterList p {
    float: left;
    font-size: 20px;
    margin: 10px 0;
    padding: 10px;
    height: 70px;
    line-height: 50px;
    width: 100%;
    border: 1px solid #ddd;
  }
  .chanpterList .acts {
    float: right;
    font-size: 14px;
  }
  
  .videoList {
    padding-left: 50px;
  }
  .videoList p {
    float: left;
    font-size: 14px;
    margin: 10px 0;
    padding: 10px;
    height: 50px;
    line-height: 30px;
    width: 100%;
    border: 1px dotted #ddd;
  }
  </style>
  