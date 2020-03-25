<%-- 
    Document   : achat
    Confirmation de l'achat si celui-ci a réussi.
    Author     : Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
--%>
<%@page import="fr.im2ag.m2cci.mytheatre.prog.model.Representation"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Achat</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">

            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-brand" href="#">
                        <img src="img/logo.jpg" alt="logo" style="width:40px;">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./index.html">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./progCtrler">Programmation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./progCtrlerAjoutProg">Administration</a>
                </li>
            </ul>
        </nav>
        <div class="wrapper">
            <h2>
                <%
                    SimpleDateFormat horaireFormatter = new SimpleDateFormat("dd/MM à HH'h'mm");
                    Representation rep = (Representation) session.getAttribute("representation");
                    String[] places = request.getParameterValues("place");
                    String[] rangs = request.getParameterValues("rang");
                %>
                Merci de votre achat du spectacle <%=rep.getSpectacle().getNom()%> du <%=horaireFormatter.format(rep.getHoraire())%><br>
                Vous avez acheté les places suivantes:<br>
                </h2>
                <div style="height:85vh; overflow: auto;">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Numero de place</th>
                                <th>Numero de rang</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < places.length - 1; i++) {
                                    Integer place = Integer.parseInt(places[i]);
                                    Integer rang = Integer.parseInt(rangs[i]);
                            %>
                            <tr>
                                <td><%=place%></td>
                                <td><%=rang%></td>
                            </tr>
                            <%
                                }
                            %>

                            </div>
                            </body>
                            </html>
