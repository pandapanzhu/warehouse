/**
 * @author pan.zhu
 *  此页面作为分页的javascript 页面，
 *  本页面还基于Jquery
 *  基于Bootstrap的组建进行分页
 */

function initPageHtml(pageNo,totalPage){
	var pageHtml='<ul class="pagination">'
	if(pageNo>1){
		if(pageNo>5){
			pageHtml+='<li><a href="javascript:;" onclick="_submitform(1)">首页</a></li>'
		}
		pageHtml+='<li><a href="javascript:;"  onclick="_submitform('+(pageNo - 1)+')">上一页</a></li>'
	}
	
	for(var i=(pageNo-1)<=0?1:(pageNo-1),no=1;i<=totalPage && no<6;i++,no++){
		if(pageNo==no){
			pageHtml+='<li class="active"><a href="javascript:void(0);" >'+i+'</a></li>'
		}else{
			pageHtml+='<li><a href="javascript:;" onclick="_submitform('+i+')">'+i+'</a></li>'
		}
	}
	
	if(pageNo<totalPage){
		pageHtml+='<li><a href="javascript:;"  onclick="_submitform('+(pageNo+1)+ ')">下一页</a></li>'
	}
	
	pageHtml+='</ul>'
	/*页面加载完毕，然后加载Js方法，上下页的点击事件*/
	pageHtml+='<script>'
	pageHtml+='function _submitform(pageNum){'
	pageHtml+='$("#pageNum").val(pageNum);'
	pageHtml+='$("#formId").submit();'
	pageHtml+='}'
	pageHtml+='</script>'
	/*页面填充*/	
	$("#pageDiv").empty().append(pageHtml);
	
}//end initPageHtml

