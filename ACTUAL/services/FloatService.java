
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.FloatRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;

@Service
@Transactional
public class FloatService {

	// Repository
	@Autowired
	private FloatRepository		floatRepository;

	//Services
	@Autowired
	private BrotherhoodService	brotherhoodService;


	// Constructor
	public FloatService() {
		super();
	}


	@Autowired
	Validator	validator;


	public domain.Float create() {

		// Logged user must be a brotherhood
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a));

		domain.Float result;
		result = new domain.Float();

		result.setTitle("");
		result.setDescription("");
		result.setPictures("");

		final Brotherhood logBrotherhood;
		logBrotherhood = this.brotherhoodService.findByPrincipal();

		result.setBrotherhood(logBrotherhood);
		return result;
	}

	public domain.Float save(final domain.Float floatt) {
		Assert.notNull(floatt);
		// Logged user must be a brotherhood
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a));

		// Logged brotherhood must be the float's brotherhood
		final Brotherhood logBrotherhood;
		logBrotherhood = this.brotherhoodService.findByPrincipal();
		Assert.notNull(logBrotherhood);
		Assert.notNull(logBrotherhood.getId());
		Assert.isTrue(logBrotherhood.equals(floatt.getBrotherhood()));

		domain.Float res;

		res = this.floatRepository.save(floatt);
		return res;
	}

	public Collection<domain.Float> findAll() {
		return this.floatRepository.findAll();
	}

	public domain.Float findOne(final int floatId) {
		domain.Float f;

		Assert.notNull(floatId);
		Assert.isTrue(floatId != 0);
		f = this.floatRepository.findOne(floatId);

		Assert.notNull(f);
		return f;
	}

	public Collection<domain.Float> findByBrotherhoodId(final int brotherhoodId) {
		return this.floatRepository.findByBrotherhoodId(brotherhoodId);
	}

	public void delete(final domain.Float floatt) {
		this.floatRepository.delete(floatt);
	}

	public Collection<domain.Float> findByProcessionId(final int processionId) {
		return this.floatRepository.findByProcessionId(processionId);
	}

	//reconstruct
	public domain.Float reconstruct(final domain.Float floatt, final BindingResult binding) {
		final domain.Float floatt2 = this.create();

		if (floatt.getId() == 0 || floatt == null) {
			floatt.setBrotherhood(floatt2.getBrotherhood());
			this.validator.validate(floatt, binding);
			return floatt;
		} else {
			final domain.Float repFloatt = this.findOne(floatt.getId());
			floatt.setBrotherhood(repFloatt.getBrotherhood());
			this.validator.validate(floatt, binding);
			return floatt;
		}
	}

}
