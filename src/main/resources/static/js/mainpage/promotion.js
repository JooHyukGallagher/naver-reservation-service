function Promotion() {

}

Promotion.prototype = {
    animatePromotion: function () {
        setTimeout(() => {
            Promotion.prototype.moveToLeftPromotion();
            addEventListener("transitionend", Promotion.prototype.changePromotionOrder);
            Promotion.prototype.animatePromotion();
        }, 2000);
    },
    changePromotionOrder: function () {
        const toChangeUl = document.querySelector(".visual_img");
        const firstChild = toChangeUl.firstElementChild;
        toChangeUl.insertAdjacentElement("beforeend", firstChild);

        const visualImg = Promotion.prototype.makeVisualImg();
        visualImg.innerHTML = toChangeUl.innerHTML;

        const containerVisual = document.querySelector(".visual_img").parentElement;
        containerVisual.replaceChild(visualImg, document.querySelector(".visual_img"));
    },
    moveToLeftPromotion: function () {
        const visualImg = document.querySelector(".visual_img");
        visualImg.classList.add("showing");
    },
    makeVisualImg: function () {
        const visualImg = document.createElement("ul");
        visualImg.classList.add("visual_img");

        return visualImg;
    }
};