package com.yyy.demo.excel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/13 8:39
 * @Version 1.0
 */
public class DataListener  extends AnalysisEventListener<ExcelData> {

    @Override
    public void invoke(ExcelData data, AnalysisContext context) {
        System.out.println("****************" + data);
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}