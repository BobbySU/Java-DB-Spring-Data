package exam.repository;

import exam.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ToDo:
@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Town findTownByName(String name);
}
