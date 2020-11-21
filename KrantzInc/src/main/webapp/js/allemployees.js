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
                populateTable(data);
        }
        }

        let filter = document.getElementById('myFilter');
        filter.addEventListener('keyup', filterTable);
        xhr.open('GET', 'http://localhost:8080/KrantzInc/actionObject/allEmployees', false);
        xhr.send(null);
       
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
                              if(columns[m] == 'createdDate' || columns[m] == 'lastLogin'){
                                cell.innerHTML = setDate(data[l][columns[m]]);
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
                var input, filter, table, tr, td, i, txtValue;
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
}

function setDate(date){
        let day = new Date(date)
        day = day.toString();
        day = day.split(' G')[0];
        return day;
}
