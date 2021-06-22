
<!DOCTYPE html>
<html lang="en">
<head>
    <!--<link rel="shortcut icon" href="#">-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
    <title>Sign In</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <style>
        body {
            color: #fff;
            background: #63738a;
            font-family: 'Roboto', sans-serif;
        }
        .form-control {
            height: 40px;
            box-shadow: none;
            color: #969fa4;
        }
        .form-control:focus {
            border-color: #5cb85c;
        }
        .form-control, .btn {
            border-radius: 3px;
        }
        .signup-form {
            width: 450px;
            margin: 0 auto;
            padding: 30px 0;
            font-size: 15px;
        }
        .signup-form h2 {
            color: #636363;
            margin: 0 0 15px;
            position: relative;
            text-align: center;
        }
        .signup-form h2:before, .signup-form h2:after {
            content: "";
            height: 2px;
            width: 30%;
            background: #d4d4d4;
            position: absolute;
            top: 50%;
            z-index: 2;
        }
        .signup-form h2:before {
            left: 0;
        }
        .signup-form h2:after {
            right: 0;
        }
        .signup-form .hint-text {
            color: #999;
            margin-bottom: 30px;
            text-align: center;
        }
        .signup-form form {
            color: #999;
            border-radius: 3px;
            margin-bottom: 15px;
            background: #f2f3f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .signup-form .form-group {
            margin-bottom: 20px;
        }
        .signup-form input[type="checkbox"] {
            margin-top: 3px;
        }
        .signup-form .btn {
            font-size: 16px;
            font-weight: bold;
            min-width: 140px;
            outline: none !important;
        }
        .signup-form .row div:first-child {
            padding-right: 10px;
        }
        .signup-form .row div:last-child {
            padding-left: 10px;
        }
        .signup-form a {
            color: #fff;
            text-decoration: underline;
        }
        .signup-form a:hover {
            text-decoration: none;
        }
        .signup-form form a {
            color: #5cb85c;
            text-decoration: none;
        }
        .signup-form form a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>


<div class="signup-form">
    <form id='foobar'>
        <h2>Sign In</h2>
        <p class="hint-text">Sign in to start listening.</p>
        <div class="form-group">
            <input type="text" class="form-control" name="username"  placeholder="Username" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
        </div>
        <div class="form-group">
            <button type = "submit" class="btn btn-success btn-lg btn-block">Sign In</button>
        </div>
    </form>
    <div class="text-center">Don't have an account yet? <a href="index.html">Register</a></div>
</div>
<pre id='output'></pre>
<script>


    //how to make get request js
    document.getElementById('foobar').addEventListener('submit', (e) => {
        const url = "http://localhost:8080/studentsapp-1.0-SNAPSHOT/LogInServlet";
        e.preventDefault();
        const formData = new FormData(e.target);
        const data1 = Array.from(formData.entries()).reduce((memo, pair) => ({...memo,[pair[0]]: pair[1],}), {});
        let json = JSON.stringify(data1);


        const xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        // xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
        xhr.setRequestHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");
//        xhr.setRequestHeader("Access-Control-Allow-Headers", "Content-Type");
//        xhr.setRequestHeader("Access-Control-Request-Headers", "X-Requested-With, accept, content-type");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log(xhr.responseText);
                const myArr = JSON.parse(xhr.responseText);

//                document.getElementById('output').innerHTML = xhr.responseText;
                if (myArr.response === "ok") {
                    localStorage.setItem("storageName", data1.username);
                    location.href = "music.jsp";
                }
            }
        }
        xhr.send(json);
    });
</script>


</body>
</html>