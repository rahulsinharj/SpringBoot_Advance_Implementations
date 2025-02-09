package com.toDownloadExcel;

import com.toDownloadExcel.repo.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbootToDownloadExcelApplicationTests {

	@Autowired
	private CategoryRepo categoryRepo;

	@Test
	public void testCategoryRepo(){
		System.out.println("Test Started !!");
		categoryRepo.findAll().forEach(ca -> System.out.println(ca.getTitle()));
	}

	@Test
	void contextLoads() {
	}

}
