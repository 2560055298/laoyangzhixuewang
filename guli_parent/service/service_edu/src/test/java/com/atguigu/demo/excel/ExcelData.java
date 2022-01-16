package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/13 8:37
 * @Version 1.0
 */

@Data
public class ExcelData {
    @ExcelProperty(value = "sno", index = 0)
    private String sno;     //学号

    @ExcelProperty(value = "sname", index = 1)
    private String sname;   //姓名
}