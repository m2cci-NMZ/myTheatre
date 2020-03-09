<%-- 
    Document   : prog
    Created on : 6 mars 2020, 10:04:02
    Author     : marti236
--%>

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
                    <div class="col-lg-0"></div>
                    <form action="progCtrler" >
                        <br>
                        <h4>Recherche</h4>
                        <br>
                        <input type="date" name="dateDebut" value="2020-03-06" min="2020-03-06" max="2021-03-06">
                        <br>
                        <input type="date" name="dateFin" value="2020-03-06" min="2020-03-06" max="2021-03-06">
                        <br><br>
                        Categorie de spectateurs
                        <br><br>
                        Indifférent <input type="radio" id="indiffC" name="cible" value="indifférent" checked>
                        <br>
                        1-5 ans <input type="radio" id="unCinqP" name="cible" value="unCinqAns">
                        <br>
                        Jeune Public <input type="radio" id="jeuneP" name="cible" value="jeunePublic">
                        <br>
                        Tout Public <input type="radio" id="toutP" name="cible" value="toutPublic">
                        <br>
                        Adultes <input type="radio" id="adulteP" name="cible" value="adulte">
                        <br>
                        Tout <input type="radio" id="tout" name="cible">
                        <br><br>
                        Type de spectacles
                        <br><br>
                        Indifférent <input type="radio" id="indiffT" name="type" value="indifférent" checked >
                        <br>
                        Opéra <input type="radio" id="ope" name="type" value="opera">
                        <br>
                        Humoristique <input type="radio" id="hum" name="type" value="humoristique">
                        <br>
                        Drame <input type="radio" id=dra name="type" value="drame">
                        <br>
                        Musical <input type="radio" id="mus" name="type" value="musical">
                        <br>
                        Cirque <input type="radio" id="cir" name="type" value="cirque">
                        <br>
                        <input type="submit" value="Envoyer"> 
                    </form>
                    <div class="col-lg-12"></div>
                    <%
                        Date dateFin = (Date) request.getAttribute("dateFin");
                        Date dateDebut = (Date) request.getAttribute("dateDebut");
                    %>
                    <h1>Programmation du <%=dateDebut%></h1> au <%=dateFin%></h1>
                        
                    <table>
                        <tbody>
                            <%
                                List<Representation> prog = (List<Representation>) request.getAttribute("progList");
                                for (Representation r : prog) {
                                    Date date = r.getDate();
                                    String nom = r.getSpectacle().getNom();
                                    Double prixDeBase = r.getSpectacle().getPrixDeBase();
                                    String cible = r.getSpectacle().getCible();
                                    String type = r.getSpectacle().getType();
                            %>
                            <tr>
                                <td>Date</td>
                                <td>Nom</td>
                                <td>Prix de base</td>
                                <td>taux de réduction</td>
                                <td>Public cible</td>
                                <td>Type de pièce</td>
                            </tr>
                            <tr>
                                <td><%=date%></td>
                                <td><%=nom%></td>
                                <td><%=prixDeBase%></td>
                                <td><%=cible%></td>
                                <td><%=type%></td>
                            </tr>
                            <%
                                }
                            %>

                        </tbody>
                    </table>                  
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
