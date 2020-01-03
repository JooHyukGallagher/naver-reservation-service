function RatingScore() {
}

RatingScore.prototype = {
    score: 0,
    onStar: function () {
        const star = document.querySelectorAll(".rating_rdo");
        for (let i = 0; i < this.score; i++) {
            star[i].classList.add("checked");
        }
    },
    offStar: function () {
        const star = document.querySelectorAll(".rating_rdo");
        star.forEach((starElement) => {
            starElement.classList.remove("checked");
        });
    },
    changeScore: function () {
        const scoreContainer = document.querySelector(".star_rank");
        scoreContainer.innerHTML = this.score;
        scoreContainer.classList.remove("gray_star");
    }
};