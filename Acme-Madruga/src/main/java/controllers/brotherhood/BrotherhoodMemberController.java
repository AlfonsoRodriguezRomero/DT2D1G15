
package controllers.brotherhood;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.MemberService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Member;

@Controller
@RequestMapping("member/brotherhood")
public class BrotherhoodMemberController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Member> members;
		final int brotherhoodId = this.brotherhoodService.findByPrincipal().getId();

		members = this.memberService.findByBrotherhoodId(brotherhoodId);
		res = new ModelAndView("member/list");
		res.addObject("members", members);
		res.addObject("requestURI", "member/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		Member member;

		member = this.memberService.findOne(id);
		res = this.createEditModelAndView(member);
		return res;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(@RequestParam final int memberId) {
		final ModelAndView res;
		Member member;
		Brotherhood brotherhood;

		member = this.memberService.findOne(memberId);
		brotherhood = this.brotherhoodService.findByPrincipal();
		this.memberService.removeMember(brotherhood, member);

		res = this.createEditModelAndView(member);

		return res;
	}

	//Ancillary methods
	protected ModelAndView createEditModelAndView(final Member member) {
		ModelAndView res;
		res = this.createEditModelAndView(member, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Member member, final String messageCode) {
		final ModelAndView res;

		res = new ModelAndView("member/edit");
		res.addObject("member", member);
		res.addObject("message", messageCode);

		return res;
	}
}
