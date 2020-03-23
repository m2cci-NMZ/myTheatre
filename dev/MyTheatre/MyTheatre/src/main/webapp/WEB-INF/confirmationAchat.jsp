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
            <h1>
                <%
                    SimpleDateFormat horaireFormatter = new SimpleDateFormat("dd/MM à HH'h'mm");
                    Representation rep = (Representation) session.getAttribute("representation");
                %>
                Merci de votre achat du spectacle <%=rep.getSpectacle().getNom()%> du <%=horaireFormatter.format(rep.getHoraire())%>
            </h1>
        </div>
    </body>
</html>
