package project.infy.productMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.infy.productMS.entity.SubscribedProductEntity;

@Repository
public interface SubscribedProductRepository extends JpaRepository<SubscribedProductEntity, Integer> {
	List<SubscribedProductEntity> findByBuyerId(Integer buyerId);
	

}
