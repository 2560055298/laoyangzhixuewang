package com.yyy.demo.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/13 8:42
 * @Version 1.0
 */
public class TestReader {
    public static void main(String[] args) {
        String fileName = "D:/student.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelData.class, new DataListener()).sheet().doRead();
    }
}