<%-- 
    Document   : prog
    Created on : 6 mars 2020, 10:04:02
    Author     : marti236
--%>

<%@page import="java.util.Calendar"%>
<%@page import="fr.im2ag.m2cci.mytheatre.prog.model.Representation"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="fr.im2ag.m2cci.mytheatre.prog.model.Spectacle"%>
<%@page import="java.util.List"%>
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
            .colRepAdmin{
                width: 14.28%;
            }

            .erreurMsg{
                margin-top: -13px;
                color : #FF0000;
                text-align: justify;
            }

            .multiple-input-feedback{   
                padding-left: 5px;
            }
        </style>
    </head>
    <body onload="init()">
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
                    <a class="nav-link" href="#">Administration</a>
                </li>
            </ul>
        </nav>
        <div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-md-6">
                                <%
                                    // Messages d'erreur liés aux ajouts du formulaire
                                    List<String> erreursSQL = (List<String>) request.getAttribute("erreursSQL");
                                    boolean erreurPK_SPE = false;
                                    boolean erreurPK_REP = false;
                                    boolean erreurFK_REP_numeroSpe = false;
                                    if (erreursSQL != null) {
                                        for (String erreur : erreursSQL) {
                                            if (erreur.equals("PK_SPE")) {
                                                erreurPK_SPE = true;
                                            } else if (erreur.equals("PK_REP")) {
                                                erreurPK_REP = true;
                                            } else if (erreur.equals("FK_REP_numeroSpe")) {
                                                erreurFK_REP_numeroSpe = true;
                                            }
                                        }
                                    }
                                %>
                                <br>
                                <h2>Spectacle</h2>
                                <form action="insertSpectacle">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Numéro</label>
                                        <div class="col-sm-9">
                                            <%
                                                // Message d'erreur si le numeroSpe est déjà pris
                                                if (erreurPK_SPE) {
                                            %>
                                            <input type="number" class="form-control is-invalid" name="numeroSpe" min="1" step="1" required>
                                            <div class="invalid-feedback">
                                                Ce numéro correspond déjà à un autre spectacle, il doit être unique.
                                            </div>        
                                            <%
                                            } else {
                                            %>
                                            <input type="number" class="form-control" name="numeroSpe" min="1" step="1" required>
                                            <%
                                                }
                                            %>
                                        </div> 
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Nom</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="nomSpe" required>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Prix</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" name="prixSpe" step="0.01" min="0.01" required>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Cible</label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="cibleSpe">
                                                <option value="unCinqAns">1-5 Ans</option>
                                                <option value="jeunePublic">Jeune Public</option>
                                                <option value="toutPublic">Tout Public</option>
                                                <option value="adulte">Adultes</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row" style="margin-bottom: 0px;">
                                        <label class="col-sm-3 col-form-label">Type</label>
                                        <div class="col-sm-9">
                                            <select class="form-control" id="typeSpeInput" name="typeSpe">
                                                <option value="opera">Opéra</option>
                                                <option value="humoristique">Humoristique</option>
                                                <option value="drame">Drame</option>
                                                <option value="musical">Musical</option>
                                                <option value="cirque">Cirque</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-sm-4"></div>
                                        <div class="col-auto">
                                            <input type="checkbox" id="orchestreOu1WShow" class="form-control" name="orchestreOu1WShow">
                                        </div>
                                        <div class="col-auto">
                                            <label class="col-form-label" id="labelOrchOu1WShow"></label>
                                        </div>
                                    </div>
                                    <div> 
                                        <button type="submit" class="btn btn-primary">Ajouter</button>
                                    </div>
                                </form>
                                <hr>
                                <h2>Représentation</h2>
                                <form action="insertRepresentation" >
                                    <div class="form-group">
                                        <label class="col-form-label">Numéro du Spectacle</label>
                                        <%
                                            // Message d'erreur si numeroSpe inexistant
                                            if (erreurFK_REP_numeroSpe) {
                                        %>
                                        <input type="number" class="form-control is-invalid" name="numeroSpeRep" min="1" step="1" required>
                                        <div class="invalid-feedback"> 
                                            Ce numéro ne correspond à aucun spectacle, il faut d'abord ajouter le spectacle
                                        </div> 
                                        <%
                                        } else {
                                        %>
                                        <input type="number" class="form-control" name="numeroSpeRep" min="1" step="1" required>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-form-label">Horaire de la Représentation</label>
                                        <div class="form-row"> 
                                            <%
                                                // Message d'erreur si l'horaire est déjà prise
                                                if (erreurPK_REP) {
                                            %>
                                            <div class="col-md-6">
                                                <input type="date" class="form-control is-invalid" name="horaireRepDate" required>
                                            </div>
                                            <label class="col-form-label">à</label>
                                            <div class="col-md-5">
                                                <input type="time" class="form-control is-invalid" name="horaireRepHeure" required>
                                            </div>
                                            <div class="invalid-feedback d-block multiple-input-feedback"> 
                                                Cette horaire est déjà attribuée à un autre spectacle 
                                            </div> 
                                            <%
                                            } else {
                                            %>
                                            <div class="col-md-6">
                                                <input type="date" class="form-control" name="horaireRepDate" required>
                                            </div>
                                            <label class="col-form-label">à</label>
                                            <div class="col-md-5">
                                                <input type="time" class="form-control" name="horaireRepHeure" required>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                    </div>
                                    <div> 
                                        <button type="submit" class="btn btn-primary">Ajouter</button>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6">
                                <br>
                                <h2>Les Spectacles</h2>
                                <div style="height:85vh; overflow: auto;">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>N°</th> 
                                                <th>Nom du Spectacle</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Spectacle> spectacles = (List<Spectacle>) request.getAttribute("listeSpectacles");
                                                for (Spectacle s : spectacles) {
                                                    int numero = s.getNumero();
                                                    String nom = s.getNom();
                                            %>

                                            <tr>
                                                <td><%=numero%></td>
                                                <td><%=nom%></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table> 
                                </div>
                            </div>  
                        </div>
                    </div>                  
                    <div class="col-md-8">
                        <br>
                        <h2>La Programmation</h2>
                        <%
                            SimpleDateFormat navigateurJourFormatter = new SimpleDateFormat("yyyy-MM-dd");      // Pour fixer la valeur dans le formulaire
                            Date dateDebut = (Date) request.getAttribute("dateDebut");
                            Date dateFin = (Date) request.getAttribute("dateFin");

                            // Affichage des dates sélectionnés dans la nouvelle page
                            String dateDebutForm = navigateurJourFormatter.format(dateDebut);
                            String dateFinForm = navigateurJourFormatter.format(dateFin);
                        %>
                        <form action="progCtrlerAjoutProg">
                            <div class="form-row">
                                <div class=col-auto">
                                    <label class="col-form-label">Du</label>
                                </div>
                                <div class=col-auto">
                                    <input type="date" class="form-control" name="dateDebut" value=<%=dateDebutForm%> required>
                                </div>
                                <div class=col-auto">
                                    <label class="col-form-label">au</label>
                                </div>
                                <div class=col-auto">
                                    <input type="date" class="form-control" name="dateFin" value=<%=dateFinForm%> required>
                                </div>
                                <div class=col-auto">
                                    <button type="submit" class="btn btn-primary">Envoyer</button>
                                </div>
                            </div>
                        </form>
                        <br>
                        <div style="height:78.5vh; overflow: auto;">
                            <%
                                int nbSem = (int) request.getAttribute("nbSemaines");   // Récupère le nombre de semaines entre les deux dates du formulaire
                                List<List<List<Representation>>> representationsParSemaine = (List<List<List<Representation>>>) request.getAttribute("repParSemaine");

                                Date[] datesLundi = (Date[]) request.getAttribute("datesLundi");
                                Date[] datesDimanche = (Date[]) request.getAttribute("datesDimanche");

                                SimpleDateFormat jourFormatter = new SimpleDateFormat("dd/MM/yyyy");    // Formatte l'affichage des jours
                                SimpleDateFormat heureFormatter = new SimpleDateFormat("HH'h'mm");      // Formatte l'affichage des heures

                                for (int iSem = 0; iSem < nbSem; iSem++) {
                            %>
                            <h4>Semaine du <%=jourFormatter.format(datesLundi[iSem])%> au <%=jourFormatter.format(datesDimanche[iSem])%></h4>
                            <%
                                // Pour chaque semaine    
                                if (representationsParSemaine.get(iSem).isEmpty()) {
                                    // Si la semaine est vide
                            %>
                            <b>Planning vide</b><br><br>
                            <%
                            } else {
                                // Si la semaine n'est pas vide, il y a une List par jour
                                int nbMaxRepJour = representationsParSemaine.get(iSem).get(0).size();   // On calcule le maximum de Representation par jour pour la semaine
                                for (int iJour = 1; iJour < 7; iJour++) {
                                    int nbRepJour = representationsParSemaine.get(iSem).get(iJour).size();
                                    if (nbMaxRepJour < nbRepJour) {
                                        nbMaxRepJour = nbRepJour;
                                    }
                                }
                            %>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th class="colRepAdmin">Lundi</th>
                                        <th class="colRepAdmin">Mardi</th>
                                        <th class="colRepAdmin">Mercredi</th>                    
                                        <th class="colRepAdmin"> Jeudi</th>
                                        <th class="colRepAdmin">Vendredi</th>   
                                        <th class="colRepAdmin">Samedi</th>
                                        <th class="colRepAdmin">Dimanche</th>   
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (int iRep = 0; iRep < nbMaxRepJour; iRep++) {
                                    %>
                                    <tr>
                                        <%
                                            for (int iJour = 0; iJour < 7; iJour++) {
                                                if (representationsParSemaine.get(iSem).get(iJour).size() > iRep) {
                                                    Representation rep = representationsParSemaine.get(iSem).get(iJour).get(iRep);
                                                    Date horaireRep = rep.getHoraire();
                                                    String nomSpe = rep.getSpectacle().getNom();
                                        %>
                                        <td><b style="color:grey"><%=heureFormatter.format(horaireRep)%></b>
                                            <br><%=nomSpe%></td>
                                            <%
                                            } else {
                                            %>
                                        <td></td>
                                        <%
                                                }
                                            }
                                        %>
                                    </tr>
                                    <%
                                        }
                                    %>      
                                </tbody>
                            </table>   
                            <%
                                    }
                                }
                            %>
                        </div>
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
        <script src="js/ajoutProgrammation.js"></script>
    </body>
</html>
