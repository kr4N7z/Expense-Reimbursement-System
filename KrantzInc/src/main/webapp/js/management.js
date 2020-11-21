/**
 * 
 */
"use strict";
window.addEventListener("load", pageFullyLoaded, false);

function pageFullyLoaded(e) {
        let xhr = new XMLHttpRequest();
        let xhr3 = new XMLHttpRequest(); 
        xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
                let data = JSON.parse(xhr.responseText);
                populateTable(data);
        }
        }

        let filter = document.getElementById('myFilter');
        filter.addEventListener('keyup', filterTable);
        let filter2 = document.getElementById('myFilter2');
        filter2.addEventListener('keyup', filterTable2);
        xhr.open('GET', 'http://localhost:8080/KrantzInc/actionObject/myRequestsToApprove', false);
        xhr.send(null);


        xhr3.onreadystatechange = function() {
                if (xhr3.readyState == 4) {
                        let data3 = JSON.parse(xhr3.responseText);
                        populateTableC(data3);
                }
        }
        xhr3.open('GET', 'http://localhost:8080/KrantzInc/actionObject/myRequests', false);
        xhr3.send(null);


        function populateTable(data){
                let columns = [];  
                for (let i = 0; i < data.length; i++) { 
                        var a = data[i];
                        for (var k in data[i]) { 
                        if (columns.indexOf(k) === -1) { 
                                
                                // Push all keys to the array 
                                columns.push(k); 
                        } 
                        } 
                } 
                for (let j = 0; j < columns.length; j++) { 
                        
                        // Create the table header th element 
                        let theader = document.createElement("th"); 
                        theader.innerHTML = columns[j]; 
                        document.getElementById('tableHead1').append(theader);
                        // Append columnName to the table row 
                
                } 
                let table = document.getElementById("tableBody1");

                for (let l = 0; l < data.length; l++) { 
                        let row = table.insertRow(-1)
                        
                        // Create a new row 
                       
                        for (let m = 0; m < columns.length; m++) { 
                            let cell = row.insertCell(-1); 
                              if(columns[m]=='reimbursement_id'){
                                      populateSelect(data[l][columns[m]]);
                              }
                              if(columns[m] == 'request_submitted'){
                                cell.innerHTML = setDate(data[l][columns[m]]);
                              }else if(columns[m]=='imgFile'){
                                var image = document.createElement('img');
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
                callNext();
        }
        function populateSelect(optionAdd){
                let newOption = document.getElementById("idSelector");
                var opt = document.createElement('option');

                // create text node to add to option element (opt)
                opt.appendChild( document.createTextNode(optionAdd) );

                // set value property of opt
                opt.value = optionAdd; 

                newOption.appendChild(opt); 
        }    
        function filterTable(){
                let input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("myFilter");
                filter = input.value.toUpperCase();
                table = document.getElementById("allRequests");
                tr = table.getElementsByTagName("tr");
        
                // Loop through all table rows, and hide those who don't match the search query
                for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1];
                if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                } else {
                        tr[i].style.display = "none";
                }
                }
                }
        
        }
        function filterTable2(){
                let input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("myFilter2");
                filter = input.value.toUpperCase();
                table = document.getElementById("closedRequests");
                tr = table.getElementsByTagName("tr");
        
                // Loop through all table rows, and hide those who don't match the search query
                for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[5];
                if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                } else {
                        tr[i].style.display = "none";
                }
                }
                }
        
        }
        function populateTableC(data3){
                let columns = [];  
                for (let i = 0; i < data3.length; i++) { 
                        for (let k in data3[i]) { 
                        if (columns.indexOf(k) === -1) { 
                                
                                // Push all keys to the array 
                                columns.push(k); 
                        } 
                        } 
                } 
                for (let j = 0; j < columns.length; j++) { 
                        
                        // Create the table header th element 
                        let theader = document.createElement("th"); 
                        theader.innerHTML = columns[j]; 
                        document.getElementById('tableHead3').append(theader);
                        // Append columnName to the table row 
                
                } 
                let table = document.getElementById("tableBody3");

                for (let l = 0; l < data3.length; l++) { 
                        let row = table.insertRow(-1)
                
                        for (let m = 0; m < columns.length; m++) { 
                            let cell = row.insertCell(-1); 
                            if(columns[m] == 'request_submitted'){
                                cell.innerHTML = setDate(data3[l][columns[m]]);
                              }else if(columns[m]=='imgFile'){
                                let image = document.createElement('img');
                                image.style.maxWidth = "150px";
                                image.style.maxHeight ="150px";
                                image.src = 'data:image/png;base64,' + data3[l][columns[m]];
                                if(image.src == 'data:image/png;base64,'){
                                        cell.innerHTML = "no Image submitted";
                                }
                                else{
                                        cell.appendChild(image);
                                }
                                
                              }else{
                                cell.innerHTML = data3[l][columns[m]]; 
                              }  
                        } 
                        document.getElementById("tableBody3").appendChild(row);
                } 
        }
}

function setDate(date){
        let day = new Date(date)
        day = day.toString();
        day= day.split(' 0')[0];
        return day;
}

function callNext(){
        let xhr2 = new XMLHttpRequest();


        xhr2.onreadystatechange = function() {
                if (xhr2.readyState == 4) {
                        let data2 = JSON.parse(xhr2.responseText);
                        populateTableB(data2);
                }
        }
        xhr2.open('GET', 'http://localhost:8080/KrantzInc/actionObject/myRequestsClosed', false);
        xhr2.send(null);
        function populateTableB(data2){
                let columns = [];  
                for (let i = 0; i < data2.length; i++) { 
                        for (var k in data2[i]) { 
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
                                var image = document.createElement('img');
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
}