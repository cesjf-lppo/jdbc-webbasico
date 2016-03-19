package br.cesjf.lppo.controllers;

import br.cesjf.lppo.Produto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igor
 */
@WebServlet(name = "ProdutosDBController", urlPatterns = {"/listar-produtos2.html"})
public class ProdutosDBController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Cria lista de Objetos
        List<Produto> produtos = new ArrayList<>();

        //Preenche lista de objetos
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2016-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT nome, quantidade, preco FROM produto");
            while(resultado.next()){
                Produto produto = new Produto();
                produto.setNome(resultado.getString("nome"));
                produto.setPreco(resultado.getFloat("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produtos.add(produto);
            }
            conexao.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDBController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", "Driver não Encontrado!");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDBController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", "Erro ao execuar uma operação no banco: "+ex.getLocalizedMessage());
            //throw new ServletException(ex);

        }
        
        //Adiciona lista de objetos no request
        request.setAttribute("produtos", produtos);
        //Encaminha a requisição para o JSP
        request.getRequestDispatcher("/WEB-INF/listar-produtos.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
