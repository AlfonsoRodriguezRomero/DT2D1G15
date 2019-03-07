<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('MEMBER')||hasRole('BROTHERHOOD')">

<form:form action="${requestURI}" modelAttribute="marchRequest">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<security:authorize access="hasRole('MEMBER')">
	
	<form:hidden path="status" />
	<form:hidden path="row" />
	<form:hidden path="columnn" />
	<form:hidden path="rejectedCause" />
	<form:hidden path="member" />
	
	<acme:select items="${processions}" itemLabel="title" code="marchRequest.procession" path="procession"/>
	
	<br />
	</security:authorize>
	
	<security:authorize access="hasRole('BROTHERHOOD')">
	
	<form:hidden path="member" />
	
	<form:label path="status">
	<spring:message code="marchRequest.status" />:
	</form:label>
	<form:select id="status" path="status" multiple="false">
			<form:option value="PENDING"/>
			<form:option value="APPROVED"/>
			<form:option value="REJECTED"/>
		</form:select>
	<br />
	<acme:textbox code="marchRequest.row" path="row"/>
	<br/>
	<acme:textbox code="marchRequest.column" path="columnn"/>
	<br/>
	<acme:textarea code="marchRequest.rejectedCause" path="rejectedCause"/>
	<br/>
	</security:authorize>
	
	
	<jstl:if test="${isRead == false}">
		<br />
		<input type="submit" name="save"
			value="<spring:message code="marchRequest.save"/>">&nbsp;
			
		<security:authorize access="hasRole('MEMBER')">
		<jstl:if test="${marchRequest.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="marchRequest.delete" />"
			onclick="return confirm('<spring:message code="marchRequest.confirm.delete" />')" />&nbsp;
		</jstl:if>
		</security:authorize>
			
	<security:authorize access="hasRole('MEMBER')">
		<input type="button" name="cancel"
			value="<spring:message code="marchRequest.cancel" />"
			onclick="javascript: relativeRedir('marchRequest/member/list.do');" />
		<br />
	</security:authorize>
	<security:authorize access="hasRole('BROTHERHOOD')">
	<input type="button" name="cancel"
			value="<spring:message code="marchRequest.cancel" />"
			onclick="javascript: relativeRedir('marchRequest/brotherhood/list.do');" />
		<br />
	</security:authorize>
	</jstl:if>
	<jstl:if test="${isRead == true}">
		<input type="button" name="back"
			value="<spring:message code="marchRequest.back" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
		<br />
	</jstl:if>
</form:form>
</security:authorize>