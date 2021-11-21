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