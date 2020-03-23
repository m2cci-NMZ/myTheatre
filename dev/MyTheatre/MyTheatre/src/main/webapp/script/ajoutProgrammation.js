/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let check = document.getElementById("testCheck");

document.getElementById("typeSpeInput").onchange = function(){
    if (this.value === "opera"){
        check.checked = false;
    } else {
        check.checked = true;
    }
}
