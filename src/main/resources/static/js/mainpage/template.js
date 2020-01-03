function Template() {
}

Template.prototype = {
    getPromotionItemTemplate: function (promotionList) {
        let resultHTML = "";

        promotionList.forEach((promotion) => {
            const promotionItemTemplate = document.querySelector("#promotionItem").innerHTML;
            const bindTemplate = makeBindTemplate(promotionItemTemplate);
            resultHTML += bindTemplate(promotion);
        });

        return resultHTML;
    },
    getProductItemTemplate: function (product) {
        const productItemTemplate = document.querySelector("#itemList").innerHTML;
        const bindTemplate = makeBindTemplate(productItemTemplate);

        const data = Template.prototype.returnProductData(product);
        return bindTemplate(data);
    },
    returnProductData: function (product) {
        const data = {};
        data.displayInfoId = product.displayInfoId;
        data.description = product.productDescription;
        data.productId = product.productId;
        data.placeName = product.placeName;
        data.content = product.productContent;

        return data;
    },
    addProductTemplate: function (productList) {
        const productListContainer = document.querySelectorAll(".lst_event_box");
        for (let i = 0; i < productList.length; i++) {
            const resultHTML = Template.prototype.getProductItemTemplate(productList[i]);
            if (i % 2 === 0) {
                productListContainer[0].innerHTML += resultHTML;
            } else {
                productListContainer[1].innerHTML += resultHTML;
            }
        }
    }
};