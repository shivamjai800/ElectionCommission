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
    <link rel="stylesheet" href="/css/officer/dashboard.css">
        <title>Postal Ballot Management System</title>

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
        float:right;
        flex-direction: row;
        display: inline-flex;
    }
    .nav-link {
        color: black;
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

    var graphData;
    function ajaxFunction(type, url, data, contentType, success, failure) {
        if (data != null) {
            $.ajax({
                type: type,
                async: false,
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
    let success = function (data, textStatus, xhr) {
        console.log("data = ", data, "text Status = ", textStatus, "xhr = ", xhr)

        $("#popUpTitle").text(textStatus)
        $("#popUpBody").text(data.data)
        $("#popUp").modal('show')
        console.log(data.data)
        graphData = data.data;
    }
    let failure = function (xhr, textStatus, errorThrown) {
        console.log("errorThrown = ", errorThrown, "text Status = ", textStatus, "xhr = ", xhr)
        $("#popUpTitle").text(textStatus)
        $("#popUpBody").text(xhr.responseJSON.apiError.message)
        $("#popUp").modal('show')
    }
</script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages': ['bar']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        let ajaxBody = {"part_id": [[${partId}]]}
        ajaxFunction("post","/dashboard/chart",ajaxBody ,'application/json',success,failure)

        var gpData = google.visualization.arrayToDataTable([
            ['Category', 'Total Elector (Count)', 'Field Verified (Count)', 'Form 12D Delivered (Count)', 'Filled-in Form 12D Received (Count)'],
            ['AVSC', graphData["AVSC"][0], graphData["AVSC"][1], graphData["AVSC"][2], graphData["AVSC"][3]],
            ['AVPD', graphData["AVPD"][0], graphData["AVPD"][1], graphData["AVPD"][2], graphData["AVPD"][3]],
            ['AVCO', graphData["AVCO"][0], graphData["AVCO"][1], graphData["AVCO"][2], graphData["AVCO"][3]],
            ['AVGE', graphData["AVGE"][0], graphData["AVGE"][1], graphData["AVGE"][2], graphData["AVGE"][3]],
            ['AVEW', graphData["AVEW"][0], graphData["AVEW"][1], graphData["AVEW"][2], graphData["AVEW"][3]]
        ]);

        var options = {
            chart: {
                // title: 'Category-wise Report',
                // subtitle: 'Total count, Field verified count, Form 12D Delivered count, and Filled-in Form 12D Received count',
            }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(gpData, google.charts.Bar.convertOptions(options));
    }
</script>
<body>
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light nav_cyan">
            <a class="navbar-brand mb-0 h1">Dashboard (Test Version)</a>
            <div class="nav-right">
                <a class="nav-link">
                    <i class="fas fa-user-circle"></i> <span
                        th:text="'Welcome ' +  ${userName} + ' (' +  ${role} + ')'"></span>
                </a>
                <a class="nav-link" href="/logoutt"> <i class="fas fa-sign-out-alt"></i>Logout</a>
            </div>
        </nav>
        <div class="col-lg mx-3 mt-4">
            <div class="card ">
                <div class="card-body ">
                    <form>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <span th:if="${partName != null}">
                                    <label for="selectPart1">Select Part</label>
                                    <select class="custom-select custom-select-sm" id="selectPart1">
                                        <option selected disabled th:value="${partId}"><span
                                                    th:text="${partName}"></span></option>
                                    </select>
                                </span>
                                <span th:if="${partName == null}">
                                    <label for="selectPart2">Select Part</label>
                                    <select class="custom-select custom-select-sm" id="selectPart2">
                                        <option selected>Open this select menu</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card-body">
                    <button type="button" class="btn btn-primary mx-1 my-1 card-3d">Download Voter Table</button>
                </div>
            </div>
        </div>
        <div class="row mx-3 mt-4 d-flex">
            <div id="graph">
                <div class="col-lg card card-3d">
                    <div class="card-body ">
                        <div id="columnchart_material" style="width: auto; height: 350px;"></div>
                    </div>
                </div>
            </div>
            <div id="reportDownload">
                <div class="col-lg card card-3d">
                    <div class="card-header">
                        Download Reports
                    </div>
                    <div class="card-body d-flex flex-column">
                        <button type="button" class="btn btn-light mx-1 my-1 card-3d">Register For Acknowledgement <br/>
                            (AVSC/AVPD)
                        </button>
                        <button type="button" class="btn btn-secondary mx-1 my-1 card-3d">Register For Acknowledgement
                            <br/> (AVCO)
                        </button>
                        <button type="button" class="btn btn-primary mx-1 my-1 card-3d">Receipt Of Form 12D (For BLO)
                        </button>
                        <button type="button" class="btn btn-primary mx-1 my-1 card-3d">Annexure 1</button>
                        <button type="button" class="btn btn-primary mx-1 my-1 card-3d">Annexure 2</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Container Main end-->
<!--    bootstrap scripts-->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
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