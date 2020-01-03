// 기획서 상품이미지 최대갯수 제한
const MAX_IMAGES_NUM = 2;
// 상품 이미지 띄우기
const showInitProductImg = (productImages) => {
    const productImgs = productImages;     // 상품 이미지 템플릿 배열
    const imgNum = productImages.length;
    let resultHTML = "";
    if (imgNum >= MAX_IMAGES_NUM) {
        makeDetailTemplate.makePageNum(MAX_IMAGES_NUM);
        for (let i = 0; i < MAX_IMAGES_NUM; i++) {
            resultHTML += productImgs[i];
        }
    } else {
        makeDetailTemplate.makePageNum(1);
        productImgs.forEach((element) => {
            resultHTML += element;
        });
        // 상품이미지가 1개인 경우 화살표 버튼 지우기
        const prevButton = document.querySelector(".prev");
        const nxtButton = document.querySelector(".nxt");
        prevButton.style.display = "none";
        nxtButton.style.display = "none";
    }
    const containerVisual = document.querySelector(".container_visual");
    const visual_img = makeDetailTemplate.makeProductImgUl(resultHTML);
    containerVisual.appendChild(visual_img);
};

// 상품 상세 화면 띄우기
const showProductDetail = (detail_data) => {
    // 상품이미지 list 배열 생성
    let productImgs = makeDetailTemplate.makeProductImgsTemplate(detail_data.productImages, detail_data.displayInfo);
    // 상품 이미지 호출
    showInitProductImg(productImgs);
    // 상품 상세정보 호출
    makeDetailTemplate.makeProductContent(detail_data.displayInfo);
    // 리뷰 평점 && 별
    makeDetailTemplate.makeReservationScore(detail_data.averageScore);
    // 한줄평 갯수
    makeDetailTemplate.makeReservationCommentNum(detail_data.comments);

    // 예매자 한줄평 3개까지
    let commentTemplates = makeDetailTemplate.makeReservationComment(detail_data.comments, detail_data.displayInfo);
    const reviewContainer = document.querySelector(".list_short_review");
    let innerHTML = "";
    if (commentTemplates.length > 3) {
        for (let i = 0; i < 3; i++) {
            innerHTML += commentTemplates[i];
        }
    } else if (commentTemplates.length > 0) {
        commentTemplates.forEach((element) => {
            innerHTML += element;
        });
    }
    reviewContainer.innerHTML = innerHTML;

    // 하단 상세 설명
    makeDetailTemplate.makeDetailInformation(detail_data.displayInfo);
};


const registEvent = (displayInfoId) => {
    // 상단 상품 이미지 터치 이벤트 등록
    const prevButton = document.querySelector(".btn_prev");
    const nxtButton = document.querySelector(".btn_nxt");
    prevButton.addEventListener("click", executeAnimation.moveProductImage);
    nxtButton.addEventListener("click", executeAnimation.moveProductImage);

    // 펼쳐보기, 접기 버튼 이벤트 등록
    const openButton = document.querySelector("._open");
    const closeButton = document.querySelector("._close");
    openButton.addEventListener("click", executeAnimation.moreClickButton);
    closeButton.addEventListener("click", executeAnimation.moreClickButton);

    // 상세정보, 오시는길 버튼 이벤트 등록
    const detailAreaWrap = document.querySelector("._detail");
    const detailLocation = document.querySelector("._path");
    detailAreaWrap.addEventListener("click", executeAnimation.detailOrWayTo);
    detailLocation.addEventListener("click", executeAnimation.detailOrWayTo);

    // 예매하기 버튼 이벤트 등록
    const reserveButton = document.querySelector(".bk_btn");
    reserveButton.addEventListener("click", executeAnimation.getReservePage);
};

// detail API 요청
const requestData = async function () {
    const displayInfoId = document.querySelector(".displayInfoId").id;
    let url = "/api/products/" + displayInfoId;
    // 상품 상세보기 데이터 요청
    const detail_data = await ajax("GET", url, null, null);
    console.log(detail_data);
    // 상품 상세 정보 보이기
    showProductDetail(detail_data);
    // 여러 이벤트 등록
    registEvent();
};

const initDetailEvent = () => {
    // detail_data에 products/{displayInfoId}요청 데이터 삽입
    requestData();
};


document.addEventListener("DOMContentLoaded", () => {
    initDetailEvent();
});