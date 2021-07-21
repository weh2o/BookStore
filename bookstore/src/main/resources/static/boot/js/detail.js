$(function () {
    totalCount();
    totalAmount();

});

/**
 * 計算購物車內總金額
 */
function totalAmount() {
    let costList = $(".cost");
    let totalCost = 0;
    for (let i = 0; i < costList.length; i++) {
        let cost = parseInt($(".cost").eq(i).html());
        totalCost += cost;
    }
    $(".cart_totalAmount").text(totalCost);
}

/**
 * 計算購物車內商品總數
 */
function totalCount() {
    let countList = $(".quantity");
    let total = 0;
    for (let i = 0; i < countList.length; i++) {
        let count = parseInt($(".quantity").eq(i).text());
        total += count;
    }
    $(".cart_totalCount").text(total);
}

/* settlement.html  */
/**
 * 結帳
 */
function checkout() {
    let total = $(".cart_totalAmount").text();
    location.href = "/orders/checkout/" + total;
}

/**
 * 開啟變更收貨人資料的模態框
 */
function userInfoModal() {
    $("#userInfoUpdateModal").modal({
        show: true,
        backdrop: 'static'
    });
}

/**
 * 更新頁面中收貨人資料
 */
function userInfoUpdate() {
    $.ajax({
        url:"/user/updateToOrders",
        type:"POST",
        data:$("#editUserForm").serialize(),
        dataType:"json",
        success:function (data) {
            //修改頁面中展示的數據
            $("#userName").text(data.userName);
            $("#userPhone").text(data.phone);
            $("#userAddress").text(data.address);
        }
    });
    //關閉模態框
    $("#userInfoUpdateModal").modal('hide');
}


