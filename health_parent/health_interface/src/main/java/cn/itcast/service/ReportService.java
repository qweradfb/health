package cn.itcast.service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    Map<String, List> getMemberReport();

    Map<String, List> getSetmealReport();

    Map getBusinessReportDate() throws Exception;
}
