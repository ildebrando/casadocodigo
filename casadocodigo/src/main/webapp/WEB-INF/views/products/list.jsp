<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:page title="<fmt:message key='products.list.pageTitle'/>">

<jsp:body>

	<sec:authorize access='hasRole("ROLE_ADMIN")'>
		<c:url value="/products/form" var="formLink"/>
		<a href="${formLink}"><fmt:message key="addNewProduct"/></a>
	</sec:authorize>
	
	<br/><br/>
	<h2>${sucesso}</h2>
	<br/>
	<table>
		<tr>
			<th><fmt:message key="products.list.title"/></th>
			<th><fmt:message key="products.list.value"/></th>
			<th><fmt:message key="products.list.detail"/></th>
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
					<a href="${linkDetalhar}"><fmt:message key="products.list.detail"/></a>
				</td>
			</tr>
		</c:forEach>
	</table>

</jsp:body>

</cdc:page>
