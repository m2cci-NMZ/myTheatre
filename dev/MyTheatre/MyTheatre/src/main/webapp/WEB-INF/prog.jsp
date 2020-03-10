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
                            <h4>Recherche</h4>
                            <br>
                            <input type="date" name="dateDebut" value="2020-03-01">
                            <br>
                            <input type="date" name="dateFin" value="2020-03-31">
                            <br><br>
                            Categorie de spectateurs
                            <br>
                            <%  // Conserve le bouton check pour la cible
                                String whichRadio = request.getParameter("cible");
                                System.out.println(whichRadio);
                                String checkIndif = "";
                                String check1Cinq = "";
                                String checkJeune = "";
                                String checkToutP = "";
                                String checkAdult = "";
                                if (whichRadio == null) checkIndif = " checked";
                                else if (whichRadio.equals("unCinqAns")) check1Cinq = " checked";
                                else if (whichRadio.equals("jeunePublic")) checkJeune = " checked";
                                else if (whichRadio.equals("toutPublic")) checkToutP = " checked";
                                else if (whichRadio.equals("adulte")) checkAdult = " checked";
                                else checkIndif = " checked";
                                %>
                            Indifférent <input type="radio" id="indiffC" name="cible" value="null" <%=checkIndif%>>
                            <br>
                            1-5 ans <input type="radio" id="unCinqP" name="cible" value="unCinqAns" <%=check1Cinq%>>
                            <br>
                            Jeune Public <input type="radio" id="jeuneP" name="cible" value="jeunePublic" <%=checkJeune%>>
                            <br>
                            Tout Public <input type="radio" id="toutP" name="cible" value="toutPublic" <%=checkToutP%>>
                            <br>
                            Adultes <input type="radio" id="adulteP" name="cible" value="adulte" <%=checkAdult%>>
                            <br><br>
                            Type de spectacles
                            <br>
                            <%  // Conserve le bouton check pour le type de spectacle
                                whichRadio = request.getParameter("type");
                                System.out.println(whichRadio);
                                checkIndif = "";
                                String checkOpera = "";
                                String checkHumor = "";
                                String checkDrame = "";
                                String checkMusic = "";
                                String checkCirqu = "";
                                if (whichRadio == null) checkIndif = " checked";
                                else if (whichRadio.equals("opera")) checkOpera = " checked";
                                else if (whichRadio.equals("humoristique")) checkHumor = " checked";
                                else if (whichRadio.equals("drame")) checkDrame = " checked";
                                else if (whichRadio.equals("musical")) checkMusic = " checked";
                                else if (whichRadio.equals("cirque")) checkCirqu = " checked";
                                else checkIndif = " checked";
                                %>
                            Indifférent <input type="radio" id="indiffT" name="type" value="null" <%=checkIndif%> >
                            <br>
                            Opéra <input type="radio" id="ope" name="type" value="opera" <%=checkOpera%>>
                            <br>
                            Humoristique <input type="radio" id="hum" name="type" value="humoristique" <%=checkHumor%>>
                            <br>
                            Drame <input type="radio" id=dra name="type" value="drame" <%=checkDrame%>>
                            <br>
                            Musical <input type="radio" id="mus" name="type" value="musical" <%=checkMusic%>>
                            <br>
                            Cirque <input type="radio" id="cir" name="type" value="cirque" <%=checkCirqu%>>
                            <br>
                            <br>
                            <input type="submit" value="Envoyer"> 
                        </form>
                    </div>
                    <div class="col-md-10">
                        <%
                            Date dateFin = (Date) request.getAttribute("dateFin");
                            Date dateDebut = (Date) request.getAttribute("dateDebut");
                            SimpleDateFormat jourFormatter = new SimpleDateFormat("dd/MM/yyyy");
                        %>
                        <br>
                        <h4>Programmation du <%=jourFormatter.format(dateDebut)%> au <%=jourFormatter.format(dateFin)%></h4>

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Horaire</td>
                                    <th>Nom</td>
                                    <th>Prix de base</td>
                                    <th>Public cible</td>
                                    <th>Type de pièce</td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    SimpleDateFormat horaireFormatter = new SimpleDateFormat("dd/MM à HH");
                                    List<Representation> prog = (List<Representation>) request.getAttribute("progList");
                                    for (Representation r : prog) {
                                        Date date = r.getDate();
                                        String nom = r.getSpectacle().getNom();
                                        Double prixDeBase = r.getSpectacle().getPrixDeBase();
                                        String cible = r.getSpectacle().getCible();
                                        String type = r.getSpectacle().getType();
                                %>
                                
                                <tr>
                                    <td><%=horaireFormatter.format(date)%>h</td>
                                    <td><%=nom%></td>
                                    <td><%=prixDeBase%> €</td>
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
