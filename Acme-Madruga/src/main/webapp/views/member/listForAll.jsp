<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="members" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="name" titleKey="member.name" />
	<display:column property="middleName" titleKey="member.middleName" />
	<display:column property="surname" titleKey="member.surname" />
	<display:column property="email" titleKey="member.email" />
	<display:column property="phoneNumber" titleKey="member.phoneNumber" />
</display:table>