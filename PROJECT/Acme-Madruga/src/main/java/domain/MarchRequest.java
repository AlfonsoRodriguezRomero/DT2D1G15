
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Access(AccessType.PROPERTY)
public class MarchRequest extends DomainEntity {

	//Atributes
	private String		status;
	private Integer		row;
	private Integer		columnn;
	private String		rejectedCause;

	private Member		member;
	private Procession	procession;


	//Getters & Setters
	@NotNull
	@Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$")
	public String getStatus() {
		return this.status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}
	@Min(0)
	public Integer getRow() {
		return this.row;
	}
	public void setRow(final Integer row) {
		this.row = row;
	}
	@Min(0)
	public Integer getColumnn() {
		return this.columnn;
	}
	public void setColumnn(final Integer columnn) {
		this.columnn = columnn;
	}
	public String getRejectedCause() {
		return this.rejectedCause;
	}
	public void setRejectedCause(final String rejectedCause) {
		this.rejectedCause = rejectedCause;
	}

	//Relationships
	@ManyToOne(optional = false)
	public Member getMember() {
		return this.member;
	}
	public void setMember(final Member member) {
		this.member = member;
	}
	@ManyToOne(optional = false)
	public Procession getProcession() {
		return this.procession;
	}
	public void setProcession(final Procession procession) {
		this.procession = procession;
	}
}
