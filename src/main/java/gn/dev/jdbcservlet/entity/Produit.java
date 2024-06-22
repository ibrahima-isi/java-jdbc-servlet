package gn.dev.jdbcservlet.entity;

import lombok.Data;

@Data
public class Produit {
    private int produit_id;
    private String produit_name;
    private int produit_qty;
}
