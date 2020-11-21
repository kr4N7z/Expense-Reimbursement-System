/**
 * 
 */
"use strict";
window.addEventListener("load", pageFullyLoaded, false);
var reimbersed;
var cost;
var type;


function pageFullyLoaded(e) {
    
   

    cost = document.getElementById('cost');
    type = document.getElementById('typeSelect');
    cost.addEventListener('input',function(evt){
        reimbersed = document.getElementById('reimbursed');
        document.getElementById('reimbursed').readOnly = false;
        if(type.value == 'University Course'){
            reimbersed.value = this.value*0.8;
        }
        if(type.value == 'Seminar'){
            reimbersed.value= this.value*0.6;
    
        }
        if(type.value == 'Certification Preparation Class'){
            reimbersed.value= this.value*0.75;
    
        }
        if(type.value == 'Certification'){
            reimbersed.value= this.value*1;
    
        }
        if(type.value == 'Technical Training'){
            reimbersed.value= this.value*0.9;
    
        }
        if(type.value == 'Other'){
            reimbersed.value = this.value *0.3;
        }
       
        let val = parseFloat(reimbersed.value).toFixed(2);
        document.getElementById('reimbursed').value = val;
        document.getElementById('reimbursed').readOnly = true;    
    });
    
    type.addEventListener('input',function(evt){
        document.getElementById('reimbursed').readOnly = false;
        reimbersed = document.getElementById('reimbursed');
        cost = document.getElementById('cost');
        let reimbersedPct = document.getElementById('reimbursedPct');

        if(this.value == 'University Course'){
            reimbersedPct.value = 80
            reimbersed.value = cost.value*0.8;
        }
        if(this.value == 'Seminar'){
            reimbersedPct.value = 60;
            reimbersed.value = cost.value*  0.6;
    
        }
        if(this.value == 'Certification Preparation Class'){
            reimbersedPct.value = 75;
            reimbersed.value = cost.value* .75;
    
        }
        if(this.value == 'Certification'){
            reimbersedPct.value = 100;
            reimbersed.value = cost.value* 1;
    
        }
        if(this.value == 'Technical Training'){
            reimbersedPct.value = 90;
            reimbersed.value = cost.value* .90;
    
        }
        if(this.value == 'Other'){
            reimbersedPct.value=30;
            reimbersed.value  = cost.value * .30;
        }
        document.getElementById('reimbursedPct').value = reimbersedPct.value;
        let val = parseFloat(reimbersed.value).toFixed(2);
        document.getElementById('reimbursed').value = val;
        document.getElementById('reimbursed').readOnly = true;

        });
    
}


