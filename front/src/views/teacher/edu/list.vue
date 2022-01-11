<template>
    <div class="app-container">
          <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="teacherQuery.name" placeholder="讲师名"/>
        </el-form-item>
  
        <el-form-item>
          <el-select v-model="teacherQuery.level" clearable placeholder="讲师头衔">
            <el-option :value="1" label="高级讲师"/>
            <el-option :value="2" label="首席讲师"/>
          </el-select>
        </el-form-item>
  
        <el-form-item label="添加时间">
          <el-date-picker
            v-model="teacherQuery.begin"
            type="datetime"
            placeholder="选择开始时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>

        <el-form-item>
          <el-date-picker
            v-model="teacherQuery.end"
            type="datetime"
            placeholder="选择截止时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="getTeacherInfo()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
  


        <!-- 表格（讲师信息） -->
        <el-table :data="list" border fit highlight-current-row>

            <el-table-column label="序号" width="70" align="center">
                <template slot-scope="scope">
                    {{ (page - 1) * limit + scope.$index + 1 }}
                </template>
            </el-table-column>

            <el-table-column prop="name" label="名称" width="80" />

            <el-table-column label="头衔" width="80">
                <template slot-scope="scope">
                    {{ scope.row.level===1?'高级讲师':'首席讲师' }}
                </template>
            </el-table-column>

            <el-table-column prop="intro" label="资历" />

            <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

            <el-table-column prop="sort" label="排序" width="60" />

            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <router-link :to="'/teacher/edit/'+scope.row.id">
                    <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
                    </router-link>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-pagination
            :current-page="page" 
            :page-size="limit"
            :total="total"
            style="padding: 30px 0; text-align: center;"
            layout="total, prev, pager, next, jumper"
            @current-change="getTeacherInfo"
        />

    </div>
  </template>
  
<script>
    import teacher from '@/api/edu/teacher'
    export default {
        data(){
            return {
                list:null,      //讲师信息
                page:1,         //当前页
                limit:3,       //每页显示条目数
                total:0,        //总页数
                teacherQuery: {} //查询条件
            }
        },

        created(){
            this.getTeacherInfo()       //调用获取讲师信息方法
        },

        methods:{
            //1、获取讲师信息
            getTeacherInfo(page = 1){
                this.page = page
                teacher.getTeacherPageInfo(this.page, this.limit, this.teacherQuery)
                       .then(response=>{
                         this.list = response.data.rows
                         this.total = response.data.total
                       })
            },

            //2、清空搜索框
            resetData(){
                this.teacherQuery = {}      //清空
                this.getTeacherInfo()       //重新查询一遍
            },
            //3、通过Id删除讲师
            removeDataById(id){
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {  //点击确认后
                        teacher.deleteTeacherById(id)
                               .then(response =>{
                                    //删除后刷新
                                    this.getTeacherInfo()
                                })

                        this.$message({       //提示信息
                            type: 'success',
                            message: '删除成功!'
                        });
                })
            }
        }
    }
</script>