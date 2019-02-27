
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MarchRequest;

@Repository
public interface MarchRequestRepository extends JpaRepository<MarchRequest, Integer> {

	@Query("select m from MarchRequest m where m.member.id = ?1")
	Collection<MarchRequest> findByMemberId(int id);

	//2 MarchRequest must not have the same row and column
	@Query("select m from MarchRequest m where m.status='APPROVED', m.row=?1 and m.columnn=?2")
	Collection<MarchRequest> sameRowAndColumn(int row, int column);

	//12.3

	@Query("select (count(m)/count(select p from Procession p)) from MarchRequest m group by m.status")
	double marchRequestRatio();

}
