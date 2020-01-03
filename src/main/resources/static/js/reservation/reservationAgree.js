function ReservationAgree() {

}

ReservationAgree.prototype = {
    toggleAgreementContent: function (evt) {
        const lookButton = evt.currentTarget.parentElement;
        if (lookButton.classList.contains("open")) {
            lookButton.classList.remove("open");
        } else {
            lookButton.classList.add("open");
        }
    },
    isValidContent: function () {
        return ReservationUser.prototype.isNameValid
            && ReservationUser.prototype.isTelephoneValid
            && ReservationUser.prototype.isEmailValid
            && (TicketInfo.prototype.totalTicketNum > 0)
    },
    isReservationButtonOnColor: function() {
        const doReservationButton = document.querySelector(".bk_btn_wrap");
        return !doReservationButton.classList.contains("disable");
    },
    switchColorReservationButton: function () {
        const doReservationButton = document.querySelector(".bk_btn_wrap");
        if (doReservationButton.classList.contains("disable")) {
            doReservationButton.classList.remove("disable");
        } else {
            doReservationButton.classList.add("disable");
        }
    }
};