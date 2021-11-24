package apap.tutorial.emsidi.repository;

import apap.tutorial.emsidi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDb extends JpaRepository<UserModel,Long> {
    UserModel findByUsername(String username);
}
