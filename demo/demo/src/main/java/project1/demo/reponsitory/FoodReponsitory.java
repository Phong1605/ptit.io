package project1.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import project1.demo.entity.Food;

public interface FoodReponsitory extends JpaRepository<Food,Long> {
}
