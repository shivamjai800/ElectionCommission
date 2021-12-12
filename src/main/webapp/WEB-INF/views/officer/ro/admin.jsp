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
        let bloId = document.getElementById("bloId"+id).innerText;
        let phone = document.getElementById("phone"+id).innerText;
        let url = "/dashboard/blo/" + bloId;
        let data = {
            "mobile_number": phone
        }
            let success = function (data, textStatus, xhr) {
                console.log("data = ",data,"text Status = ",textStatus,"xhr = ",xhr)

                $("#popUpTitle").text(textStatus)
                $("#popUpBody").text( data.data)
                $("#popUp").modal('show')
                // location.reload();
            }
            let failure =  function (xhr, textStatus,errorThrown)
            {
                console.log("errorThrown = ",errorThrown,"text Status = ",textStatus,"xhr = ",xhr)
                $("#popUpTitle").text(textStatus)
                $("#popUpBody").text(xhr.responseJSON.apiError.message)
                $("#popUp").modal('show')
            }
            ajaxFunction("PUT",url,data,'application/json',success,failure)

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
        <div class="card text-center mx-1 card-3d" th:if="${bloList != null}">
            <div class="card-header">
                BLO lists
            </div>

            <table class="table card-body table-striped">
                <thead class="card-title">
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

    </div>
</div>

<div class="modal" id="popUp" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 id="popUpTitle" class="modal-title">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal"onclick="location.reload()" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="popUpBody"> </p>
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