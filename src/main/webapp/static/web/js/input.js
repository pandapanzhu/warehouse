/**
 * 上传文件的js
 */
$(function(){
	
	$("form").bootstrapValidator({
        message: '信息有误',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        live: "disabled",
        fields: {
        	name: {
                validators: {
                    notEmpty: {
                        message: '商品名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: '商品名称的长度应该在2-30之间'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty: {
                        message: '商品描述不能为空'
                    },
                    stringLength: {
                        min: 16,
                        max: 18,
                        message: '商品描述长度应该在15-100之间'
                    }
                }
            },
            largecategory: {
                validators: {
                    notEmpty: {
                        message: '分类不能为空'
                    }
                }
            },
            price: {
                validators: {
                    notEmpty: {
                        message: '价格不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '价格应该是1-1亿之间'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '价格只能由数字组成'
                    }
                }
            },
            degree: {
                validators: {
                    notEmpty: {
                        message: '折旧不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]{1}$/,
                        message: '折旧只能由1位数字组成'
                    }
                }
            },
            stock: {
                validators: {
                    notEmpty: {
                        message: '库存不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 5,
                        message: '库存应该是1-5位数之间'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '库存只能由数字组成'
                    }
                }
            },
            transactionaddress:{
                validators: {
                    notEmpty: {
                        message: '交易地点不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '交易地点长度应该是1-20之间'
                    }
                }
            }
        }
    });
	
	
	
	
	//上传图片的返回事件
	 $("#file_input").on("fileuploaded", function(event, data, previewId, index) {
	        parent.layer.msg("上传成功!");
	        console.log(data.response.filename);
	        var html='<input type="hidden" id="imgUrl" name="image" value="'+data.response.filename+'"> '
	       	$("#images").append(html);
	  });
	 
 	//根据大分类，查询小分类
 	$("#largeCategory").change(function(){
 		var id=$(this).val();
 		getsmall(id);
 	});
})






/**
 * 初始化上传文件的属性
 */
function initFileInput(ctrName,uploadUrl){
	//id
	var control=$("#"+ctrName);
	
	control.fileinput({
		language:'zh',//语言
		uploadUrl:uploadUrl, //url
		showPreview:true,
		uploadAsync: true,
		allowedFileExrensions:['jpg','png','gif'],//后缀
		showUpload:true,//是否显示上传按钮
		showCaption:false,//是否显示标题
		browseClass:'btn btn-info',//按钮样式
		priviewFileIcon:"<i class='glyphicon glyphicon-king></i>'",
		maxFileSize:3096,//图片最大为3096M
		maxFileCount:8,
		minFileCount :1,
		enctype:'multipart/form-data',
		msgFilesTooMany:"请选择上传的文件数量({n}) 超过允许上传的最大数值{m}! ",
		initialCaption:"请选择最多10张照片作为商品图片！",
		//这是文件名替换
		slugCallback:function(filename) {
	      return filename.replace('(', '_').replace(']', '_');
	    }
	})
}//end init

