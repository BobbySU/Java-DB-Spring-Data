package exam.repository;

import exam.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ToDo:
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
