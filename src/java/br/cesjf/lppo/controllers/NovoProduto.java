/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lppo.controllers;

import br.cesjf.lppo.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igor
 */
@WebServlet(name = "NovoProduto", urlPatterns = {"/novo-produto.html"})
public class NovoProduto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.getRequestDispatcher("/WEB-INF/novo-produto-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 System.out.println("Nome: "+request.getParameter("nomeProduto"));
 System.out.println("Quantidade: "+request.getParameter("qtd"));
 System.out.println("Preço: "+request.getParameter("preco"));
 String nomeStr = request.getParameter("nomeProduto");
 String quantidadeStr = request.getParameter("qtd");
 String precoStr = request.getParameter("preco");
 
   //Cria novo Produto na memória
   Produto novoProduto = new Produto();
   
   //Preenche novo Produto com dados do formulário
   novoProduto.setNome(nomeStr);
   novoProduto.setQuantidade(Integer.parseInt(quantidadeStr));
   novoProduto.setPreco(Float.parseFloat(precoStr));
   
   //Envia objeto para o banco de dados
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String driverURL = "jdbc:derby://localhost:1527/lppo-2016-1";
            Connection conexao = DriverManager.getConnection(driverURL, "usuario", "senha");

            Statement operacao = conexao.createStatement();
            
            String sql = "INSERT INTO produto(nome, quantidade, preco, atualizado) VALUES('"
                    + novoProduto.getNome() + "', "
                    + novoProduto.getQuantidade() + ","
                    + novoProduto.getPreco() + ", CURRENT_TIMESTAMP)";
            operacao.executeUpdate(sql);

            conexao.close();
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver do JavaDB não disponível!");
            request.setAttribute("erro", "Driver do JavaDB não disponível!");
        } catch (SQLException ex) {
            System.err.println("Erro ao executar operação no SGBD:\n" + ex);
            request.setAttribute("erro", "Erro ao executar operação no SGBD:\n" + ex);
        }
   
        //Redireciona requisição
        response.sendRedirect("listar-produtos2.html");
       
    }

}
