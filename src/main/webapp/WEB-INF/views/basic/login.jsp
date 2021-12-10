<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <!--    local css script-->
    <link href="/css/basic/login.css" rel="stylesheet">
    <title></title>
    <link rel="icon" href="/images/launch_image.png"/>
    <style>
        .background {
            background-image: url("/images/vote.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center;
        }

        .new {
            width: 30vw;
            margin: auto;
            margin-top: 30vh;
            font-family: sans-serif;
            box-shadow: 0px 0px 9px 0px black;
            padding: 2vw;
            background-color: #ADD8E6;
            text-shadow: 0px 0px black;
        }
        .new button{
            margin: auto;
        }
        .form-group label {
            font-size: 2.5vh;
            display: inline-block;
            margin-top: 0.25vh;
            margin-bottom: 0.25vh;
            font-family: sans-serif;
        }
    </style>
    <script>
        function validateForm()
        {
            let validate = valMobileNumber()
            if(!validate)
                document.loginForm.mobile.innerHTML="";
            return validate
        }
        function clearError()
        {
            document.getElementById("showError").innerHTML=""
        }
        function valMobileNumber()
        {
            let mobileNumber = document.loginForm.mobile.value;
            let errorMessage = ""
            if(mobileNumber=="")
            {
                errorMessage= "Please fill the mobile Number"
            }
            else if(isNaN(mobileNumber))
            {
                errorMessage= "Enter the proper mobile Number"
            }
            // else if(!mobileNumber.match("^[0-9]{10}"))
            // {
            //     errorMessage = "Please enter the ten digit mobile Number"
            // }

            if(errorMessage=="") return true;
            document.getElementById("showError").innerHTML=errorMessage
            return false
        }
        function sendLogin(){
            let data = {
                "mobileNumber": document.getElementById("mobile-number").value.toString(),
                "password": document.getElementById("password").value.toString()
            }
            let success = function (data){
                // sessionStorage.setItem("Authorization", "Bearer "+data['token']);
                document.cookie = "Authorization="+data['token']+";"

                //name and value of the cookie
                console.log(data['token'])
                location.reload()

            }
            let failure = function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(errorThrown)
            }
            console.log(data)
            ajaxFunction("POST","/login",data,'application/json',success,failure)

        }
        function ajaxFunction(type, url, data, contentType, success, failure) {
            if (data != null) {
                $.ajax({
                    type: type,
                    url: url,
                    data: JSON.stringify(data),
                    contentType: contentType,
                    success: success,
                    error: failure
                });
            } else {
                $.ajax({
                    type: type,
                    url: url,
                    success: success,
                    error: failure
                });
            }
        }
    </script>
</head>
<body class="background">

    <div class="content-section new">
        <form name="loginForm" th:action="@{/login}" method="POST" >



            <!-- Mobile input -->
        <div class="form-outline mb-4">
            <label class="form-label" for="mobile-number"> Enter Your Mobile Number</label>
            <input type="text" name="mobileNumber" id="mobile-number" placeholder="Mobile Number" class="form-control"  onkeyup="clearError()"/>
            <div style="color: #721c24" id="showError"></div>
        </div>
        <div class="form-outline mb-4">
            <label class="form-label" for="password"> Password </label>
            <input type="password" name="password" id="password" class="form-control"/>
        </div>

        <!-- Submit button -->

        </form>
        <button id="submitButton" class="btn btn-primary btn-block mb-4" onclick="sendLogin()">Submit</button>
    </div>


<!--    bootstrap scripts-->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
<!--local scripts-->
<script type="text/javascript" th:src="@{/js/basic/login.js}" />

</body>
</html>