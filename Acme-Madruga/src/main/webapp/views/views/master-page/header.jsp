<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

 <%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div align="left">
	<a href="#"><img src="images/logo2.png"
		alt="Acme-madruga Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li><a href="configuration/administrator/list.do"><spring:message
								code="master.page.administrator.configuration" /></a></li>
					<li><a class="fNiv" href="position/administrator/list.do"><spring:message
								code="master.page.administrator.positions" /></a> <br></li>
					<li><a href="register/actor.do?authority=ADMIN"><spring:message
								code="master.page.register.admin" /></a></li>
				</ul></li>

		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>

			<li><a class="fNiv"><spring:message	code="master.page.anon" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="register/actor.do?authority=MEMBER"><spring:message
								code="master.page.register.member" /></a></li>

					<li><a href="register/actor.do?authority=BROTHERHOOD"><spring:message
								code="master.page.register.brotherhood" /></a></li>
				</ul>
			</li>
			
			<li><a class="fNiv" href="brotherhood/list.do"><spring:message
						code="master.page.brotherhood" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
					<li><a class="fNiv" href="socialProfile/list.do"><spring:message
								code="master.page.socialProfile" /></a></li>
				</ul></li>
			<li><a class="fNiv" href="brotherhood/list.do"><spring:message
						code="master.page.brotherhood" /></a></li>


			<li><a class="fNiv" href="actor/edit.do"><spring:message
						code="master.page.actor.edit" /></a></li>

		</security:authorize>

		<security:authorize access="hasRole('BROTHERHOOD')">
			<li><a class="fNiv" href="procession/brotherhood/list.do"><spring:message
						code="master.page.myProcessions" /></a></li>
			<li><a class="fNiv" href="float/brotherhood/list.do"><spring:message
						code="master.page.floats" /></a></li>
			<li><a class="fNiv" href="request/brotherhood/list.do"><spring:message
						code="master.page.request" /></a></li>
			<li><a class="fNiv" href="brotherhood/edit.do"><spring:message
						code="master.page.settle" /></a></li>
			<li><a class="fNiv" href="enrolment/brotherhood/list.do"><spring:message
						code="master.page.enrolment" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('MEMBER')">
			<li><a class="fNiv" href="request/member/listMember.do"><spring:message
						code="master.page.myRequest" /></a></li>
			<li><a class="fNiv" href="enrolment/member/list.do"><spring:message
						code="master.page.brotherhood.member" /></a></li>
			<li><a class="fNiv" href="finder/member/listProcessions.do"><spring:message
						code="master.page.finder" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">

			<li><a href="register/actor.do?authority=ADMIN"><spring:message
						code="master.page.register.admin" /></a></li>

		</security:authorize>


	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>
 