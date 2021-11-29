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
    <link rel="stylesheet" href="/css/officer/dashboard.css">
    <title></title>
</head>
<style>
    .form-group{
        display: flex;
        flex-direction: column;
        width: fit-content;
        margin: .5vw .5vw .5vh .5vh;
    }
    .form-group label{
        top: 1vh;
        left: .75vw;
        position: relative;
        background: white;
        width: fit-content;
    }
    #selectPart{
        width: 15vw;
        height: 6vh;
    }
    #graph{
        width: 60%;
        height: 100%
        color: black;
    }
    #reportDownload
    {
        width: 40%;
        height: 50vh;
        color: blue;
    }
    #reportDownload .card .card-body button:nth-child(1), #reportDownload .card .card-body button:nth-child(2){
        height: 12vh;
    }
    #reportDownload .card .card-body button:nth-child(3){
        height: 9vh;
        width: 80%;
    }
    #reportDownload .card .card-body button:nth-child(4), #reportDownload .card .card-body button:nth-child(5){
        height: 6vh;
        margin-left: 50%;
        width: 50%;
    }
    .nav_cyan{
        background-color:#20B2AA;
        box-shadow: 0 1px 10px slategrey;
    }
    /**{
        border: 1px solid black;
    }*/
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
</script>
<body >
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light nav_cyan">
            <span class="navbar-brand mb-0 h1">Navbar</span>
        </nav>
        <div class="col-lg mx-3 mt-4">
            <div class="card ">
                <div class="card-body ">
                    <form>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <span th:if="${partNames != null}">
                                    <label for="selectPart1">Select Part</label>
                                    <select class="custom-select custom-select-sm" id="selectPart1">
                                        <option selected>Open this select menu</option>
                                        <option th:id="'option' + ${iStat.count}" th:each="partName, iStat: ${partNames}">
                                            <span th:text="${partName}"></span>
                                        </option>
                                    </select>
                                </span>
                                <span th:if="${partNames == null}">
                                    <label for="selectPart2">Select Part</label>
                                    <select class="custom-select custom-select-sm" id="selectPart2">
                                        <option selected>Open this select menu</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg mx-3 mt-4 d-flex card-3d">
            <div id="graph">
                <div class="card">
                    <div class="card-body ">
                    </div>
                </div>
            </div>
            <div id="reportDownload">
                <div class="card">
                    <div class="card-header">
                        Download Reports
                    </div>
                    <div class="card-body d-flex flex-column">
                        <button type="button" class="btn btn-light active mx-1 my-1">Register For Acknowledgement <br/> (AVSC/AVPD)</button>
                        <button type="button" class="btn btn-secondary mx-1 my-1">Register For Acknowledgement <br/> (AVCO)</button>
                        <button type="button" class="btn btn-primary active mx-1 my-1 ">Receipt Of Form 12D (For BLO) </button>
                        <button type="button" class="btn btn-primary mx-1 my-1 ">Annexure 1</button>
                        <button type="button" class="btn btn-primary mx-1 my-1 ">Annexure 2</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Container Main end-->
<!--    bootstrap scripts-->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!--local scripts-->
<script type="text/javascript" src="/js/officer/bloDashboard.js"/>
</body>
</html>