<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<security:authorize access="hasRole('ADMIN')">
	<h1><spring:message code="dashboard.show"/></h1>
	<br />
	
	<spring:message code="dashboard.avgMembers"/><jstl:out value="${avgMem}"/>
	<br />
	<spring:message code="dashboard.minMembers"/><jstl:out value="${minMem}"/>
	<br />
	<spring:message code="dashboard.maxMembers"/><jstl:out value="${maxMem}"/>
	<br />
	<spring:message code="dashboard.stddevMembers"/><jstl:out value="${stddevMem}"/>
	<br />
	
	<spring:message code="dashboard.largestBrotherhoods"/><jstl:out value="${largestBroth}"/>
	<br />
	
	<spring:message code="dashboard.smallestBrotherhoods"/><jstl:out value="${smallestBroth}"/>
	<br />
	
	<spring:message code="dashboard.requestMarchProcessionRatio"/>
	<spring:message code="dashboard.ratioList"/><jstl:out value="${ratioList}"/>
	<br />
	<spring:message code="dashboard.processionList"/><jstl:out value="${processionList}"/>
	<br />
	<spring:message code="dashboard.statusList"/><jstl:out value="${statusList}"/>
	<br />
	
	<spring:message code="dashboard.processions30Days"/><jstl:out value="${30DaysProcess}"/>
	<br />
	
	<spring:message code="dashboard.requestRatio"/><jstl:out value="${requestRatio}"/>
	<br />
	
	<spring:message code="dashboard.members10Percent"/><jstl:out value="${10PercentMaxNumber}"/>
	<br />
	
	<h2><spring:message code="dashboard.histogram"/></h2>
	<canvas id="myChart" width="500" height="500"></canvas>
	<script type="text/javascript">
	var position = "<jstl:out value='${positionList}'/>";
	var count = "<jstl:out value='${positionCountList}'/>";
	var labels=[];
	var values=[];
	<jstl:forEach var="val" items="${positionList}">
		values.push("<jstl:out value='${val}'/>");
	</jstl:forEach>
	values.push("${minM}");
	document.writeln(values[0]);
	var ctx = document.getElementById("myChart").getContext('2d');
	var chart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: title,
	        datasets: [{
	            label: 'Number of positions',
	            data: data,
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	</script>
</security:authorize>