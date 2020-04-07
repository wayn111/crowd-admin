package com.wayn;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import com.wayn.commom.domain.User;
import com.wayn.commom.excel.IExcelExportStylerImpl;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PoiTest {

    @Test
    public void test() throws IOException {
        //准备员工数据
        User user = new User();
        user.setUserName("张翰");
        user.setUserState(1);
        user.setPhone("1332342");
        user.setEmail("1332342@qq.com");
        user.setRemarks("wayn");
        user.setUserImg("E:/wayn/upload/avatar/2020/01/11/eb083d2dcfdc194b32891db53668d08f.png");
        user.setCreateTime(new Date());
        User user1 = new User();
        user1.setUserName("张三");
        user1.setUserState(1);
        user1.setPhone("332134");
        user1.setEmail("1332342@qq.com");
        user1.setUserImg("E:/wayn/upload/avatar/2020/01/05/d3607ba554729fba72f7da784456aafc.png");
        user1.setCreateTime(new Date());
        user1.setRemarks("wayn");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);

        /**
         * 进行相应的展出
         *  参数1:一些基本配置(表头等)
         *  参数2:导出的类型
         *  参数3:导出的数据
         */
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                User.class, list);

        //保存数据
        FileOutputStream fos = new FileOutputStream("emp.xls");
        workbook.write(fos);
        fos.close();
    }

    @Test
    public void test1() throws Exception {
        InputStream inputstream = new FileInputStream("E:\\Goolge\\download\\用户列表 (22).xls");
        ImportParams params = new ImportParams();
        params.setSaveUrl("E://excel");
        List list = new ExcelImportService().importExcelByIs(inputstream, User.class, params,false).getList();
        System.out.println(list);
    }
}
