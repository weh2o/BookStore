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
    let countList = $(".count");
    let total = 0;
    for (let i = 0; i < countList.length; i++) {
        let count = parseInt($(".count").eq(i).val());
        total += count;
    }
    $(".cart_totalCount").text(total);
}

/**
 * 輸入框修改商品數量
 * @param obj
 */
function updateCount(obj) {
    let cartIndex = $(".count").index(obj);
    let cartId = $(".cartId").eq(cartIndex).val();
    let count = parseInt($(".count").eq(cartIndex).val());
    let price = parseInt($(".price").eq(cartIndex).text());

    let cost = count * price;

    $.ajax({
        url:"/cart/updateCount/" + cartId + "/" + count,
        type:"POST",
        success:function (data) {
            if (data == "success") {
                $(".cost").eq(cartIndex).text(cost);
                totalCount();
                totalAmount();
            }
        }
    });
}

/**
 * AJAX異步請求
 * @param index 當前圖書索引
 * @param count 圖書總數
 */
function ajaxUpdate(index,count) {

    let cartId = $(".cartId").eq(index).val();
    let price = parseInt($(".price").eq(index).text());
    let cost = count * price;

    $.ajax({
        url:"/cart/updateCount/" + cartId + "/" + count,
        type:"POST",
        success:function (data) {
            if (data == "success") {
                $(".count").eq(index).val(count);
                $(".cost").eq(index).text(cost);
                totalCount();
                totalAmount();
            }
        }
    });
}


/**
 * 按鈕: 商品數量增加
 * @param obj 調用該函數的對象
 * @returns {boolean}
 */
function addQuantity(obj) {
    let index = $(".cart-add").index(obj);
    //獲取當前商品數量
    let count = parseInt($(".count").eq(index).val());
    //獲取隱藏域中的庫存
    let stock = parseInt($(".bookStock").eq(index).val());

    //判斷庫存是否足夠
    if (count >= stock){ //購物車內的商品數 >= 庫存
        alert("已達可購買的上限")
        return false; //中止
    }
    count++;
    ajaxUpdate(index,count);
}

/**
 * 按鈕: 商品數量減少
 * @param obj 調用該函數的對象
 * @returns {boolean}
 */
function subQuantity(obj) {
    //獲取當前調用者的索引
    let index = $(".cart-subtract").index(obj);
    //獲取當前商品數量
    let count = parseInt($(".count").eq(index).val());
    if (count == 1) {
        alert("至少選擇一本圖書")
        return false;
    }
    count--;
    ajaxUpdate(index,count);

}
//清空購物車
function deleteAll() {
    if (window.confirm("是否要清空購物車?")){
        location.href = "/cart/deleteAll";
    }
}
