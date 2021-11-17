package apap.tutorial.emsidi.repository;

import apap.tutorial.emsidi.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDb extends JpaRepository<RoleModel,Long> {

}
