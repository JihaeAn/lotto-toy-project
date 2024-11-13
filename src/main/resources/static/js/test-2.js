$(function () {

})

// 역대 당첨 번호 검색
function searchWinningNum(drawNum) {


    $.ajax({
        url: `/lotto/get/lottery?drawNum=${drawNum}`,
        method: 'GET',
        contentType: 'application/json',
        success: function (data) {
            console.log("역대 당첨 번호 검색 ", data)
        },
        error: function (error) {
            console.log("오류 발생 ", error);
        }
    })
}
