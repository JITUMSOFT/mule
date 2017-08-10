<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Framework Editor</title>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
       
            $(document).on("click", "#somebutton", function() {
            	 var params = {
                 		comment: document.getElementById("comment").value,
                 		test: "test"
                 };
                $.post("FrameworkServlet", $.param(params), function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $("#result").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                });
               
            });
        </script>
 <script type="text/javascript">
 function readBox(value){
	 if (value=="blank"){
		 document.getElementById("comment").value='';
		 document.getElementById("result").value='';
	 }
	 var filePath= ("http://localhost:8081/FrameworkEditor/") + value + (".xml");
	 $( "#comment" ).load(filePath);
	 document.getElementById("result").value='';
 
 }
  </script>
  
  <script>

function copySelectionText(){
    var copysuccess // var to check whether execCommand successfully executed
    try{
        copysuccess = document.execCommand("copy") // run command to copy selected text to clipboard
    } catch(e){
        copysuccess = false
    }
    return copysuccess
}
</script>
</head>
<body>

<div class="container-fluid" style="border:1px solid #cecece;">
  <h1><small>Framework Editor</small></h1>
  <div class="row">
    <div class="col-sm-4">
  <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo">ReadMe</button>
  <div id="demo" class="collapse">
   This editor would compile xml framework based config files in standalone manner. 
  </div></div>
  <div class="col-sm-4">
  <button type="button" class="btn btn-info" onclick="copySelectionText();">CopyContent</button>
  </div>
  <div class="col-xs-4">
    	<div class="form-group">
            <label>UploadZip</label>
            <input type="file" id="icondemo">
        </div>
    </div>
  
  </div>

<br>

 
  
  <p></p>

  <label>ChooseFramework</label>
  <select id="selectMe" onchange="readBox(this.value)">
   <option value="blank"></option>
    <option value="mule">mule</option>
    <option value="spring">spring</option>
</select>
<p><p>

<br><br>
    <label for="comment">Untitled File:</label>
  <textarea class="form-control" rows="25" style="font-weight: bold" id="comment" name="comment"></textarea>
  <br><br>
    <button  class="btn btn-info" id="somebutton">Validate</button>
    <br>
    <br>
   <label for="result">Result:</label>
    
    <textarea class="form-control" id="result" style="overflow: visible" name="result"></textarea>

 
    
</div>

</body>
</html>