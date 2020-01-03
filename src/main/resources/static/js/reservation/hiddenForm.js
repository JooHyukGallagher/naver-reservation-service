function HiddenForm() {

}

HiddenForm.prototype = {
    makeHiddenPrices: function(ticketInfoData) {
        ticketInfoData.forEach((ticketInfo) => {
            const inputHiddenTag = this.getInputHiddenTag("price");
            const stringReservationPrice = this.getStringifyProductPrices(ticketInfo);
            inputHiddenTag.value = stringReservationPrice;

            this.addToFormContainer(inputHiddenTag);
        });
    },
    getStringifyProductPrices: function(ticketInfo) {
        const reservationPrice = {
            count: ticketInfo.count,
            productPriceId: ticketInfo.productPriceId,
            reservationInfoId: 0,
            reservationInfoPriceId: 0
        };

        return JSON.stringify(reservationPrice);
    },
    getInputHiddenTag: function (tagName) {
        const hiddenInputTag = document.createElement("input");
        hiddenInputTag.type = "hidden";
        hiddenInputTag.name = tagName;

        return hiddenInputTag;
    },
    addToFormContainer: function (inputHiddenNode) {
        const formContainer = document.querySelector(".form_horizontal");
        formContainer.appendChild(inputHiddenNode);
    }
};