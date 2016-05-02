<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Produto: formulário</title>
    </head>
    <body>
        <h1>Novo Produto: formulário</h1>
        <form method="POST">
            <div>
                <label>Nome:
                    <input name="nomeProduto" type="text" value="Teste"/>
                </label>
            </div>
            <div>
                <label>Quantidade:
                    <input name="qtd" type="text" value="10"/>
                </label>
            </div>
            <div>
                <label>Preço:
                    <input name="preco" type="text" value="100"/>
                </label>
            </div>
            <div>
                <input type="submit"/>
            </div>
        </form>
    </body>
</html>
