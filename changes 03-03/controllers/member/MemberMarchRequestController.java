
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

import services.MarchRequestService;
import controllers.AbstractController;
import domain.MarchRequest;

@Controller
@RequestMapping("marchRequest/member")
public class MemberMarchRequestController extends AbstractController {

	@Autowired
	MarchRequestService	marchRequestService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int memberId) {
		ModelAndView res;
		Collection<MarchRequest> marchRequests;

		marchRequests = this.marchRequestService.findByMemberId(memberId);
		res = new ModelAndView("marchRequest/list");
		res.addObject("marchRequests", marchRequests);
		res.addObject("requestURI", "marchRequest/member/list.do");

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
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		MarchRequest marchRequest;

		marchRequest = this.marchRequestService.create();

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

		if (binding.hasErrors())
			res = this.createEditModelAndView(marchRequest);
		else
			try {
				this.marchRequestService.save(marchRequest);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(marchRequest, "marchRequest.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MarchRequest marchRequest, final BindingResult binding) {
		ModelAndView res;

		try {
			this.marchRequestService.delete(marchRequest);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(marchRequest, "marchRequest.commit.error");
		}

		return res;
	}

	//Ancillary methods
	protected ModelAndView createEditModelAndView(final MarchRequest marchRequest) {
		ModelAndView res;
		res = this.createEditModelAndView(marchRequest, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final MarchRequest v, final String messageCode) {
		final ModelAndView res;

		res = new ModelAndView("marchRequest/edit");
		res.addObject("message", messageCode);

		return res;
	}
}
