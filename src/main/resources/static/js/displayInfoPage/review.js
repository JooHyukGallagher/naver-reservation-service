// 모든 리뷰
const makeReview = (detail_data) => {
    // 리뷰 평점 && 별
    makeDetailTemplate.makeReservationScore(detail_data.averageScore);

    // 한줄평 갯수
    makeDetailTemplate.makeReservationCommentNum(detail_data.comments);

    // 모든 한줄평 리뷰
    let commentTemplates = makeDetailTemplate.makeReservationComment(detail_data.comments, detail_data.displayInfo);
    const reviewContainer = document.querySelector(".list_short_review");
    let innerHTML = "";
    commentTemplates.forEach((element) => {
       innerHTML += element;
    });
    reviewContainer.innerHTML = innerHTML;
};


// detail API 요청
const requestData = async function () {
    const displayInfoId = document.querySelector(".displayInfoId").id;
    let url = "/api/products/" + displayInfoId;
    // 상품 상세보기 데이터 요청
    const detail_data = await ajax("GET", url, null, null);
    // 한줄평 삽입하기
    makeReview(detail_data);
};

const init = () => {
    // detail_data에 products/{displayInfoId}요청 데이터 삽입
    requestData();
};


document.addEventListener("DOMContentLoaded", () => {
    init();
});