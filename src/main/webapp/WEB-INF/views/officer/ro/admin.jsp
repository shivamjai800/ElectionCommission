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
    <title></title>
</head>
<style>
    body {
        background-color: #F8F8F8;
    }

    .form-group {
        display: flex;
        flex-direction: column;
        width: fit-content;
        margin: .5vw .5vw .5vh .5vh;
    }

    .form-group label {
        top: 1vh;
        left: .75vw;
        position: relative;
        background: white;
        width: fit-content;
    }

    #selectPart {
        width: 15vw;
        height: 6vh;
    }

    #graph {
        width: 60%;
        height: 100%
        color: black;
    }

    #reportDownload {
        width: 40%;
        height: 50vh;
        color: blue;
    }

    #reportDownload .card .card-body button:nth-child(1), #reportDownload .card .card-body button:nth-child(2) {
        height: 12vh;
    }

    #reportDownload .card .card-body button:nth-child(3) {
        height: 9vh;
        width: 80%;
    }

    #reportDownload .card .card-body button:nth-child(4), #reportDownload .card .card-body button:nth-child(5) {
        height: 6vh;
        margin-left: 50%;
        width: 50%;
    }

    .nav_cyan {
        background-color: #20B2AA;
        box-shadow: 0 1px 10px slategrey;
    }

    .card-header {
        background-color: white;
        text-align: center;
        font-size: x-large;
        color: black;
    }

    /**{
        border: 1px solid black;
    }*/
    .nav-right {
        float: right;
        flex-direction: row;
        display: inline-flex;
    }

    .nav-link {
        color: black;
    }

    .card-3d {
        box-shadow: 0 1px 6px rgba(0, 0, 0, 0.12), 0 1px 4px rgba(0, 0, 0, 0.24), 0 -1px 6px rgba(0, 0, 0, 0.12), 0 -1px 4px rgba(0, 0, 0, 0.24);
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

    function formFilled() {

    }

</script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    function editMobilek(id) {
        $("#" + "phone" + id).attr('contenteditable', true);
    }

    function saveMobile(id) {
        if (!document.getElementById("phone" + id).getAttribute("contenteditable")) return;
        let bloId = document.getElementById("bloId" + id).innerText;
        let phone = document.getElementById("phone" + id).innerText;
        let url = "/dashboard/blo/" + bloId;
        let data = {
            "mobile_number": phone
        }
        let success = function (data, textStatus, xhr) {
            console.log("data = ", data, "text Status = ", textStatus, "xhr = ", xhr)

            $("#popUpTitle").text(textStatus)
            $("#popUpBody").text(data.data)
            $("#popUp").modal('show')
            // location.reload();
        }
        let failure = function (xhr, textStatus, errorThrown) {
            console.log("errorThrown = ", errorThrown, "text Status = ", textStatus, "xhr = ", xhr)
            $("#popUpTitle").text(textStatus)
            $("#popUpBody").text(xhr.responseJSON.apiError.message)
            $("#popUp").modal('show')
        }
        ajaxFunction("PUT", url, data, 'application/json', success, failure)

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

    function selectPart(id) {

        document.getElementById('bloListTable').style.display = "none"
        document.getElementById('eligibleVoterListTable').style.display = "none"
        document.getElementById('avcoVoterListTable').style.display = "none"
        if (id == 'eligibleVoterListTable') {
            let url = '/admin/voters'
            let success = function (data, textStatus, xhr) {
                console.log("data = ", data, "text Status = ", textStatus, "xhr = ", xhr)
                let voterDatas = data['data']
                $("#eligibleVoterListTable table tbody").empty();
                for (let i = 0; i < voterDatas.length; i++) {
                    let voterData = voterDatas[i]
                    let remarks = " "
                    if (voterData.filled_form_12d_received_remarks != null) {
                        remarks = remarks + " " + voterData.filled_form_12d_received_remarks
                    }
                    if (voterData.form_12d_delivered_remarks != null) {
                        remarks = remarks + " " + voterData.form_12d_delivered_remarks
                    }
                    if (voterData.second_visit_remarks != null) {
                        remarks = remarks + " " + voterData.second_visit_remarks
                    }
                    $("#eligibleVoterListTable table tbody").append(`
                        <tr id="voterRow"${i}">
                         <td class="row-index text-center">
                         ${i}
                         </td>
                         <td class="row-index text-center">
                         <div class="form-check form-switch">
                              <input class="form-check-input" type="checkbox" id="switchRow${i}">
                         </div>
                         </td>
                         <td class="row-index " >
                         <p id="epicRow${i}">${voterData.voter.epic_no}</p>
                         </td>
                         <td class="row-index ">
                         ${voterData.voter_sl_no}
                         </td>
                         <td class="row-index ">
                         <img id="voterImage${i}" onclick="previewFunction('voterImage${i}')" src="${voterData.voter_id_image_id == null? "/images/otherImages/user_img.jpg" : "/images/voter/"+voterData.voter_id_image_id+".jpeg"}" style="width:5rem; height:5rem" alt-text="Image Not available"/>
                         </td>
                         <td class="row-index">
                         <img id="categoryImage${i}" onclick="previewFunction('categoryImage${i}')" src="${voterData.certificate_image_id == null? "/images/otherImages/user_img.jpg" : "/images/category/"+voterData.certificate_image_id+".jpeg"}" style="width:5rem; height:5rem" alt-text="Image Not available"/>
                         </td>
                         <td class="row-index">
                         <img  id="selfieImage${i}" onclick="previewFunction('selfieImage${i}')" src="${voterData.selfie_with_voter_image_id == null? "/images/otherImages/user_img.jpg" : "/images/selfie/"+voterData.selfie_with_voter_image_id+".jpeg"}" style="width:5rem; height:5rem" alt-text="Image Not available"/>
                         </td>
                         <td class="row-index">
                         <img id="form12dImage${i}" onclick="previewFunction('form12dImage${i}')" src="${voterData.form_12d_image_id == null? "/images/otherImages/user_img.jpg" : "/images/form12d/"+voterData.form_12d_image_id+".jpeg"}" style="width:5rem; height:5rem" alt-text="Image Not available"/>
                         </td>
                         <td class="row-index ">
                         ${voterData.voter.first_name} ${voterData.voter.last_name}
                         </td>
                         <td class="row-index ">
                         ${voterData.voter.age}
                         </td>
                         <td class="row-index ">
                         ${voterData.voter.relative_first_name} ${voterData.voter.relative_last_name}
                         </td>
                         <td class="row-index t">
                         ${voterData.voter.category}
                         </td>
                         <td class="row-index ">
                         ${voterData.voter.district_name}
                         </td>
                         <td class="row-index ">
                         ${voterData.voter.constituency_name}
                         </td>
                          <td class="row-index ">
                         ${voterData.voter.part_name}
                         </td>
                         <td class="row-index ">
                         ${(voterData.physically_met == false || voterData.physically_met == null) ? "No": "Yes"}
                         </td>
                         <td class="row-index ">
                         ${voterData.first_visit_timestamp == null? " ": voterData.first_visit_timestamp}
                         </td>
                         <td class="row-index ">
                         ${voterData.second_visit_timestamp == null ? " ": voterData.first_visit_timestamp}
                         </td>
                         <td class="row-index ">
                         ${(voterData.filled_form_12d_delivered == false || voterData.filled_form_12d_delivered == null) ? "No": "Yes"}
                         </td>
                         <td class="row-index ">
                         ${(voterData.filled_form_12d_received == false || voterData.filled_form_12d_received == null) ? "No": "Yes"}
                         </td>
                         <td class="row-index ">
                         ${remarks}
                         </td>

                          </tr>

                    `);
                    $("#switchRow" + i).prop("checked", voterData.voter.eligible == true ? true : false);

                }
                // location.reload();
            }
            let failure = function (xhr, textStatus, errorThrown) {
                console.log("errorThrown = ", errorThrown, "text Status = ", textStatus, "xhr = ", xhr)
            }
            ajaxFunction('POST', url, null, 'application/json', success, failure)
        }
        document.getElementById(id).style.display = "block";


    }

    function markEligible() {
        let n = $("#eligibleVoterListTable  tr").length
        let eligible = [];
        let inEligible = [];
        for (let i = 0; i < n; i++) {
            if ($("#switchRow" + i).is(":checked")) {
                eligible.push($("#epicRow" + i).text())
            } else {
                inEligible.push($("#epicRow" + i).text())
            }
        }
        let data = {"eligible": eligible, "in_eligible": inEligible}
        console.log(eligible)
        console.log(inEligible)
        let success = function (data, textStatus, xhr) {
            // console.log("data = ", data, "text Status = ", textStatus, "xhr = ", xhr)
            $("#popUpTitle").text(textStatus)
            $("#popUpBody").text(data.data)
            $("#popUp").modal('show')

        }
        let failure = function (xhr, textStatus, errorThrown) {
            // console.log("errorThrown = ", errorThrown, "text Status = ", textStatus, "xhr = ", xhr)
            $("#popUpTitle").text(textStatus)
            $("#popUpBody").text(xhr.responseJSON.apiError.message)
            $("#popUp").modal('show')
        }
        ajaxFunction("put", "/admin/voters", data, 'application/json', success, failure)
    };
    function searchVoter()
    {
        $("#avcoVoterListTableCardBody").css("display" ,"block")
        let success = function (data, textStatus, xhr) {
            console.log("data = ", data, "text Status = ", textStatus, "xhr = ", xhr)
            let voterData = data['data']
            $("#voterFirstName").val(voterData["first_name"])
            $("#voterLastName").val(voterData["last_name"])
            $("#voterGender").val(voterData["gender"])
            $("#voterAge").val(voterData["age"])
            $("#voterRelativeName").val(voterData["relative_first_name"]+" "+voterData["relative_first_name"])
            $("#voterDistrict").val(voterData["district_name"])
            $("#voterConstituency").val(voterData["constituency_name"])
            $("#voterPart").val(voterData["part_name"])
            $("#voterMobileNumber").val(voterData["mobile_no"]==null?"None":voterData["mobile_no"])
            $("#voterCategory").val(voterData["category"]==null?"None":voterData["category"] )
            $("#voterCategorySwitch").prop("checked", voterData.category=="AVCO"? true : false);
        }
        let failure = function (xhr, textStatus, errorThrown) {

            console.log("errorThrown = ", errorThrown, "text Status = ", textStatus, "xhr = ", xhr)
            // console.log("message = ",data["apiError"]["message"])
            $("#showError").html(xhr.responseJSON.apiError.message)

        }
        ajaxFunction("get", "/admin/voter/"+$("#searchEpicNo").val(), null, 'application/json', success, failure)
    }
    function clearError()
    {
        document.getElementById("showError").innerHTML=""
    }
    function changeCategory()
    {
        let avco = [],nullCategory =[];
            if ($("#voterCategorySwitch").is(":checked")) {
                avco.push($("#searchEpicNo").val())
            }
            else
            {
                nullCategory.push($("#searchEpicNo").val())
            }
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
        ajaxFunction("put", "/admin/voters", {"avco" :avco,"null_category":nullCategory}, 'application/json', success, failure)


    }
    function previewFunction(x ) {
        // console.log(x)
        $('#imagepreview').attr('src', $('#'+x).attr('src')); // here asign the image to the modal when the user click the enlarge link
        $('#imagemodal').modal('show'); // imagemodal is the id attribute assigned to the bootstrap modal, then i use the show function
    }
</script>
<body>
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light nav_cyan">
            <a class="navbar-brand mb-0 h1">Admin (Test Version)</a>
            <div class="nav-right">
                <a class="nav-link">
                    <i class="fas fa-user-circle"></i> <span
                        th:text="'Welcome ' +  ${userName} + ' (' +  ${role} + ')'"></span>
                </a>
                <a class="nav-link" href="/logoutt"> <i class="fas fa-sign-out-alt"></i>Logout</a>
            </div>
        </nav>
        <div class="card text-center mx-1 card-3d">
            <div class="card-header">
                <button class="btn btn-primary" onclick="selectPart('bloListTable')">Blo List</button>
                <button class="btn btn-primary" onclick="selectPart('eligibleVoterListTable')">Eligible Voter</button>
                <button class="btn btn-primary" onclick="selectPart('avcoVoterListTable')">AVCO Voter</button>

            </div>
            <div id="cardBody">
                <div id="bloListTable" class="card-body">
                    <table th:if="${bloList != null}" class="table table-striped">

                        <thead class="card-title">
                        <tr>
                            <th scope="col" class="d-flex flex-row">
                                <input class="form-control" type="text"
                                       placeholder="Enter Keyword">
                                <button class="btn btn-primary">Search</button>
                            </th>
                        </tr>
                        <tr>
                            <th scope="col"> S.No</th>
                            <th scope="col">AC Id</th>
                            <th scope="col">Part No</th>
                            <th scope="col">Name</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Options</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr th:id="'row' + ${iStat.count}" th:each="blo, iStat: ${bloList}">
                            <td th:text="${iStat.count}"/>
                            <td th:id="'bloId' + ${iStat.count}" th:text="${blo.userId}"/>
                            <td th:id="'partNo' + ${iStat.count}" th:text="${blo.partId}"/>
                            <td th:id="'name' + ${iStat.count}" th:text="${blo.firstName}+' '+${blo.lastName}"/>
                            <td th:id="'phone' + ${iStat.count}" th:text="${blo.mobileNumber}"/>

                            <td>
                                <button type="button" class="btn btn-primary"
                                        th:onclick="|editMobilek('${iStat.count}')|">Edit Mobile Number
                                </button>
                                <button type="button" class="btn btn-success"
                                        th:onclick="|saveMobile('${iStat.count}')|">Update
                                </button>
                            </td>
                        </tr>
                        </tbody>

                    </table>


                </div>

                <div id="eligibleVoterListTable" class="card-body" style="display: none">

                    <table class="table table-striped">
                        <thead class="card-title">
                        <tr>
                            <th>
                                <button class="btn btn-success" onclick="markEligible()">Save Changes</button>
                            </th>
                        </tr>
                        <tr>
                            <th scope="col"> S.No</th>
                            <th scope="col"> Mark Eligible</th>
                            <th scope="col"> Epic No</th>
                            <th scope="col"> Voter Id</th>
                            <th scope="col">Voter Id Image</th>
                            <th scope="col">Category Image</th>
                            <th scope="col">Selfie with Image</th>
                            <th scope="col">Form 12D</th>
                            <th scope="col">Voter Name</th>
                            <th scope="col">Age</th>
                            <th scope="col">Relative Name</th>
                            <th scope="col">Category</th>
                            <th scope="col">District</th>
                            <th scope="col">Constituency</th>
                            <th scope="col">Part</th>
                            <th scope="col">Physically met</th>
                            <th scope="col">first visit date</th>
                            <th scope="col">second visit date</th>
                            <th scope="col">Filled form 12d Delivered</th>
                            <th scope="col">Filled form 12d Received</th>
                            <th scope="col">Remarks</th>
                        </tr>
                        </thead>
                        <tbody></tbody>


                    </table>


                </div>

                <div id="avcoVoterListTable" class="card-body" style="display: none;width: max-content;margin: auto;">

                    <div class="card bg-light" style="width: max-content;margin: auto;">
                        <div class="card-header d-flex flex-row" style="width: max-content;margin: auto;">
                            <div class="form-group col-md my-1 mx-2 ">
                                <input id="searchEpicNo" type="search" placeholder="Epic" aria-label="Search"
                                       onkeyup="clearError()">
                            </div>
                            <button class="form-group btn btn-outline-success mx-2" onclick="searchVoter()"><i class="fa fa-search fa-xs"></i></button>
                        </div>


                        <div id="avcoVoterListTableCardBody" class="card-body " style="display: none;">
                            <div style="color: #721c24" id="showError" th:text="${error}? ${error}:''"></div>
                            <div class="form-row d-flex" style="width: max-content;margin: auto;">
                                <div class="form-group">
                                    <img src="/images/otherImages/user_img.jpg" alt="No Image" style="width:10rem; height:10rem">
                                </div>
                            </div>
                            <form id="voterDetail" style="flex-direction:column;">

                                <div class="form-row d-flex" style="width: max-content;margin: auto;">
                                    <div class="form-group col-md-5">
                                        <label for="voterFirstName">First Name</label>
                                        <input name="firstName" type="text" class="form-control" id="voterFirstName"
                                               placeholder="First Name"
                                               readonly>
                                    </div>
                                    <div class="form-group col-md-5">
                                        <label for="voterLastName">Last Name</label>
                                        <input name="lastName" type="text" class="form-control" id="voterLastName"
                                               placeholder="Last Name"
                                               readonly>
                                    </div>
                                </div>

                                <div class="form-row d-flex" style="width: max-content;margin: auto;">
                                    <div class="form-group col-md-3">
                                        <label for="voterGender">Gender</label>
                                        <input name="gender" type="text" class="form-control" id="voterGender"
                                               placeholder="Gender"
                                               readonly>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="voterAge">Age</label>
                                        <input name="age" type="text" class="form-control" id="voterAge"
                                               placeholder="Age"
                                               min="18" readonly>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="voterEpicNo">Relative Name</label>
                                        <input name="voterEpicNo" type="text" class="form-control" id="voterEpicNo"
                                               placeholder="Relative Name"
                                               readonly>
                                    </div>
                                </div>
                                <div class="form-row d-flex" style="width: max-content;margin: auto;">
                                    <div class="form-group col-md-3">
                                        <label for="voterDistrict">District</label>
                                        <input name="voterDistrict" type="text" class="form-control" id="voterDistrict"
                                               placeholder="District"
                                               readonly>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="voterConstituency">Constituency</label>
                                        <input name="voterConstituency" type="text" class="form-control"
                                               id="voterConstituency" placeholder="Constituency"
                                               readonly>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="voterPart">Part</label>
                                        <input name="voterPart" type="text" class="form-control" id="voterPart"
                                               placeholder="Voter Part"
                                               readonly>
                                    </div>
                                </div>
                                <div class="form-row d-flex" style="width: max-content;margin: auto;">
                                    <div class="form-group col-md-4">
                                        <label for="voterMobileNumber">Mobile Number</label>
                                        <input name="mobileNumber" type="number" class="form-control"
                                               id="voterMobileNumber"
                                               placeholder="Mobile Number" readonly>
                                    </div>
                                    <div class="form-group col-md-5">
                                        <label for="voterCategory">Current Category</label>
                                        <input id="voterCategory" name="voterCategory" type="text" class="form-control"
                                               placeholder="Current Category" readonly>
                                    </div>
                                </div>
                                <div class="form-row d-flex" style="width: max-content;margin: auto;">
                                    <div class="form-check form-switch d-flex flex-row">
                                            <input class="form-check-input" type="checkbox" id="voterCategorySwitch">
                                         <p> Enable AVCO </p>

                                    </div>
                                </div>

                            </form>
                            <div  class="form-row d-flex" style="width: max-content;margin: auto;">
                                <button class="btn btn-primary" onclick="changeCategory()">Change Category</button>
                            </div>
                        </div>
                    </div>


                </div>

            </div>
        </div>

    </div>
</div>

<div class="modal" id="popUp" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 id="popUpTitle" class="modal-title">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" onclick="window.location.href = '/admin'" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="popUpBody"></p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title d-flex .justify-content-center" id="myModalLabel" style="justify-content: center;" >Image preview</h4>
            </div>
            <div class="modal-body">
                <img src="" id="imagepreview" style="width: 100%; height: 100%;" >
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
</body>
</html>