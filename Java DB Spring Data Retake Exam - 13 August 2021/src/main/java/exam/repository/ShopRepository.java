package exam.repository;

import exam.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ToDo:
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
