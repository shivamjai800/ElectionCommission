<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <!--    local css script-->
    <link type="text/css" rel="stylesheet" href="/css/basic/login.css">

    <title>Absentee Voter Management System</title>
    <link rel="icon" href="/images/launch_image.png"/>

    <style>
        .background {
            background-image: url('/images/vote.jpg');
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

        .new button {
            margin: auto;
        }

        .form-group label {
            font-size: 2.5vh;
            display: inline-block;
            margin-top: 0.25vh;
            margin-bottom: 0.25vh;
            font-family: sans-serif;
        }

        #other-login {
            display: none;
        }

        #otp {
            display: none;
        }

        #submitButton1 {
            display: none;
        }

        #otp-sent {
            display: none;
            text-decoration-color: green;
        }
    </style>
    <script>
        function validateForm() {
            let validate = valMobileNumber()
            if (!validate)
                document.loginForm.mobile.innerHTML = "";
            return validate
        }

        function clearError() {
            document.getElementsByClassName("showError").innerHTML = ""
        }

        function valMobileNumber() {
            let mobileNumber = document.loginForm.mobile.value;
            let errorMessage = ""
            if (mobileNumber == "") {
                errorMessage = "Please fill the mobile Number"
            } else if (isNaN(mobileNumber)) {
                errorMessage = "Enter the proper mobile Number"
            }
            // else if(!mobileNumber.match("^[0-9]{10}"))
            // {
            //     errorMessage = "Please enter the ten digit mobile Number"
            // }

            if (errorMessage == "") return true;
            document.getElementsByClassName("showError").innerHTML = errorMessage
            return false
        }

        function sendLogin() {

            let data = {
                "userRole": document.getElementById('select-role').value.toString()

            }
            if (data["userRole"] == "BLO") {
                data["mobileNumber"] = document.getElementById('mobile-number').value.toString()
                data["otp"] = document.getElementById('otp-input').value.toString()
            } else {
                data["username"] = document.getElementById('user-name').value.toString()
                data["password"] = document.getElementById('password').value.toString()
            }


            let success = function (data) {
                console.log(data)
                // sessionStorage.setItem("Authorization", "Bearer "+data['token']);
                document.cookie = "Authorization=" + data.data['token'] + ";"
                location.reload()

            }
            let failure = function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(errorThrown)
            }
            console.log(data)
            ajaxFunction("POST", "/login", data, 'application/json', success, failure)

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

        function sendOtp() {
            document.getElementById('otp-send').style.display = 'none';
            document.getElementById('otp').style.display = 'block';
            $("#mobile-number").prop('readonly', true);
            document.getElementById('submitButton1').style.display = 'block';
            document.getElementById('otp-sent').style.display = 'block';
            let success = function(data){}
            let failure = function (data){}
            let data = {
                "mobileNumber": document.getElementById("mobile-number").value
            }
            ajaxFunction("post","/otp",data ,'application/json',success,failure)
        }

        window.onload = function () {
            document.getElementById('select-role').addEventListener('change', (event) => {
                document.getElementById('mobile-number').value = null;
                document.getElementById('otp-input').value = null;
                document.getElementById('user-name').value = null;
                document.getElementById('password').value = null;
                $("#mobile-number").prop('readonly', false);
                document.getElementById('otp').style.display = 'none';
                document.getElementById('submitButton1').style.display = 'none';
                document.getElementById('otp-send').style.display = 'block';
                document.getElementById('otp-sent').style.display = 'none';
                if (event.target.value == "BLO") {
                    document.getElementById("blo-login").style.display = 'block';
                    document.getElementById("other-login").style.display = 'none';
                } else {
                    document.getElementById("other-login").style.display = 'block';
                    document.getElementById("blo-login").style.display = 'none';
                }
            });
        }

        function validateForm()
        {
            let validate = valotp()
            if(!validate)
                document.loginForm.otp.innerHTML="";
            return validate
        }
        function clearError()
        {
            document.getElementById("showError").innerHTML=""
        }
        function valotp()
        {
            let otp = document.loginForm.otp.value;
            let errorMessage = ""
            if(otp=="")
            {
                errorMessage= "Please fill the otp Number"
            }
            else if(isNaN(otp))
            {
                errorMessage= "Enter the proper otp Number"
            }
            else if(!otp.match("/\d{6}"))
            {
                errorMessage = "Please enter the 6 digit otp Number"
            }

            if(errorMessage=="") return true;
            document.getElementById("showError").innerHTML=errorMessage
            return false
        }
    </script>
</head>
<body class="background">
<div class="container">
    <div class="row">
        <div class="col-md">
            <div class="header" style="background-color: cornsilk;">Welcome to the Absentee Voter Management System
            </div>
            <img src="/images/launch_image.png">
        </div>
        <div class="col-md">
            <div>
                Please select the login role:
                <span>
                    <label for="select-role">Select Role</label>
                    <select class="custom-select custom-select-sm select-role" id="select-role" name="select-role">
                        <option selected value="BLO">BLO</option>
                        <option value="RO">RO</option>
                        <option value="SO">SO</option>
                        <option value="DEO">DEO</option>
                        <option value="CEO">CEO</option>
                    </select>
                </span>
            </div>
            <div class="content-section new" id="blo-login">
                <form name="loginForm" th:action="@{/login}" method="POST">


                    <!-- Mobile input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="mobile-number"> Enter Your Mobile Number</label>
                        <input type="text" name="mobileNumber" id="mobile-number" placeholder="Mobile Number"
                               class="form-control"
                               onkeyup="clearError()"/>
                        <div style="color: #721c24" class="showError"></div>
                    </div>

                    <div id="otp" class="form-outline mb-4">
                        <label class="form-label" for="otp"> OTP </label>
                        <input type="text" name="otp" id="otp-input" placeholder="OTP" class="form-control"/>
                    </div>

                </form>
                <div class="d-flex flex-row">
                    <!-- Send OTP button -->
                    <button id="otp-send" class="btn btn-primary btn-block mb-4" onclick="sendOtp()">Send OTP</button>
                    <p id="otp-sent">OTP sent successfully. OTP is valid only for 5 minutes.</p>
                    <!-- Submit button -->
                    <button id="submitButton1" class="btn btn-primary btn-block mb-4" onclick="sendLogin()">Submit
                    </button>
                </div>
            </div>

            <div class="content-section new" id="other-login">
                <form name="loginForm" th:action="@{/login}" method="POST">


                    <!-- Mobile input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="user-name"> Enter Your Username</label>
                        <input type="text" name="userName" id="user-name" placeholder="User Name" class="form-control"
                               onkeyup="clearError()"/>
                        <div style="color: #721c24" class="showError"></div>
                    </div>
                    <div class="form-outline mb-4">
                        <label class="form-label" for="password"> Password </label>
                        <input type="password" name="password" id="password" placeholder="Password"
                               class="form-control"/>
                    </div>

                    <!-- Submit button -->

                </form>
                <button id="submitButton2" class="btn btn-primary btn-block mb-4" onclick="sendLogin()">Submit</button>
            </div>
        </div>
    </div>
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
<script type="text/javascript" th:src="@{/js/basic/login.js}"/>

</body>
</html>