<%-- 
    Document   : prog
    Created on : 6 mars 2020, 10:04:02
    Author     : marti236
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Date dateDebut =  (Date) request.getAttribute("dateDebut");
            Date dateFin =  (Date) request.getAttribute("dateFin");
        %>
        <h1>Programmation du <%=dateDebut%> au <%=dateFin%></h1>

        <table>
            <tbody>
                <%
                    List<Representation> prog = (List<Representation>) request.getAttribute("progList");
                    for (Representation r : prog) {
                        Date date = r.getDate();
                        Double tauxReduc = r.getTauxReduc();
                        String nom = r.getNom();
                        int prixDeBase = r.getPrixDeBase();
                        String cible = r.getCible();
                        String type = r.getType();
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
                    <td><%=tauxReduc%></td>
                    <td><%=cible%></td>
                    <td><%=type%></td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>
    </body>
</html>
