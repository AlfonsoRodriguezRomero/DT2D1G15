
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

import services.FloatService;
import controllers.AbstractController;

@Controller
@RequestMapping("float/brotherhood")
public class BrotherhoodFloatController extends AbstractController {

	@Autowired
	FloatService	floatService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		ModelAndView res;
		Collection<domain.Float> floats;

		floats = this.floatService.findByBrotherhoodId(brotherhoodId);
		res = new ModelAndView("float/list");
		res.addObject("floats", floats);
		res.addObject("requestURI", "float/brotherhood/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		domain.Float floatt;

		floatt = this.floatService.findOne(id);
		res = this.createEditModelAndView(floatt);
		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		domain.Float floatt;

		floatt = this.floatService.create();

		res = this.createEditModelAndView(floatt);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int floatId) {
		final ModelAndView res;
		domain.Float floatt;

		floatt = this.floatService.findOne(floatId);
		res = this.createEditModelAndView(floatt);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final domain.Float floatt, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(floatt);
		else
			try {
				this.floatService.save(floatt);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(floatt, "float.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final domain.Float floatt, final BindingResult binding) {
		ModelAndView res;

		try {
			this.floatService.delete(floatt);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(floatt, "float.commit.error");
		}

		return res;
	}
	//Ancillary methods
	protected ModelAndView createEditModelAndView(final domain.Float floatt) {
		ModelAndView res;
		res = this.createEditModelAndView(floatt, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final domain.Float floatt, final String messageCode) {
		final ModelAndView res;

		res = new ModelAndView("float/edit");
		res.addObject("message", messageCode);

		return res;
	}
}
