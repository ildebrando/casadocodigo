<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:page title="Listagem de livros">

<jsp:body>

	<sec:authorize access='hasRole("ROLE_ADMIN")'>
		<c:url value="/products/form" var="formLink"/>
		<a href="${formLink}"><spring:message code="addNewProduct"/></a>
	</sec:authorize>
	
	<br/><br/>
	<h2>${sucesso}</h2>
	<br/>
	<table>
		<tr>
			<th><spring:message code="list.title"/></th>
			<th><spring:message code="list.value"/></th>
			<th><spring:message code="list.detail"/></th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.id} - ${product.title}</td>
				<td>
					<c:forEach items="${product.prices}" var="price">
						[ ${price.value} - ${price.bookType} ]
					</c:forEach>
				</td>
				<td>
					<c:url value="/products/${product.id}" var="linkDetalhar"/>
					<a href="${linkDetalhar}">Detalhar</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</jsp:body>

</cdc:page>
