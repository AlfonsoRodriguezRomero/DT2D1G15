<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

	<display:table pagesize="5" class="displaytag"
		name="actors" requestURI="${requestURI}" id="row">
		<display:column property="userAccount.username" 
		titleKey="actor.username" />
		<display:column titleKey="actor.profile">
			<jstl:if test="${row.userAccount.enabled == true}"></jstl:if>
			<a href="actor/administrator/show.do?actorId=${row.id}"> 
			<spring:message code="actor.profile" />
			</a>
		</display:column>
	</display:table>