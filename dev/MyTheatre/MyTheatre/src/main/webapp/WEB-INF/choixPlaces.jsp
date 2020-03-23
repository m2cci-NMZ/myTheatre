<%-- 
    Document   : achatPlaces
    Cette page utilise le plugin jQuery jQuery Seat Charts pour afficher un
    plan de salle sur lequel l'utilisateur peut sélectionner ses places et
    les acheter.
    Cette page utilise JQuery pour à intervalles réguliers rafraichir le plan de
    salle afin de mettre à jour la liste des places disponibles.

    Author     : Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
--%>
<%@page import="fr.im2ag.m2cci.mytheatre.prog.model.Representation"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Achat de places</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link href="js/jQuery-Seat-Charts/jquery.seat-charts.css" rel="stylesheet" type="text/css"/>
        <link href="css/stylesTheatre.css" rel="stylesheet" type="text/css"/>
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
                Veuillez choisir vos places pour <%=rep.getSpectacle().getNom()%><br>
                <%=horaireFormatter.format(rep.getHoraire())%>
            </h1>
            <div id="map-container">
                <!-- Le div qui contient le plan de la salle -->
                <div id="seat-map">
                    <div class="front-indicator">Scène</div>
                </div>
                <!-- Le div qui contient le récapitulatif des places sélectionnées par
                     l'utilisateur -->
                <div id="commande">
                    <div id="legend"></div>
                    <h3>Votre sélection</h3>
                    <p>Nbre de places: <strong><span id="nbplaces">0</span></strong></p>
                    <p>Prix Total: <strong><span id="prixtotal">0</span> €</strong></p>
                    <button id="achatBtn" disabled>Acheter</button>
                </div>
            </div>
            <hr>

        </div>
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="js/jQuery-Seat-Charts/jquery.seat-charts.min.js" type="text/javascript"></script>
        <script src="js/achatPlaces.js" type="text/javascript"></script>

    </body>
</html>

