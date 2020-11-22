
     /*var url = 'http://localhost:8080/HelloREST/rest/response'
     var Studentdata;
  	    $.getJSON(url, function(data) {
    	        	data = Studentdata;
    	                        });
    	                        
    	                        <select class="form-control"><option selected>-</option>
							<option value="U">U</option>
							<option value ="G">G</option>
							<option value="VG">VG</option>
    	                        *
    	                        *
    	                        *
    	                        */
     
function myFunction() {

    	  var cell5 = getElementById("showBetyg");

    	  //Skapar checkbox för varje rad
    	  var checkbox = document.createElement('input');
    	  checkbox.type = "checkbox";
    	  cell1.appendChild(checkbox);
    	  //Sätter in namn för varje rad
    	  //var text = `${Studentdata[0].fname}`;
    	  //var printNamn = document.createTextNode(text);
    	  //cell2.appendChild(printNamn);
    	  //Skapar datum input för varje rad
    	  var date = document.createElement('input');
    	  date.type = "date";
    	  cell4.appendChild(date);
    	  //Skapar betyg val för varje rad
    	  var betyg = document.createElement('input');
    	  var options = ["IG","G","VG"];
    	  betyg.type = "select";
    	  cell5.appendChild(betyg);
    	}

