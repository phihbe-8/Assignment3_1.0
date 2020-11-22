
     /*var url = 'http://localhost:8080/HelloREST/rest/response'
     var Studentdata;
  	    $.getJSON(url, function(data) {
    	        	data = Studentdata;
    	                        });*/
     
function myFunction() {
    	  var table = document.getElementById("myTable");
    	  var row = table.insertRow(1);
    	  var cell1 = row.insertCell(0);
    	  var cell2 = row.insertCell(1);
    	  var cell3 = row.insertCell(2);
    	  var cell4 = row.insertCell(3);
    	  var cell5 = row.insertCell(4);
    	  var cell6 = row.insertCell(5);
    	  var cell7 = row.insertCell(6);
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

