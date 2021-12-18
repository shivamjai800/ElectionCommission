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
    <link rel="icon" href="/images/otherImages/launch_image.png"/>
</head>
<style>
    /**{*/
    /*border: 1px solid black;*/
    /*}*/
    /* *{
    border: 1px solid black;
    } */
    .upper-body {
        display: inline-block;
        padding: 1vw;
    }

    .upper-body .form-group input {
        width: fit-content;
        height: fit-content;
    }

    .upper-body button {
        width: fit-content;
        height: fit-content;
    }

    .upper-body select {
        width: fit-content;
        height: fit-content;
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

    .btn-outline-success {
        width: fit-content;
        height: fit-content;
    }

    .nav_cyan {
        background-color: #20B2AA;
        box-shadow: 0 1px 10px slategrey;
    }

    .nav-right {
        float: right;
        flex-direction: row;
        display: inline-flex;
    }

    .nav-link {
        color: black;
    }

    .holder {
        height: 12vh;
        width: 10vw;
        border: 2px solid black;
    }

    img {
        max-width: 150px;
        max-height: 150px;
        min-width: 150px;
        min-height: 150px;
    }

    /*input[type="file"] {*/
    /*    margin-top: 5px;*/
    /*}*/
</style>
<script>
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
        if (valEpicNo(epicNo)) {
            document.getElementById("searchForm").method = "GET";
            document.getElementById("searchForm").action = "/postalBallot/" + category + "/" + epicNo;
            document.getElementById("searchForm").submit();
        }
    }

    function getCoordinates() {
        navigator.geolocation.getCurrentPosition((position) => {
            setCoordinates(position.coords.latitude, position.coords.longitude)
        }, (error) => {
            let message = ""
            switch (error.code) {
                case error.PERMISSION_DENIED:

                    message = "User denied the request for Geolocation."
                    break;
                case error.POSITION_UNAVAILABLE:
                    message = "Location information is unavailable."
                    break;
                case error.TIMEOUT:
                    message = "The request to get user location timed out Check your browser."
                    break;
                case error.UNKNOWN_ERROR:
                    message = error.message
                    break;
            }
            stateModifierHelper()
            document.getElementById('locationErrorMessage').innerHTML = message
            $('#locationError').modal('show')
        }, {maximumAge: 60000, timeout: 5000, enableHighAccuracy: true})
    }

    let formState = {
        latCoordinate: "",
        longCoordinate: ""
    }

    function setCoordinates(x, y) {
        formState.latCoordinate = x
        formState.longCoordinate = y
    }


    function voteCasted() {
        let createElement = function
            (type, name, elementId, data, value) {

            let input = document.createElement('input')

            input.setAttribute('type', type)
            if (elementId != null) {
                if (document.getElementById(elementId) != null && document.getElementById(elementId).getAttribute('type') == 'checkbox') {
                    data[name] = $('#' + elementId).is(":checked") ? true : false
                } else
                    data[name] = $('#' + elementId).val()
            } else
                data[name] = value
        }
        let addMultipartElement = function (name, elementId, formData) {
            if (document.getElementById(elementId) != null && document.getElementById(elementId).getAttribute('type') == 'file') {
                formData.append(name, document.getElementById(elementId).files[0])
            } else {
                formData.append(name, null)
            }
        }
        let data = {}

        createElement('text', 'voter_epic_no', 'epicNo', data)
        createElement('text', 'voter_category', 'category', data)
        createElement('text', 'voter_first_name', 'firstName', data)
        createElement('text', 'voter_last_name', 'lastName', data)
        createElement('number', 'user_id', 'userId', data)
        createElement('checkbox', 'is_vote_casted', null, data, true)
        getCoordinates()
        createElement('text', 'gps_coord_lat', null, data, formState.latCoordinate)
        createElement('text', 'gps_coord_lon', null, data, formState.longCoordinate)

        let formData = new FormData()
        formData.append('vote', JSON.stringify(data))

        addMultipartElement('envelopeImage', 'envelopeImage', formData)
        addMultipartElement('othersImage', 'othersImage', formData)
        addMultipartElement('selfieWithVoterImage', 'selfieWithVoterImage', formData)
        addMultipartElement('voterIdImage', 'voterIdImage', formData)


        let success = function (data, textStatus, xhr) {
            console.log("data = ", data, "text Status = ", textStatus, "xhr = ", xhr)
            $("#popUpTitle").text(textStatus)
            $("#popUpBody").text(data.data)
            $("#popUp").modal('show')
        }
        let failure = function (xhr, textStatus, errorThrown) {
            console.log("errorThrown = ", errorThrown, "text Status = ", textStatus, "xhr = ", xhr)
            $("#popUpTitle").text(textStatus)
            $("#popUpBody").text(xhr.responseJSON.apiError.message)
            $("#popUp").modal('show')
        }
        $('#voteCastedPopUp').modal('hide')
        ajaxFunction("POST", "/vote", formData, 'multipart/form-data', false, false, success, failure)

    }

    function openPostalBallotEntryPage() {
        window.location.href = '/postalBallotEntry'
    }

    function ajaxFunction(type, url, data, enctype, processData, contentType, success, failure) {
        if (data != null) {
            $.ajax({
                type: type,
                url: url,
                data: data,
                dataType: 'json',
                enctype: 'multipart/form-data', // tell jQuery not to process the data
                contentType: contentType,
                // contentType: 'multipart/form-data; boundary=----WebKitFormBoundarymA46Tut5fkOGALWr ',
                processData: processData,// tell jQuery not to set contentType
                success: success,
                error: failure
            });
        } else {
            $.ajax({
                type: type,
                url: url,
                enctype: enctype,
                success: success,
                error: failure
            });
        }
    }

    function valEpicNo(epicNo) {
        console.log("Validating Epic Number")
        let errorMessage = ""
        if (epicNo === "") {
            errorMessage = "Please enter the Epic Number"
        } else if (!epicNo.match("^[A-Z0-9]{10}$")) {
            errorMessage = "Entered Epic Number is invalid. Please, enter a valid Epic Number"
        }
        console.log(epicNo)
        console.log(errorMessage)
        if (errorMessage === "") return true;
        document.getElementById("showError").innerHTML = errorMessage
        return false
    }
    let loadFile = function(event,elementId) {
        let output = document.getElementById(elementId);
        output.src = URL.createObjectURL(event.target.files[0]);
        output.onload = function() {
            URL.revokeObjectURL(output.src) // free memory
        }
    };
</script>
<body>
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light nav_cyan">
            <a class="navbar-brand mb-0 h1">Postal Ballot Entry (Test Version)</a>
            <div class="nav-right">
                <a class="nav-link">
                    <i class="fas fa-user-circle"></i> <span
                        th:text="'Welcome ' +  ${userName} + ' (' +  ${role} + ')'"></span>
                </a>
                <a class="nav-link" href="/logoutt"> <i class="fas fa-sign-out-alt"></i>Logout</a>
            </div>
        </nav>
        <div class="card" style="width: 40vw; margin: auto; margin-top: 5vh;">
            <div class="card-body">
                <div class="upper-body" style="display:inline-block; align-content: center">
                    <form th:action="@{/login}" th:method="GET" id="searchForm" class="form-inline my-2 my-lg-0">
                        <div class="form-row d-flex">
                            <div class="form-group mx-2">
                                <select id="category" class="form-select" aria-label="Default select example">
                                    <option selected value="AVSC" th:selected="${categorySelected=='AVSC'}">AVSC
                                    </option>
                                    <option value="AVPD" th:selected="${categorySelected=='AVPD'}">AVPD</option>
                                    <option value="AVCO" th:selected="${categorySelected=='AVCO'}">AVCO</option>
                                    <option value="AVGE" th:selected="${categorySelected=='AVGE'}">AVGE</option>
                                    <option value="AVEW" th:selected="${categorySelected=='AVEW'}">AVEW</option>
                                </select>
                            </div>
                            <div class="form-group col-md my-1 mx-2 ">
                                <input id="epicNo" type="search" placeholder="Epic" aria-label="Search"
                                       onkeyup="clearError()"
                                       th:value="${voter} and ${voter.epicNo}?${voter.epicNo}:''">
                            </div>
                            <button class="form-group btn btn-outline-success mx-2" onclick="searchVoter()"><i
                                    class="fa fa-search fa-xs"></i>
                            </button>
                        </div>
                    </form>
                    <div style="color: #721c24" id="showError" th:text="${error}? ${error}:''"></div>
                    <div th:if="${result != null}" style="color: #28a745" id="showResult"
                         th:text="${result}? ${result}:''"></div>
                </div>
                <div th:if="${voter != null}" class="lower-body">
                    <form id="voterDetail" style="flex-direction:column;">

                        <div class="form-row d-flex">
                            <div class="form-group col-md-5">
                                <label for="firstName">First Name</label>
                                <input name="firstName" type="text" class="form-control" id="firstName"
                                       placeholder="First Name" th:value="${voter.firstName} ? ${voter.firstName}: '' "
                                       readonly>
                            </div>
                            <div class="form-group col-md-5">
                                <label for="lastName">Last Name</label>
                                <input name="lastName" type="text" class="form-control" id="lastName"
                                       placeholder="Last Name" th:value="${voter.lastName} ? ${voter.lastName}: '' "
                                       readonly>
                            </div>
                        </div>

                        <div class="form-row d-flex">
                            <div class="form-group col-md-3">
                                <label for="gender">Gender</label>
                                <input name="gender" type="text" class="form-control" id="gender" placeholder="Gender"
                                       th:value="${voter.gender} ? ${voter.gender}: '' " readonly>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="age">Age</label>
                                <input name="age" type="number" class="form-control" id="age" placeholder="Password"
                                       min="18" th:value="${voter.age} ? ${voter.age}: '' " readonly>
                            </div>
                            <div class="form-group col-md-3">
                                <input name="voterSlNo" type="number" class="form-control" id="partSlNo"
                                       placeholder="part serial no"
                                       th:value="${voter.listSlNo} ? ${voter.listSlNo}: '' " readonly>
                            </div>
                        </div>

                        <div class="form-row d-flex">
                            <div class="form-group">
                                <!--                                <img th:src="${voter.image} ? ${voter.image}: '' " alt="No Image ">-->
                                <img src="/images/otherImages/user_img.jpg" alt="No Image"
                                     style="width:10rem; height:10rem">

                            </div>
                        </div>

                        <div class="form-row" style="display: none">
                            <div class="form-group col-md-5">
                                <input id="voterEpicNo" name="voterEpicNo" type="text" class="form-control"
                                       placeholder="First Name" th:value="${voter.epicNo} ? ${voter.epicNo}: '' ">
                            </div>
                            <div class="form-group col-md-5">
                                <input id="voterCategory" name="voterCategory" type="text" class="form-control"
                                       placeholder="First Name"
                                       th:value="${voter.category} ? ${voter.category}: '' ">
                            </div>
                            <div class="form-group col-md-5">
                                <input id="userId" name="userId" type="number" class="form-control"
                                       placeholder="First Name" th:value="${userId} ? ${userId}: 1 ">
                            </div>


                        </div>
                        <div class="form-group d-flex flex-row mx-5 my-2">

                            <div class="d-flex flex-column">
                                <div class="holder">
                                    <img id="imgPreviewEnvelope" src="#" alt="pic"/>
                                </div>
                                <label for="envelopeImage">Upload Envelope Image</label>
                                <input type="file" accept=".jpg,.jpeg"  name="envelopeImage"
                                       id="envelopeImage" required="true"
                                       onchange="loadFile(event,'imgPreviewEnvelope')"/>
                            </div>
                            <div class="d-flex flex-column">
                                <div class="holder">
                                    <img id="imgPreviewVoterId" src="#" alt="pic"/>
                                </div>
                                <label for="voterIdImage">Upload ID of the voter</label>
                                <input type="file" accept=".jpg,.jpeg"  name="voterIdImage"
                                       id="voterIdImage" required="true"
                                       onchange="loadFile(event,'imgPreviewVoterId')"/>
                            </div>
                        </div>
                        <div class="form-group d-flex flex-row mx-5 my-2">
                            <div class="d-flex flex-column">

                                <div class="holder">
                                    <img id="imgPreviewSelfie" src="#" alt="pic"/>
                                </div>
                                <label for="selfieWithVoterImage">Upload a selfie with the voter</label>
                                <input type="file" accept=".jpg,.jpeg"  name="selfieWithVoterImage"
                                       id="selfieWithVoterImage" required="true"
                                       onchange="loadFile(event,'imgPreviewSelfie')"/>
                            </div>
                            <div class="d-flex flex-column">

                                <div class="holder">
                                    <img id="imgPreviewOthersImage" src="#" alt="pic"/>
                                </div>
                                <label for="othersImage">Other Image</label>
                                <input type="file" accept=".jpg,.jpeg"  name="othersImage"
                                       id="othersImage" required="true"
                                       onchange="loadFile(event,'imgPreviewOthersImage')"/>
                            </div>
                        </div>


                    </form>

                    <button id="lowerBodyButton" class="btn btn-primary" data-backdrop="static" data-keyboard="false"
                            data-toggle="modal" data-target="#voteCastedPopUp">Vote Casted
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Button trigger modal -->
<!-- Modal -->
<div th:if="${voter != null}" class="modal fade" id="voteCastedPopUp" tabindex="-1" role="dialog"
     aria-labelledby="voteCasted" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Vote casted by the elector?
                <br/>
                <span style="color: red; font-size: x-small">This action can't be undone!</span>
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-success" onclick="voteCasted()">Yes</button>
            </div>
        </div>
    </div>
</div>

<div th:if="${voter != null}" class="modal fade" id="locationError" tabindex="-1" role="dialog"
     aria-labelledby="voteCasted"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p id="locationErrorMessage"></p>
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="openPostalBallotEntryPage()">
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal" id="popUp" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 id="popUpTitle" class="modal-title">Message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="window.location.href = '/postalBallotEntry'">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="popUpBody"></p>
            </div>
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
