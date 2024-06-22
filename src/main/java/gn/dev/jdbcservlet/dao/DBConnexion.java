package gn.dev.jdbcservlet.dao;

import lombok.Getter;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnexion {
    private Connection connexion;
    @Getter
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private int succes;

    // mise en place de logs
    private static final Logger LOGGER = Logger.getLogger(DBConnexion.class.getName());

    private Connection getConnexion(){
        String url = "jdbc:mysql://localhost:3306/jdbc_db";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
        }catch (Exception e){
//            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "ECHEC DE CONNEXION", e);
        }
        return connexion;
    }

    public ResultSet doSelect(){
        resultSet = null;
        try{
//            connexion = getConnexion();
//            preparedStatement = connexion.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

        }catch (SQLException exception){
//            exception.printStackTrace();
            LOGGER.log(Level.SEVERE, "ECHEC de select !", exception);
        }
        return resultSet;
    }

    public int executeMaj(){
        try {
//            connexion = getConnexion();
//            preparedStatement = connexion.prepareStatement(query);
//            for (int i = 0; i < params.length; i++) {
//                preparedStatement.setObject(i+1, params[i]);
//            }
            succes = preparedStatement.executeUpdate();

        }catch (SQLException exception){
//            exception.printStackTrace();
            LOGGER.log(Level.SEVERE, "ExecuteUpdate failed !", exception);

        }finally {
            closeConnexion();
        }
        return succes;
    }

//    public int insert(String query, Object... params) {
//        return executeUpdate(query, params);
//    }
//
//    public int update(String query, Object... params) {
//        return executeUpdate(query, params);
//    }
//
//    public int delete(String query, Object... params) {
//        return executeUpdate(query, params);
//    }

    public void closeConnexion(){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connexion != null){
                connexion.close();
            }
    }catch (SQLException e){
//            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "ECHEC DE la fermeture de CONNEXION", e);

        }
    }

    public void initPrepare(String sql){
        try{
            getConnexion();
            preparedStatement = connexion.prepareStatement(sql);
        }catch (Exception exception){
            LOGGER.log(Level.SEVERE, "Erreur de preparation de la requete", exception);
        }
    }
}
