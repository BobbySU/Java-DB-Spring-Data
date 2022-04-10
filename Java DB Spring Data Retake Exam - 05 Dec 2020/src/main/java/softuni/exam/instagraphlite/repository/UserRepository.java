package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String userName);

    @Query("SELECT u FROM User u WHERE size(u.posts) > 0 ORDER BY size(u.posts) DESC")
    List<User> findAllByPostsOrderByPosts();
}
