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
    <link rel="stylesheet" href="/css/officer/bloDashboard.css">
    <title></title>
</head>
<style>
    /**{*/
    /*    border: 1px solid black;*/
    /*}*/
    #selectPart{
        width: 15vw;
        height: 6vh;
    }
    #voterType{
        width: 10vw;
        height: 6vh;
    }
    #voterEligiblity{
        width: 12vw;
        height: 6vh;
    }
    #physicallyMet{
        width: 12vw;
        height: 6vh;
    }
    #voterNotFound{
        width: 15vw;
        height: 6vh;
    }
    #formDelivered{
        width: 15vw;
        height: 6vh;
    }
    #formNotDelivered{
        width: 17vw;
        height: 6vh;
    }
    #formCollected{
        width: 15vw;
        height: 6vh;
    }
    #formNotCollected{
        width: 19vw;
        height: 6vh;
    }
    #voteCasted{
        width: 15vw;
        height: 6vh;
    }
    #locked{
        width: 10vw;
        height: 6vh;
    }
    #reportSubmit{
        margin-top: 2vh;
    }
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
    .form-group select{
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
                    nav.classList.toggle('show')
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
    <div th:replace="officer/bloDashboard :: sidebar"></div>
    <div class="right-body" id="right-body">
        <nav class="navbar navbar-light bg-light">
            <span class="navbar-brand mb-0 h1">Navbar</span>
        </nav>
        <div>
            <form>
                <div class="col-lg mx-3 mt-4">
                    <div class="card ">
                        <div class="card-body ">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="selectPart">Select Part</label>
                                    <select class="custom-select custom-select-sm" id="selectPart">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class=" col-lg mx-3 mt-5 ">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-row d-flex">
                                <div class="form-group col-md-3">
                                    <label for="voterType">Voter Type</label>
                                    <select class="custom-select" id="voterType">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="voterEligiblity">Voter  Eligiblity</label>
                                    <select class="custom-select" id="voterEligiblity">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="physicallyMet">PhysicallyMet</label>
                                    <select class="custom-select" id="physicallyMet">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="voterNotFound">Voter Not Found</label>
                                    <select class="custom-select" id="voterNotFound">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="formDelivered">Form Delivered</label>
                                    <select class="custom-select" id="formDelivered">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row d-flex">
                                <div class="form-group col-md-3">
                                    <label for="formNotDelivered">Form Not Delivered</label>
                                    <select class="custom-select" id="formNotDelivered">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="formCollected">Form Collected</label>
                                    <select class="custom-select" id="formCollected">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="formNotCollected">Form Not Collected</label>
                                    <select class="custom-select" id="formNotCollected">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="voteCasted">Vote Casted</label>
                                    <select class="custom-select" id="voteCasted">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="locked">Locked</label>
                                    <select class="custom-select" id="locked">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
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