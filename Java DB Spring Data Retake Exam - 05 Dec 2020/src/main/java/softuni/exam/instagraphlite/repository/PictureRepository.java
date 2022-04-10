package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.Picture;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    Picture findPictureByPath(String profilePicture);

    @Query("SELECT p FROM Picture p WHERE p.size > :SIZE ORDER BY p.size")
    List<Picture> findAllBySizeOrderBySizeAsc(@Param("SIZE")double size);
}
