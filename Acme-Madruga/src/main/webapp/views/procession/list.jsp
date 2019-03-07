<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="displaytag"
 name="processions" requestURI="${requestURI}" id="row">

	<display:column property="title" titleKey="procession.title" />
	<display:column property="description" titleKey="procession.description" />
	<display:column property="moment" titleKey="procession.moment" />

<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column>
			<jstl:if test="${row.finalMode == true}">
				<a href="procession/brotherhood/edit.do?processionId=${row.id}">
					<spring:message code="procession.edit" />
				</a>
			</jstl:if>
		</display:column>
		<display:column>
				<a href="procession/brotherhood/show.do?processionId=${row.id}">
					<spring:message code="procession.show" />
				</a>
		</display:column>
	</security:authorize>

</display:table>
<security:authorize access="hasRole('BROTHERHOOD')">
<a href="procession/brotherhood/create.do"> <spring:message
		code="procession.create" />
</a>
</security:authorize>