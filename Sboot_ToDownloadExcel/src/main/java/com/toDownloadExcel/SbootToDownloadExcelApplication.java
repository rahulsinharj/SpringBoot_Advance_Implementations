package com.toDownloadExcel;

import com.toDownloadExcel.model.Category;
import com.toDownloadExcel.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class SbootToDownloadExcelApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepo categoryRepo;

	public static void main(String[] args) {
		SpringApplication.run(SbootToDownloadExcelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Category c1 = new Category(UUID.randomUUID().toString(), "Pen", "This is the Description for Pen", "image_Pen");
//		Category c2 = new Category(UUID.randomUUID().toString(), "College", "This is the Description for College", "image_College");
//		Category c3 = new Category(UUID.randomUUID().toString(), "Teacher", "This is the Description for Teacher", "image_Teacher");
//		Category c4 = new Category(UUID.randomUUID().toString(), "Zoo", "This is the Description for Zoo", "image_Zoo");
//		Category c5 = new Category(UUID.randomUUID().toString(), "Animal", "This is the Description for Animal", "image_Animal");
//
//		categoryRepo.save(c1);
//		categoryRepo.save(c2);
//		categoryRepo.save(c3);
//		categoryRepo.save(c4);
//		categoryRepo.save(c5);

	}
}
