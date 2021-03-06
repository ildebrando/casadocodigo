<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@attribute name="title" required="true"%>
<%@attribute name="extraImports" fragment="true" %>
<%@attribute name="bodyClass" required="false"%>
<%@attribute name="extraScripts" fragment="true"%>

<!DOCTYPE html>
<html class="no-js" lang="pt">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title><fmt:message key='${title}'/></title>
	
	<link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/cssbase-min.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
	<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'>
	<link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/fonts.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
	<link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/fontello-ie7.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
	<link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/fontello-embedded.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
	<link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/fontello.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
    <link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/book-collection.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
    <link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/style.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
  	<link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/layout-colors.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
  	<link href="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/responsivo-style.css?1463716112737630901" rel="stylesheet" type="text/css"  media="all"  />
  	
  	<jsp:invoke fragment="extraImports" />
  	
</head>

<body class="${bodyClass}">
	
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user"/>
		<div><spring:message code="users.welcome" arguments="${user.name}"/></div>
	</sec:authorize>
	
	<jsp:doBody />
	
	<jsp:invoke fragment="extraScripts" />
	
</body>
</html>
