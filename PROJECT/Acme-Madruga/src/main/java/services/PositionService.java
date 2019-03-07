
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Position;

@Service
@Transactional
public class PositionService {

	// Repository
	@Autowired
	private PositionRepository	positionRepository;


	// Constructor
	public PositionService() {
		super();
	}

	public Position create() {

		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		Position result;
		result = new Position();

		result.setName("");
		result.setLanguage("");

		return result;
	}

	public Position save(final Position position) {
		Assert.notNull(position);
		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		Position res;

		res = this.positionRepository.save(position);
		return res;
	}

	public Collection<Position> findAll() {
		return this.positionRepository.findAll();
	}

	public Position findOne(final int positionId) {
		Position f;

		Assert.notNull(positionId);
		Assert.isTrue(positionId != 0);
		f = this.positionRepository.findOne(positionId);

		Assert.notNull(f);
		return f;
	}

	public void delete(final Position position) {
		//Used positions can't be deleted
		Assert.isTrue(!this.positionRepository.usedPositions().contains(position));
		this.positionRepository.delete(position);
	}
}
