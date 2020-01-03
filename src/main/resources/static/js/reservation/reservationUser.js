function ReservationUser() {
}

ReservationUser.prototype = {
    name: "",
    telephone: "",
    email: "",
    isNameValid: false,
    isTelephoneValid: false,
    isEmailValid: false,
    checkResUserName: function (evt) {
        let userName = evt.target.value;
        this.name = userName;

        let isValid = (/^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/).test(userName);
        const warningMessage = evt.currentTarget.lastElementChild;
        ReservationUser.prototype.isNameValid = isValid;
        ReservationUser.prototype.toggleIsValid(isValid, warningMessage);

    },
    checkResUserTelephone: function (evt) {
        let userTelephone = evt.target.value;
        this.telephone = userTelephone;

        let isValid = (/^\d{2,3}-\d{3,4}-\d{4}$/).test(userTelephone);
        const warningMessage = evt.currentTarget.lastElementChild;
        ReservationUser.prototype.isTelephoneValid = isValid;
        ReservationUser.prototype.toggleIsValid(isValid, warningMessage);
    },
    checkResUserEmail: function (evt) {
        let userEmail = evt.target.value;
        this.email = userEmail;

        let isValid = (/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/).test(userEmail);
        const warningMessage = evt.currentTarget.lastElementChild;
        ReservationUser.prototype.isEmailValid = isValid;
        ReservationUser.prototype.toggleIsValid(isValid, warningMessage);
    },
    toggleIsValid: function (isValid, warningMsg) {
        warningMsg.style.color = "red";
        if (isValid && !warningMsg.classList.contains("warning_msg")) {
            warningMsg.classList.add("warning_msg");
        }
        if (!isValid && warningMsg.classList.contains("warning_msg")) {
            warningMsg.classList.remove("warning_msg");
        }
    }
};