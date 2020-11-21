/**
 * 
 */
"use strict";
var button = document.getElementById('cancelBtn');
button.addEventListener('click', returnPage);

function returnPage() {
  window.history.back();
}
