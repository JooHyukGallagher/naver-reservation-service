function TicketInfo(productPriceId, count, totalPrice, limitTicketNum) {
    this.productPriceId = productPriceId;
    this.count = count;
    this.totalPrice = totalPrice;
    this.limitTicketNum = limitTicketNum;
}

TicketInfo.prototype = {
    displayInfoId: 0,
    productId: 0,
    reservationYearMonthDay: "",
    totalTicketNum: 0,
    plusButton: function (countControl, productPrice) {
        const ticketButton = countControl.firstElementChild;

        const minusButton = ticketButton.children[0];
        const showTicketNum = ticketButton.children[1];
        const plusButton = ticketButton.children[2];
        this.onClearfixColor(showTicketNum);
        this.onClearfixColor(minusButton);

        if (this.count < this.limitTicketNum) {
            this.modifyTicketNum(this, countControl, "plus");
            this.modifyTicketPrice(this, countControl, productPrice, "plus");
            this.modifyTotalTicketNum("plus");

            const individualPrice = countControl.lastElementChild;

            if (!individualPrice.classList.contains("on_color") && this.totalPrice > 0) {
                individualPrice.classList.add("on_color");
            }

            if (this.count === this.limitTicketNum) {
                this.offClearfixColor(plusButton);
            }
        }


    },
    minusButton: function (countControl, productPrice) {
        const ticketButton = countControl.firstElementChild;

        const minusButton = ticketButton.children[0];
        const showTicketNum = ticketButton.children[1];
        const plusButton = ticketButton.children[2];

        if (this.count > 0) {
            this.onClearfixColor(plusButton);
            this.modifyTicketNum(this, countControl, "minus");
            this.modifyTicketPrice(this, countControl, productPrice, "minus");
            this.modifyTotalTicketNum("minus");

            const individualPrice = countControl.lastElementChild;

            if (this.count === 0) {
                this.offClearfixColor(minusButton);
                this.offClearfixColor(showTicketNum);
                individualPrice.classList.remove("on_color");
            }
        }
    },
    modifyTicketNum: function (ticketInfo, countControl, flag) {
        const ticketButton = countControl.firstElementChild;
        const showTicketNum = ticketButton.children[1];

        if (flag === "plus") {
            // ticketInfo.totalTicketNum++;
            ticketInfo.count++;
            showTicketNum.value = ticketInfo.count;
        } else if (flag === "minus") {
            // ticketInfo.totalTicketNum--;
            ticketInfo.count--;
            showTicketNum.value = ticketInfo.count;
        }

    },
    modifyTicketPrice: function (ticketInfo, countControl, productPrice, flag) {
        if (flag === "plus") {
            ticketInfo.totalPrice += productPrice.price;
            const individualPrice = countControl.lastElementChild;
            const totalPrice = individualPrice.firstElementChild;
            totalPrice.innerHTML = ticketInfo.totalPrice;
        } else if (flag === "minus") {
            ticketInfo.totalPrice -= productPrice.price;
            const individualPrice = countControl.lastElementChild;
            const totalPrice = individualPrice.firstElementChild;
            totalPrice.innerHTML = ticketInfo.totalPrice;
        }
    },
    modifyTotalTicketNum: function (flag) {
        if (flag === "plus") {
            TicketInfo.prototype.totalTicketNum++;
        } else if (flag === "minus") {
            TicketInfo.prototype.totalTicketNum--;
        }

        const totalCount = document.querySelector("#totalCount");
        totalCount.innerHTML = TicketInfo.prototype.totalTicketNum;
    },
    isClickedPlus: function (target) {
        return (target.classList.contains("ico_plus3"));
    },
    isClickedMinus: function (target) {
        return (target.classList.contains("ico_minus3"));
    },
    isButtonArea: function (target) {
        return target.classList.contains("ico_plus3") || target.classList.contains("ico_minus3");
    },
    onClearfixColor: function (target) {
        if (target.classList.contains("disabled")) {
            target.classList.remove("disabled")
        }
    },
    offClearfixColor: function (target) {
        if (!target.classList.contains("disabled")) {
            target.classList.add("disabled");
        }
    }
};
