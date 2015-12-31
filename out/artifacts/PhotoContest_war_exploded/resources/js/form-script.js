
function validateForm() {
    var firstName = document.forms["contactForm"]["firstName"].value;
    var lastName = document.forms["contactForm"]["lastName"].value;
    var email =  document.forms["contactForm"]["email"].value;
    var password = document.forms["contactForm"]["password"].value;
    var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;

    if (firstName == null || lastName == null || firstName == "" || lastName == "" ) {
        document.getElementById("formErrorMessage").innerHTML = "Numele nu a fost scris corect.";
        document.getElementById("formErrorMessageContainer").style.visibility = "visible";
        document.forms["contactForm"]["firstName"].focus();
        return false;
    }
    if(email == null || email == "" || !pattern.test(email) ) {
        document.getElementById("formErrorMessage").innerHTML = "Adresa email nu este valida.";
        document.getElementById("formErrorMessageContainer").style.visibility = "visible";
        document.forms["contactForm"]["email"].focus();
        return false;
    }
    if(password == null || password =="" || password.length < 8) {
        document.getElementById("formErrorMessage").innerHTML = "Parola nu este valida.";
        document.getElementById("formErrorMessageContainer").style.visibility = "visible";
        document.forms["contactForm"]["email"].focus();
        return false;
    }

    return true;
}
