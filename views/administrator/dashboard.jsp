<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
	<spring:message code="netcasheAvgStddev"/>
	<jstl:out value = "${netcasheAvgStddev}" /><br/>
	<spring:message code="netcashePublishedRatio"/>
	<jstl:out value = "${netcashePublishedRatio}" /><br/>
	<spring:message code="netcasheUnpublishedRatio"/>
	<jstl:out value = "${netcasheUnpublishedRatio}" /><br/>
	<spring:message code="avg.fixuptask"/>
	<jstl:out value = "${avgFixUpTask}" /><br/>
	<spring:message code="min.fixuptask"/>
	<jstl:out value = "${minFixUpTask}" /><br/>
	<spring:message code="max.fixuptask"/>
	<jstl:out value = "${maxFixUpTask}" /><br/>
	<spring:message code="stdev.fixuptask"/>
	<jstl:out value = "${stdevFixUpTask}" /><br/>
	
	<spring:message code="avg.application"/>
	<jstl:out value = "${avgApp}" /><br/>
	<spring:message code="min.application"/>
	<jstl:out value = "${minApp}" /><br/>
	<spring:message code="max.application"/>
	<jstl:out value = "${maxApp}" /><br/>
	<spring:message code="stdev.application"/>
	<jstl:out value = "${stdevApp}" /><br/>
	
	<spring:message code="avg.price"/>
	<jstl:out value = "${avgPrice}" /><br/>
	<spring:message code="min.price"/>
	<jstl:out value = "${minPrice}" /><br/>
	<spring:message code="max.price"/>
	<jstl:out value = "${maxPrice}" /><br/>
	<spring:message code="stdev.price"/>
	<jstl:out value = "${stdevPrice}" /><br/>
	
	<spring:message code="avg.offPrice"/>
	<jstl:out value = "${avgOffPrice}" /><br/>
	<spring:message code="min.offPrice"/>
	<jstl:out value = "${minOffPrice}" /><br/>
	<spring:message code="max.offPrice"/>
	<jstl:out value = "${maxOffPrice}" /><br/>
	<spring:message code="stdev.offPrice"/>
	<jstl:out value = "${stdevOffPrice}" /><br/>
	
	<spring:message code="application.pending"/>
	<jstl:out value = "${pendingApplications}" /><br/>
	<spring:message code="application.rejected"/>
	<jstl:out value = "${rejectedApplications}" /><br/>
	<spring:message code="application.accepted"/>
	<jstl:out value = "${acceptedApplications}" /><br/>
	<spring:message code="application.elapsed"/>
	<jstl:out value = "${elapsedApplications}" /><br/>
	
	<spring:message code="customer.morefixuptasks"/>
	<jstl:out value = "${moreFixUpTasks}" /><br/>
	<spring:message code="handyworker.moreapplications"/>
	<jstl:out value = "${moreApplications}" /><br/>
	
	
</security:authorize>