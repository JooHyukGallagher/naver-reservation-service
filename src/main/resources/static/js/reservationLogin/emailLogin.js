const clickLoginButtonEvent = () => {
    const loginButton = document.querySelector(".login_btn");
    loginButton.addEventListener("click", () => {
        const reservationUser = new ReservationUser();
        if (reservationUser.isEmailValid) {
            const redirectPage = new RedirectPage();
            redirectPage.requestReservationInfoResponse(reservationUser.email);

        }
    });
};

const registEmailCheckEvent = () => {
    const loginInput = document.querySelector(".login_form");
    loginInput.addEventListener("keyup", (evt) => {
        ReservationUser.prototype.checkResUserEmail(evt);
    });
};

const initLoginEvent = () => {
    registEmailCheckEvent();
    clickLoginButtonEvent();
};

document.addEventListener("DOMContentLoaded", () => {
    initLoginEvent();
});
