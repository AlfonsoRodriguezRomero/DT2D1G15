
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Brotherhood extends Actor {

	//Atributes
	private String				title;
	private Date				establishmentDate;
	private String				pictures;

	private Collection<Member>	members;


	//Getters & Setters
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEstablishmentDate() {
		return this.establishmentDate;
	}
	public void setEstablishmentDate(final Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	@NotBlank
	public String getPictures() {
		return this.pictures;
	}
	public void setPictures(final String pictures) {
		this.pictures = pictures;
	}

	//Relationships
	@ManyToMany
	public Collection<Member> getMembers() {
		return this.members;
	}
	public void setMembers(final Collection<Member> members) {
		this.members = members;
	}
}
