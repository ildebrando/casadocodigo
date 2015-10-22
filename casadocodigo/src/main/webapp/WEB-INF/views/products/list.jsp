<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Listagem de livros</title>
</head>

<body>
	<h2>${sucesso}</h2>
	<br/>
	<table>
		<tr>
			<th>Título</th>
			<th>Valores</th>
			<th>Detalhes</th>
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
	<div>
		<br/>
		<c:url value="/products/form" var="url"/>
		<form action="${url}" method="post">
			<input type="submit" value="Inserir" />
		</form>
	</div>
</body>
</html>