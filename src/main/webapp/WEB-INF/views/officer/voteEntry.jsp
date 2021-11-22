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
      <link rel="stylesheet" href="/css/officer/voteEntry.css">
      <title></title>
   </head>
   <style>
      *{
      border: 1px solid black;
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
         <div th:replace="officer/sidebar :: sidebar"></div>
         <div class="right-body" id="right-body">
            <nav class="navbar navbar-light bg-light">
               <span class="navbar-brand mb-0 h1">Navbar</span>
            </nav>
            <div class="card" style="width: 40vw; margin: auto; margin-top: 5vh;">
               <div class="card-body">
                  <div class="upper-body">
                     <form class="form-inline my-2 my-lg-0" style="display: flex">
                        <div class="form-row d-flex">
                           <div class="form-group col-md-3" >
                              <select class="form-select" aria-label="Default select example">
                                 <option selected>Choose</option>
                                 <option value="AVSC">AVSC</option>
                                 <option value="AVPD">AVPD</option>
                                 <option value="AVCO">AVCO</option>
                              </select>
                           </div>
                           <div class="form-group col-md-5">
                              <input type="search" placeholder="Search" aria-label="Search">
                           </div>
                           <button class="btn btn-outline-success" type="submit">Search</button>
                        </div>
                     </form>
                  </div>
                  <div class="lower-body" >
                     <form style="display:flex; flex-direction:column;">
                        <div class="form-row d-flex">
                           <div class="form-group col-md-5" >
                              <label for="firstName">First Name</label>
                              <input type="text" class="form-control" id="firstName" placeholder="First Name">
                           </div>
                           <div class="form-group col-md-5">
                              <label for="lastName">Last Name</label>
                              <input type="text" class="form-control" id="lastName" placeholder="Last Name">
                           </div>
                        </div>
                        <div class="form-row d-flex">
                           <div class="form-group col-md-3">
                              <label for="gender">Gender</label>
                              <input type="text" class="form-control" id="gender" placeholder="Gender">
                           </div>
                           <div class="form-group col-md-3">
                              <label for="age">Age</label>
                              <input type="number" class="form-control" id="age" placeholder="Password" min="18">
                           </div>
                           <div class="form-group col-md-3">
                              <label for="partSlNo">Part SL No</label>
                              <input type="number" class="form-control" id="partSlNo" placeholder="part serial no">
                           </div>
                        </div>
                        <div class="form-row d-flex">
                           <div class="form-group col-md-4">
                              <label for="mobilePhone">Mobile Number</label>
                              <input type="number" class="form-control" id="mobilePhone" placeholder="Mobile Number">
                           </div>
                        </div>
                        <img src="" alt="No Image " >
                        <button type="submit" class="btn btn-primary" onclick="formFilled()">On Field Verification</button>
                     </form>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="pop-up" style="display:none;">
         <div class="top">
            <h3> Physically met the blo </h3>
         </div>
         <div class="bottom d-flex">
            <button type="button" class="btn btn-danger">Close</button>
            <button type="button" class="btn btn-warning">No</button>
            <button type="button" class="btn btn-success">Yes</button>
         </div>
      </div>
      <div class="pop-up" style="display:none;">
         <div class="top">
            <h3> Has form be delivered to </h3>
         </div>
         <div class="bottom d-flex">
            <button type="button" class="btn btn-danger">Close</button>
            <button type="button" class="btn btn-warning">No</button>
            <button type="button" class="btn btn-success">Yes</button>
         </div>
      </div>
      <div class="remarks" style="display:none;">
      <div>
         <h5> Remarks </h5>
         <p> Please Submit the reason against your action</p>
      </div>
      <div >
         <textarea ></textarea>
      </div>
      <div >
         <button type="button" class="btn btn-danger">Close</button>
         <button type="button" class="btn btn-success">Yes</button>
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
