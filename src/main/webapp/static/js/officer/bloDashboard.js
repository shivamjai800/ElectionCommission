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