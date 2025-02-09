package productcrud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import productcrud.entity.Product;
import productcrud.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	// Insert a product entry 
	@Transactional
	public void createProduct(Product product) {
		this.productRepository.save(product);
	}
	

	// Fetching a single Product
	public Product getProduct(int pid) {
		Product prod = this.productRepository.findById(pid).get();
		return prod;
	}
	
	
	// Fetching all product entries
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		for(Product prod : this.productRepository.getAllProductsOrderById()) 	 // productRepository.findAll()   // productRepository.getAllProductsOrderById()
		{
			products.add(prod);
		}
		return products;
	}
	
	
	// Delete a single product entry
	@Transactional
	public void deleteProduct(int pid) {
		this.productRepository.deleteById(pid);
	}

	

}
