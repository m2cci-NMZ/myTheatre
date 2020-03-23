<%-- 
    Document   : erreurachat
    Page d'erreur affichée lorsque l'utilisateur n'a pu effectuer son achat 
    parceque certaines des places qu'il voulait acheter viennent d'être vendues 
    (la méthode acheterPlace de la DAO PlaceDAO a levé une exception de type 
     AchatConcurrentException).
    Author     : Philippe Genoud - LIG Steamer - Université Grenoble Alpes
--%>
<%@page import="fr.im2ag.m2cci.mytheatre.prog.model.Representation"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CyberTheatre</title>
    </head>
    <body>
        <h1>Votre achat a échoué</h1>
        <%
            SimpleDateFormat horaireFormatter = new SimpleDateFormat("dd/MM à HH'h'mm");
            Representation rep = (Representation) session.getAttribute("representation");
        %>
        <p>Certaines des places que vous désiriez acheter pour le spectacle <strong><%=rep.getSpectacle().getNom()%> du %=horaireFormatter.format(rep.getHoraire())%></strong>
            viennent d'être vendues.</p>
        <div>
            <ul>
                <li>
                    <p><a href="spectacle?nospectacle=${spectacle.id}">Recommencez votre achat</a></p>
                </li>
                <li>
                    <p>Abandonner et <a href="/TheatreDemo">retourner à l'accueil</a></p>
                </li>
            </ul>
        </div>
    </body>
</html>
