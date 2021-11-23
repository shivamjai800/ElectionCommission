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
    <style>

    </style>
    <script>
        // function validateForm()
        // {
        //     let validate = valMobileNumber()
        //     if(!validate)
        //         document.loginForm.mobile.innerHTML="";
        //     return validate
        // }
        // function clearError()
        // {
        //     document.getElementById("showError").innerHTML=""
        // }
        // function valMobileNumber()
        // {
        //     let mobileNumber = document.loginForm.mobile.value;
        //     let errorMessage = ""
        //     if(mobileNumber=="")
        //     {
        //         errorMessage= "Please fill the mobile Number"
        //     }
        //     else if(isNaN(mobileNumber))
        //     {
        //         errorMessage= "Enter the proper mobile Number"
        //     }
        //     else if(!mobileNumber.match("/\d{10}"))
        //     {
        //         errorMessage = "Please enter the ten digit mobile Number"
        //     }
        //
        //     if(errorMessage=="") return true;
        //     document.getElementById("showError").innerHTML=errorMessage
        //     return false
        // }
    </script>
</head>
<body class="background">

    <div class="content-section new">
        <form name="loginForm" th:action="@{/login}" method="post" onsubmit="return validateForm()">

            <!-- Mobile input -->
        <div class="form-outline mb-4">
            <label class="form-label" for="mobile-number"> Enter Your Mobile Number</label>
            <input type="text" name="mobile" id="mobile-number" placeholder="Mobile Number" class="form-control"  onkeyup="clearError()"/>
            <div style="color: #721c24" id="showError"></div>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4">Get OTP</button>
        </form>
    </div>


<!--    bootstrap scripts-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<!--local scripts-->
<script type="text/javascript" th:src="@{/js/basic/login.js}" />

</body>
</html>