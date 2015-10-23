<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:page title="Cadastro de Livros">

<jsp:attribute name="extraImports">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</jsp:attribute>

<jsp:attribute name="extraScripts">
	<script>
		$("#releaseDate").datepicker({dateFormat:'dd/mm/yy'})
	</script>
</jsp:attribute>

<jsp:body>

	<c:url value="/products" var="listLink"/>
	<a href="${listLink}">Voltar</a>
		
	<c:url value="/products/save" var="url"/>
	<!--form:form action="${spring:mvcUrl('PC#save').build()}" method="post" commandName="product"-->
	<form:form action="${url}" method="post" commandName="product" enctype="multipart/form-data">
		<div>
			<p>${msgErro}</p>
			<br/>
		</div>
		<div>
			<label for="title">Título</label>
			<form:input id="title" path="title"/>
			<form:errors path="title"/>
		</div>
		<div>
			<label for="description">Descrição</label>
			<form:textarea id="description" rows="4" cols="70" path="description"/>
			<form:errors path="description"/>
		</div>
		<div>
			<label for="numberOfPages">Número de Páginas</label>
			<form:input id="numberOfPages" path="numberOfPages"/>
			<form:errors path="numberOfPages"/>
		</div>
		<div>
			<label for="releaseDate">Data</label>
			<form:input path="releaseDate" id="releaseDate" type="text"/>
			<form:errors path="releaseDate"/>
		</div>
		<div>
			<label for="summary">Sumário do Livro</label>
			<!--form:input path="summary" id="summary" type="file"/>-->
			<input type="file" name="summary" id="summary"/>
			<form:errors path="summaryPath"/>
		</div>
		<div>
			<br/>
			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label>
					<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/>
					<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
				</div>
			</c:forEach>
		</div>
		<div>
			<br/>
			<input type="submit" value="Gravar"/>
		</div>
	</form:form>

</jsp:body>
		
</cdc:page>
