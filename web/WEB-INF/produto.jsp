<%-- 
    Document   : produto
    Created on : 14/03/2016, 21:43:36
    Author     : igor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produto</title>
    </head>
    <body>
        <h1>Produto</h1>
        <div>Nome: ${produto.nome}</div>
        <div>Preço: ${produto.preco}</div>
        <div>Quantidade: ${produto.quantidade}</div>
    </body>
</html>
