
package repositories;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Brotherhood;
import domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query("select m from Member m where m.userAccount.id = ?1")
	Member findByUserAccountId(int userAccount);

	//11.3
	@Query("select b from Brotherhood b, Enrolment e where e.brotherhood is b and e.member.id=?1")
	Collection<Brotherhood> belongedBrotherhoods(int memberId);

	//12.3

	@Query("select avg(count(m)), min(count(m)), max(count(m)), stddev(count(m))from Member m, Brotherhood b where b member of m.brotherhoods")
	ArrayList<Object> memberStatistics();

	@Query("select m from Member m, MarchRequest mr where count(mr.member is m)>(0.1*count(select max(count(mr2)) from Member m2, MarchRequest mr2 where mr2.member is m2))")
	Collection<Member> membersWith10percentOfMarchRequestAccepted();

}
