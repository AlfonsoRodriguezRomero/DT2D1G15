
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MarchRequest;

@Component
@Transactional
public class MarchRequestToStringConverter implements Converter<MarchRequest, String> {

	@Override
	public String convert(final MarchRequest marchRequest) {
		String res;

		if (marchRequest == null)
			res = null;
		else
			res = String.valueOf(marchRequest.getId());

		return res;
	}

}
