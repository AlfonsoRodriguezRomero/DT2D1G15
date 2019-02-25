
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarchRequestRepository extends JpaRepository<MarchRequest, Integer> {

	@Query("select m from MarchRequest m where m.member.id = ?1")
	Collection<MarchRequest> findByMemberId(int id);

	//12.3

	@Query("select (count(m)/count(select p from Procession p)) from MarchRequest m group by m.status")
	Double marchRequestRatio();

}
