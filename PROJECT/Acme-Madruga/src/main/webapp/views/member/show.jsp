<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="member.show"/>	
</h1>
<br/>
<spring:message code="member.marchRequests"/>
<jstl:out value="${member.marchRequests}"/>
<br/>


<!-- Actions -->
<security:authorize access="hasRole('BROTHERHOOD')">
<a href="brotherhood/show.do?${member.brotherhood.id}"><spring:message code="member.brotherhood"/></a>
<br/>
<spring:message code="member.brotherhood.marchRequests"/>
<jstl:out value="${member.brotherhood.marchRequests}"/>
<br/>

<a href="member/create.do?${member.id}">
<spring:message code="member.show"/></a>
<input type="button" name="cancel"
		value="<spring:message code="member.cancel" />"
		onclick="javascript: relativeRedir('member/brotherhood/list.do');" />
</security:authorize>
<security:authorize access="hasRole('BROTHERHOOD')">
<input type="button" name="cancel"
		value="<spring:message code="member.cancel"/>"
		onclick="javascript: relativeRedir('member/brotherhood/list.do')"/>
</security:authorize>