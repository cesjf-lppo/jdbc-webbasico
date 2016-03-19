<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Produtos</title>
    </head>
    <body>
        <h1>Listar Produtos</h1>
        <c:if test="${erro!=null}">
            <div style="background-color: pink;">${erro}</div>
        </c:if>
        <ul>
            <c:forEach items="${produtos}" var="produto">
                <li>${produto.nome} R$${produto.preco}</li>
            </c:forEach>
        </ul>
    </body>
</html>
