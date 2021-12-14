<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!--    local css script-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" href="/css/officer/sidebar.css">
    <link rel="stylesheet" href="/css/officer/reports.css">
    <title></title>
</head>
<style>
    body{
        background-color: #F8F8F8;
    }
    .form-group {
        display: flex;
        flex-direction: row;
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
    .nav_cyan {
        background-color: #20B2AA;
        box-shadow: 0 1px 10px slategrey;
    }
    .nav-right {
        float:right;
        flex-direction: row;
        display: inline-flex;
    }
    .nav-link {
        color: black;
    }
</style>
<script>
    document.addEventListener("DOMContentLoaded", function(event) {

        const showNavbar = ( toggleId,navId, rightBodyId) =>{
            const toggle = document.getElementById(toggleId),
                nav = document.getElementById(navId),
                rightBody = document.getElementById(rightBodyId)

            if(toggle && nav ){
                toggle.addEventListener('click', ()=>{
                    nav.classList.toggle('side-bar-show')
                    toggle.classList.toggle('bx-x')
                    rightBody.classList.toggle('right-body-toggle')
                })
            }
        }

        showNavbar('header-toggle','nav-bar','right-body')

        const linkColor = document.querySelectorAll('.nav_link')

        function colorLink(){
            if(linkColor){
                linkColor.forEach(l=> l.classList.remove('active'))
                this.classList.add('active')
            }
        }
        linkColor.forEach(l=> l.addEventListener('click', colorLink))

    });

    function formFilled()
    {

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

    window.onload = function () {
        document.getElementById("selectDistrict1").addEventListener('change', (event) => {
            let url = "/test/constituencies/" + event.target.value
            let success = function (data) {
                console.log("here")
                $("#selectConstituency2").empty()
                $("#selectConstituency2").append(new Option("All Constituencies", 0))
                $("#selectPart2").empty()
                $("#selectPart2").append(new Option("All Parts", 0))
                for (let i = 0; i < data.length; i++) {
                    let c = data[i]
                    $("#selectConstituency2").append(new Option(c.constituency_name, c.constituency_id))
                }
            }
            let failure = function (data) {
                console.log(data)
            }
            ajaxFunction("post", url, null, 'application/json', success, failure)
        })
        document.getElementById("selectConstituency2").addEventListener('change', (event) => {
            let url = "/test/parts/" + event.target.value
            let success = function (data) {
                $("#selectPart2").empty()
                $("#selectPart2").append(new Option("All Parts", 0))
                for (let i = 0; i < data.length; i++) {
                    let p = data[i]
                    $("#selectPart2").append(new Option(p.part_name, p.part_id))
                }
            }
            let failure = function (data) {
                console.log(data)
            }
            ajaxFunction("post", url, null, 'application/json', success, failure)
        })
    }
</script>
<body >
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light nav_cyan">
            <a class="navbar-brand mb-0 h1">Dashboard (Test Version)</a>
            <div class="nav-right">
                <a class="nav-link" href="#"><i class="fas fa-lock"></i>Lock</a>
                <a class="nav-link" href="#"><i class="fas fa-unlock-alt"></i>Unlock</a>
                <a class="nav-link" href="#"><i class="fas fa-check-circle"></i>Finalize</a>
                <a class="nav-link">
                    <i class="fas fa-user-circle"></i> <span
                        th:text="'Welcome ' +  ${userName} + ' (' +  ${role} + ')'"></span>
                </a>
                <a class="nav-link" href="/logoutt"> <i class="fas fa-sign-out-alt"></i>Logout</a>
            </div>
        </nav>
        <div>
            <form>
                <div class="col-lg mx-3 mt-4">
                    <div class="card ">
                        <div class="card-body ">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <span th:if="${districts != null}">
                                    <label for="selectDistrict1">Select District</label>
                                    <select class="custom-select custom-select-sm" id="selectDistrict1">
                                        <option selected value=0>All Districts</option>
                                        <option th:each="district, iStat: ${districts}"
                                                th:value="${district.districtId}">
                                            <span th:text="${district.districtName}"></span>
                                        </option>
                                    </select>
                                </span>
                                    <span th:if="${districts == null}">
                                    <label for="selectDistrict2">Select District</label>
                                    <select class="custom-select custom-select-sm" id="selectDistrict2">
                                        <option selected disabled hidden style="color:grey">Select District</option>
                                    </select>
                                </span>

                                    <span th:if="${constituencies != null}">
                                    <label for="selectConstituency1">Select Constituency</label>
                                    <select class="custom-select custom-select-sm" id="selectConstituency1">
                                        <option selected value=0>All Constituencies</option>
                                        <option th:each="constituency, iStat: ${constituencies}"
                                                th:value="${constituency.constituencyId}">
                                            <span th:text="${constituency.constituencyName}"></span>
                                        </option>
                                    </select>
                                </span>
                                    <span th:if="${constituencies == null}">
                                    <label for="selectConstituency2">Select Constituency</label>
                                    <select class="custom-select custom-select-sm" id="selectConstituency2">
                                        <option selected disabled hidden style="color:grey">Select Constituency</option>
                                    </select>
                                </span>

                                    <span th:if="${partNames != null}">
                                    <label for="selectPart1">Select Part</label>
                                    <select class="custom-select custom-select-sm" id="selectPart1">
                                        <option selected value="0">All Parts</option>
                                        <option th:id="'option' + ${iStat.count}"
                                                th:each="partName, iStat: ${partNames}"
                                                th:value="${iStat.count}">
                                            <span th:text="${partName}"></span>
                                        </option>
                                    </select>
                                </span>
                                    <span th:if="${partNames == null}">
                                    <label for="selectPart2">Select Part</label>
                                    <select class="custom-select custom-select-sm" id="selectPart2">
                                        <option selected disabled hidden style="color:grey">Select Part</option>
                                    </select>
                                </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class=" col-lg mx-3 mt-5 card-3d">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-row d-flex">
                                <div class="form-group col-md-3">
                                    <label for="voterType">Voter Type</label>
                                    <select class="custom-select" id="voterType">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">AVSC</option>
                                        <option value="2">AVPD</option>
                                        <option value="3">AVCO</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="voterEligiblity">Voter Eligiblity</label>
                                    <select class="custom-select" id="voterEligiblity">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="physicallyMet">Physically Met</label>
                                    <select class="custom-select" id="physicallyMet">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="voterNotFound">Voter Not Found</label>
                                    <select class="custom-select" id="voterNotFound">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="formDelivered">Form Delivered</label>
                                    <select class="custom-select" id="formDelivered">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row d-flex">
                                <div class="form-group col-md-3">
                                    <label for="formNotDelivered">Form Not Delivered</label>
                                    <select class="custom-select" id="formNotDelivered">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="formCollected">Form Collected</label>
                                    <select class="custom-select" id="formCollected">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="formNotCollected">Form Not Collected</label>
                                    <select class="custom-select" id="formNotCollected">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="voteCasted">Vote Casted</label>
                                    <select class="custom-select" id="voteCasted">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="locked">Locked</label>
                                    <select class="custom-select" id="locked">
                                        <option selected disabled hidden>Open this select menu</option>
                                        <option value="1">Yes</option>
                                        <option value="2">No</option>
                                    </select>
                                </div>
                            </div>
                            <button id="reportSubmit" type="submit" class="btn btn-primary">Show Report</button>
                        </div>
                    </div>
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