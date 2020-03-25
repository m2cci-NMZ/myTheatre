/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Formulaire ajout des Spectacle
let checkBoxBool = document.getElementById("orchOu1WShowInput");
let labelCBBool = document.getElementById("labelOrchOu1WShowInput");
let typeSpeInput = document.getElementById("typeSpeInput");


function setCheckBoxBool() {
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
;


function init() {
    setCheckBoxBool();

    // Associe une fonction de gestion à l'événement onclick du bouton 'dessiner'
    document.getElementById("typeSpeInput").addEventListener("change", setCheckBoxBool);
}


function selectRow(indiceRow){
    document.getElementById("numLigneTabSpe").value = indiceRow;
    console.log(indiceRow);
    
    let table = document.getElementById("tableSpectacle");   
    let rowsSpectacle = table.getElementsByClassName("ligneSpectacle");
    rowsSpectacle[indiceRow].classList.toggle("caseLigneSelect");
    console.log(rowsSpectacle[indiceRow].classList);
    
    // A enlevé quand y a plus rien
    document.getElementById("supprSpeBouton").disabled = false;
}


$(document).ready(function () {

    $('#tableSpectacle').find('.ligneSpectacle').click(function () {
        //alert('You clicked row ' + ($(this).index() + 1));
        let indice = ($(this).index());
        selectRow(indice);
    });

});