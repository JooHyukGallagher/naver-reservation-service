function RedirectPage() {

}

RedirectPage.prototype = {
    requestReservationInfoResponse: async function (reservationUserEmail) {
        try {
            const url = "/api/reservations?reservationEmail=" + reservationUserEmail;
            const reservationInfoResponse = await ajax("GET", url, null, null);
            if (this.hasReservation(reservationInfoResponse.size)) {
                this.redirectReservationListPage();
            } else {
                this.redirectNonListPage();
            }
        } catch (error) {
            console.log(error);
        }
    },
    redirectNonListPage: function () {
        alert("예약 내역이 없습니다.");
    },
    redirectReservationListPage: function () {
        const inputEmailForm = document.querySelector("#form1");
        inputEmailForm.submit();
    },
    hasReservation: function (size) {
        return size !== 0;
    }
};