package csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvReaderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvReaderAppApplication.class, args);

		System.out.println("CsvReaderAppApplication started !!");
	}

}
