const TicketTemplate = () => {

};

TicketTemplate.prototype = {
    getTicketTypeFormTemplate: function () {
        return document.querySelector("#ticketTypeForm").innerHTML;
    },
    makeTicketTypeFormTemplate: function (ticketTypeFormTemplate, productPriceData) {
        const bindTemplate = makeBindTemplate(ticketTypeFormTemplate);

        return bindTemplate(productPriceData);
    },
    makeMappingData: function (productPrice) {
        let productPriceData = {};

        productPriceData.productPriceId = productPrice.productPriceId;
        productPriceData.priceTypeName = TicketTemplate.prototype.getPriceTypeName(productPrice.priceTypeName);
        productPriceData.price = productPrice.price;
        productPriceData.priceChar = TicketTemplate.prototype.getTicketPriceChar(productPrice);
        productPriceData.discountRate = productPrice.discountRate;

        return productPriceData;
    },
    getPriceTypeName: function (priceTypeName) {
        switch (priceTypeName) {
            case "A":
                return "성인";
            case "Y":
                return "청소년";
            case "B":
                return "유아";
            case "V":
                return "VIP";
            case "R":
                return "R석";
            case "S":
                return "세트";
            case "D":
                return "장애인";
            case "E":
                return "얼리버드";
        }
    },
    getTicketPriceChar: function (productPrice) {
        if (productPrice.priceTypeName === "S") {
            return "2인 관람권"
        }
        return productPrice.price + "원";
    },
    showReservationDay: async function () {
        const url = "/api/timenow";
        const requestTimeNow = (url) => {
            fetch(url).then((response) => {
                response.text().then((localDateTime) => {
                    TicketInfo.prototype.reservationYearMonthDay = localDateTime;

                    localDateTime = TicketTemplate.prototype.removeTime(localDateTime);
                    const reservationDate = document.querySelector("#reservationDate");
                    reservationDate.innerHTML = localDateTime;

                });
            });
        };
        requestTimeNow(url);
    },
    removeTime: function (localDateTime) {
        const removedLocalDateTime = localDateTime.substring(0, 10);
        const localDate = removedLocalDateTime.replace(/-/g, ".");
        return localDate;
    }
};

