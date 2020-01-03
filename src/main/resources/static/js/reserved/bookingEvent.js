function BookingEvent() {

}

BookingEvent.prototype = {
    requestUpdateReservation: async function (reservationInfoId) {
        const reqReservationResponseUrl = "/api/reservations/" + reservationInfoId;
        const reservationResponse = await ajax("PUT", reqReservationResponseUrl, null, null);
        console.log(reservationResponse);

        BookingEvent.prototype.goToCancelReservationList(reservationInfoId);
    },
    goToCancelReservationList: async function (reservationInfoId) {
        const reqReservationInfoUrl = "/api/reservationInfo?reservationInfoId=" + reservationInfoId;
        const reservationInfo = await ajax("GET", reqReservationInfoUrl, null, null);
        const reservationCard = new ReservationCard(reservationInfo, reservationInfo.cancelYn);

        reservationCard.makeReservationCard();
        ReservationCard.prototype.setReservationListNumber();
    },
    removeConfirmedReservationList: function (reservationInfoId) {
        const confirmedReservation = document.getElementById(reservationInfoId);

        ReservationCard.prototype.confirmedReservation -= 1;
        confirmedReservation.remove();
    },
    popUpButtonEvent: function () {
        const popup = document.querySelector(".popup_booking_wrapper");
        popup.style.display = "block";
    },
    popUpCancelNoEvent: function () {
        const popup = document.querySelector(".popup_booking_wrapper");

        const no = document.querySelector(".btn_gray");
        no.addEventListener("click", () => {
            popup.style.display = "none";
        });

        const xButton = document.querySelector(".popup_btn_close");
        xButton.addEventListener("click", () => {
            popup.style.display = "none";
        });
    },
    popUpCancelYesEvent: function (reservationInfoId) {
        const popup = document.querySelector(".popup_booking_wrapper");

        const yes = document.querySelector(".btn_green");
        yes.id = reservationInfoId;

        yes.addEventListener("click", (evt) => {
            if (BookingEvent.prototype.equalReservationInfoId(evt, reservationInfoId)) {
                popup.style.display = "none";
                BookingEvent.prototype.removeConfirmedReservationList(reservationInfoId);
                BookingEvent.prototype.requestUpdateReservation(reservationInfoId);
            }
        });
    },
    equalReservationInfoId: function (evt, reservationInfoId) {
        return (evt.target.parentElement.id === reservationInfoId) || (evt.target.parentElement.parentElement.id === reservationInfoId);
    }

};