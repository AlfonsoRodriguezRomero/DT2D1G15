
package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BrotherhoodRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import forms.BrotherhoodForm;

@Service
@Transactional
public class BrotherhoodService {

	// Repository
	@Autowired
	private BrotherhoodRepository	brotherhoodRepository;


	// Constructor
	public BrotherhoodService() {
		super();
	}


	@Autowired
	private Validator	validator;


	public Brotherhood create() {

		Brotherhood result;
		result = new Brotherhood();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.BROTHERHOOD);
		newUser.addAuthority(f);

		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhotoURL("");
		result.setUserAccount(newUser);

		// Brotherhood
		result.setTitle("");
		result.setEstablishmentDate(Calendar.getInstance().getTime());
		return result;
	}

	public Brotherhood save(final Brotherhood brotherhood) {
		Assert.notNull(brotherhood);
		// Logged user must be a brotherhood
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a));
		// Modified brotherhood must be logged brotherhood
		final Brotherhood logBrotherhood;
		logBrotherhood = this.findByPrincipal();
		Assert.notNull(logBrotherhood);
		Assert.notNull(logBrotherhood.getId());

		Brotherhood res;

		res = this.brotherhoodRepository.save(brotherhood);
		return res;
	}

	// Complex methods

	// Returns logged customer
	public Brotherhood findByPrincipal() {
		Brotherhood res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	// 12.3

	public Collection<Brotherhood> largestBrotherhoods() {
		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		Collection<Brotherhood> res;
		res = this.brotherhoodRepository.largestBrotherhoods();
		return res;
	}

	public Collection<Brotherhood> smallestBrotherhoods() {
		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		Collection<Brotherhood> res;
		res = this.brotherhoodRepository.smallestBrotherhoods();
		return res;
	}

	// Returns logged brotherhood from his or her userAccount
	public Brotherhood findByUserAccount(final UserAccount userAccount) {
		Brotherhood res;
		Assert.notNull(userAccount);

		res = this.brotherhoodRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Brotherhood> findAll() {
		return this.brotherhoodRepository.findAll();
	}

	public Brotherhood findOne(final int brotherhoodId) {
		Brotherhood b;

		Assert.notNull(brotherhoodId);
		Assert.isTrue(brotherhoodId != 0);
		b = this.brotherhoodRepository.findOne(brotherhoodId);

		Assert.notNull(b);
		return b;
	}

	//Reconstructs
	public Brotherhood reconstruct(final BrotherhoodForm brotherhoodForm, final BindingResult binding) {
		Brotherhood result;
		this.validator.validate(brotherhoodForm, binding);

		result = this.create();

		result.setName(brotherhoodForm.getName());
		result.setMiddleName(brotherhoodForm.getMiddleName());
		result.setSurname(brotherhoodForm.getSurname());
		result.setPhotoURL(brotherhoodForm.getPhotoURL());
		result.setEmail(brotherhoodForm.getEmail());
		result.setPhoneNumber(brotherhoodForm.getPhoneNumber());
		result.setAddress(brotherhoodForm.getAddress());

		result.getUserAccount().setUsername(brotherhoodForm.getUsername());
		result.getUserAccount().setPassword(brotherhoodForm.getPassword());

		return result;
	}

	public Brotherhood reconstruct(final Brotherhood brotherhood, final BindingResult binding) {

		final Brotherhood repBrotherhood = this.findOne(brotherhood.getId());

		brotherhood.setUserAccount(repBrotherhood.getUserAccount());

		this.validator.validate(brotherhood, binding);
		return brotherhood;
	}
}
