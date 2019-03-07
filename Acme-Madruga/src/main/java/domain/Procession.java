
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Procession extends DomainEntity {

	//Atributes
	private String				title;
	private String				description;
	private Date				moment;
	private String				ticker;
	private Boolean				finalMode;

	private Brotherhood			brotherhood;
	private Collection<Float>	floats;


	//Getters & Setters
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@Column(unique = true)
	@Pattern(regexp = "^([0][0-9]|[1][0-9])(0[0-9]|1[0-2])(0[0-9]|[12][0-9]|3[01])-[A-Z0-9_]{6}$")
	//Cambiar pattern
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	public Boolean getFinalMode() {
		return this.finalMode;
	}
	public void setFinalMode(final Boolean finalMode) {
		this.finalMode = finalMode;
	}

	//Relationships
	@ManyToOne(optional = false)
	public Brotherhood getBrotherhood() {
		return this.brotherhood;
	}
	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}
	@ManyToMany
	public Collection<Float> getFloats() {
		return this.floats;
	}
	public void setFloats(final Collection<Float> floats) {
		this.floats = floats;
	}
}
