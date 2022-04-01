package exam.repository;

import exam.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ToDo:
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
