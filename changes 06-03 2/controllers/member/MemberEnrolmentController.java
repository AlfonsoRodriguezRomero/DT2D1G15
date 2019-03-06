
package controllers.member;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EnrolmentService;
import services.MemberService;
import controllers.AbstractController;
import domain.Enrolment;

@Controller
@RequestMapping("enrolment/member")
public class MemberEnrolmentController extends AbstractController {

	@Autowired
	EnrolmentService	enrolmentService;

	@Autowired
	MemberService		memberService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Enrolment> enrolments;
		final int memberId = this.memberService.findByPrincipal().getId();

		enrolments = this.enrolmentService.findByMemberId(memberId);
		res = new ModelAndView("enrolment/list");
		res.addObject("enrolments", enrolments);
		res.addObject("requestURI", "enrolment/member/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		Enrolment enrolment;

		enrolment = this.enrolmentService.findOne(id);
		res = this.createEditModelAndView(enrolment);
		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		Enrolment enrolment;

		enrolment = this.enrolmentService.create();

		res = this.createEditModelAndView(enrolment);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int enrolmentId) {
		final ModelAndView res;
		Enrolment enrolment;

		enrolment = this.enrolmentService.findOne(enrolmentId);
		res = this.createEditModelAndView(enrolment);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Enrolment enrolment, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(enrolment);
		else
			try {
				this.enrolmentService.save(enrolment);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(enrolment, "enrolment.commit.error");
			}
		return res;
	}

	//Ancillary methods
	protected ModelAndView createEditModelAndView(final Enrolment enrolment) {
		ModelAndView res;
		res = this.createEditModelAndView(enrolment, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Enrolment enrolment, final String messageCode) {
		final ModelAndView res;

		res = new ModelAndView("enrolment/edit");
		res.addObject("message", messageCode);

		return res;
	}
}
