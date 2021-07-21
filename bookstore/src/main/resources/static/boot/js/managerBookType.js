/**
 * 開啟模態框:添加圖書類型
 */
function addBookType() {
    $("#addBookType").modal({
        show: true,
        backdrop: 'static'
    });
}
/**
 * 開啟模態框:修改圖書類型
 * @param id 要修改的圖書類型ID
 * @param obj 調用當前方法的對象
 */
function editBookType(id,obj) {
    //獲取當前類型
    let index = $(".btn-info").index(obj);
    //由於第一個索引"未分類"隱藏了，所以索引+1
    let bookTypeName = $(".bookTypeName").eq(index +1).text();
    $("#nowBookType").val(bookTypeName);
    $(".bookTypeId").val(id);
    //展示所有圖書類型

    //開啟模態框
    $("#editBookType").modal({
        show: true,
        backdrop: 'static'
    });
}

/**
 * 刪除圖書類型
 * @param id 要刪除的圖書類型ID
 * @param obj 調用當前方法的對象
 */
function deleteType(id,obj) {
    let index = $(".deleteBtn").index(obj);
    //由於第一個索引"未分類"隱藏了，所以索引+1
    let bookName = $(".bookTypeName").eq(index + 1).text();
    if (window.confirm('確定要刪除' + ' ' + bookName + ' ' + '嗎?')) {
        location.href = "/bookType/delete/" + id;
    }
}