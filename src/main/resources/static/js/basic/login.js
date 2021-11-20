function validateForm()
{
    let validate = valMobileNumber()
    if(!validate)
        document.loginForm.mobile.innerHTML="";
    return validate
}
function clearError()
{
    document.getElementById("showError").innerHTML=""
}
function valMobileNumber()
{
    let mobileNumber = document.loginForm.mobile.value;
    let errorMessage = ""
    if(mobileNumber=="")
    {
        errorMessage= "Please fill the mobile Number"
    }
    else if(isNaN(mobileNumber))
    {
        errorMessage= "Enter the proper mobile Number"
    }
    else if(!mobileNumber.match("/\d{10}"))
    {
        errorMessage = "Please enter the ten digit mobile Number"
    }

    if(errorMessage=="") return true;
    document.getElementById("showError").innerHTML=errorMessage
    return false
}