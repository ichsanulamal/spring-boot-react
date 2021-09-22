package apap.tutorial.emsidi.repository;

import apap.tutorial.emsidi.model.MenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDB extends JpaRepository<MenuModel, Long> {
}
