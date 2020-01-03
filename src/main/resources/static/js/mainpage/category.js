function Category() {
}

Category.prototype = {
    start: 4,
    categoryId: 0,
    categoryNumArray: [],
    changeSelectedCategoryButton: function (beforeButton, currentButton) {
        if (beforeButton.classList.contains("active")){
            beforeButton.classList.remove("active");
        }

        if (!currentButton.classList.contains("active")){
            currentButton.classList.add("active");
        }
    },
    changeCategoryNumber: function(categoryId) {
        const pink = document.querySelector(".pink");
        pink.innerText = this.categoryNumArray[categoryId] + "개";
    },
    isCategoryButton: function (selectingButton) {
        return selectingButton.classList.contains("anchor");
    },
    getProductTemplateByCategoryId: function (productList) {
        const productListContainer = document.querySelectorAll(".lst_event_box");
        productListContainer[0].innerHTML = "";
        productListContainer[1].innerHTML = "";
        Template.prototype.addProductTemplate(productList);
    },
    saveCategoryNum: function (categoryList) {
        let initCategoryNum = 0;
        categoryList.forEach((category) => {
            initCategoryNum += category.count;
        });
        this.categoryNumArray.push(initCategoryNum);
        categoryList.forEach((categoryElement) => {
            this.categoryNumArray.push(categoryElement.count);
        });
    }
};