<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<spring:message code="marchRequest.approved" />
<br>
<display:table name="approvedMarchRequests" id="row"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<display:column property="procession.title" style="color:green" titleKey="marchRequest.procession" />
	<display:column property="row" titleKey="marchRequest.row" />
	<display:column property="columnn" titleKey="marchRequest.columnn" />

</display:table>
<br>
<br>
<spring:message code="marchRequest.rejected" />
<br>
<display:table name="rejectedMarchRequests" id="row"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<display:column property="procession.title" style="color:orange"
		titleKey="marchRequest.procession" />
	<display:column property="rejectedCauseedCauseect" titleKey="marchRequest.rejectedCause" />
</display:table>
<br>
<br>
<spring:message code="marchRequest.pending" />
<br>
<display:table name="pendingMarchRequests" id="row"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<display:column property="procession.title" style="color:grey"
		titleKey="marchRequest.procession" />

	<display:column>
		<a href="marchRequest/member/delete.do?marchRequestId=${row.id}"> 
		<spring:message code="marchRequest.delete" /> </a>
	</display:column>
</display:table>
<br>
<br>
<div>
		<a href="marchRequest/member/create.do"> <spring:message
				code="marchRequest.create" />
		</a>
	</div>