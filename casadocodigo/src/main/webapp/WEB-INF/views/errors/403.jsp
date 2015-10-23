<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Não Autorizado</title>
</head>

<body>
	<p>Você não tem autorização para ver essa página!</p>
	<br/>
	<br/>
	<c:url value="/products" var="link"/>
	<a href="${link}">Voltar</a>
</body>
</html>