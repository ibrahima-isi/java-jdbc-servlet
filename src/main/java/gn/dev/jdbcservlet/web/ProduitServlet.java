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
            // initiation du mot clé
            String keyword = "";

            // recuperation des produits grace au mot clé, il retourne tous les produits si il est vide.
            List<Produit> produits = dao.getProduitByKeyWord("%"+keyword+"%");

            // recuperation des produits dans le model produit et envoi vers la vue.
            ProduitModel produitModel = new ProduitModel();

            // definition des valeurs a renvoyer vers la vue
            produitModel.setKeyword("");
            produitModel.setProduits(produits);
            req.setAttribute("prodModel", produitModel);
            // renvoi de la vue list.jsp
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } else if (path.equalsIgnoreCase("/search.do")) {
            // initiation du mot clé
            String keyword = req.getParameter("keyword");

            // recuperation des produits grace au mot clé, il retourne tous les produits si il est vide.
            List<Produit> produits = dao.getProduitByKeyWord("%"+keyword+"%");

            // recuperation des produits dans le model produit et envoi vers la vue.
            ProduitModel produitModel = new ProduitModel();

            // definition des valeurs a renvoyer vers la vue
            produitModel.setKeyword(keyword);
            produitModel.setProduits(produits);
            req.setAttribute("prodModel", produitModel);
            // renvoi de la vue list.jsp
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        }
    }
}
