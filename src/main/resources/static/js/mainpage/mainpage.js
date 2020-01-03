const requestProductResponse = async (start, categoryId) => {
    const url = "/api/products?start=" + start + "&categoryId=" + categoryId;
    return await ajax("GET", url, null, "items");
};

const requestCategoryResponse = async () => {
    const url = "/api/categories";
    return await ajax("GET", url, null, "items");
};

const requestPromotionResponse = async () => {
    const url = "/api/promotions";
    return await ajax("GET", url, null, "items");
};

const registCategoryEvent = (initCategoryButton) => {
    let selectedCategoryButton = initCategoryButton.firstElementChild;

    const categoryListButton = document.querySelector(".event_tab_lst");
    categoryListButton.addEventListener("click", async (evt) => {
        const moreButton = document.querySelector(".btn");
        if (moreButton.style.display === "none") {
            moreButton.style.display = "";
        }

        const selectingCategoryButton = evt.target.parentElement;
        const category = new Category();
        if (category.isCategoryButton(selectingCategoryButton)) {
            const categoryId = selectingCategoryButton.parentElement.id;
            // 현재 start, categoryId 설정
            Category.prototype.categoryId = categoryId;
            Category.prototype.start = 4;
            // 카테고리 버튼 선택 표시 와 해제
            category.changeSelectedCategoryButton(selectedCategoryButton, selectingCategoryButton);
            selectedCategoryButton = evt.target.parentElement;
            // categoryId에 따른 상품정보의 갯수 바꾸기
            category.changeCategoryNumber(categoryId);
            // categoryId에 따른 상품 목록 보여주기
            const productList = await requestProductResponse(0, categoryId);
            category.getProductTemplateByCategoryId(productList);
        }
    });
};

const registMoreProductEvent = () => {
    const moreButton = document.querySelector(".btn");
    const category = new Category();
    let currentProductList;
    let nextProductList;
    moreButton.addEventListener("click", async () => {
        let start = Category.prototype.start;
        const categoryId = category.categoryId;

        currentProductList = await requestProductResponse(start, categoryId);
        nextProductList = await requestProductResponse(start + 4, categoryId);
        if (nextProductList.length === 0) {
            moreButton.style.display = "none";
        }

        Template.prototype.addProductTemplate(currentProductList);
        Category.prototype.start += 4;
    });
};

const registPromotionActionEvent = async () => {
    const promotionList = await requestPromotionResponse();
    let resultPromotionHTML = Template.prototype.getPromotionItemTemplate(promotionList);

    // 완성된 프로모션 이미지를 화면에 넣기
    const containerVisual = document.querySelector(".visual_img");
    containerVisual.innerHTML = resultPromotionHTML;

    const promotion = new Promotion();
    promotion.animatePromotion();
};

const initMainPage = async () => {
    // 초기 상품목록 보여주기
    const productList = await requestProductResponse(0, 0);
    Template.prototype.addProductTemplate(productList);

    // 카테고리 숫자를 Category.categoryNumArray 프로퍼티에 저장
    const category = new Category();
    const categoryList = await requestCategoryResponse();
    category.saveCategoryNum(categoryList);
    category.changeCategoryNumber(0);

    const initCategoryButton = document.querySelector(".event_tab_lst").firstElementChild;
    registCategoryEvent(initCategoryButton);

    registMoreProductEvent();

    registPromotionActionEvent();
};

document.addEventListener("DOMContentLoaded", () => {
    initMainPage();
});