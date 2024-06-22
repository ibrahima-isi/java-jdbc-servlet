package gn.dev.jdbcservlet.web;

import gn.dev.jdbcservlet.entity.Produit;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProduitModel {
    private String keyword;
    private List<Produit> produits = new ArrayList<>();
}
