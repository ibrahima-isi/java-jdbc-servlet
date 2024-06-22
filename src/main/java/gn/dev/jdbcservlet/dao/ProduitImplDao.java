package gn.dev.jdbcservlet.dao;

import gn.dev.jdbcservlet.entity.Produit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProduitImplDao implements IProduit{
    /**
     * Represent a class of connexion
     */
    private DBConnexion db = new DBConnexion();
    private static final Logger LOGGER = Logger.getLogger(ProduitImplDao.class.getName());
    private ResultSet rs;
    private  int success;

    /**
     * Get All products as a list using a keyword searching
     * @param keyword the keyword to search for
     * @return the list of products matching the keyword or all the products if keyword is null
     */
    @Override
    public List<Produit> getProduitByKeyWord(String keyword) {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produits WHERE produit_name LIKE ? ORDER BY produit_id DESC";        try{
            db.initPrepare(sql);
            db.getPreparedStatement().setString(1, keyword);
            rs = db.doSelect();
            while (rs.next()){
                Produit produit = new Produit();
                produit.setProduit_id(rs.getInt("produit_id"));
                produit.setProduit_name(rs.getString("produit_name"));
                produit.setProduit_qty(rs.getInt("produit_qty"));
                produits.add(produit);
            }
            db.closeConnexion();
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Couldn't find product", e);
        }
        return produits;
    }

    /**
     * Get one product using the id
     * @param id the id of the product
     * @return the product with the specified id, or null if not found
     */
    @Override
    public Produit getProduitById(int id) {
        Produit produit = null;
        String sql = "SELECT * FROM produits WHERE produit_id = ?";
        try{
            db.initPrepare(sql);
            db.getPreparedStatement().setInt(1, id);
            rs = db.doSelect();
            if (rs.next()){
                produit = new Produit();
                produit.setProduit_id(rs.getInt("produit_id"));
                produit.setProduit_name(rs.getString("produit_name"));
                produit.setProduit_qty(rs.getInt("produit_qty"));
            }
            db.closeConnexion();
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Couldn't find product", e);
        }
        return produit;
    }

    /**
     * Save a product to the database
     * @param produit the product that have been saved to the database
     */
    @Override
    public void saveProduit(Produit produit) {
        String sql = "INSERT INTO produits(produit_name, produit_qty) VALUES(?, ?)";
        try{
            db.initPrepare(sql);
            db.getPreparedStatement().setString(1, produit.getProduit_name());
            db.getPreparedStatement().setInt(2, produit.getProduit_qty());
            success = db.executeMaj();
            db.closeConnexion();
        }catch (SQLException exception){
            LOGGER.log(Level.SEVERE, "Save failled !", exception);
        }
    }

    /**
     * Delete a product by his id
     * @param id the id of the product to search for
     */
    @Override
    public void deleteProduitById(int id) {
        String sql = "DELETE FROM produits WHERE produit_id = ?";
        try{
            db.initPrepare(sql);
            db.getPreparedStatement().setInt(1, id);
            db.executeMaj();
            db.closeConnexion();
        }catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete", e);
        }
    }

    /**
     * Update a product in the database
     * @param produit the product updated
     */
    @Override
    public void updateProduit(Produit produit) {
        String sql = "UPDATE produits SET produit_name = ?, produit_qty = ? WHERE produit_id = ?";
        try{
            db.initPrepare(sql);
//            db.getPreparedStatement().setInt(1, produit.getProduit_id());
            db.getPreparedStatement().setString(1, produit.getProduit_name());
            db.getPreparedStatement().setInt(2, produit.getProduit_qty());
            db.getPreparedStatement().setInt(3, produit.getProduit_id());
            db.executeMaj();
            db.closeConnexion();
            System.out.println("Updated successfully !");
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed to update", e);
        }
    }
}
