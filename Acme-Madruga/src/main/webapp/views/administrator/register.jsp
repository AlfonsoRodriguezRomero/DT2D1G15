<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

	<form:form action="administrator/register.do" modelAttribute="administrator">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />

	<form:label path="userAccount.username">
		<spring:message code="admin.userAccount.username" />
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="admin.userAccount.password" />:
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />

	<form:label path="name">
			<spring:message code="admin.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="middleName">
			<spring:message code="admin.middleName" />
	</form:label>
	<form:input path="middleName" />
	<form:errors cssClass="error" path="middleName" />
	<br />

	<form:label path="surname">
			<spring:message code="admin.surname" />
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="photoURL">
			<spring:message code="admin.photoURL" />
	</form:label>
	<form:input path="photoURL" />
	<form:errors cssClass="error" path="photoURL" />
	<br />
	
	<form:label path="email">
			<spring:message code="admin.email" />
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="phoneNumber">
		<spring:message code="admin.phoneNumber" />
	</form:label>
	<form:input path="phoneNumber" id="tlphon" />
	<form:errors path="phoneNumber" cssClass="error" />
	<br />

	<script type="text/javascript">
		function isValid() {
			var phoneRegist = /^(((\+[1-9][0-9]{0,2}) \(([1-9][0-9]{0,2})\) (\d\d\d\d+))|((\+[1-9][0-9]{0,2}) (\d\d\d\d+))|((\d\d\d\d+)))$/;
			var digits = document.getElementById('tlphon').value;
			var res = phoneRegist.test(digits);
			if (res) {
				return true;
			} else {
				return confirm('<spring:message code="phoneNumber.confirm" />');
			}
		}
	</script>
	
	<form:label path="address">
			<spring:message code="admin.address" />
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="admin.save"/>"
		onclick="javascript: relativeRedir('welcome/index.do');" />

	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />
</form:form>