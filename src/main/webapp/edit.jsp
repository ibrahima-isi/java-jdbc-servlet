<%--
  Created by IntelliJ IDEA.
  User: ibrab
  Date: 22/06/2024
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier produit</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<%@include file="topbar.jsp" %>
<div class="container col-md-10 col-md-offset-1 mt-5">
    <div class="card">
        <div class="card-header">Edition produit</div>
        <div class="card-body">
            <form action="update.do" method="post">
                <div class="form-group" >
                    <label for="id" class="form-label">ID</label>
                    <input class="form-control" type="text" id="id" name="id" value="${produit.produit_id}" readonly>
                </div>
                <div class="form-group">
                    <label for="name" class="form-label">Nom du produit</label>
                    <input type="text" id="name" name="name" class="form-control" value="${produit.produit_name}">
                </div>
                <div class="form-group">
                    <label for="qty" class="form-label">Quantité du produit</label>
                    <input type="text" id="qty" name="qty" class="form-control" value="${produit.produit_qty}">
                </div>
                <div class="form-groupe">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
