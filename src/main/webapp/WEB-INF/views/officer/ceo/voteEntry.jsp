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
        <title>Postal Ballot Management System</title>

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
            document.getElementById("searchForm").action = "/voter/" + category + "/" + epicNo;
            document.getElementById("searchForm").submit();
        }
    }

    let formState = {
        stateName: "initialState",
        buttonName: "On field verification",
        buttonTarget: "#physicallyMet",
        fieldVerified: false,
        form12dDelivered: false,
        filledInForm12dReceived: false,
        mobileNumberLocked: false,
        hasVoterExpiredCheckboxDisplay: "none",
        backButtonDisplay: "none",
        latCoordinate: "",
        longCoordinate: ""
    }
    function setCoordinates(x,y)
    {
        formState.latCoordinate = x
        formState.longCoordinate = y
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
        }, {maximumAge:60000, timeout:5000, enableHighAccuracy:true})

    }

    function createVisit() {
        let createElement = function
            (type, name, elementId, form, value) {

            let input = document.createElement('input')

            input.setAttribute('type', type)
            if (elementId != null)
                input.value = $('#' + elementId).val()
            else
                input.value = value
            input.setAttribute('name', name)
            form.appendChild(input)
            console.log(input)
        }
        let form = document.createElement('form');
        form.action = "/visit"
        form.method = "POST"
        form.style.display = "none";

        createElement('text', 'voterEpicNo', 'epicNo', form)
        createElement('number', 'voterSlNo', 'partSlNo', form)
        createElement('text', 'voterCategory', 'category', form)
        createElement('number', 'bloId', 'bloId', form)
        createElement('number', 'voterMobileNo', 'mobileNumber', form)
        createElement('text', 'isPhysicallyMet', null, form,$('#fieldVerified').is(":checked")?true:false)
        getCoordinates()
        createElement('text', 'firstVisitGpsCoordLat', null, form,formState.latCoordinate)
        createElement('text', 'firstVisitGpsCoordLon', null, form,formState.longCoordinate)
        createElement('checkbox','isVoterExpired' , 'hasVoterExpired', form)

        let remarksFor = ""
        if(formState.stateName=="notMet")
        {
            remarksFor = "firstVisitRemarks"
        }
        else if(formState.stateName == "notFormDelivered")
        {
            remarksFor = "form_12dDeliveredRemarks"
        }
        else
        {
            remarksFor = "filledForm_12dReceivedRemarks"
        }
        createElement('text',remarksFor , 'remarksInside', form)

        document.body.append(form)
        form.submit()


    }
    function openVoteEntryPage()
    {
        window.location.href = '/voteEntry'
    }
    function stateModifier(stateName) {

        //Forward Rows Left->Left->Down
        //Row1
        if (formState.stateName == "initialState" && stateName == "notMet") {
            formState.stateName = "notMet"
            formState.hasVoterExpiredCheckboxDisplay = "block"
            stateModifierHelper()
            $('#remarks').modal('show')
        } else if (formState.stateName == "notMet" && stateName == "zeroState") {
            formState.stateName = "zeroState"
            formState.hasVoterExpiredCheckboxDisplay = "none"
            //    Create Visit
            //    Open VoteEntry Page
            createVisit()
            stateModifierHelper()

            // openVoteEntryPage()
        } else if (formState.stateName == "initialState" && stateName == "physicallyMetYes") {
            formState.stateName = "physicallyMetYes"
            formState.buttonName = "Form 12D Delivered";
            formState.buttonTarget = "#form12D"
            formState.fieldVerified = true
            formState.mobileNumberLocked = true
            formState.backButtonDisplay = "inline-block"
            stateModifierHelper()
        }
        //Row2
        else if (formState.stateName == "physicallyMetYes" && stateName == "notFormDelivered") {
            formState.stateName = "notFormDelivered"
            stateModifierHelper()
            $('#remarks').modal('show')
        } else if (formState.stateName == "notFormDelivered" && stateName == "zeroState") {
            formState.stateName = "zeroState"
            //  Create Visit
            //    Open VoteEntry Page
            createVisit()
            stateModifierHelper()

            // openVoteEntryPage()
        } else if (formState.stateName == "physicallyMetYes" && stateName == "formDeliveredYes") {
            formState.stateName = "formDeliveredYes"
            formState.buttonName = "Filled-in Form 12D Received";
            formState.buttonTarget = "#form12dReceived"
            formState.fieldVerified = true
            formState.form12dDelivered = true
            stateModifierHelper()
        }
        //Row3
        else if (formState.stateName == "formDeliveredYes" && stateName == "notForm12dReceived") {
            formState.stateName = "notForm12dReceived"
            stateModifierHelper()
            $('#remarks').modal('show')
        } else if (formState.stateName == "notForm12dReceived" && stateName == "zeroState") {
            formState.stateName = "zeroState"
            //  Create Visit
            createVisit()
            //  Open VoteEntry Page
            stateModifierHelper()
            // openVoteEntryPage()
        }
        //    Row4
        else if (formState.stateName == "formDeliveredYes" && stateName == "form12dReceived") {
            formState.stateName = "form12dReceived"
            formState.fieldVerified = true
            formState.form12dDelivered = true
            formState.filledInForm12dReceived = true
            stateModifierHelper()
            //    Create Visit
            //    Open VoteEntry Page
            createVisit()
            // openVoteEntryPage()
        }

        //Backward Rows Up->Right
        //Row1
        if (formState.stateName == "notMet" && stateName == "remarkCancelled") {
            formState.stateName = "initialState"
            formState.hasVoterExpiredCheckboxDisplay = "none"
            stateModifierHelper()
            $('#remarks').modal('hide')
        }
        //Row2
        else if (formState.stateName == "physicallyMetYes" && stateName == "back") {
            formState.stateName = "initialState"
            formState.buttonName = "On field verification"
            formState.buttonTarget = "#physicallyMet"
            formState.fieldVerified = false
            formState.mobileNumberLocked = false
            formState.backButtonDisplay = "none"
            stateModifierHelper()
        } else if (formState.stateName == "notFormDelivered" && stateName == "remarkCancelled") {
            formState.stateName = "physicallyMetYes"
            stateModifierHelper()
            $('#remarks').modal('hide')
        }
        //Row3
        else if (formState.stateName == "formDeliveredYes" && stateName == "back") {
            formState.stateName = "physicallyMetYes"
            formState.buttonName = "Form 12D Delivered"
            formState.buttonTarget = "#form12D"
            formState.form12dDelivered = false
            stateModifierHelper()
        } else if (formState.stateName == "notForm12dReceived" && stateName == "remarkCancelled") {
            formState.stateName = "formDeliveredYes"
            stateModifierHelper()
            $('#remarks').modal('hide')
        }
    }

    function stateModifierHelper() {
        let button = document.getElementById('lowerBodyButton');
        button.innerText = formState.buttonName
        button.setAttribute("data-target", formState.buttonTarget)
        document.getElementById('fieldVerified').checked = formState.fieldVerified
        document.getElementById('form12dDelivered').checked = formState.form12dDelivered
        document.getElementById('filledInForm12dReceived').checked = formState.filledInForm12dReceived
        $('#mobileNumber').prop("readonly", formState.mobileNumberLocked)
        $('#physicallyMet').modal('hide')
        $('#remarks').modal('hide')
        $('#form12D').modal('hide')
        $('#form12dReceived').modal('hide')
        document.getElementById('hasVoterExpiredCheckbox').style.display = formState.hasVoterExpiredCheckboxDisplay
        document.getElementById('backButtonDisplay').style.display = formState.backButtonDisplay
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
</script>
<body>
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light nav_cyan">
            <span class="navbar-brand mb-0 h1">Navbar</span>
        </nav>
        <div class="card" style="width: 40vw; margin: auto; margin-top: 5vh;">
            <div class="card-body">
                <div class="upper-body" style="display:inline-block; align-content: center">
                    <form th:action="@{/login}" th:method="POST" id="searchForm" class="form-inline my-2 my-lg-0">
                        <div class="form-row d-flex">
                            <div class="form-group mx-2">
                                <select id="category" class="form-select" aria-label="Default select example">
                                    <option selected value="AVSC">AVSC</option>
                                    <option value="AVPD">AVPD</option>
                                    <option value="AVCO">AVCO</option>
                                </select>
                            </div>
                            <div class="form-group col-md my-1 mx-2 ">
                                <input id="epicNo" type="search" placeholder="Epic" aria-label="Search"
                                       onkeyup="clearError()"
                                       th:value="${voter} and ${voter.epicNo}?${voter.epicNo}:''">
                            </div>
                            <button class="form-group btn btn-outline-success mx-2" onclick="searchVoter()"><i class="fa fa-search fa-xs"></i>
                            </button>
                        </div>
                    </form>
                    <div style="color: #721c24" id="showError" th:text="${error}? ${error}:''"></div>
                    <div th:if="${result != null}" style="color: #28a745" id="showResult" th:text="${result}? ${result}:''"></div>
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
                                <img src="/images/otherImages/user_img.jpg" alt="No Image" style="width:10rem; height:10rem">

                            </div>
                        </div>

                        <div class="form-row d-flex">
                            <div class="form-group col-md-4">
                                <label for="mobileNumber">Mobile Number</label>
                                <input name="mobileNumber" type="number" class="form-control" id="mobileNumber"
                                       placeholder="Mobile Number"
                                       th:value="${voter.mobileNo} ? ${voter.mobileNo}: '1234567890' ">
                            </div>
                        </div>
                        <div class="form-row d-flex">

                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="fieldVerified"
                                       disabled>
                                <label class="form-check-label" for="fieldVerified">
                                    field verified
                                </label>
                            </div>
                            <div class="form-check">
                                <input name="form_12dDelivered" class="form-check-input" type="checkbox" value=""
                                       id="form12dDelivered" disabled>
                                <label class="form-check-label" for="form12dDelivered">
                                    form 12D Delivered
                                </label>
                            </div>
                            <div class="form-check">
                                <input name="filledForm_12dReceived" class="form-check-input" type="checkbox"
                                       value=""
                                       id="filledInForm12dReceived" disabled>
                                <label class="form-check-label" for="filledInForm12dReceived">
                                    filled in form 12D Received
                                </label>
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
                                <input id="bloId" name="bloId" type="number" class="form-control"
                                       placeholder="First Name" th:value="${bloId} ? ${bloId}: 1 ">
                            </div>


                        </div>

                    </form>

                    <button type="button" class="btn btn-warning" id="backButtonDisplay" style="display: none"
                            onclick="stateModifier('back')">Back
                    </button>
                    <button id="lowerBodyButton" class="btn btn-primary" data-backdrop="static" data-keyboard="false"
                            data-toggle="modal" data-target="#physicallyMet">On Field Verification
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Button trigger modal -->
<!-- Modal -->
<div th:if="${voter != null}" class="modal fade" id="physicallyMet" tabindex="-1" role="dialog"
     aria-labelledby="form12DTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Have physically met the elector?
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-warning" onclick="stateModifier('notMet')">No</button>
                <button type="button" class="btn btn-success" onclick="stateModifier('physicallyMetYes')">Yes</button>
            </div>
        </div>
    </div>
</div>
<div th:if="${voter != null}" class="modal fade" id="form12D" tabindex="-1" role="dialog"
     aria-labelledby="physicallyMetTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Has form be delivered to <span th:text="${voter.firstName} ? ${voter.firstName}: '' "></span> <span
                    th:text="${voter.lastName} ? ${voter.lastName}: '' "></span>
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-warning" onclick="stateModifier('notFormDelivered')">No</button>
                <button type="button" class="btn btn-success" onclick="stateModifier('formDeliveredYes')">Yes</button>
            </div>
        </div>
    </div>
</div>
<div th:if="${voter != null}" class="modal fade" id="form12dReceived" tabindex="-1" role="dialog"
     aria-labelledby="form12dReceived"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Has filled-in form 12D been received from <span
                    th:text="${voter.firstName} ? ${voter.firstName}: '' "></span> <span
                    th:text="${voter.lastName} ? ${voter.lastName}: '' "></span>
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-warning" onclick="stateModifier('notForm12dReceived')">No</button>
                <button type="button" class="btn btn-success" onclick="stateModifier('form12dReceived')">Yes</button>
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
            <div class="form-check" id="hasVoterExpiredCheckbox" style="display: none">
                <input class="form-check-input" type="checkbox" value="" id="hasVoterExpired">
                <label class="form-check-label" for="hasVoterExpired">
                    Has Voter Expired?
                </label>
            </div>
            <form>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="remarksInside" class="col-form-label">Remarks</label>
                        <textarea name="remarks" class="form-control" id="remarksInside" rows="3"></textarea>
                    </div>
                </div>
                <div class="modal-footer d-flex">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"
                            onclick="stateModifier('remarkCancelled')">Close
                    </button>
                    <button type="button" class="btn btn-success" onclick="stateModifier('zeroState')">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div th:if="${voter != null}" class="modal fade" id="locationError" tabindex="-1" role="dialog"
     aria-labelledby="physicallyMetTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p id="locationErrorMessage"></p>
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="openVoteEntryPage()">Close</button>
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
