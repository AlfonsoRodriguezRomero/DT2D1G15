
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import forms.AdministratorForm;

@Service
@Transactional
public class AdministratorService {

	// Repository
	@Autowired
	private AdministratorRepository	administratorRepository;


	// Constructor
	public AdministratorService() {
		super();
	}


	@Autowired
	private Validator	validator;


	public Administrator create() {

		Administrator result;
		result = new Administrator();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.ADMIN);
		newUser.addAuthority(f);

		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhotoURL("");
		result.setUserAccount(newUser);

		return result;
	}

	public Administrator save(final Administrator admin) {
		Assert.notNull(admin);
		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));
		// Modified administrator must be logged administrator
		final Administrator logAdministrator;
		logAdministrator = this.findByPrincipal();
		Assert.notNull(logAdministrator);
		Assert.notNull(logAdministrator.getId());

		Administrator res;

		res = this.administratorRepository.save(admin);
		return res;
	}

	// Complex methods

	// Returns logged administrator
	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	// Returns logged administrator from his or her userAccount
	public Administrator findByUserAccount(final UserAccount userAccount) {
		Administrator res;
		Assert.notNull(userAccount);

		res = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}

	public Administrator findOne(final int adminId) {
		Administrator a;

		Assert.notNull(adminId);
		Assert.isTrue(adminId != 0);
		a = this.administratorRepository.findOne(adminId);

		Assert.notNull(a);
		return a;
	}

	//Reconstructs
	public Administrator reconstruct(final AdministratorForm administratorForm, final BindingResult binding) {
		Administrator result;
		this.validator.validate(administratorForm, binding);

		result = this.create();

		result.setName(administratorForm.getName());
		result.setMiddleName(administratorForm.getMiddleName());
		result.setSurname(administratorForm.getSurname());
		result.setPhotoURL(administratorForm.getPhotoURL());
		result.setEmail(administratorForm.getEmail());
		result.setPhoneNumber(administratorForm.getPhoneNumber());
		result.setAddress(administratorForm.getAddress());

		result.getUserAccount().setUsername(administratorForm.getUsername());
		result.getUserAccount().setPassword(administratorForm.getPassword());

		return result;
	}

	public Administrator reconstruct(final Administrator administrator, final BindingResult binding) {

		final Administrator repAdministrator = this.findOne(administrator.getId());

		administrator.setUserAccount(repAdministrator.getUserAccount());

		this.validator.validate(administrator, binding);
		return administrator;
	}
}
