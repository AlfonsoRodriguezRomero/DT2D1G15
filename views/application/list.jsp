<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="applications" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	
	<display:column property="moment" titleKey="application.moment" sortable="true" />

	<spring:message code="application.status" var="statusHeader" />
	<jsp:useBean id="now" class="java.util.Date"/>
	
	<jstl:choose>
	<jstl:when test="${row.status == 'pending'}">
	<display:column property="status" title="${statusHeader}" style="color:black" sortable="false" />
	</jstl:when>
	<jstl:when test="${row.status == 'rejected'}">
	<display:column property="status" title="${statusHeader}" style="color:red" sortable="false" />
	</jstl:when>
	<jstl:when test="${row.status == 'accepted'}">
	<display:column property="status" title="${statusHeader}" style="color:green" sortable="false" />
	</jstl:when>
	<jstl:when test="${row.fixUpTask.endDate lt now && row.status=='pending'}">
	<display:column property="status" title="${statusHeader}" style="color:gray" sortable="false" />
	</jstl:when> 
	<jstl:otherwise>
	<display:column property="status" title="${statusHeader}" style="color:black" sortable="false" />
	</jstl:otherwise>
	</jstl:choose>

	<spring:message code="application.offeredPrice" var="offeredPriceHeader" />
	<display:column property="offeredPrice" title="${offeredPriceHeader}" sortable="false" />
	<display:column title="${offeredPriceHeader}" sortable="false">
	<jstl:out value="${row.offeredPrice + row.offeredPrice*vat/100 }"></jstl:out>
	</display:column>

	<spring:message code="application.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}"	sortable="false" />
	
	<spring:message code="application.rejectedCause" var="rejectedCauseHeader" />
	<display:column property="rejectedCause" title="${rejectedCauseHeader}"	sortable="false" />
	
	
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
					<a href="application/customer/edit.do?applicationId=${row.id}">
						<spring:message code="application.edit" />
					</a>					
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('HANDYWORKER')">
		<display:column>
					<a href="application/handyWorker/show.do?applicationId=${row.id}">
						<spring:message code="application.show" />
					</a>					
		</display:column>		
	</security:authorize>
</display:table>

<!-- 
<security:authorize access="hasRole('HANDYWORKER')">
	<div>
		<a href="application/handyworker/create.do"> <spring:message
				code="application.create" />
		</a>
	</div>
</security:authorize>

 -->