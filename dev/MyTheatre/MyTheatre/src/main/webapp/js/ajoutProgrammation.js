/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Formulaire ajout des Spectacle
let checkBoxBool = document.getElementById("orchOu1WShowInput");
let labelCBBool = document.getElementById("labelOrchOu1WShowInput");
let typeSpeInput = document.getElementById("typeSpeInput");

let indice = 0;


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


$(document).ready(function () {

    $('#tableTest').find('tr').click(function () {
        //alert('You clicked row ' + ($(this).index() + 1));
        document.getElementById("supprSpeBouton").disabled = false;
        indice = ($(this).index() + 1);
        //List<Spectacle> spectacles = (List<Spectacle>) request.getAttribute("listeSpectacles");
        //Spectacle s = spectacles.get(indice);
        //let num = s.getNumero();
        document.getElementById("numLigneTabSpe").value = indice;
    });

});