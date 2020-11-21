/**
 * 
 */
"use strict";
window.addEventListener("load", pageFullyLoaded, false);

function pageFullyLoaded(e) {
        var xhr = new XMLHttpRequest();
        var xhr2 = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
                var data = JSON.parse(xhr.responseText);
                populateTable(data);
        }
        }

        xhr2.onreadystatechange = function() {
                if (xhr2.readyState == 4) {
                        var data2 = JSON.parse(xhr2.responseText);
                        populateTableB(data2);
                }
        }
        xhr.open('GET', 'http://localhost:8080/KrantzInc/actionObject/myRequests', false);
        xhr2.open('GET', 'http://localhost:8080/KrantzInc/actionObject/employeeRequestsClosed', false);
        xhr.send(null);
        xhr2.send(null);

        function populateTable(data){
                var columns = [];  
                for (let i = 0; i < data.length; i++) { 
                        for (let k in data[i]) { 
                        if (columns.indexOf(k) === -1) { 
                                
                                // Push all keys to the array 
                                columns.push(k); 
                        } 
                        } 
                } 
                for (var j = 0; j < columns.length; j++) { 
                        
                        // Create the table header th element 
                        var theader = document.createElement("th"); 
                        theader.innerHTML = columns[j]; 
                        document.getElementById('tableHead1').append(theader);
                        // Append columnName to the table row 
                
                } 
                let table = document.getElementById("tableBody1");

                for (let l = 0; l < data.length; l++) { 
                        let row = table.insertRow(-1)
                
                        for (let m = 0; m < columns.length; m++) { 
                            let cell = row.insertCell(-1); 
                            if(columns[m] == 'request_submitted'){
                                cell.innerHTML = setDate(data[l][columns[m]]);
                              }else if(columns[m]=='imgFile'){
                                let image = document.createElement('img');
                                image.style.maxWidth = "150px";
                                image.style.maxHeight ="150px";
                                image.src = 'data:image/png;base64,' + data[l][columns[m]];
                                if(image.src == 'data:image/png;base64,'){
                                        cell.innerHTML = "no Image submitted";
                                }
                                else{
                                        cell.appendChild(image);
                                }                                
                              }else{
                                cell.innerHTML = data[l][columns[m]]; 
                              }  
                        } 
                        document.getElementById("tableBody1").appendChild(row);
                } 
        }
        function populateTableB(data2){
                var columns = [];  
                for (let i = 0; i < data2.length; i++) { 
                        for (let k in data2[i]) { 
                        if (columns.indexOf(k) === -1) { 
                                
                                // Push all keys to the array 
                                columns.push(k); 
                        } 
                        } 
                } 
                for (let j = 0; j < columns.length; j++) { 
                        
                        // Create the table header th element 
                        var theader = document.createElement("th"); 
                        theader.innerHTML = columns[j]; 
                        document.getElementById('tableHead2').append(theader);
                        // Append columnName to the table row 
                
                } 
                let table = document.getElementById("tableBody2");

                for (let l = 0; l < data2.length; l++) { 
                        let row = table.insertRow(-1)
                        
                        // Create a new row 
                       
                        for (let m = 0; m < columns.length; m++) { 
                            let cell = row.insertCell(-1); 
                            if(columns[m] == 'request_submitted'){
                                cell.innerHTML = setDate(data2[l][columns[m]]);
                              }else if(columns[m]=='imgFile'){
                                let image = document.createElement('img');
                                image.style.maxWidth = "150px";
                                image.style.maxHeight ="150px";
                                image.src = 'data:image/png;base64,' + data2[l][columns[m]];
                                if(image.src == 'data:image/png;base64,'){
                                        cell.innerHTML = "no Image submitted";
                                }
                                else{
                                        cell.appendChild(image);
                                }                                
                              }else{
                                cell.innerHTML = data2[l][columns[m]]; 
                              }  
                        } 
                        document.getElementById("tableBody2").appendChild(row);
                } 
        }
        function setDate(date){
                let day = new Date(date)
                day = day.toString();
                day= day.split(' 0')[0];
                return day;
        }
}