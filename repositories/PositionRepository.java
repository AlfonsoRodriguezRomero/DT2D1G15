
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

	//12.2
	//Used positions can't be deleted
	@Query("select p from Position p, Enrolment e where e.position is p")
	Collection<Position> usedPositions();
}
