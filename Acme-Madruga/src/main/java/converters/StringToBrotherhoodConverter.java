
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.BrotherhoodService;
import domain.Brotherhood;

@Component
@Transactional
public class StringToBrotherhoodConverter implements Converter<String, Brotherhood> {

	@Autowired
	BrotherhoodService	brotherhoodService;


	@Override
	public Brotherhood convert(final String text) {
		Brotherhood result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.brotherhoodService.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
