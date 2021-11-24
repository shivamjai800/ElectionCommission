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
      /**{*/
      /*border: 1px solid black;*/
      /*}*/
      /* *{
      border: 1px solid black;
      } */
      .upper-body{
      display: flex;
      padding: 1vw;
      }
      .upper-body .form-group input{
      width: 20vw;
      height: 3.5vh;
      }
      .upper-body button{
      width: fit-content;
      height: 3.5vh;
      }
      .upper-body select{
      width: 10vw;
      height: 3.5vh;
      }
      .form-group label{
      top: 0.75vh;
      left: .75vw;
      position: relative;
      background: white;
      width: fit-content;
      font-size: 0.75rem;
      }
      .form-group img{
      width: 25vw;
      height: 10vh;
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
            <nav class="navbar navbar-light bg-light">
               <span class="navbar-brand mb-0 h1">Navbar</span>
            </nav>
            <div class="card" style="width: 40vw; margin: auto; margin-top: 5vh;">
               <div class="card-body">
                  <div class="upper-body">
                     <form class="form-inline my-2 my-lg-0" style="display: flex">
                        <div class="form-row d-flex">
                           <div class="form-group mx-2" >
                              <select class="form-select" aria-label="Default select example">
                                 <option selected>Choose</option>
                                 <option value="AVSC">AVSC</option>
                                 <option value="AVPD">AVPD</option>
                                 <option value="AVCO">AVCO</option>
                              </select>
                           </div>
                           <div class="form-group col-md">
                              <input type="search" placeholder="Epic " aria-label="Search">
                           </div>
                           <button class="btn btn-outline-success" type="submit"><i class="fa fa-search"></i></button>
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
                        <div class="form-row d-flex">
                           <div class="form-group">
                              <img src="" alt="No Image " >
                           </div>
                        </div>
                        <button type="submit" class="btn btn-primary" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#remarks" onclick="formFilled()">On Field Verification</button>
                     </form>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Button trigger modal -->
      <!-- Modal -->
      <div class="modal fade" id="physicallyMet" tabindex="-1" role="dialog" aria-labelledby="physicallyMetTitle" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-body">
                  Has form be delivered to
               </div>
               <div class="modal-footer d-flex">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-warning">No</button>
                  <button type="button" class="btn btn-success">Yes</button>
               </div>
            </div>
         </div>
      </div>
      <div class="modal fade" id="form12D" tabindex="-1" role="dialog" aria-labelledby="form12DTitle" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-body">
                  Has filled in form 12D delivered from
               </div>
               <div class="modal-footer d-flex">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-warning">No</button>
                  <button type="button" class="btn btn-success">Yes</button>
               </div>
            </div>
         </div>
      </div>
      <div class="modal fade" id="remarks" tabindex="-1" role="dialog" aria-labelledby="remarks" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header d-flex flex-column">
                  <h5 class="modal-title">Remarks</h5>
                  <p class="mt-3 mr-3"> Please submit the reason(s) against your action.
               </div>
               <form>
                  <div class="modal-body">
                     <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Remarks</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                     </div>
                  </div>
                  <div class="modal-footer d-flex">
                     <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                     <button type="button" class="btn btn-success">Submit</button>
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
