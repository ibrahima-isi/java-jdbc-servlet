package gn.dev.jdbcservlet.dao;

import gn.dev.jdbcservlet.entity.Produit;

import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDao {
    public static void main(String[] args) {
        DBConnexion db = new DBConnexion();
        Logger LOGGER = Logger.getLogger(TestDao.class.getName());
        ResultSet rs;
        IProduit dao = new ProduitImplDao();

        // Test insert
        Produit p = new Produit();
        p.setProduit_name("Orange");
        p.setProduit_qty(12);
//        dao.saveProduit(p);
//        dao.deleteProduitById(7);
//        String kw = "%a%";
//        List<Produit> produits = dao.getProduitByKeyWord(kw);
//        System.out.printf("Liste des produits");
//        for (Produit produit: produits){
//            System.out.println(produit);
//        }
//        System.out.println(dao.getProduitById(3));
//        Produit produit = dao.getProduitById(3);
//        System.out.println("Avant MAJ : "+produit);
//        produit.setProduit_name("Clavier");
//        produit.setProduit_qty(21);
//        dao.updateProduit(produit);
//        System.out.println("APRES MAJ : " + dao.getProduitById(3));

    }
}
