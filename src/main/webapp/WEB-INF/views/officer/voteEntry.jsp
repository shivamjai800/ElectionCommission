
<!doctype html>
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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" href="/css/officer/sidebar.css">
    <link rel="stylesheet" href="/css/officer/voteEntry.css">
    <title></title>
</head>
<style>
    /**{*/
    /*border: 1px solid black;*/
    /*}*/
    /* *{
    border: 1px solid black;
    } */
    .upper-body {
        display: flex;
        padding: 1vw;
    }

    .upper-body .form-group input {
        width: 20vw;
        height: 3.5vh;
    }

    .upper-body button {
        width: fit-content;
        height: 3.5vh;
    }

    .upper-body select {
        width: 10vw;
        height: 3.5vh;
    }

    .form-group label {
        top: 0.75vh;
        left: .75vw;
        position: relative;
        background: white;
        width: fit-content;
        font-size: 0.75rem;
    }
    .form-check label {
        position: relative;
        background: white;
        width: fit-content;
        font-size: 0.75rem;
        margin-left: 0.5vw;
        margin-right: 0.5vw;
    }

    .form-group img {
        width: 25vw;
        height: 10vh;
    }
</style>
<script>
    let formState = {
        buttonName: "On field verification",
        buttonTarget: "#physicallyMet",
        fieldVerified: false,
        form12dDelivered: false,
        filledInForm12dReceived: false
    }
    document.addEventListener("DOMContentLoaded", function (event) {

        const showNavbar = (toggleId, navId, rightBodyId) => {
            const toggle = document.getElementById(toggleId),
                nav = document.getElementById(navId),
                rightBody = document.getElementById(rightBodyId)

            if (toggle && nav) {
                toggle.addEventListener('click', () => {
                    nav.classList.toggle('side-bar-show')
                    toggle.classList.toggle('bx-x')
                    rightBody.classList.toggle('right-body-toggle')
                })
            }
        }

        showNavbar('header-toggle', 'nav-bar', 'right-body')

        const linkColor = document.querySelectorAll('.nav_link')

        function colorLink() {
            if (linkColor) {
                linkColor.forEach(l => l.classList.remove('active'))
                this.classList.add('active')
            }
        }

        linkColor.forEach(l => l.addEventListener('click', colorLink))

    });

    function searchVoter() {
        let category = document.getElementById("category").value;
        let epicNo = document.getElementById("epicNo").value.toUpperCase();
        console.log(epicNo);
        if(valEpicNo(epicNo)) {
            document.getElementById("searchForm").action = "/voter/" + category + "/" + epicNo;
            document.getElementById("searchForm").submit();
        }
    }
    function physicallyMet(val)
    {
        if(val==false)
        {
            stateModifier()
            $('#remarks').modal('show')
        }
        else
        {
            formState.buttonName = "Form 12D Delivered";
            formState.buttonTarget= "#form12D"
            formState.fieldVerified = true
        }
        stateModifier()
        // let button = document.getElementById('lowerBodyButton');
        // button.innerText = "Form 12D Delivered"
        // button.setAttribute("data-target","#form12D")
        // document.getElementById('fieldVerified').checked = true
        // $('#physicallyMet').modal('hide');
    }
    function stateModifier()
    {
        let button = document.getElementById('lowerBodyButton');
        button.innerText = formState.buttonName
        button.setAttribute("data-target",formState.buttonTarget)
        document.getElementById('fieldVerified').checked = formState.fieldVerified
        document.getElementById('form12dDelivered').checked = formState.form12dDelivered
        document.getElementById('filledInForm12dReceived').checked = formState.filledInForm12dReceived
        $('#physicallyMet').modal('hide')
        $('#remarks').modal('hide')
        $('#form12D').modal('hide')
    }


    function ajaxFunction(type, url, data, contentType, success, failure) {
        if (data != null) {
            $.ajax({
                type: type,
                url: url,
                data: data,
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

    function valEpicNo(epicNo)
    {
        console.log("Validating Epic Number")
        let errorMessage = ""
        if(epicNo==="")
        {
            errorMessage= "Please enter the Epic Number"
        }
        else if(!epicNo.match("^[A-Z0-9]{10}$"))
        {
            errorMessage = "Entered Epic Number is invalid. Please, enter a valid Epic Number"
        }
        console.log(epicNo)
        console.log(errorMessage)
        if(errorMessage==="") return true;
        document.getElementById("showError").innerHTML=errorMessage
        return false
    }
</script>
<body>
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light bg-light">
            <span class="navbar-brand mb-0 h1">Navbar</span>
        </nav>
        <div class="card" style="width: 40vw; margin: auto; margin-top: 5vh;">
            <div class="card-body">
                <div class="upper-body" style="display:flex;flex-direction: column;">
                    <form th:action="@{/login}" th:method="POST" id="searchForm" class="form-inline my-2 my-lg-0" style="display: flex;">
                        <div class="form-row d-flex">
                            <div class="form-group mx-2">
                                <select id="category" class="form-select" aria-label="Default select example">
                                    <option selected value="AVSC">AVSC</option>
                                    <option value="AVPD">AVPD</option>
                                    <option value="AVCO">AVCO</option>
                                </select>
                            </div>
                            <div class="form-group col-md">
                                <input id="epicNo" type="search" placeholder="Epic" aria-label="Search" onkeyup="clearError()">
                            </div>
                        </div>
                    </form>
                    <button class="btn btn-outline-success" onclick="searchVoter()"><i class="fa fa-search"></i>
                    </button>
                    <div style="color: #721c24" id="showError" th:text="${error}? ${error}:''"></div>
                </div>
                <div th:if="${voter != null}" class="lower-body">
                    <form id="voterDetail"  style="flex-direction:column;">
                        <div class="form-row d-flex">
                            <div class="form-group col-md-5">
                                <label for="firstName">First Name</label>
                                <input name="firstName" type="text" class="form-control" id="firstName"
                                       placeholder="First Name" th:value="${voter.firstName} ? ${voter.firstName}: '' ">
                            </div>
                            <div class="form-group col-md-5">
                                <label for="lastName">Last Name</label>
                                <input name="lastName" type="text" class="form-control" id="lastName"
                                       placeholder="Last Name" th:value="${voter.lastName} ? ${voter.lastName}: '' ">
                            </div>
                        </div>
                        <div class="form-row d-flex">
                            <div class="form-group col-md-3">
                                <label for="gender">Gender</label>
                                <input name="gender" type="text" class="form-control" id="gender" placeholder="Gender" th:value="${voter.gender} ? ${voter.gender}: '' ">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="age">Age</label>
                                <input name="age" type="number" class="form-control" id="age" placeholder="Password"
                                       min="18" th:value="${voter.age} ? ${voter.age}: '' ">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="partSlNo">Part SL No</label>
                                <input name="partSlNo" type="number" class="form-control" id="partSlNo"
                                       placeholder="part serial no" th:value="${voter.listSlNo} ? ${voter.listSlNo}: '' ">
                            </div>
                        </div>
                        <div class="form-row d-flex">
                            <div class="form-group col-md-4">
                                <label for="mobileNumber">Mobile Number</label>
                                <input name="mobileNumber" type="number" class="form-control" id="mobileNumber"
                                       placeholder="Mobile Number" th:value="${voter.mobileNo} ? ${voter.mobileNo}: '' ">
                            </div>
                        </div>
                        <div class="form-row d-flex">
                            <div class="form-group">
                                <img th:src="${voter.image} ? ${voter.image}: '' " alt="No Image ">
                            </div>
                        </div>
                        <div class="form-row d-flex" >

                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="fieldVerified" disabled>
                                <label class="form-check-label" for="fieldVerified">
                                    field verified
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="form12dDelivered" disabled>
                                <label class="form-check-label" for="form12dDelivered">
                                    form 12D Delivered
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="filledInForm12dReceived" disabled>
                                <label class="form-check-label" for="filledInForm12dReceived">
                                    filled in form 12D Received
                                </label>
                            </div>
                        </div>

                    </form>
                    <button id="lowerBodyButton" class="btn btn-primary" data-backdrop="static" data-keyboard="false"
                            data-toggle="modal" data-target="#physicallyMet">On Field Verification
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Button trigger modal -->
<!-- Modal -->
<div th:if="${voter != null}" class="modal fade" id="form12D" tabindex="-1" role="dialog" aria-labelledby="physicallyMetTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Has form be delivered to <span th:text="${voter.firstName} ? ${voter.firstName}: '' "></span> <span th:text="${voter.lastName} ? ${voter.lastName}: '' "></span>
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-warning">No</button>
                <button type="button" class="btn btn-success">Yes</button>
            </div>
        </div>
    </div>
</div>
<div th:if="${voter != null}" class="modal fade" id="physicallyMet" tabindex="-1" role="dialog" aria-labelledby="form12DTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Have physically met the blo
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-warning" onclick="physicallyMet(false)">No</button>
                <button type="button" class="btn btn-success" onclick="physicallyMet(true)">Yes</button>

            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="remarks" tabindex="-1" role="dialog" aria-labelledby="remarks" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header d-flex flex-column">
                <h5 class="modal-title">Remarks</h5>
                <p class="mt-3 mr-3"> Please submit the reason(s) against your action.
            </div>
            <form>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1" class="col-form-label">Remarks</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div>
                </div>
                <div class="modal-footer d-flex">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!--Container Main end-->
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

<script type="text/javascript" src="/js/officer/bloDashboard.js"/>
</body>
</html>
