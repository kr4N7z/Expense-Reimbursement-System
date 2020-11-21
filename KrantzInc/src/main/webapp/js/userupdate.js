/**
 * 
 */
"use strict";
window.addEventListener("load", pageFullyLoaded, false);

function pageFullyLoaded(e) {
    let xhr = new XMLHttpRequest();
        
    xhr.onreadystatechange = function() {
    if (xhr.readyState == 4) {
            let data = JSON.parse(xhr.responseText);
            populateForm(data);
    }
    }

    xhr.open('GET', 'http://localhost:8080/KrantzInc/actionObject/getInfo', false);
    xhr.send(null);

    function populateForm(data){
        document.getElementById('email').value = data.email;
        document.getElementById('email').readOnly = true;
        document.getElementById('password').value = data.password;
        document.getElementById('password').readOnly = true;
        document.getElementById('firstName').value = data.firstName;
        document.getElementById('firstName').readOnly = true;
        document.getElementById('lastName').value = data.lastName;
        document.getElementById('lastName').readOnly = true;
        document.getElementById('submitBtn').style.display = "none";
    }
    let edit = document.getElementById('editBtn');
    edit.addEventListener('click', editForm)
    function editForm(){
        document.getElementById('submitBtn').style.display = "";
        document.getElementById('editBtn').style.display= "none";
        document.getElementById('email').readOnly = false;
        document.getElementById('firstName').readOnly = false;
        document.getElementById('lastName').readOnly = false;

    }
}