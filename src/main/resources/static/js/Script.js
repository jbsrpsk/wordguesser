//Selecting DOM elements
var usernameInput_el = document.getElementById("UsernameInput");
var emailInput_el = document.getElementById("exampleInputEmail1");
var passwordInput_el = document.getElementById("exampleInputPassword1");
var userError_el = document.getElementById("userError");
var emailError_el = document.getElementById("emailError");
var passwordError_el = document.getElementById("passwordError");
var submitBtn_el = document.getElementById("submitBtn");
var form_el = document.getElementById("form");

//Form entities Validation
function usernameValidation() {
    if (usernameInput_el.value === "") {
        userError_el.textContent = "Please don't leave the fields empty";
    } else if (usernameInput_el.value.length < 5) {
        userError_el.textContent =
            "This field should contain atleast 5 characters.";
    } else {
        userError_el.textContent = "";
    }
}
function emailValidation() {
    if (emailInput_el.value === "") {
        emailError_el.textContent = "Please don't leave the fields empty";
    } else if (emailInput_el.value.length < 5) {
        emailError_el.textContent =
            "This field should contain atleast 5 characters.";
    } else {
        emailError_el.textContent = "";
    }
}
function passwordValidation() {
    const password = passwordInput_el.value.trim();
    const capitalRegex = /[A-Z]/;
    const numberRegex = /[0-9]/;

    if (passwordInput_el.value === "") {
        passwordError_el.textContent = "Please don't leave the fields empty";
    } else if (password.length < 5) {
        passwordError_el.textContent =
            "Password should contain at least 5 characters.";
    } else if (!capitalRegex.test(password)) {
        passwordError_el.textContent =
            "Password should contain at least one capital letter.";
    } else if (!numberRegex.test(password)) {
        passwordError_el.textContent =
            "Password should contain at least one number.";
    } else {
        passwordError_el.textContent = "";
    }
}
//Form Validation
function formValidation() {
    usernameValidation();
    emailValidation();
    passwordValidation();

    if (
        userError_el.textContent ||
        emailError_el.textContent ||
        passwordError_el.textContent
    ) {
        console.log("Not passed");
        return false;
    } else {
        console.log("Passed");
        return true;
    }
}
//submit form
function submitForm() {
    var isValid = formValidation();
    if (isValid) {
        console.log("submitted");
        alert("SignIn successfull")
        form_el.submit();
    } else {
        console.log("not submitted");
    }
}

//Event listener for submit button
submitBtn_el.addEventListener("click", submitForm);
