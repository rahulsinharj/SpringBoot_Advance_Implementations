package com.toDownloadExcel.controller;

import com.toDownloadExcel.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "/categories")
public class ExcelController {

    Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;

    @RequestMapping(path = "/excel")
    public ResponseEntity<Resource> download() {
        String filename = "categories_data.xlsx";

        ByteArrayInputStream actualData = excelService.getActualData();
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

        logger.info("Excel file getting downloaded !! at " + new Date());
        logger.info("Excel file getting downloaded !! at " + new SimpleDateFormat("EEE dd-MMMMM-yyyy 'T'HH:mm:ss 'Z'Zz").format(new Date()));

        return body;
    }

}
