function ReservationCard(reservationInfo, isCancel) {
    this.reservationInfo = reservationInfo;
    this.isCancel = isCancel;
}

ReservationCard.prototype = {
    allReservation: 0,
    confirmedReservation: 0,
    usedReservation: 0,
    cancelReservation: 0,
    setAllListNumber: function(reservationInfoResponse){
        const size = reservationInfoResponse.size;
        ReservationCard.prototype.allReservation = size;

        const allReservationNumTemplate = document.querySelector("#allReservation");
        allReservationNumTemplate.innerHTML = ReservationCard.prototype.allReservation;
    },
    setReservationListNumber: function () {
        const confirmedReservationNumTemplate = document.querySelector("#confirmedReservation");
        confirmedReservationNumTemplate.innerHTML = ReservationCard.prototype.confirmedReservation;

        const usedReservationNumTemplate = document.querySelector("#usedReservation");
        usedReservationNumTemplate.innerHTML = ReservationCard.prototype.usedReservation;

        const cancelReservationNumTemplate = document.querySelector("#cancelReservation");
        cancelReservationNumTemplate.innerHTML = ReservationCard.prototype.cancelReservation;
    },
    makeReservationCard: function () {
        const nowDate = new Date();
        const reservationDate = new Date(this.reservationInfo.reservationDate);
        if (!this.isCancel) {
            if (this.isUsed(nowDate, reservationDate)) {
                ReservationCard.prototype.usedReservation += 1;
                const usedList = document.querySelector(".used");
                this.addReservationCard("used", usedList, this.reservationInfo);
            } else {
                const confirmedList = document.querySelector(".confirmed");
                ReservationCard.prototype.confirmedReservation += 1;
                this.addReservationCard("confirmed", confirmedList, this.reservationInfo);
            }
        } else {
            const cancelList = document.querySelector(".cancel");
            ReservationCard.prototype.cancelReservation += 1;
            this.addReservationCard("cancel", cancelList, this.reservationInfo)
        }
    },
    addReservationCard: function (flag, list, reservationInfo) {
        const id = "#" + flag + "_card_item_template";
        const cardItemTemplate = document.querySelector(id).innerHTML;
        const reservationCardTemplate = makeBindTemplate(cardItemTemplate);
        const data = ReservationCard.prototype.getBindData(reservationInfo);

        list.innerHTML += reservationCardTemplate(data);
    },
    isUsed: function (nowDate, reservationDate) {
        if (nowDate >= reservationDate) return true;  // 이용완료
        else if (nowDate < reservationDate) return false; // 예약확정
    },
    getBindData: function (reservationInfo) {
        const data = {
            reservationInfoId: reservationInfo.reservationInfoId,
            productDescription: reservationInfo.displayInfo.productDescription,
            openingHours: reservationInfo.displayInfo.openingHours,
            homepage: reservationInfo.displayInfo.homepage,
            placeLot: reservationInfo.displayInfo.placeLot,
            reservationName: reservationInfo.reservationName,
            reservationDate: reservationInfo.reservationDate,
            totalPrice: reservationInfo.totalPrice
        };

        if (data.homepage === null || data.homepage === ""){
            data.homepage = "없음";
        }

        const reservationDate = data.reservationDate.substring(0, 10)
                                    .replace("-", "년 ")
                                    .replace("-", "월 ")
                                    + "일";

        data.reservationDate = reservationDate;

        return data;
    }
};
