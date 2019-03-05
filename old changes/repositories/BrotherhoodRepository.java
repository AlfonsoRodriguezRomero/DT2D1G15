
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Brotherhood;

@Repository
public interface BrotherhoodRepository extends JpaRepository<Brotherhood, Integer> {

	@Query("select b from Brotherhood b where b.userAccount.id = ?1")
	Brotherhood findByUserAccountId(int userAccount);

	//12.3

	@Query("select distinct b from Brotherhood b order by (count(select m from Member m where b member of m.brotherhoods))")
	Collection<Brotherhood> largestBrotherhoods();

	@Query("select distinct b from Brotherhood b order by (count(select m from Member m where b member of m.brotherhoods)) desc")
	Collection<Brotherhood> smallestBrotherhoods();

}
