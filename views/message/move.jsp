<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
	<form:form action="message/move.do" modelAttribute="m">
		<form:hidden path="id"/>

		<form:hidden path="sender"/>
		<form:hidden path="moment"/>
		<form:hidden path="flagSpam"/>
		<form:hidden path="subject"/>
		<form:hidden path="body"/>
		<form:hidden path="tag"/>
		<form:hidden path="priority"/>
		<form:hidden path="recipient"/>
		<form:label path="version">
			<spring:message code="box.edit.name"/>:
		</form:label>
		<form:select id ="version" path="version">
			<form:options items="${listBoxes}" itemValue="id" itemLabel="name"/>
		</form:select> 
		<br/>
		<br/>
		<%-- </jstl:if> --%>
		<input type="submit" name="save"
		value="<spring:message code="message.edit.save" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="message.edit.cancel" />"
		onclick="javascript: relativeRedir('box/list.do');" />
	<br />
	</form:form>
