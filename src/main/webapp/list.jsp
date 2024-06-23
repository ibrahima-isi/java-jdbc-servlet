<%--
  Created by IntelliJ IDEA.
  User: ibrab
  Date: 22/06/2024
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Produits</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <%@include file="topbar.jsp" %>
    <div class="container col-md-10 col-md-offset-1 mt-5">
        <div class="card">
            <div class="card-header">Liste des produits</div>
            <div class="card-body">
                <form action="search.do" method="get">
                    <div class="form-group">
                        <label for="keyword" class="form-label">Mot clé</label>
                        <div class="d-flex justify-content-between">
                            <input type="text" name="keyword" id="keyword" class="col-6">
                            <button type="submit" class="btn btn-primary col-4">rechercher</button>
                        </div>
                    </div>
                </form>
                <div class="card ">
                    <c:forEach items="${prodModel.produits}" var="p">
                        <div class="card-header">Nom du produit : ${p.produit_name}</div>
                        <div class="card-body btn-outline-primary">Quantité : ${p.produit_qty}</div>
                        <div class="card-footer"><a href="add.do" class="btn btn-info">Ajouter</a> | <a href="edit.do?id=${p.produit_id}" class="btn btn-warning">Modifier</a>  | <a href="delete.do?id=${p.produit_id}" class="btn btn-danger" onclick="return confirm('Voulez-vous supprimer ce produit ?')">Supprimer</a></div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
