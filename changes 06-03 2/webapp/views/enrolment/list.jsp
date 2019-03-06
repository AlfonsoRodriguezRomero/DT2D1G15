<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

	<security:authorize access="hasRole('BROTHERHOOD')">

		<display:table pagesize="5" class="displaytag"
			name="enrolments" requestURI="${requestURI}" id="row">
				
			<display:column property="moment" titleKey="enrolment.establishmentDate" 
			sortable="true" format="{0,date,dd/MM/yyyy, hh:mm:ss}" />
			<display:column property="dropOutMoment" titleKey="enrolment.dropOutMoment" 
			sortable="true" format="{0,date,dd/MM/yyyy, hh:mm:ss}" />
			<display:column property="member" titleKey="enrolment.member" />
			<display:column property="brotherhood" titleKey="enrolment.brotherhood" />
			<display:column property="position" titleKey="enrolment.position" />
		</display:table>
	</security:authorize>