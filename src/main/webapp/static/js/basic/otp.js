function validateForm()
{
    let validate = valotp()
    if(!validate)
        document.loginForm.otp.innerHTML="";
    return validate
}
function clearError()
{
    document.getElementById("showError").innerHTML=""
}
function valotp()
{
    let otp = document.loginForm.otp.value;
    let errorMessage = ""
    if(otp=="")
    {
        errorMessage= "Please fill the otp Number"
    }
    else if(isNaN(otp))
    {
        errorMessage= "Enter the proper otp Number"
    }
    else if(!otp.match("/\d{6}"))
    {
        errorMessage = "Please enter the 6 digit otp Number"
    }

    if(errorMessage=="") return true;
    document.getElementById("showError").innerHTML=errorMessage
    return false
}