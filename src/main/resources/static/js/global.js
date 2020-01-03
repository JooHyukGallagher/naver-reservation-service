// // api 요청
// const ajax = (method, url, data, property, length) => {
//     return new Promise((resolve, reject) => {
//         // 1. XMLHttpRequest 생성
//         const xhr = new XMLHttpRequest();
//
//         // 2. 아래 4번에서 요청한 Request를 받을 Response 이벤트 (onreadystatechange)
//         xhr.onreadystatechange = function () {
//             if (this.readyState === 4 && this.status === 200) {
//                 let data = JSON.parse(this.responseText);
//                 if (property === null) {
//                     resolve(data);
//                 } else {
//                     resolve(data[property]);
//                 }
//             }
//         };
//
//         // 3. XHR Object Open
//         xhr.open(method, url); // 리퀘스트할 method 와 url 설정
//         xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
//
//         // 4. 세팅 완료한 XHR 오브젝트 Request 요청
//         xhr.send(data);
//     });
// };
//
const ajax = async (method, url, data, property) => {
    let response;
    try {
        if (data == null) {
            response = await fetch(url, {
                method: method
            });
        } else {
            response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
        }
        const responseData = await response.json();

        if (property == null) {
            return responseData;
        }
        return responseData[property];
    } catch (error) {
        console.log("Error:", error);
    }

};

// This will upload the file after having read it
const fileUpload = (url, file) => {
    fetch(url, { // Your POST endpoint
        method: 'POST',
        body: file // This is your file object
    }).then(
        response => response.json() // if the response is a JSON object
    ).then(
        success => console.log(success) // Handle the success response object
    ).catch(
        error => console.log(error) // Handle the error response object
    );
};


// handlebar 메서드 반환
const makeBindTemplate = (template) => {
    return Handlebars.compile(template);
};

const moveTop = () => {
    window.scrollTo(0, 0);
};

const toHome = () => {
    location.href = "/reservation/mainpage";
};

const toLogin = (evt) => {
    const loginButton = evt.currentTarget;
    const button = loginButton.childNodes[1];
    if (button.id === "email") {
        const reservationEmail = button.innerText;
        loginButton.href = "/reservation/booking/list?reservationEmail=" + reservationEmail;
    } else if (button.id === "home") {
        loginButton.href = "/reservation/bookinglogin";
    }
};

const initGlobalEvent = () => {
    const top_button = document.querySelector(".gototop");
    top_button.addEventListener("click", moveTop);

    const homeButton = document.querySelector(".section_visual > header > .logo");
    if (homeButton !== null) {
        homeButton.addEventListener("click", toHome);
    }

    // 비회원 로그인으로 이동하는 이벤트 등록
    const resConfigButtonChild = document.querySelector(".btn_my > .viewReservation");
    if (resConfigButtonChild != null) {
        const resConfirmButton = resConfigButtonChild.parentElement;
        resConfirmButton.addEventListener("click", toLogin);
    }
};

document.addEventListener("DOMContentLoaded", () => {
    initGlobalEvent();
});