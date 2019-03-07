<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="requestForm">

	<form:hidden path="id" />
	<form:hidden path="rejectedCause" />

	<form:label path="row">
		<spring:message code="request.row" />:
	</form:label>
	<form:input path="row" />
	<form:errors cssClass="error" path="row" />
	<br />

	<form:label path="columnn">
		<spring:message code="request.column" />:
	</form:label>
	<form:input path="columnn" />
	<form:errors cssClass="error" path="row" />
	<br />
	<input type="submit" name="save"
		value="<spring:message code="request.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="request.cancel" />"
		onclick="javascript: relativeRedir('request/brotherhood/list.do');" />
	<br />
	
</form:form>