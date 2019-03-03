
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import domain.Member;
import forms.MemberForm;

@Service
@Transactional
public class MemberService {

	// Repository
	@Autowired
	private MemberRepository	memberRepository;

	//Services
	@Autowired
	private BrotherhoodService	brotherhoodService;


	// Constructor
	public MemberService() {
		super();
	}


	@Autowired
	private Validator	validator;


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

	//8.2
	public Collection<Member> findByBrotherhoodId(final int id) {
		return this.memberRepository.findByBrotherhoodId(id);
	}

	//11.3
	public Collection<Brotherhood> belongedBrotherhoods(final int memberId) {
		return this.memberRepository.belongedBrotherhoods(memberId);
	}

	// 12.3

	public String memberStatistics() {
		// Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		String res;
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

	//remove member by brotherhood
	public void removeMember(final Brotherhood brotherhood, final Member member) {
		// Logged user must be a brotherhood
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a));
		// Modifier brotherhood must be the member's brotherhood
		//and modified brotherhood
		final Brotherhood logBrotherhood;
		logBrotherhood = this.brotherhoodService.findByPrincipal();
		Assert.notNull(logBrotherhood);
		Assert.isTrue(logBrotherhood.equals(brotherhood));
		Assert.isTrue(member.getBrotherhoods().contains(logBrotherhood));

		member.getBrotherhoods().remove(brotherhood);
	}

	//Reconstructs
	public Member reconstruct(final MemberForm memberForm, final BindingResult binding) {
		Member result;

		if (memberForm.getId() == 0)
			result = this.create();
		else {
			result = this.memberRepository.findOne(memberForm.getId());

			result.setName(memberForm.getName());
			result.setMiddleName(memberForm.getMiddleName());
			result.setSurname(memberForm.getSurname());
			result.setPhotoURL(memberForm.getPhotoURL());
			result.setEmail(memberForm.getEmail());
			result.setPhoneNumber(memberForm.getPhoneNumber());
			result.setAddress(memberForm.getAddress());

			result.setUserAccount(memberForm.getUserAccount());

			result.setBrotherhoods(memberForm.getBrotherhoods());

			this.validator.validate(result, binding);
		}
		return result;
	}

	public Member reconstruct(final Member member, final BindingResult binding) {
		Member result;

		if (member.getId() == 0)
			result = member;
		else {
			result = this.memberRepository.findOne(member.getId());

			result.setName(member.getName());
			result.setMiddleName(member.getMiddleName());
			result.setSurname(member.getSurname());
			result.setPhotoURL(member.getPhotoURL());
			result.setEmail(member.getEmail());
			result.setPhoneNumber(member.getPhoneNumber());
			result.setAddress(member.getAddress());

			this.validator.validate(result, binding);
		}
		return result;
	}
}
