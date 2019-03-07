
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FloatToStringConverter implements Converter<domain.Float, String> {

	@Override
	public String convert(final domain.Float floatt) {
		String res;

		if (floatt == null)
			res = null;
		else
			res = String.valueOf(floatt.getId());

		return res;
	}

}
