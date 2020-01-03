const makeReservationPrice = (productPrices, ticketInfoData) => {
    productPrices.forEach((productPrice) => {
        let ticketInfo = new TicketInfo(productPrice.productPriceId, 0, 0, 4);
        ticketInfoData.push(ticketInfo);
    });
};

const makeTicketNumberTemplate = (productPrices) => {
    let resultHTML = "";
    productPrices.forEach((productPrice) => {
        const data = TicketTemplate.prototype.makeMappingData(productPrice);
        const template = TicketTemplate.prototype.getTicketTypeFormTemplate();
        resultHTML += TicketTemplate.prototype.makeTicketTypeFormTemplate(template, data);
    });

    const ticketBody = document.querySelector(".ticket_body");
    ticketBody.innerHTML = resultHTML;

    TicketTemplate.prototype.showReservationDay();
};

const registTicketButtonEvent = (productPrices, ticketInfoData) => {
    const ticketBody = document.querySelector(".ticket_body");
    const pivot = ticketInfoData[0].productPriceId;
    ticketBody.addEventListener("click", (event) => {
        if (TicketInfo.prototype.isButtonArea(event.target)) {
            let productPriceId = event.target.parentElement.id;

            const ticketInfo = ticketInfoData[pivot - productPriceId];
            const countControl = document.getElementById(productPriceId).parentElement;
            let productPrice = productPrices[pivot - productPriceId];

            if (ticketInfo.isClickedPlus(event.target)) {
                ticketInfo.plusButton(countControl, productPrice);
            } else if (ticketInfo.isClickedMinus(event.target)) {
                ticketInfo.minusButton(countControl, productPrice);
            }
        }
    });
};

const registReservationUserEvent = () => {
    const reservationUser = new ReservationUser();
    const inputBox = document.querySelectorAll(".inline_control");

    const userNameInputBox = inputBox[0];
    userNameInputBox.addEventListener("keyup", (evt) => {
        reservationUser.checkResUserName(evt);
    });

    const userTelephoneInputBox = inputBox[1];
    userTelephoneInputBox.addEventListener("keyup", (evt) => {
        reservationUser.checkResUserTelephone(evt);
    });

    const userEmailInputBox = inputBox[2];
    userEmailInputBox.addEventListener("keyup", (evt) => {
        reservationUser.checkResUserEmail(evt);
    });
};

const registAgreementToggleEvent = () => {
    const reservationAgree = new ReservationAgree();

    const chkAgree = document.querySelector(".chk_txt_label");
    chkAgree.addEventListener("click", () => {
        if (reservationAgree.isValidContent()) {
            reservationAgree.switchColorReservationButton();
        }
    });

    const lookButton = document.querySelectorAll(".btn_agreement");

    const lookCollectAgreeButton = lookButton[0];
    lookCollectAgreeButton.addEventListener("click", (evt) => {
        reservationAgree.toggleAgreementContent(evt);
    });

    const lookProvideAgreeButton = lookButton[1];
    lookProvideAgreeButton.addEventListener("click", (evt) => {
        reservationAgree.toggleAgreementContent(evt);
    });
};

const registPostRequestEvent = (displayInfo, ticketInfoData) => {
    const registButton = document.querySelector(".bk_btn_wrap");
    registButton.addEventListener("click", () => {
        if (ReservationAgree.prototype.isValidContent()
            && ReservationAgree.prototype.isReservationButtonOnColor()) {
            const hiddenForm = new HiddenForm();
            hiddenForm.makeHiddenPrices(ticketInfoData);

            const reservationParamObj = new ReservationParam();
            const reservationParam = reservationParamObj.makeReservationParam(displayInfo);

            console.log(reservationParam);
            reservationParamObj.requestReservationParam(reservationParam);
        }
    });
};

const requestDisplayInfoData = async () => {
    const displayInfoId = document.querySelector(".displayInfoId").id;
    const url = "/api/products/" + displayInfoId;
    const displayInfoResponse = await ajax("get", url, null, null);
    console.log(displayInfoResponse);
    const productPrices = displayInfoResponse.productPrices;
    const ticketInfoData = [];

    TicketInfo.prototype.displayInfoId = displayInfoResponse.displayInfoId;
    TicketInfo.prototype.productId = displayInfoResponse.productId;

    makeReservationPrice(productPrices, ticketInfoData);

    makeTicketNumberTemplate(productPrices);
    registTicketButtonEvent(productPrices, ticketInfoData);

    registReservationUserEvent();

    registAgreementToggleEvent();

    registPostRequestEvent(displayInfoResponse.displayInfo, ticketInfoData);
};

document.addEventListener("DOMContentLoaded", () => {
    requestDisplayInfoData();
});