package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer>{
	//get gordugunde where kosulunu yazar jpa
	Product getByProductName(String productName);
	
	@Query("From Product where id=:id")
	Product getById(short id);
	  
	  Product getByProductNameAndCategory_CategoryId(String productName, short categoryId);
	  
	  List<Product> getByProductNameOrCategory_CategoryId(String productName, short categoryId);
	  
	  List<Product> getByCategory_CategoryIdIn(List<Short> categories);
	  
	  List<Product> getByProductNameContains(String productName);
	  
	  List<Product> getByProductNameStartsWith(String productName);
	  
	  @Query("From Product where productName=:productName and category.categoryId=:categoryId")
	  List<Product> getByNameAndCategory(String productName, short categoryId);
	
	
	/**
	 * getByProductNameOrCategoryId olmaz cunku Product içinde productName var ama categoryId yok category var. 
	 * categorynin Idsini ise yanında bir daha yazıyoruz
	 */
	  
	  @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto"
		  		+ "(p.id, p.productName, c.categoryName) "
		  		+ "From Category c Inner Join c.products p")
		  List<ProductWithCategoryDto> getProductWithCategoryDetails();
	  
	  //select p.productId, p.productName, c.categoryName from Catgeory c inner join Product p
	  //on c.categoryId=p.categoryId
}
