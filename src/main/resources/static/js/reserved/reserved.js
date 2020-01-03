const registRequestCancelEvent = () => {
    const confirmedReservationList = document.querySelector(".confirmed");
    confirmedReservationList.addEventListener("click", (evt) => {
        if (evt.target.className === "btn" || evt.target.innerHTML === "취소") {
            let reservationInfoId = evt.target.parentElement.id;
            if (reservationInfoId === "") {
                reservationInfoId = evt.target.parentElement.parentElement.id;
            }
            BookingEvent.prototype.popUpButtonEvent();
            BookingEvent.prototype.popUpCancelNoEvent();
            BookingEvent.prototype.popUpCancelYesEvent(reservationInfoId);
        }
    });
};

const makeReservationListCard = (reservationInfoResponse) => {
    const reservatioInfoList = reservationInfoResponse.reservations;
    reservatioInfoList.forEach((reservationInfo) => {
        const reservationCard = new ReservationCard(reservationInfo, reservationInfo.cancelYn);
        reservationCard.makeReservationCard();
    });

    ReservationCard.prototype.setAllListNumber(reservationInfoResponse);
    ReservationCard.prototype.setReservationListNumber();
};

const requestReservationInfoResponse = async () => {
    const reservationEmail = document.querySelector(".reservationEmail").id;
    const url = "/api/reservations?reservationEmail=" + reservationEmail;
    const reservationInfoResponse = await ajax("GET", url, null, null);

    makeReservationListCard(reservationInfoResponse);

    registRequestCancelEvent();
};

document.addEventListener("DOMContentLoaded", () => {
    requestReservationInfoResponse();
});