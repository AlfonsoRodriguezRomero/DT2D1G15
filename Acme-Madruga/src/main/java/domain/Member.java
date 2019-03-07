
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	private Collection<MarchRequest>	marchRequests;


	@OneToMany
	public Collection<MarchRequest> getMarchRequests() {
		return this.marchRequests;
	}

	public void setMarchRequests(final Collection<MarchRequest> marchRequests) {
		this.marchRequests = marchRequests;
	}

}
