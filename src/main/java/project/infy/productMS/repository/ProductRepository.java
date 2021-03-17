package project.infy.productMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.infy.productMS.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	
	List<ProductEntity> findByCategory(String category);
	List<ProductEntity> findByProductName(String productName);
	List<ProductEntity> findBySellerId(Integer sellerId);
	Optional<ProductEntity> findById(Integer prodId);
	void deleteById(Integer prodId);

}
