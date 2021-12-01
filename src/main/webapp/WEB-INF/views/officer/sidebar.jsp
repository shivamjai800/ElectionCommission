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
    <style>
    </style>
    <script>
        $(".nav a").on("click", function(){
            $(".nav").find(".active").removeClass("active");
            $(this).addClass("active");
        });
    </script>
</head>
<body id="body-pd">
<div th:fragment="sidebar" class="l-navbar" id="nav-bar">
    <nav class="nav side-bar">
        <div class="nav_list">
            <div class="nav_link toggle_display"><i class="fas fa-bars" id="header-toggle"></i> <span class="nav_name">Postal Ballot Entry</span>  </div>
            <a th:href="'/admin'" class="nav_link"> <i class="fab fa-angellist"></i> <span class="nav_name">Admin Module</span> </a>
            <a th:href="'/reports'" class="nav_link"> <i class="fas fa-anchor"></i> <span class="nav_name">Reports</span> </a>
            <a th:href="'/dashboard'" class="nav_link"> <i class="fas fa-anchor"></i> <span class="nav_name">Dashboard</span> </a>
            <a th:href="'/voteEntry'" class="nav_link"> <i class="fas fa-anchor"></i> <span class="nav_name">Vote Entry</span> </a>
        </div>
    </nav>
</div>
<!--Container Main end-->
<script type="text/javascript" src="/js/officer/bloDashboard.js"/>
<!--    bootstrap scripts-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<!--local scripts-->
</body>
</html>