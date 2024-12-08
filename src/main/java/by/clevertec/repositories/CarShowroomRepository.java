package by.clevertec.repositories;

import by.clevertec.models.CarShowroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {
}
