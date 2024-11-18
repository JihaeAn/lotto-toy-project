$(function (){
    getStoreList();
})

function getStoreList() {
    $.ajax({
        url: '/store/get/storeList',
        method: 'GET',
        contentType: 'application/json',
        success: function (data) {

            data.forEach(function (item) {

                const store = `
                    <li class="store-item">
                        <div class="store-name">${item.storeName}</div>
                        <div class="store-address">${item.address}</div>
                    </li>
                `;

                $(".store-list").append(store);
                $("#store-info").text(`${item.drawNum}회 1등 배출 판매점`)
            })
        },
        error: function (error) {
            console.log("error", error);
        }
    })
}