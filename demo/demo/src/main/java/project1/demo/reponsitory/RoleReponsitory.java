package project1.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project1.demo.entity.Role;

@Repository
public interface RoleReponsitory extends JpaRepository<Role,Long> {
    public Role findByName(String name);
}
