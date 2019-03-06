<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('BROTHERHOOD')">

<form:form action="${requestURI}" modelAttribute="brotherhood">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount" />

	<form:label path="title">
			<spring:message code="brotherhood.title" />
	</form:label>
	<form:input path="title" readonly="${isRead}" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="establishmentDate">
			<spring:message code="brotherhood.establishmentDate" />
	</form:label>
	<form:input path="establishmentDate" readonly="${isRead}" />
	<form:errors cssClass="error" path="establishmentDate" />
	<br />
	
	<form:label path="pictures">
			<spring:message code="brotherhood.pictures" />
	</form:label>
	<form:input path="pictures" readonly="${isRead}" />
	<form:errors cssClass="error" path="pictures" />
	<br />

	<form:label path="name">
			<spring:message code="actor.name" />
	</form:label>
	<form:input path="name" readonly="${isRead}" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="middleName">
			<spring:message code="actor.middleName" />
	</form:label>
	<form:input path="middleName" readonly="${isRead}" />
	<form:errors cssClass="error" path="middleName" />
	<br />

	<form:label path="surname">
			<spring:message code="actor.surname" />
	</form:label>
	<form:input path="surname" readonly="${isRead}" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="photoURL">
			<spring:message code="actor.photoURL" />
	</form:label>
	<form:input path="photoURL" readonly="${isRead}" />
	<form:errors cssClass="error" path="photoURL" />
	<br />
	
	<form:label path="email">
			<spring:message code="actor.email" />
	</form:label>
	<form:input path="email" readonly="${isRead}" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="phoneNumber">
		<spring:message code="actor.phoneNumber" />
	</form:label>
	<form:input path="phoneNumber" id="tlphon" readonly="${isRead}" />
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
			<spring:message code="actor.address" />
	</form:label>
	<form:input path="address" readonly="${isRead}" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<jstl:if test="${isRead == false}">
		<br />
		<input type="submit" name="save"
			value="<spring:message code="actor.save"/>"
			onclick=" javascript: relativeRedir('welcome/index.do');">
			
		<input type="button" name="cancel"
			value="<spring:message code="actor.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
		<br />
	</jstl:if>
	
	<jstl:if test="${isRead == true}">
		<input type="button" name="back"
			value="<spring:message code="actor.back" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
		<br />
	</jstl:if>
</form:form>
</security:authorize>