<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="boxes" requestURI="${requestURI}" id="row">

<!-- Attributes -->
		<display:column property="name" titleKey="box.name" sortable="true"/>
<!-- Actions -->
		<display:column>
	
			<a href="message/list.do?boxId=${row.id}"> 
				<spring:message	code="box.select" />
			</a>

		</display:column>
		
		<display:column>
			<jstl:if test="${row.predefined==false}">
			<a href="box/edit.do?boxId=${row.id}"> 
				<spring:message	code="box.edit" />
			</a>
			</jstl:if>
		</display:column>
		
	</display:table>

<div>
	<a href="box/create.do"> 
	<spring:message	code="box.create" />
	</a>
</div>
<div>
	<a href="message/create.do">
	<spring:message code="message.create"/>
	</a>
</div>
<security:authorize access="hasRole('ADMIN')">
<div>
	<a href="message/createBroadcast.do">
	<spring:message code="message.createBroadcast"/>
	</a>
</div>
</security:authorize>
</security:authorize>