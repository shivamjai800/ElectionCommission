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
        <title>Postal Ballot Management System</title>

    <link rel="icon" href="/images/otherImages/launch_image.png"/>
</head>
<style>
    body{
        background-color: #F8F8F8;
    }
    .nav_cyan {
        background-color: #20B2AA;
        box-shadow: 0 1px 10px slategrey;
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
</script>
<body >
<div class="outer-class">
    <div th:replace="officer/sidebar :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light nav_cyan">
            <span class="navbar-brand mb-0 h1">Reports (Test Version)</span>
        </nav>
        <div>
            <form>
                <div class="col-lg mx-3 mt-4">
                    <div class="card ">
                        <div class="card-body ">
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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!--local scripts-->
<script type="text/javascript" src="/js/officer/bloDashboard.js"/>
</body>
</html>