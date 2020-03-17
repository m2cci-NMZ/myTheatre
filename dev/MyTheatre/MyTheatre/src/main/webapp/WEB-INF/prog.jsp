<%-- 
    Document   : prog
    Created on : 6 mars 2020, 10:04:02
    Author     : marti236
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="fr.im2ag.m2cci.mytheatre.prog.model.Representation"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Programmation de My Theatre</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">

        <style>
            .form-check{
                padding-left : 0.9cm;
            }
        </style>

    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">

            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-brand" href="#">
                        <img src="img/logo" alt="logo" style="width:40px;">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./index.html">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Programmation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./progCtrlerAjoutProg">Administration</a>
                </li>
            </ul>
        </nav>
        <div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-3">
                        <form action="progCtrler" >
                            <br>
                            <h2>Recherche</h2>
                            <h4>Dates</h4>
                            <%
                                SimpleDateFormat navigateurJourFormatter = new SimpleDateFormat("yyyy-MM-dd");      // Pour fixer la valeur dans le formulaire
                                Date dateDebut = (Date) request.getAttribute("dateDebut");
                                Date dateFin = (Date) request.getAttribute("dateFin");

                                // Affichage des dates sélectionnés dans la nouvelle page
                                String dateDebutForm;
                                if (dateDebut == null) {
                                    dateDebutForm = "2020-03-01";
                                } else {
                                    dateDebutForm = navigateurJourFormatter.format(dateDebut);
                                }
                                String dateFinForm;
                                if (dateFin == null) {
                                    dateFinForm = "2020-03-31";
                                } else {
                                    dateFinForm = navigateurJourFormatter.format(dateFin);
                                }
                            %>
                            <div class="form-row">
                                <div class=col-auto">
                                    <input type="date" class="form-control" name="dateDebut" value=<%=dateDebutForm%>>
                                </div>
                                <label class="col-form-label">au</label>
                                <div class=col-auto">
                                    <input type="date" class="form-control" name="dateFin" value=<%=dateFinForm%>>
                                </div>
                            </div>
                            <br>
                            <h4>Catégorie de spectateurs</h4>
                            <%  // Conserve le bouton check pour la cible
                                String whichRadio = request.getParameter("cible");
                                String checkIndif = "";
                                String check1Cinq = "";
                                String checkJeune = "";
                                String checkToutP = "";
                                String checkAdult = "";
                                if (whichRadio == null)
                                    checkIndif = " checked";
                                else if (whichRadio.equals("unCinqAns"))
                                    check1Cinq = " checked";
                                else if (whichRadio.equals("jeunePublic"))
                                    checkJeune = " checked";
                                else if (whichRadio.equals("toutPublic"))
                                    checkToutP = " checked";
                                else if (whichRadio.equals("adulte"))
                                    checkAdult = " checked";
                                else
                                    checkIndif = " checked";
                            %>
                            <div class="form-group">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" id="indiffC" name="cible" value="null" <%=checkIndif%>> Indifférent
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" id="unCinqP" name="cible" value="unCinqAns" <%=check1Cinq%>> 1-5 ans
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" id="jeuneP" name="cible" value="jeunePublic" <%=checkJeune%>> Jeune Public
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" id="toutP" name="cible" value="toutPublic" <%=checkToutP%>> Tout Public
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" id="adulteP" name="cible" value="adulte" <%=checkAdult%>> Adultes
                                    </label>
                                </div>
                            </div>
                            <h4>Type de spectacles</h4>
                            <%  // Conserve le bouton check pour le type de spectacle
                                String[] typesCheck = request.getParameterValues("type");
                                String checkOpera = "";
                                String checkHumor = "";
                                String checkDrame = "";
                                String checkMusic = "";
                                String checkCirqu = "";
                                if (typesCheck != null) {
                                    for (int i = 0; i < typesCheck.length; i++) {
                                        if (typesCheck[i].equals("opera")) {
                                            checkOpera = " checked";
                                        } else if (typesCheck[i].equals("humoristique")) {
                                            checkHumor = " checked";
                                        } else if (typesCheck[i].equals("drame")) {
                                            checkDrame = " checked";
                                        } else if (typesCheck[i].equals("musical")) {
                                            checkMusic = " checked";
                                        } else if (typesCheck[i].equals("cirque")) {
                                            checkCirqu = " checked";
                                        }
                                    }
                                } else {
                                    checkOpera = " checked";
                                    checkHumor = " checked";
                                    checkDrame = " checked";
                                    checkMusic = " checked";
                                    checkCirqu = " checked";
                                }
                            %>
                            <div class="form-group">        
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="type" value="opera"<%=checkOpera%>> Opéra
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="type" value="humoristique"<%=checkHumor%>> Humoristique
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="type" value="drame" <%=checkDrame%>> Drame
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="type" value="musical" <%=checkMusic%>> Musical
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="type" value="cirque" <%=checkCirqu%>> Cirque
                                    </label>
                                </div> 
                            </div>
                            <div> 
                                <button type="submit" class="btn btn-primary">Envoyer</button>
                            </div>

                    </div>
                    <div class="col-md-9">
                        <br>
                        <%
                            if (dateDebut != null && dateFin != null) {
                                SimpleDateFormat jourFormatter = new SimpleDateFormat("dd/MM/yyyy");
                        %>
                        <h2>Programmation du <%=jourFormatter.format(dateDebut)%> au <%=jourFormatter.format(dateFin)%></h2>

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Horaire</th>
                                    <th>Nom</th>
                                    <th>Prix</th>                    
                                    <th>Type de spectacle</th>
                                    <th>Catégorie de spectateurs</th>   
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    SimpleDateFormat horaireFormatter = new SimpleDateFormat("dd/MM à HH");
                                    List<Representation> prog = (List<Representation>) request.getAttribute("progList");
                                    for (Representation r : prog) {
                                        Date date = r.getHoraire();
                                        String nom = r.getSpectacle().getNom();

                                        // Formatage du prix à la française
                                        Double prixDeBase = r.getSpectacle().getPrixDeBase();
                                        NumberFormat prix = NumberFormat.getInstance(Locale.FRENCH);
                                        prix.setMinimumFractionDigits(2);
                                        prix.setMaximumFractionDigits(2);
                                        String prixBase = prix.format(prixDeBase);

                                        // Mise en forme des cibles et des types
                                        String cible = r.getSpectacle().getCible();
                                        String type = r.getSpectacle().getType();
                                        switch (cible) {
                                            case "toutPublic":
                                                cible = "Tout Public";
                                                break;
                                            case "unCinqAns":
                                                cible = "1-5 Ans";
                                                break;
                                            case "jeunePublic":
                                                cible = "Jeune Public";
                                                break;
                                            case "adulte":
                                                cible = "Adulte";
                                                break;
                                        }
                                        switch (type) {
                                            case "opera":
                                                type = "Opéra";
                                                break;
                                            case "drame":
                                                type = "Drame";
                                                break;
                                            case "humoristique":
                                                type = "Humoristique";
                                                break;
                                            case "musical":
                                                type = "Musical";
                                                break;
                                            case "cirque":
                                                type = "Cirque";
                                                break;
                                        }
                                %>

                                <tr>
                                    <td><%=horaireFormatter.format(date)%>h</td>
                                    <td><%=nom%></td>
                                    <td><%=prixBase%> €</td>
                                    <td><%=type%></td>
                                    <td><%=cible%></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>   
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous">
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous">
        </script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous">
        </script>

    </body>
</html>
