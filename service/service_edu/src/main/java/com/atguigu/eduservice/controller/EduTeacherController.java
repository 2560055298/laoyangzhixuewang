package com.atguigu.eduservice.controller;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.impl.EduTeacherServiceImpl;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-05
 */

@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherServiceImpl eduTeacherService;

    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public R findAll(){
        return R.ok().data("items", eduTeacherService.list(null));

    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID") @PathVariable String id){
        boolean result = eduTeacherService.removeById(id);
        return result ? R.ok() : R.error();
    }

    /**
     * @Author HeJinYang
     * @Description 讲师数据：分页查询
     * @Date 2022/1/6
     **/
    @ApiOperation(value = "讲师分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页")
                             @PathVariable Long current,
                             @ApiParam(name = "limit", value = "每页显示条目数")
                             @PathVariable Long limit){

        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher, null);

        int i = 10 / 0;

        return R.ok().data("total", pageTeacher.getTotal())
                .data("rows", pageTeacher.getRecords());
    }

    @ApiOperation(value = "讲师分页条件查询")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current", value = "当前页")
                                  @PathVariable Long current,
                                  @ApiParam(name = "limit", value = "每页显示条目数")
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //获取信息
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断
        if(!StringUtils.isEmpty(name)){     //姓名
            wrapper.like("name", name);
        }

        if(!StringUtils.isEmpty(level)){     //级别 >= level
            wrapper.ge("level", level);
        }

        if(!StringUtils.isEmpty(begin)){     //时间 >= 开始时间
            wrapper.ge("gmt_create", begin);
        }

        if(!StringUtils.isEmpty(end)){     //时间 <= 结束时间
            wrapper.le("gmt_modified", end);
        }

        IPage<EduTeacher> page = eduTeacherService.page(eduTeacherPage, wrapper);

        System.out.println(teacherQuery);

        return R.ok().data("total", page.getTotal())
                .data("rows", page.getRecords());
    }


    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);

        return save ? R.ok() : R.error();
    }

    //根据ID查询讲师信息
    @ApiOperation(value = "根据ID查询讲师信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable Long id){
        return R.ok().data("items", eduTeacherService.getById(id));
    }

    //根据ID修改讲师信息
    @ApiOperation(value = "根据ID修改讲师信息")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean result = eduTeacherService.updateById(eduTeacher);

        return result ? R.ok() : R.error();
    }
}

