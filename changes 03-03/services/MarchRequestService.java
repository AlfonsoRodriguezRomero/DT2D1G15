
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MarchRequestRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import domain.MarchRequest;
import domain.Member;

@Service
@Transactional
public class MarchRequestService {

	// Repository
	@Autowired
	private MarchRequestRepository	marchRequestRepository;

	//Services
	@Autowired
	private MemberService			memberService;

	@Autowired
	private BrotherhoodService		brotherhoodService;


	// Constructor
	public MarchRequestService() {
		super();
	}

	public MarchRequest create() {

		// Logged user must be a member
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		Assert.isTrue(user.getAuthorities().contains(a));

		MarchRequest result;
		result = new MarchRequest();

		result.setStatus("PENDING");
		result.setRow(0);
		result.setColumnn(0);
		result.setRejectedCause("");

		final Member logMember;
		logMember = this.memberService.findByPrincipal();

		result.setMember(logMember);
		return result;
	}
	public MarchRequest save(final MarchRequest marchRequest) {
		Assert.notNull(marchRequest);
		MarchRequest res;

		//If the marchRequest is in the DB, it must be updated
		//by it's brotherhood
		if (marchRequest.getId() != 0) {
			// Logged user must be a brotherhood
			final Authority a = new Authority();
			final UserAccount user = LoginService.getPrincipal();
			a.setAuthority(Authority.BROTHERHOOD);
			Assert.isTrue(user.getAuthorities().contains(a));

			// Logged brotherhood must be the marchRequest's procession's brotherhood
			final Brotherhood logBrotherhood;
			logBrotherhood = this.brotherhoodService.findByPrincipal();
			Assert.notNull(logBrotherhood);
			Assert.notNull(logBrotherhood.getId());
			Assert.isTrue(logBrotherhood.equals(marchRequest.getProcession().getBrotherhood()));

			//If the MarchRequest is rejected, the brotherhood must provide a cause
			if (marchRequest.getStatus().equals("REJECTED"))
				Assert.notNull(marchRequest.getRejectedCause());

			//2 MarchRequest must not have the same row and column
			Assert.isNull(this.marchRequestRepository.sameRowAndColumn(marchRequest.getRow(), marchRequest.getColumnn()));

			res = this.marchRequestRepository.save(marchRequest);

		} else {
			//If the MarchRequest isn't in the DB, it must be created
			//by it's member

			// Logged user must be a member
			final Authority a = new Authority();
			final UserAccount user = LoginService.getPrincipal();
			a.setAuthority(Authority.MEMBER);
			Assert.isTrue(user.getAuthorities().contains(a));

			// Logged member must be the marchRequest's member
			final Member logMember;
			logMember = this.memberService.findByPrincipal();
			Assert.notNull(logMember);
			Assert.notNull(logMember.getId());
			Assert.isTrue(logMember.equals(marchRequest.getMember()));

			res = this.marchRequestRepository.save(marchRequest);

		}

		return res;
	}
	public Collection<MarchRequest> findAll() {
		return this.marchRequestRepository.findAll();
	}

	public MarchRequest findOne(final int marchRequestId) {
		MarchRequest f;

		Assert.notNull(marchRequestId);
		Assert.isTrue(marchRequestId != 0);
		f = this.marchRequestRepository.findOne(marchRequestId);

		Assert.notNull(f);
		return f;
	}

	public Collection<MarchRequest> findByMemberId(final int memberId) {
		return this.marchRequestRepository.findByMemberId(memberId);
	}

	public Collection<MarchRequest> findByBrotherhoodId(final int brotherhoodId) {
		return this.marchRequestRepository.findByBrotherhoodId(brotherhoodId);
	}

	public void delete(final MarchRequest marchRequest) {
		//MarchRequest status must be PENDING
		Assert.isTrue(marchRequest.getStatus().equals("PENDING"));
		this.marchRequestRepository.delete(marchRequest);
	}

	//12.3
	public double marchRequestRatio() {
		return this.marchRequestRepository.marchRequestRatio();
	}
}
