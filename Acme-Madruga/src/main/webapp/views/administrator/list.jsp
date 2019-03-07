<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

	<display:table name="actors" id="row" requestURI="${requestURI}"
		pagesize="5" class="displaytag">
	
		<display:column property="userAccount.username" titleKey="actor.username" />
		<display:column property="name" titleKey="actor.name" />
		<display:column property="middleName" titleKey="actor.middleName" />
		<display:column property="surname" titleKey="actor.surname" />
		<display:column property="email" titleKey="actor.email" />
		<display:column property="phoneNumber" titleKey="actor.phoneNumber" />	
	</display:table>