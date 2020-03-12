<%-- 
    Document   : prog
    Created on : 6 mars 2020, 10:04:02
    Author     : marti236
--%>

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
                    <a class="nav-link" href="../index.html">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Programmation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../administration.html">Administration</a>
                </li>
            </ul>
        </nav>
        <div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-6">
                                <br>
                                <h2>Spectacle</h2>
                                <form action="ajoutSpectacle">
                                    &nbsp&nbsp Numéro <input type="number" name="numeroSpe" min="1" step="1">
                                    <br><br>
                                    &nbsp&nbsp Nom <input type="text" name="nomSpe">
                                    <br><br>
                                    &nbsp&nbsp Prix <input type="number" name="prix" step="0.01" min="0.01">
                                    <br><br>
                                    &nbsp&nbsp Cible <select name="cible" class="custom-select-sm">
                                        <option value="unCinqAns">1-5 Ans</option>
                                        <option value="jeunePublic">Jeune Public</option>
                                        <option value="toutPublic">Tout Public</option>
                                        <option value="adulte">Adultes</option>
                                    </select>
                                    <br><br>
                                    &nbsp&nbsp Type <select name="typeSpe" class="custom-select-sm">
                                        <option value="opera">Opéra</option>
                                        <option value="humoristique">Humoristique</option>
                                        <option value="drame">Drame</option>
                                        <option value="musical">Musical</option>
                                        <option value="cirque">Cirque</option>
                                    </select>
                                    <br><br>
                                    <input type="submit" value="Ajouter"> 
                                </form>
                                <br>
                                <hr>
                                <h2>Représentation</h2>
                                <br>
                                <form action="???" >
                                    &nbsp&nbsp Numéro du spectacle <input type="number" name="numero" min="1" step="1">
                                    <br><br>
                                    &nbsp&nbsp Horaire <input type="date" name="horaire_date"> à <input type="time" name="horaire_heure">
                                    <br><br>
                                    <input type="submit" value="Ajouter"> 
                                </form>
                            </div>
                            <div class="col-md-6">
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
                                            %>

                                            <tr>
                                                <td><%=nom%></td>
                                                <td><%=numero%></td>
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
                    <div class="col-md-7">
                        <br>
                        <h2>Programmation</h2>
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
