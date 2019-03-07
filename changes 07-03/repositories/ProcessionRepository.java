
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

	//Show to other actors only processions in final mode
	@Query("select p from Procession p where p.finalMode='true'")
	Collection<Procession> findAllInFinalMode(int id);

	@Query("select p from Procession p, Brotherhood b, Member m where m member of b.members and b is p.brotherhood and m.id=?1")
	Collection<Procession> findByMemberId(int id);

	//12.3

	@Query("select p from Procession p where datediff(p.moment,current_timestamp)<=30")
	Collection<Procession> processionsIn30days();

}
