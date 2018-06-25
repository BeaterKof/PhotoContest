
function validateForm() {
    var newFirstName = document.forms["accountForm"]["newFirstName"].value;
    var newLastName = document.forms["accountForm"]["newLastName"].value;
    var newEmail =  document.forms["accountForm"]["newEmail"].value;
    var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;

    if (newFirstName == null || newLastName == null || newFirstName == "" || newLastName == "" ) {
        document.getElementById("formErrorMessage").innerHTML = "Numele nu a fost scris corect.";
        document.getElementById("formErrorMessageContainer").style.visibility = "visible";
        document.forms["accountForm"]["newFirstName"].focus();
        return false;
    }
    if(newEmail == null || newEmail == "" || !pattern.test(newEmail) ) {
        document.getElementById("formErrorMessage").innerHTML = "Adresa email nu este valida.";
        document.getElementById("formErrorMessageContainer").style.visibility = "visible";
        document.forms["accountForm"]["newEmail"].focus();
        return false;
    }
    return true;
}

function displayForm(){
    document.getElementById("accountPhotos").style.display = "none";
    document.getElementById("accountPassword").style.display = "none";
    document.getElementById("accountForm").style.display = "block";
    document.getElementById("deletionPrompt").style.display = "none";
    document.getElementById("deletionPrompt").style.visibility = "hidden";
}

function displayPassword(){
    document.getElementById("accountPhotos").style.display = "none";
    document.getElementById("accountPassword").style.display = "block";
    document.getElementById("accountForm").style.display = "none";
    document.getElementById("deletionPrompt").style.display = "none";
    document.getElementById("deletionPrompt").style.visibility = "hidden";
}

function displayPhotos(){
    document.getElementById("accountPhotos").style.display = "block";
    document.getElementById("accountPassword").style.display = "none";
    document.getElementById("accountForm").style.display = "none";
    document.getElementById("deletionPrompt").style.display = "none";
    document.getElementById("deletionPrompt").style.visibility = "hidden";
}

function displayDeletionPrompt(){
    document.getElementById("deletionPrompt").style.display = "block";
    document.getElementById("deletionPrompt").style.visibility = "visible";
    document.getElementById("accountPhotos").style.display = "none";
    document.getElementById("accountPassword").style.display = "none";
    document.getElementById("accountForm").style.display = "none";
}

function closeDeletionPrompt(){
    document.getElementById("deletionPrompt").style.display = "none";
    document.getElementById("deletionPrompt").style.visibility = "hidden";
    document.getElementById("accountPhotos").style.display = "none";
    document.getElementById("accountPassword").style.display = "none";
    document.getElementById("accountForm").style.display = "none";
}

function validatePassword(){
    var oldPass = document.forms["passwordForm"]["oldPass"].value;
    var newPass = document.forms["passwordForm"]["newPass"].value;

    if(oldPass == null || oldPass == "" || oldPass.length < 7)
    {
        document.getElementById("passErrorMessage").innerHTML = "Parola veche nu este valida.";
        document.getElementById("passErrorMessageContainer").style.visibility = "visible";
        return false;
    }

    if( newPass == null || newPass == "" || newPass.length < 7 ){
        document.getElementById("passErrorMessage").innerHTML = "Parola noua nu este valida.";
        document.getElementById("passErrorMessageContainer").style.visibility = "visible";
        return false;
    }

    if(oldPass == newPass){
        document.getElementById("passErrorMessage").innerHTML = "Parolele sunt la fel. Introduceti o parola diferita";
        document.getElementById("passErrorMessageContainer").style.visibility = "visible";
        return false;
    }
    return true;
}


/* File upload */

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#thumbnail-image').attr('src', e.target.result);
        }

        document.getElementById("thumbnail-image").style.display = "block";
        reader.readAsDataURL(input.files[0]);
    }
}

var counter = 1;
function displayUploadForm() {
    counter++;
    if(counter%2 == 0){
        document.getElementById("fileInputForm").style.display = "block";
    } else {
        document.getElementById("fileInputForm").style.display = "none";
    }
}