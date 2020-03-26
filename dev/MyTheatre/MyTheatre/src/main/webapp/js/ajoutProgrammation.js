/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Formulaire ajout des Spectacle
let checkBoxBool = document.getElementById("orchOu1WShowInput");
let labelCBBool = document.getElementById("labelOrchOu1WShowInput");
let typeSpeInput = document.getElementById("typeSpeInput");

// Table de spectacle
let tableSpectacle = document.getElementById("tableSpectacle");

// Toutes les cases des représentations
let casesRep = document.getElementsByClassName("caseRep");


function init() {
    setCheckBoxBool();

    // Associe une fonction de gestion à l'événement change du typeSpe
    document.getElementById("typeSpeInput").addEventListener("change", setCheckBoxBool);
    
    // On vide les Spectacles à supprimer et on cache le bouton si quelqu'un reload
    document.getElementById("supprSpeBouton").disabled = true;
    document.getElementById("lignesSpeSelected").value = "";
}


function setCheckBoxBool() {
    // Permet de controler le checkbox sous le type de Spectacle
    // En fonction du type, il s'affiche ou pas et le champ change
    if (typeSpeInput.value === "opera") {
        checkBoxBool.style.visibility = 'visible';
        labelCBBool.innerHTML = "Orchestre";
    } else if (typeSpeInput.value === "humoristique") {
        checkBoxBool.style.visibility = 'visible';
        labelCBBool.innerHTML = "Stand-up";
    } else {
        checkBoxBool.style.visibility = 'hidden';
        labelCBBool.innerHTML = "";
    }
}


function selectRow(indiceRow){
    // Appelé quand on clique sur des cases du tableau de spectacle
    // Affiche le fait qu'on a cliqué, prépare le formulaire et active ou pas le bouton supprimé
    
    // Permet d'afficher la sélection : Spectacle
    let rowsSpectacle = tableSpectacle.getElementsByClassName("ligneSpectacle");    // Toutes les lignes de spectacles
    rowsSpectacle[indiceRow].classList.toggle("ligneOuCaseSelected");   // On toggle la class pour la ligne cliquée
    let nomSpeSelected = rowsSpectacle[indiceRow].cells[1].textContent;     // Recupère le nom du Spectacle
    
    // Affiche les Representations correspondantes en rouge
    for (let i = 0; i < casesRep.length ; i++){     // Pour toutes les Rep 
        let nomSpeRep = casesRep[i].querySelector("#nomSpeRep").textContent;    // On récupère le nom du Spe
        if(nomSpeRep === nomSpeSelected){   // Si c'est celui sur lequel on a cliqué on le passe en rouge
            casesRep[i].classList.toggle("caseRepsOfSpeSelected");
        }
    }
    
    // Rempli le formulaire avec les indices sélectionnées
    let indicesSelected = [];
    let stringIndices = "";
    for (let i = 0; i < rowsSpectacle.length; i++){
        if (rowsSpectacle[i].classList.contains("ligneOuCaseSelected")){
            indicesSelected.push(i);
            stringIndices += i + " ";
        }
    }
    // On met dans l'input
    document.getElementById("lignesSpeSelected").value = stringIndices;
    
    // Regarde si il reste des lignes sélectionnées pour savoir si on active le bouton
    if(indicesSelected.length > 0){
        document.getElementById("supprSpeBouton").disabled = false;
    } else {
        document.getElementById("supprSpeBouton").disabled = true;
    }
}


// Permet d'avoir une action quand on clique sur le tableau
$(document).ready(function () {
    // Pour les lignes du tableau des Spectacles
    $('#tableSpectacle').find('.ligneSpectacle').click(function () {
        let indice = ($(this).index());     // Indice cliqué
        selectRow(indice);
    });
});