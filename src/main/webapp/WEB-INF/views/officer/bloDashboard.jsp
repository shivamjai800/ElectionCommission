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
    <title></title>
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap");
        /** { border: 1px solid black; }*/
        :root {
            --header-height: 3rem;
            --nav-width: 68px;
            --first-color-light: #8B6969;
            --white-color: #332421;
            --body-font: 'Nunito', sans-serif;
            --normal-font-size: 1rem;
            --z-fixed: 100
        }
        .l-navbar {
            position: fixed;
            left: -30%;
            width: var(--nav-width);
            height: 100vh;
            background-color: #EEE9E9;
            padding: .5rem 1rem 0 0;
            transition: .5s;
            z-index: var(--z-fixed)
        }

        .nav {
            height: 100%;
            display: flex;
            flex-direction: column;
            /*justify-content: space-between;*/
            overflow: hidden
        }

        .nav_logo,
        .nav_link {
            display: grid;
            grid-template-columns: max-content max-content;
            align-items: center;
            column-gap: 1rem;
            padding: .5rem 0 .5rem 1.5rem
        }

        .nav_logo {
            margin-bottom: 2rem
        }

        .nav_logo-icon {
            font-size: 1.25rem;
            color: var(--white-color)
        }

        .nav_logo-name {
            color: var(--white-color);
            font-weight: 700
        }

        .nav_link {
            position: relative;
            color: var(--first-color-light);
            margin-bottom: 1.5rem;
            transition: .3s
        }

        .nav_link:hover {
            color: var(--white-color)
        }

        .nav_icon {
            font-size: 1.25rem
        }

        .show {
            left: 0
        }

        .active {
            color: var(--white-color)
        }

        .active::before {
            content: '';
            position: absolute;
            left: 0;
            width: 2px;
            height: 32px;
            background-color: var(--white-color)
        }

        @media screen and (min-width: 768px) {


            .l-navbar {
                left: 0;
                padding: 1rem 1rem 0 0
            }

            .show {
                width: calc(var(--nav-width) + 156px)
            }
        }

    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function(event) {

            const showNavbar = (toggleId, navId, bodyId) =>{
                const toggle = document.getElementById(toggleId),
                    nav = document.getElementById(navId)

                if(toggle && nav){
                    toggle.addEventListener('click', ()=>{
                        nav.classList.toggle('show')
                        toggle.classList.toggle('bx-x')
                    })
                }
            }

            showNavbar('header-toggle','nav-bar')

            const linkColor = document.querySelectorAll('.nav_link')

            function colorLink(){
                if(linkColor){
                    linkColor.forEach(l=> l.classList.remove('active'))
                    this.classList.add('active')
                }
            }
            linkColor.forEach(l=> l.addEventListener('click', colorLink))

        });
    </script>
</head>
<body id="body-pd">

<div class="l-navbar" id="nav-bar">
    <nav class="nav">
            <div class="nav_list">
                <div class="nav_link"><i class="fas fa-bars" id="header-toggle"></i> <span class="nav_name">Postal Ballot Entry</span>  </div>
                <a href="#" class="nav_link active"> <i class="fab fa-angellist"></i> <span class="nav_name">Admin Module</span> </a>
                <a href="#" class="nav_link"> <i class="fas fa-anchor"></i> <span class="nav_name">Reports</span> </a>
                <a href="#" class="nav_link"> <i class="fas fa-anchor"></i> <span class="nav_name">Dashboard</span> </a>
                <a href="#" class="nav_link"> <i class="fas fa-anchor"></i> <span class="nav_name">Vote Entry</span> </a>
            </div>
    </nav>
</div>
<!--Container Main end-->
<!--    bootstrap scripts-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<!--local scripts-->
<script type="text/javascript" src="/js/basic/login.js"/>
</body>