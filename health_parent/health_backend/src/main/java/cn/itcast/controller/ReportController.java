package cn.itcast.controller;

import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.Result;
import cn.itcast.service.ReportService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        try {
            Map<String, List> map = reportService.getMemberReport();
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        try {
            Map<String,List> map = reportService.getSetmealReport();
            System.out.println(map);
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        try {
            Map reportData = reportService.getBusinessReportDate();
            return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,reportData);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("exportBusinessReport")
    public Result exportBusinessReport(HttpServletResponse response, HttpServletRequest request) {
        try {
            Map reportData = reportService.getBusinessReportDate();
            String path = request.getSession().getServletContext().getRealPath("template")+ File.separator+"report_template.xlsx";
            System.out.println(path);
            XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream(path));
            XSSFSheet sheet = sheets.getSheetAt(0);

            XSSFRow row = sheet.getRow(2);
            row.getCell(5).setCellValue((String)reportData.get("reportDate"));

            XSSFRow row2 = sheet.getRow(4);
            row2.getCell(5).setCellValue((Integer)reportData.get("todayNewMember"));

            XSSFRow row3 = sheet.getRow(4);
            row3.getCell(7).setCellValue((Integer)reportData.get("totalMember"));

            XSSFRow row4 = sheet.getRow(5);
            row4.getCell(5).setCellValue((Integer)reportData.get("thisWeekNewMember"));

            XSSFRow row5 = sheet.getRow(5);
            row5.getCell(7).setCellValue((Integer)reportData.get("thisMonthNewMember"));

            XSSFRow row6 = sheet.getRow(7);
            row6.getCell(5).setCellValue((Integer)reportData.get("todayOrderNumber"));

            XSSFRow row7 = sheet.getRow(7);
            row7.getCell(7).setCellValue((Integer)reportData.get("todayVisitsNumber"));

            XSSFRow row8 = sheet.getRow(8);
            row8.getCell(5).setCellValue((Integer)reportData.get("thisWeekOrderNumber"));

            XSSFRow row9 = sheet.getRow(8);
            row9.getCell(7).setCellValue((Integer)reportData.get("thisWeekVisitsNumber"));

            XSSFRow row10 = sheet.getRow(9);
            row10.getCell(5).setCellValue((Integer)reportData.get("thisMonthOrderNumber"));

            XSSFRow row11 = sheet.getRow(9);
            row11.getCell(7).setCellValue((Integer)reportData.get("thisMonthVisitsNumber"));

            List<Map> hotSetmeal = (List<Map>) reportData.get("hotSetmeal");
            int count = 12;
            for (Map map : hotSetmeal) {
                XSSFRow row12 = sheet.getRow(count);
                row12.getCell(4).setCellValue((String) map.get("name"));
                System.out.println((String) map.get("name"));
                row12.getCell(5).setCellValue((Long) map.get("setmeal_count"));
                row12.getCell(6).setCellValue(String.valueOf((BigDecimal)map.get("proportion")));
                count++;
            }

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition","attachment;filename=report.xlsx");
            sheets.write(response.getOutputStream());
            sheets.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
