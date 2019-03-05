
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import domain.Member;

@Service
@Transactional
public class MemberService {

	// Repository
	@Autowired
	private MemberRepository	memberRepository;


	// Constructor
	public MemberService() {
		super();
	}

	public Member create() {

		Member result;
		result = new Member();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.MEMBER);
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

	public Member save(final Member member) {
		Assert.notNull(member);
		// Logged user must be a member
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		Assert.isTrue(user.getAuthorities().contains(a));
		// Modified member must be logged member
		final Member logMember;
		logMember = this.findByPrincipal();
		Assert.notNull(logMember);
		Assert.notNull(logMember.getId());

		Member res;

		res = this.memberRepository.save(member);
		return res;
	}

	// Complex methods

	// Returns logged customer
	public Member findByPrincipal() {
		Member res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	// Returns logged member from his or her userAccount
	public Member findByUserAccount(final UserAccount userAccount) {
		Member res;
		Assert.notNull(userAccount);

		res = this.memberRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Member> findAll() {
		return this.memberRepository.findAll();
	}

	public Member findOne(final int memberId) {
		Member b;

		Assert.notNull(memberId);
		Assert.isTrue(memberId != 0);
		b = this.memberRepository.findOne(memberId);

		Assert.notNull(b);
		return b;
	}

	//11.3
	public Collection<Brotherhood> belongedBrotherhoods(final int memberId) {
		return this.memberRepository.belongedBrotherhoods(memberId);
	}

	// 12.3

	public ArrayList<Object> memberStatistics() {
		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		ArrayList<Object> res;
		res = this.memberRepository.memberStatistics();
		return res;
	}

	public Collection<Member> membersWith10percentOfMarchRequestAccepted() {
		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		Collection<Member> res;
		res = this.memberRepository.membersWith10percentOfMarchRequestAccepted();
		return res;
	}
}
