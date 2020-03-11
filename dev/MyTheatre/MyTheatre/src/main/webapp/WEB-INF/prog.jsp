<%-- 
    Document   : prog
    Created on : 6 mars 2020, 10:04:02
    Author     : marti236
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="fr.ima2g.m2cci.mytheatre.prog.model.Representation"%>
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
                    <a class="nav-link" href="#">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Programmation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contacts</a>
                </li>
            </ul>
        </nav>
        <div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-2">
                        <form action="progCtrler" >
                            <br>
                            <h2>Recherche</h2>
                            <h5>Dates</h5>
                            <%
                                SimpleDateFormat navigateurJourFormatter = new SimpleDateFormat("yyyy-MM-dd");      // Pour fixer la valeur dans le formulaire
                                Date dateDebut = (Date) request.getAttribute("dateDebut");
                                Date dateFin = (Date) request.getAttribute("dateFin");
                                
                                // Affichage des dates sélectionnés dans la nouvelle page
                                String dateDebutForm;
                                if (dateDebut == null){
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
                            <input type="date" name="dateDebut" value=<%=dateDebutForm%>> au                             
                            <input type="date" name="dateFin" value=<%=dateFinForm%>>
                            <br><br>
                            <h5>Categorie de spectateurs</h5>
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
                            &nbsp&nbsp <input type="radio" id="indiffC" name="cible" value="null" <%=checkIndif%>> Indifférent 
                            <br>
                            &nbsp&nbsp <input type="radio" id="unCinqP" name="cible" value="unCinqAns" <%=check1Cinq%>> 1-5 ans
                            <br>
                            &nbsp&nbsp <input type="radio" id="jeuneP" name="cible" value="jeunePublic" <%=checkJeune%>> Jeune Public
                            <br>
                            &nbsp&nbsp <input type="radio" id="toutP" name="cible" value="toutPublic" <%=checkToutP%>> Tout Public
                            <br>
                            &nbsp&nbsp <input type="radio" id="adulteP" name="cible" value="adulte" <%=checkAdult%>> Adultes
                            <br><br>
                            <h5>Type de spectacles</h5>
                            <%  // Conserve le bouton check pour le type de spectacle
                                String whichCheck= request.getParameter("type");
                                checkIndif = "";
                                String checkOpera = "";
                                String checkHumor = "";
                                String checkDrame = "";
                                String checkMusic = "";
                                String checkCirqu = "";
                                if (whichCheck.equals("opera"))
                                    checkOpera = " checked";
                                else if (whichCheck.equals("humoristique"))
                                    checkHumor = " checked";
                                else if (whichCheck.equals("drame"))
                                    checkDrame = " checked";
                                else if (whichCheck.equals("musical"))
                                    checkMusic = " checked";
                                else if (whichCheck.equals("cirque"))
                                    checkCirqu = " checked";
                                else
                                    checkIndif = " checked";
                            %>
                            <br>
                            &nbsp&nbsp <input type="checkbox" id="ope" name="type" value="opera" <%=checkOpera%>> Opéra
                            <br>
                            &nbsp&nbsp <input type="checkbox" id="hum" name="type" value="humoristique" <%=checkHumor%>> Humoristique
                            <br>
                            &nbsp&nbsp <input type="checkbox" id=dra name="type" value="drame" <%=checkDrame%>> Drame
                            <br>
                            &nbsp&nbsp <input type="checkbox" id="mus" name="type" value="musical" <%=checkMusic%>> Musical
                            <br>
                            &nbsp&nbsp <input type="checkbox" id="cir" name="type" value="cirque" <%=checkCirqu%>> Cirque
                            <br>
                            <br>
                            <input type="submit" value="Envoyer"> 
                        </form>
                    </div>
                    <div class="col-md-10">
                        <br>
                        <%
                            SimpleDateFormat jourFormatter = new SimpleDateFormat("dd/MM/yyyy");
                        %>
                        <h4>Programmation du <%=jourFormatter.format(dateDebut)%> au <%=jourFormatter.format(dateFin)%></h4>

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Horaire</td>
                                    <th>Nom</td>
                                    <th>Prix de base</td>                    
                                    <th>Type de pièce</td>
                                     <th>Public cible</td>   
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    SimpleDateFormat horaireFormatter = new SimpleDateFormat("dd/MM à HH");
                                    List<Representation> prog = (List<Representation>) request.getAttribute("progList");
                                    for (Representation r : prog) {
                                        Date date = r.getHoraire();
                                        String nom = r.getSpectacle().getNom();
                                        Double prixDeBase = r.getSpectacle().getPrixDeBase();
                                        String cible = r.getSpectacle().getCible();
                                        String type = r.getSpectacle().getType();
                                %>

                                <tr>
                                    <td><%=horaireFormatter.format(date)%>h</td>
                                    <td><%=nom%></td>
                                    <td><%=prixDeBase%> €</td>
                                    <td><%=type%></td>
                                    <td><%=cible%></td>
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
