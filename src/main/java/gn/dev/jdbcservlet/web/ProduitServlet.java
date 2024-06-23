package gn.dev.jdbcservlet.web;

import gn.dev.jdbcservlet.dao.IProduit;
import gn.dev.jdbcservlet.dao.ProduitImplDao;
import gn.dev.jdbcservlet.entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "produitServlet", urlPatterns = "*.do")
public class ProduitServlet extends HttpServlet {
    private IProduit dao;

    @Override
    public void init() throws ServletException {
        dao = new ProduitImplDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if(path.equalsIgnoreCase("/index.do")){
            forward("", req, resp);

        } else if (path.equalsIgnoreCase("/search.do")) {
            // initiation du mot clé
            String keyword = req.getParameter("keyword");
            forward(keyword, req, resp);

        }else if(path.equalsIgnoreCase("/add.do")){
            req.getRequestDispatcher("add.jsp").forward(req, resp);

        } else if (path.equalsIgnoreCase("/save.do")) {
            String p_name = req.getParameter("name");
            int p_qty = Integer.parseInt(req.getParameter("qty"));
            Produit produit = new Produit();
            produit.setProduit_name(p_name);
            produit.setProduit_qty(p_qty);
            dao.saveProduit(produit);
            forward("", req, resp);

        } else if (path.equalsIgnoreCase("/edit.do")){
            Produit produit = dao.getProduitById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("produit", produit);
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        } else if(path.equalsIgnoreCase("/update.do") && req.getMethod().equalsIgnoreCase("post")){
            Produit produit = new Produit();
            produit.setProduit_id(Integer.parseInt(req.getParameter("id")));
            produit.setProduit_name(req.getParameter("name"));
            produit.setProduit_qty(Integer.parseInt(req.getParameter("qty")));
            System.out.println(produit);
            dao.updateProduit(produit);
            forward("", req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public  void forward(String s, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // initiation du mot clé
        String keyword = s;

        // recuperation des produits grace au mot clé, il retourne tous les produits si il est vide.
        List<Produit> produits = dao.getProduitByKeyWord("%"+keyword+"%");

        // recuperation des produits dans le model produit et envoi vers la vue.
        ProduitModel produitModel = new ProduitModel();

        // definition des valeurs a renvoyer vers la vue
        produitModel.setKeyword(s);
        produitModel.setProduits(produits);
        request.setAttribute("prodModel", produitModel);
        // renvoi de la vue list.jsp
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
