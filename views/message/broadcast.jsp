<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<security:authorize access="isAuthenticated()">

	<form:form action="message/edit.do" modelAttribute="m">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="sender"/>
		<form:hidden path="moment"/>
		<form:hidden path="flagSpam"/>

		
		<jstl:if test="${m.id == 0}">
		<form:label path="subject">
			<spring:message code="message.subject" />:
		</form:label>
		<form:input path="subject"/>
		<form:errors cssClass="error" path="subject" />
		<br/>
		<br/>
		
		<form:label path="body">
			<spring:message code="message.body" />:
		</form:label>
		<form:input path="body"/>
		<form:errors cssClass="error" path="body" />
		<br/>
		<br/>
		
		<form:label path="tag">
			<spring:message code="message.tag" />:
		</form:label>
		<form:input path="tag"/>
		<form:errors cssClass="error" path="tag" />
		<br/>
		<br/>
		
		<form:label path="priority">
			<spring:message code="message.priority" />:
		</form:label>
		<form:input path="priority"/>
		<form:errors cssClass="error" path="priority" />
		<br/>

		</jstl:if>
		<jstl:if test="${m.id==0 }">
		<br/>
		<br/>
		</jstl:if>
		 <!-- Editar boxes -->
	<%-- 	<jstl:if test="${m.id != 0}">
		<form:label path="boxes">
			<spring:message code="message.edit.boxes" />:
		</form:label>
		<form:select id ="boxes" path="boxes">
			<form:option value="0" label="---------"/>
			<form:options items="boxes" itemValue="name"/>
		</form:select> --%>
	
		<%-- </jstl:if> --%>
	<security:authorize access="hasRole('ADMIN')">
	<div>
	<input type="submit" name="broadcast" value="<spring:message 
	code="message.createBroadcast"/>"/>
	</div></security:authorize>
	<input type="button" name="cancel"
		value="<spring:message code="message.edit.cancel" />"
		onclick="javascript: relativeRedir('box/list.do');" />
	<br />
	
	</form:form>
</security:authorize>