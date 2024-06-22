package gn.dev.jdbcservlet.dao;


import gn.dev.jdbcservlet.entity.Produit;

import java.util.List;

public interface IProduit {
    public List<Produit> getProduitByKeyWord(String keyword);
    public Produit getProduitById(int id);
    public void saveProduit(Produit produit);
    public  void deleteProduitById(int id);
    public void updateProduit(Produit produit);
}
