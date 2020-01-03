function TextArea() {

}

TextArea.prototype = {
    textIsValid: false,
    removePlaceholder: function() {
        const textAreaInfo = document.querySelector(".review_write_info");
        textAreaInfo.style.display = "none";
    },
    showPlaceholder: function() {
        const textAreaInfo = document.querySelector(".review_write_info");
        textAreaInfo.style.display = "";
    },
    checkTextArea: function (evt) {
        let textAreaValue = evt.target.value;
        let result = (/^.{5,400}$/).test(textAreaValue);
        TextArea.prototype.textIsValid = result;
    },
    changeTextLength: function (textLength) {
        const guideReview = document.querySelector(".guide_review");
        guideReview.firstElementChild.innerHTML = textLength;
    },
    restrictTextLength: function (evt) {
        const textArea = evt.target;
        const TEXT_MAX_NUM = 400;
        if (textArea.value.length > TEXT_MAX_NUM){
            const text = textArea.value.substring(0, TEXT_MAX_NUM);
            textArea.value = text;
        }
    }
};