/**
 * 批量删除
 */

function deleteBatch(basePath){
	$("#mainForm").attr("action",basePath+"DeleteBatchServlet.action");
	$("#mainForm").submit();
}
/**
 * 修改当前页码，调用后重新查询*/
function changeCurrentPage(currentPage){
	//alert(currentPage);
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}



