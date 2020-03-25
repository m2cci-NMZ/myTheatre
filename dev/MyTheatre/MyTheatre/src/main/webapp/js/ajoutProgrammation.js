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


function init() {
    setCheckBoxBool();

    // Associe une fonction de gestion à l'événement onclick du bouton 'dessiner'
    document.getElementById("typeSpeInput").addEventListener("change", setCheckBoxBool);
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
    
    // Permet d'afficher la sélection
    let rowsSpectacle = tableSpectacle.getElementsByClassName("ligneSpectacle");
    rowsSpectacle[indiceRow].classList.toggle("ligneSelected");
    
    // Rempli le formulaire avec les indices sélectionnées
    let indicesSelected = [];
    let stringIndices = "";
    for (let i = 0; i < rowsSpectacle.length; i++){
        if (rowsSpectacle[i].classList.contains("ligneSelected")){
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