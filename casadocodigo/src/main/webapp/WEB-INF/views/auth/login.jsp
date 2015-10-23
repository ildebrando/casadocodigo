<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>

<body>
	<h3>Efetue Login</h3>
	<c:url value="/login" var="url"/>
	<form:form servletRelativeAction="/login" method="post">
		<div>
			<p>${msgErro}</p>
			<br/>
		</div>
		<div>
			<table>
				<tr>
					<td>Login:</td>
					<td><input type="text" name="username" value=""/></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input name="submit" type="submit" value="Logar"/>
					</td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>