package kz.mouzitoto.gransoftwaretest2.controller;


import kz.mouzitoto.gransoftwaretest2.model.SortDirection;
import kz.mouzitoto.gransoftwaretest2.model.response.FileOperationResponse;
import kz.mouzitoto.gransoftwaretest2.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * sortDirection can be "asc" or "desc"
     *
     * @param startDateFrom
     * @param startDateTo
     * @param sortDirection
     * @return
     */
    @GetMapping("/removeStyles")
    public List<FileOperationResponse> getRemoveStylesReport(@RequestParam(value = "startDateFrom", defaultValue = "2000-01-01")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateFrom,
                                                             @RequestParam(value = "startDateTo", defaultValue = "2020-01-01")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateTo,
                                                             @RequestParam(value = "sortDirection", defaultValue = "asc") SortDirection sortDirection) throws Exception {

        return reportService.getRemoveStylesReport(startDateFrom, startDateTo, sortDirection);
    }
}
