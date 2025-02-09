package productcrud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrud.entity.Product;
import productcrud.service.ProductService;

@Controller
public class MainController {

	@Value("${my.channel.name}")
	private String myChannelName;

	@Value("${myname}")
	private String myName;
		
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String chome() {
		return "redirect:home";
	}
	
	
	@RequestMapping("/home")
	public String home(Model m) 
	{
		List<Product> allProducts = productService.getAllProducts();	
		m.addAttribute("products",allProducts);
		
		System.out.println("my.channel.name : " +myChannelName);
		System.out.println("myname : " +myName);
		
		return "home";
	}
	
	
	// Show add product form ::
	@RequestMapping("/add-product")
	public String addProduct(Model m)
	{
		m.addAttribute("title","Add Product");
		return "add_product_form";
	}
	
	
	// Handle add product form ::
	@RequestMapping(path = "/handle-product" , method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute("product") Product product , HttpServletRequest request)
	{
//		System.out.println("mypath : "+request.getContextPath());			// Here it will be returned blank string , because springboot is directing accessing localhost:8080/home , without adding any project name in between   
		System.out.println(product);
		this.productService.createProduct(product);
		
		RedirectView rview = new RedirectView();
		rview.setUrl("/home");
		return rview; 
		
	}
	
	
	// Delete handler for Product
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId )
	{
		this.productService.deleteProduct(productId);
		
		RedirectView rview = new RedirectView();
		rview.setUrl("/home");
		return rview; 
	}

	
	// Update handler for Product
	@RequestMapping("/update/{productId}")
	public String updateProduct(@PathVariable("productId") int productId , Model m)
	{
		Product product = this.productService.getProduct(productId);
		m.addAttribute("product", product);
		
		return "update_form";
	}
	
	
	
}
