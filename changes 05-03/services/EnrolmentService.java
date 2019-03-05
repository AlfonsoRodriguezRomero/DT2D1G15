
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EnrolmentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Member;

@Service
@Transactional
public class EnrolmentService {

	// Repository
	@Autowired
	private EnrolmentRepository	enrolmentRepository;

	//Services
	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private MemberService		memberService;


	// Constructor
	public EnrolmentService() {
		super();
	}

	public Enrolment create() {

		// Logged user must be a member
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		Assert.isTrue(user.getAuthorities().contains(a));

		Enrolment result;
		result = new Enrolment();

		result.setMoment(Calendar.getInstance().getTime());

		final Member logMember;
		logMember = this.memberService.findByPrincipal();

		result.setMember(logMember);
		return result;
	}
	public Enrolment save(final Enrolment enrolment) {
		Assert.notNull(enrolment);

		// Logged user must be a member or a brotherhood
		final Authority a = new Authority();
		final Authority a2 = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		a2.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a) || user.getAuthorities().contains(a2));

		if (user.getAuthorities().contains(a2)) {
			// Logged brotherhood must be the enrolment's brotherhood
			final Brotherhood logBrotherhood;
			logBrotherhood = this.brotherhoodService.findByPrincipal();
			Assert.notNull(logBrotherhood);
			Assert.notNull(logBrotherhood.getId());
			Assert.isTrue(logBrotherhood.equals(enrolment.getBrotherhood()));
		} else {
			// Logged member must be the enrolment's member
			final Member logMember;
			logMember = this.memberService.findByPrincipal();
			Assert.notNull(logMember);
			Assert.notNull(logMember.getId());
			Assert.isTrue(logMember.equals(enrolment.getMember()));
		}

		//If the member is dropping out of the brotherhood,
		//it must be removed from his or her brotherhood's members list
		if (enrolment.getId() != 0)
			if (!enrolment.getDropOutMoment().equals(null)) {
				final Member m = enrolment.getMember();
				final Brotherhood b = enrolment.getBrotherhood();
				final Collection<Member> ms = new ArrayList<>();
				final Collection<Member> brotherhoodMembers = b.getMembers();
				ms.addAll(brotherhoodMembers);
				ms.remove(m);
				b.setMembers(ms);
			}

		Enrolment res;

		res = this.enrolmentRepository.save(enrolment);
		return res;
	}

	public Collection<Enrolment> findAll() {
		return this.enrolmentRepository.findAll();
	}

	public Enrolment findOne(final int enrolmentId) {
		Enrolment f;

		Assert.notNull(enrolmentId);
		Assert.isTrue(enrolmentId != 0);
		f = this.enrolmentRepository.findOne(enrolmentId);

		Assert.notNull(f);
		return f;
	}

	public Collection<Enrolment> findByMemberId(final int memberId) {
		return this.enrolmentRepository.findByMemberId(memberId);
	}

	public Collection<Enrolment> findByBrotherhoodId(final int brotherhoodId) {
		return this.enrolmentRepository.findByBrotherhoodId(brotherhoodId);
	}

}
