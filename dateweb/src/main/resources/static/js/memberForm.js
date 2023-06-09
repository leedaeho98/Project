$(document).ready(function(){
    var errorMessage = [[${errorMessage}]]; // 회원 등록 시 실패 메시지를 받아서 멤버 페이지 재진입 시 alert를 통해서 실패사유를 보여줌
    if(errorMessage != null){
        alert(errorMessage);
    }
    bindDomEvent();
});

function bindDomEvent(){
    $(".custom-file-input").on("change", function(){
        var fileName = $(this).val().split("\\").pop(); // 이미지 파일명
        var fileExt = fileName.substring(fileName.lastIndexOf(".")+1;
        // 확장자 추출
        fileExt = fileExt.toLowerCase(); // 소문자 변환

        if(fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif" && fileExt != "png" && fileExt != "bmp"){ // 이 확장자가 아니면
            alert("이미지 파일만 등록이 가능합니다") // 화면에 출력해라
            return;
        }
        $(this).siblings(".custom-file-label").html(fileName); // label 태그 안의 내용을 jquery의 .html()을 이용하여 파일명을 입력
    })
}