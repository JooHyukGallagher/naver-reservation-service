const executeAnimation = {
    // 이전 이미지 보이기
    moveProductImagePrev: function(productImages, num) {
        const visual_img = makeDetailTemplate.changeProductImgOrder(productImages);
        visual_img.classList.add("init_left");
        // 기존의 proudctImgae 리스트를 바꾼 producImage 리스트로 교체
        const container_visual = document.querySelector(".container_visual");
        container_visual.replaceChild(visual_img, productImages);
        // 오른쪽으로 이동하는 transition 적용
        setTimeout(() => {
            const new_productImages = container_visual.firstElementChild;
            new_productImages.classList.add("move_right");
            new_productImages.addEventListener("transitionend", () => {
                // 페이지 번호 변경
                makeDetailTemplate.makeCurrentPageNum(num);
            });
        }, 0);
    },
    // 다음 이미지 보이기
    moveProductImageNext: function(productImages, num) {
        if (productImages.classList.contains("move_right")) {
            productImages.classList.remove("move_right");
        } else {
            // 상품이미지 Ul에 showing 속성 추가
            productImages.classList.add("move_left");
        }
        // transition이 끝날경우 실행할 작업
        productImages.addEventListener("transitionend", () => {
            // productImage li태그 순서 바꾸기
            const visual_img = makeDetailTemplate.changeProductImgOrder(productImages);

            // 기존의 proudctImgae 리스트를 바꾼 producImage 리스트로 교체
            const container_visual = document.querySelector(".container_visual");
            container_visual.replaceChild(visual_img, productImages);

            // 페이지 번호 변경
            makeDetailTemplate.makeCurrentPageNum(num);
        });
    },
    // 상품이미지 화면 이동
    moveProductImage: function (evt) {
        // 페이지 번호 변경
        const figure_pagination = document.querySelector(".figure_pagination");
        const curPageNum = parseInt(figure_pagination.firstElementChild.innerHTML);
        const num = 3 - curPageNum;

        const currentStatus = evt.currentTarget;
        // 기존 producImgage 리스트
        const productImages = document.querySelector(".visual_img");
        // 왼쪽 화살표 버튼을 눌렀을경우
        if (currentStatus.className === "btn_prev") {
            executeAnimation.moveProductImagePrev(productImages, num);
            // 오른쪽 화살표를 눌렀을 경우
        } else if (currentStatus.className === "btn_nxt") {
            executeAnimation.moveProductImageNext(productImages, num);
        }
    },
    // 예매하기 이동
    getReservePage: function(){
        const displayInfoId = document.querySelector(".displayInfoId").id;
        location.href = "/reservation/reservationForm/" + displayInfoId;
    },
    // 펼쳐보기, 접기
    moreClickButton: function (evt) {
        const target = evt.currentTarget;
        const storeDetails = document.querySelector(".store_details");  // 상세설명
        const openButton = document.querySelector("._open");            // 펼쳐보기 버튼
        const closeButton = document.querySelector("._close");          // 접기 버튼

        if (target.className === "bk_more _open") {
            storeDetails.classList.remove("close3");    // 설명 다 보이기
            openButton.style.display = "none";                   // 펼쳐보기 버튼 가리기
            closeButton.style.display = "";                      // 접기 버튼 생성
        } else if (target.className === "bk_more _close") {
            storeDetails.classList.add("close3");                // 설명 가리기
            closeButton.style.display = "none";                  // 접기 버튼 가리기
            openButton.style.display = "";                       // 펼쳐보기 버튼 생성
        }
    },
    // 상세보기, 오시는길
    detailOrWayTo: function (evt) {
        const currentStatus = evt.target;
        const detail = document.querySelector("#detail_tab");
        const path = document.querySelector("#path_tab");

        const detailArea = document.querySelector(".detail_area_wrap");
        const detailLocation = document.querySelector(".detail_location");
        if (currentStatus.className === "._detail" || currentStatus.parentElement.id === "detail_tab") {
            if (detail.classList.contains("active") === false) {
                detail.classList.add("active");
                path.classList.remove("active");
                // 상세보기 보이기
                detailArea.classList.remove("hide");
                detailLocation.classList.add("hide");
            }
        } else if (currentStatus.className === "._path" || currentStatus.parentElement.id === "path_tab") {
            if (path.classList.contains("active") === false) {
                path.classList.add("active");
                detail.classList.remove("active");
                // 오시는길 보이기
                detailLocation.classList.remove("hide");
                detailArea.classList.add("hide");
            }
        }
    }
};
