
package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ProcessionRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Brotherhood;
import domain.Procession;

@Service
@Transactional
public class ProcessionService {

	// Repository
	@Autowired
	private ProcessionRepository	processionRepository;

	//Services
	@Autowired
	private BrotherhoodService		brotherhoodService;


	// Constructor
	public ProcessionService() {
		super();
	}


	@Autowired
	Validator	validator;


	public Procession create() {

		// Logged user must be a brotherhood
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a));

		Procession result;
		result = new Procession();

		result.setTitle("");
		result.setDescription("");
		result.setMoment(Calendar.getInstance().getTime());
		result.setTicker(TickerGenerator.generateTicker());
		result.setFinalMode(false);

		final Brotherhood logBrotherhood;
		logBrotherhood = this.brotherhoodService.findByPrincipal();

		result.setBrotherhood(logBrotherhood);
		return result;
	}
	public Procession save(final Procession procession) {
		Assert.notNull(procession);
		// Logged user must be a brotherhood
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a));

		// Logged brotherhood must be the procession's brotherhood
		final Brotherhood logBrotherhood;
		logBrotherhood = this.brotherhoodService.findByPrincipal();
		Assert.notNull(logBrotherhood);
		Assert.notNull(logBrotherhood.getId());
		Assert.isTrue(logBrotherhood.equals(procession.getBrotherhood()));

		Procession res;

		res = this.processionRepository.save(procession);
		return res;
	}

	public Collection<Procession> findAll() {
		return this.processionRepository.findAll();
	}

	//To show to other actors
	public Collection<Procession> findAllInFinalMode(final int brotherhoodId) {
		return this.processionRepository.findAllInFinalMode(brotherhoodId);
	}

	public Procession findOne(final int processionId) {
		Procession f;

		Assert.notNull(processionId);
		Assert.isTrue(processionId != 0);
		f = this.processionRepository.findOne(processionId);

		Assert.notNull(f);
		return f;
	}

	public Collection<Procession> findByBrotherhoodId(final int brotherhoodId) {
		return this.processionRepository.findByBrotherhoodId(brotherhoodId);
	}

	public Collection<Procession> findByMemberId(final int memberId) {
		return this.processionRepository.findByMemberId(memberId);
	}

	public void delete(final Procession procession) {
		this.processionRepository.delete(procession);
	}

	//12.3
	public Collection<Procession> processionsIn30Days() {
		return this.processionRepository.processionsIn30days();
	}

	//reconstruct
	public Procession reconstruct(final Procession procession, final BindingResult binding) {
		final Procession procession2 = this.create();

		if (procession.getId() == 0 || procession == null) {
			procession.setBrotherhood(procession2.getBrotherhood());
			procession.setFloats(procession2.getFloats());
			this.validator.validate(procession, binding);
			return procession;
		} else {
			final Procession repProcession = this.findOne(procession.getId());
			procession.setBrotherhood(repProcession.getBrotherhood());
			procession.setFloats(repProcession.getFloats());
			this.validator.validate(procession, binding);
			return procession;
		}
	}
}
