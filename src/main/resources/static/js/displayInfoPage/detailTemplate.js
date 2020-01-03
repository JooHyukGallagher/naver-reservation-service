const makeDetailTemplate = {
    // 상품이미지 순서 바꾸기
    changeProductImgOrder: function (productImages) {
        productImages.insertAdjacentElement("beforeend", productImages.firstElementChild);

        const visual_img = document.createElement("ul");
        visual_img.classList.add("visual_img", "detail_swipe");
        visual_img.innerHTML = productImages.innerHTML;

        return visual_img;
    },
    makeProductImgsTemplate: function (productImages, displayInfo) {
        const productImgs = [];     // 상품이미지 template 배열
        // 상품이미지 템플릿 배열 생성
        productImages.forEach((element) => {
            const productImagesTemplate = document.querySelector("#productImg").innerHTML;
            // const bindTemplate = Handlebars.compile(productImagesTemplate);
            const bindTemplate = makeBindTemplate(productImagesTemplate);
            // 텔플릿에 집어넣을 데이터 객체 생성
            const obj = {};
            obj.placeName = displayInfo.placeName;
            obj.productId = element.productId;
            obj.type = element.type;

            const resultHTML = bindTemplate(obj);
            productImgs.push(resultHTML);
            // productImgs.push(resultHTML);
        });
        return productImgs;
    },
    // 상품 이미지 템플릿 생성
    makeProductImgUl: function (resultHTML) {
        const visualImg = document.createElement("ul");
        visualImg.classList.add("visual_img", "detail_swipe");

        visualImg.innerHTML = resultHTML;
        return visualImg;
    },
    // 현재 페이지 번호
    makeCurrentPageNum: function (num) {
        const currentPageNum = document.createElement("span");
        currentPageNum.classList.add("num");
        currentPageNum.innerText = num;

        const figure_pagination = document.querySelector(".figure_pagination");
        figure_pagination.replaceChild(currentPageNum, figure_pagination.firstElementChild);
    },
    // 상단 총 페이지 번호
    makePageNum: function (num) {
        const pageNum = document.createElement("span");
        pageNum.innerText = num;
        const num_off = document.querySelector(".figure_pagination > .off");
        num_off.appendChild(pageNum);
    },
    // 상품 설명
    makeProductContent: function (detail_data) {
        const productContent = detail_data.productContent;

        const storeDetailTemplate = document.querySelector(".store_details > .dsc");
        storeDetailTemplate.innerText = productContent;
    },
    // 리뷰 평점 && 별 채우기
    makeReservationScore: function (avgScore) {
        const avgScoreTemplate = document.querySelector("#averageScore");
        const scoreGraph = document.querySelector(".graph_value");
        // if (avgScore === "NaN") {
        //     avgScoreTemplate.innerText = "0.0";
        //     scoreGraph.style.width = "0%";
        // } else {
            avgScore = (avgScore).toFixed(1);
            avgScoreTemplate.innerText = avgScore;
            scoreGraph.style.width = avgScore * 10 + "%";
        // }
    },
    // 한줄평 갯수
    makeReservationCommentNum: function (detail_data) {
        const commentNum = detail_data.length;
        const joinCount = document.querySelector(".join_count > .green");
        joinCount.innerText = commentNum + "건";
    },
    // 예매자 한줄평
    makeReservationComment: function (detail_data, displayInfo) {
        let commentTemplates = [];      // 한줄평 완성된 템플릿 배열
        // 한줄평 리뷰 삽입
        const comments = detail_data;
        const reviewImageOff = document.querySelector("#review01").innerText; // 이미지 없는 경우
        const reviewImageOn = document.querySelector("#review02").innerText; // 이미지 있는 경우

        const reviewTemplate = makeBindTemplate(reviewImageOff);
        const reviewImageTemplate = makeBindTemplate(reviewImageOn);

        if (comments.length !== 0) {
            comments.forEach((element) => {
                const data = {};
                const reviewComment = element.comment;
                const reviewScore = element.score;
                const email = element.reservationEmail.substring(0, 4) + "****";
                const date = element.reservationDate.substring(0, 10).replace(/-/gi, ".");
                const title = displayInfo.productDescription;

                data.comment = reviewComment;
                data.score = reviewScore + ".0";
                data.reservationEmail = email;
                data.reservationDate = date;
                data.productDescription = title;
                if (element.commentImages.length !== 0) {
                    data.commentId = element.commentImages[0].reservationUserCommentId;
                    commentTemplates.push(reviewImageTemplate(data));
                } else {
                    commentTemplates.push(reviewTemplate(data));
                }
            });

        }
        return commentTemplates;
    },
    // 상세페이지 하단 상세정보
    makeDetailInformation: function (displayInfoData) {
        const sectionInfoTab = document.querySelector(".section_info_tab");
        let resultHTML = "";
        const displayInfo = displayInfoData;
        let data = {
            "productContent": displayInfo.productContent,
            "productDescription": displayInfo.productDescription,
            "placeStreet": displayInfo.placeStreet,
            "placeLot": displayInfo.placeLot,
            "placeName": displayInfo.placeName,
            "telephone": displayInfo.telephone,
            "displayInfoId": displayInfo.displayInfoId
        };
        // 상세정보
        const detailInfoContainer = document.querySelector("#detail").innerHTML;
        // const detailInfoTemplate = Handlebars.compile(detailInfoContainer);
        const detailInfoTemplate = makeBindTemplate(detailInfoContainer);
        resultHTML = detailInfoTemplate(data);
        sectionInfoTab.innerHTML += resultHTML;
        // 오시는길
        const pathContainer = document.querySelector("#path").innerHTML;
        // const pathTemplate = Handlebars.compile(pathContainer);
        const pathTemplate = makeBindTemplate(pathContainer);
        resultHTML = pathTemplate(data);
        sectionInfoTab.innerHTML += resultHTML;
    }
};