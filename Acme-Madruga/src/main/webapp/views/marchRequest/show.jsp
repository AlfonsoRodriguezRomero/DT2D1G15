<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="marchRequest.show.head"/>	
</h1>
<br/>
<spring:message code="marchRequest.show.status"/>
<jstl:out value="${marchRequest.status}"/>
<br/>
<spring:message code="marchRequest.show.columnn"/>
<jstl:out value="${marchRequest.columnn}"/>
<br/>
<spring:message code="marchRequest.show.rejectedCause"/>
<jstl:out value="${marchRequest.rejectedCause}"/>
<br/>
<spring:message code="marchRequest.show.member"/>
<jstl:out value="${marchRequest.member}"/>
<br/>
<spring:message code="marchRequest.show.procession"/>
<jstl:out value="${marchRequest.procession}"/>
<br/>

<!-- Actions -->
<security:authorize access="hasRole('BROTHERHOOD')">
<a href="brotherhood/show.do?${marchRequest.brotherhood.id}"><spring:message code="float.show.brotherhood"/></a>
<br/>
<spring:message code="marchRequest.show.brotherhood.status"/>
<jstl:out value="${marchRequest.brotherhood.status}"/>
<br/>
<spring:message code="marchRequest.show.brotherhood.row"/>
<jstl:out value="${marchRequest.brotherhood.row}"/>
<br/>
<spring:message code="marchRequest.show.brotherhood.columnn"/>
<jstl:out value="${marchRequest.brotherhood.columnn}"/>
<br/>
<spring:message code="marchRequest.show.brotherhood.rejectedCause"/>
<jstl:out value="${marchRequest.brotherhood.rejectedCause}"/>
<br/>
<spring:message code="marchRequest.show.brotherhood.member"/>
<jstl:out value="${marchRequest.brotherhood.member}"/>
<br/>
<spring:message code="marchRequest.show.brotherhood.procession"/>
<jstl:out value="${marchRequest.brotherhood.procession}"/>
<br/>
<a href="marchRequest/create.do?${marchRequest.id}">
<spring:message code="marchRequest.show"/></a>
<input type="button" name="cancel"
		value="<spring:message code="marchRequest.show.cancel" />"
		onclick="javascript: relativeRedir('marchRequest/brotherhood/list.do');" />
</security:authorize>
<security:authorize access="hasRole('BROTHERHOOD')">
<input type="button" name="cancel"
		value="<spring:message code="marchRequest.show.cancel"/>"
		onclick="javascript: relativeRedir('marchRequest/brotherhood/list.do')"/>
</security:authorize>