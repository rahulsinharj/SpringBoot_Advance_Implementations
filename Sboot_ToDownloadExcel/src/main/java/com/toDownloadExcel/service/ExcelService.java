package com.toDownloadExcel.service;

import com.toDownloadExcel.helper.ExcelHelper;
import com.toDownloadExcel.model.Category;
import com.toDownloadExcel.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private CategoryRepo categoryRepo;

    public ByteArrayInputStream getActualData(){
        List<Category> allData = categoryRepo.findAll();
        ByteArrayInputStream byteArrayInputStream = ExcelHelper.dataToExcel(allData);
        return byteArrayInputStream;
    }

}
