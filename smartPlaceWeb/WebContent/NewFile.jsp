<%@ page language="java" contentType="text/html"
    pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${utente!=null}">
		<h2>Loggato</h2>
		<h2>${utente.email}</h2>
		<h2>${utente.nome}</h2>
		<h2>${utente.cognome}</h2>
	</c:if>
	<c:if test="${utente==null}">
		<h2>Non loggato</h2>
	</c:if>

</body>
</html>