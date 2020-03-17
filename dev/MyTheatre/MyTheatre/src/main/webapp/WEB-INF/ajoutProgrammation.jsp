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
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-5">
                                <br>
                                <h2>Spectacle</h2>
                                <form action="ajoutSpectacle">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Numéro</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" name="numeroSpe" min="1" step="1">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Nom</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="nomSpe">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Prix</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" name="prixSpe" step="0.01" min="0.01">
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
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Type</label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="typeSpe">
                                                <option value="opera">Opéra</option>
                                                <option value="humoristique">Humoristique</option>
                                                <option value="drame">Drame</option>
                                                <option value="musical">Musical</option>
                                                <option value="cirque">Cirque</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div> 
                                        <button type="submit" class="btn btn-primary">Ajouter</button>
                                    </div>
                                </form>
                                <hr>
                                <h2>Représentation</h2>
                                <form action="???" >
                                    <div class="form-group">
                                        <label class="col-form-label">Numéro du Spectacle</label>
                                        <input type="number" class="form-control" name="numeroSpeRep" min="1" step="1">
                                    </div>
                                    <div class="form-group">
                                        <label class="col-form-label">Horaire de la Représentation</label>
                                        <div class="form-row"> 
                                            <div class="col-md-6">
                                                <input type="date" class="form-control" name="horaireRepDate">
                                            </div>
                                            <label class="col-form-label">à</label>
                                            <div class="col-md-5">
                                                <input type="time" class="form-control" name="horaireRepHeure">
                                            </div>
                                        </div>
                                    </div>
                                    <div> 
                                        <button type="submit" class="btn btn-primary">Ajouter</button>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-7">
                                <br>
                                <h2>Spectacles à l'affiche</h2>
                                <div style="height:85vh; overflow: auto;">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Nom</th>
                                                <th>Numéro</th> 
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Spectacle> spectacles = (List<Spectacle>) request.getAttribute("listeSpectacles");
                                                for (Spectacle s : spectacles) {
                                                    int numero = s.getNumero();
                                                    String nom = s.getNom();
                                                    for (int i = 0; i < 2; i++) {
                                            %>

                                            <tr>
                                                <td><%=nom%></td>
                                                <td><%=numero%></td>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table> 
                                </div>
                            </div>  
                        </div>
                    </div>                  
                    <div class="col-md-7">
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
                        <form>
                            <div class="form-row">
                                <div class=col-auto">
                                    <label class="col-form-label">Du</label>
                                </div>
                                <div class=col-auto">
                                    <input type="date" class="form-control" name="dateDebut" value=<%=dateDebutForm%>>
                                </div>
                                <div class=col-auto">
                                    <label class="col-form-label">au</label>
                                </div>
                                <div class=col-auto">
                                    <input type="date" class="form-control" name="dateFin" value=<%=dateFinForm%>>
                                </div>
                            </div>
                        </form>
                        <br>
                        <%
                            int nbSem = (int) request.getAttribute("nbSemaines");   // Récupère le nombre de semaines entre les deux dates du formulaire
                            //List<Representation> representations = (List<Representation>) request.getAttribute("listeRepresentations");
                            SimpleDateFormat jourFormatter = new SimpleDateFormat("dd/MM/yyyy");    // Formatte l'affichage des jours
                            SimpleDateFormat heureFormatter = new SimpleDateFormat("HH");           // Formatte l'affichage des heures

                            Date[] datesDebutsSemaines = (Date[]) request.getAttribute("datesDebutsSemaines");
                            Date[] datesFinsSemaines = (Date[]) request.getAttribute("datesFinsSemaines");

                            /*// Dates de début et de fin de chaque semaines
                            Date dateDebutSem = dateDebut;
                            Date dateFinSem;
                            Calendar c = Calendar.getInstance();

                            // On retrouve la date du lundi correspondant à la première semaine
                            c.setTime(dateDebutSem);
                            int numeroJourDateDebSem = c.get(Calendar.DAY_OF_WEEK);        // Commence au Dimanche donc on décalle de 1 jour
                            numeroJourDateDebSem -= 1;
                            if (numeroJourDateDebSem == 0) {
                                numeroJourDateDebSem = 7;
                            }
                            c.add(Calendar.DATE, -(numeroJourDateDebSem - 1));      // Ici on met dans le calendrier la date du lundi
                            dateDebutSem = c.getTime();     // On convertit en date*/
                            for (int i = 0; i < nbSem; i++) {       // Pour chaque semaine


                        %>
                        <h4>Semaine du <%=jourFormatter.format(datesDebutsSemaines[i])%> au <%=jourFormatter.format(datesFinsSemaines[i])%></h4>
                        <%
                            }
                        %>











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
