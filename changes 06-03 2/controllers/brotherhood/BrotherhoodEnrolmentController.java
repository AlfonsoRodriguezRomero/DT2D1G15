
package controllers.brotherhood;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.EnrolmentService;
import controllers.AbstractController;
import domain.Enrolment;

@Controller
@RequestMapping("enrolment/brotherhood")
public class BrotherhoodEnrolmentController extends AbstractController {

	@Autowired
	EnrolmentService	enrolmentService;

	@Autowired
	BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Enrolment> enrolments;
		final int brotherhoodId = this.brotherhoodService.findByPrincipal().getId();

		enrolments = this.enrolmentService.findByBrotherhoodId(brotherhoodId);
		res = new ModelAndView("enrolment/list");
		res.addObject("enrolments", enrolments);
		res.addObject("requestURI", "enrolment/brotherhood/list.do");

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
