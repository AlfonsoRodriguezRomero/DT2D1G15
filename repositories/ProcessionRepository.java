
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Procession;

@Repository
public interface ProcessionRepository extends JpaRepository<Procession, Integer> {

	@Query("select p from Procession p where p.brotherhood.id = ?1")
	Collection<Procession> findByBrotherhoodId(int id);

	//12.3

	@Query("select p from Procession p where p.moment between current_timestamp() and dateadd(day,30,current_timestamp())")
	Collection<Procession> processionsIn30days();

}