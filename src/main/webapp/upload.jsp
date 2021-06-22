<html>
<head>
    <title>Music</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <nav id = "nav" class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">CBMS</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="index.jsp">Music<span class="sr-only">(current)</span></a>
                <a class="nav-item nav-link" href="upload.jsp">Upload</a>
            </div>
        </div>
    </nav>
    <link rel="stylesheet" href="style.css">

</head>
<body class="overflow-auto">


<div class="container mt-3">
  <h2>Custom File</h2>
    <form id="myForm" action="UploadServlet" method="post">
    <div class="custom-file mb-3">
        <input type="hidden" name="np4">
      <input type="file" class="custom-file-input" id="customFile" name="filename">
      <label class="custom-file-label" for="customFile">Choose file</label>

    </div>
    <div class="mt-3">
      <button type="submit" class="btn btn-primary">Submit</button>
    </div>
  </form>
</div>

<script>
    const username = localStorage.getItem("storageName");
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        const fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
        // document.getElementById("output").innerHTML = "np4";

        const form = document.getElementById('myForm');
        const xhr1 = new XMLHttpRequest();
        const data = new FormData(form);

        xhr.onload = function() {
            console.log(this.responseText);
        }

        xhr1.open("post", form.action);
        xhr1.send(data);
    });



</script>


</body>
</html>
<!--how to put js variable in html-->


