
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FloatRepository extends JpaRepository<domain.Float, Integer> {

	@Query("select f from Float f where f.brotherhood.id = ?1")
	Collection<domain.Float> findByBrotherhoodId(int id);

	@Query("select p.floats from Procession p where p.id = ?1")
	Collection<domain.Float> findByProcessionId(int id);

}
