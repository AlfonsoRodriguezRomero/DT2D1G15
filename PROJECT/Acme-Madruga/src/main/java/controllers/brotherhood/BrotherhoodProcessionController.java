
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
import services.FloatService;
import services.ProcessionService;
import controllers.AbstractController;
import domain.Procession;

@Controller
@RequestMapping("procession/brotherhood")
public class BrotherhoodProcessionController extends AbstractController {

	@Autowired
	ProcessionService	processionService;

	@Autowired
	FloatService		floatService;

	@Autowired
	BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Procession> processions;
		final int brotherhoodId = this.brotherhoodService.findByPrincipal().getId();

		processions = this.processionService.findByBrotherhoodId(brotherhoodId);
		res = new ModelAndView("procession/list");
		res.addObject("processions", processions);
		res.addObject("requestURI", "procession/brotherhood/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		Procession procession;

		procession = this.processionService.findOne(id);
		res = this.createEditModelAndView(procession);
		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		Procession procession;

		procession = this.processionService.create();
		res = this.createEditModelAndView(procession);

		return res;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int processionId) {
		final ModelAndView res;
		Procession procession;

		procession = this.processionService.findOne(processionId);
		res = this.createEditModelAndView(procession);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Procession procession, final BindingResult binding) {
		ModelAndView res;
		Procession procession2;
		procession2 = this.processionService.reconstruct(procession, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(procession2);
		else
			try {
				this.processionService.save(procession2);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(procession2, "procession.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Procession procession, final BindingResult binding) {
		ModelAndView res;

		try {
			this.processionService.delete(procession);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(procession, "procession.commit.error");
		}

		return res;
	}
	//Ancillary methods
	protected ModelAndView createEditModelAndView(final Procession procession) {
		ModelAndView res;
		res = this.createEditModelAndView(procession, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Procession procession, final String messageCode) {
		final ModelAndView res;

		final int processionId = procession.getId();

		final Collection<domain.Float> floats = this.floatService.findByProcessionId(processionId);

		res = new ModelAndView("procession/edit");
		res.addObject("procession", procession);
		res.addObject("floats", floats);
		res.addObject("message", messageCode);

		return res;
	}
}
