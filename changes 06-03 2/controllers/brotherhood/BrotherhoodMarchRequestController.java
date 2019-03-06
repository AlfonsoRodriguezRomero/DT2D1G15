
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
import services.MarchRequestService;
import services.MemberService;
import controllers.AbstractController;
import domain.MarchRequest;

@Controller
@RequestMapping("marchRequest/brotherhood")
public class BrotherhoodMarchRequestController extends AbstractController {

	@Autowired
	MarchRequestService	marchRequestService;

	@Autowired
	MemberService		memberService;

	@Autowired
	BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<MarchRequest> marchRequests;

		final int brotherhoodId = this.brotherhoodService.findByPrincipal().getId();

		marchRequests = this.marchRequestService.findByBrotherhoodId(brotherhoodId);
		res = new ModelAndView("marchRequest/list");
		res.addObject("marchRequests", marchRequests);
		res.addObject("requestURI", "member/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		MarchRequest marchRequest;

		marchRequest = this.marchRequestService.findOne(id);
		res = this.createEditModelAndView(marchRequest);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int marchRequestId) {
		final ModelAndView res;
		MarchRequest marchRequest;

		marchRequest = this.marchRequestService.findOne(marchRequestId);
		res = this.createEditModelAndView(marchRequest);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MarchRequest marchRequest, final BindingResult binding) {
		ModelAndView res;
		MarchRequest marchRequest2;
		marchRequest2 = this.marchRequestService.reconstruct(marchRequest, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(marchRequest2);
		else
			try {
				this.marchRequestService.save(marchRequest2);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(marchRequest2, "marchRequest.commit.error");
			}
		return res;
	}

	//Ancillary methods
	protected ModelAndView createEditModelAndView(final MarchRequest marchRequest) {
		ModelAndView res;
		res = this.createEditModelAndView(marchRequest, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final MarchRequest marchRequest, final String messageCode) {
		final ModelAndView res;

		res = new ModelAndView("marchRequest/edit");
		res.addObject("message", messageCode);

		return res;
	}
}
