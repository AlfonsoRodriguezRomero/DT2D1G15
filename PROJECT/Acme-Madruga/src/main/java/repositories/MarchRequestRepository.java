
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

	@Query("select m from MarchRequest m where m.member.id = ?1 and m.status='APPROVED'")
	Collection<MarchRequest> findApprovedByMemberId(int id);

	@Query("select m from MarchRequest m where m.member.id = ?1 and m.status='REJECTED'")
	Collection<MarchRequest> findRejectedByMemberId(int id);

	@Query("select m from MarchRequest m where m.member.id = ?1 and m.status='PENDING'")
	Collection<MarchRequest> findPendingByMemberId(int id);

	@Query("select m from MarchRequest m where m.procession.brotherhood.id = ?1")
	Collection<MarchRequest> findByBrotherhoodId(int id);

	//2 MarchRequest must not have the same row and column
	@Query("select m from MarchRequest m where m.status='APPROVED' and m.row=?1 and m.columnn=?2 and m.procession.id=?3")
	Collection<MarchRequest> sameRowAndColumn(int row, int column, int processionId);

	//12.3

	@Query("select (count(m)/(select count(p) from Procession p)) from MarchRequest m")
	double marchRequestRatio();

}
