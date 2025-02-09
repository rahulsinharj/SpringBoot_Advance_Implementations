package productcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import productcrud.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query("select p from Product p order by p.id desc")						// "from User usr" , this query will also work same way ; but "select user" is somewhat more readable 
	public List<Product> getAllProductsOrderById();
	
}
