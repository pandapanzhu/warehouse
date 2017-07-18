/**
 * 
 */
	$(function(){
		
		parent.layer.alert("所有项必须全部填写！<br>请保证信息尽量真实,若审核不通过，将冻结账号！");

		
		$("form").bootstrapValidator({
            message: '信息有误',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            live: "disabled",
            fields: {
            	realname: {
                    validators: {
                        notEmpty: {
                            message: '名称不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '名称的长度应该在2-30之间'
                        }
                    }
                },
                idcard: {
                    validators: {
                        notEmpty: {
                            message: '身份证不能为空'
                        },
                        stringLength: {
                            min: 16,
                            max: 18,
                            message: '身份证长度应该在16-18之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: '身份证只能由字母和数字组成'
                        }
                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        stringLength: {
                            min: 10,
                            max: 11,
                            message: '手机号长度应该是11位'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: '手机号只能由数字组成'
                        }
                    }
                },
                address: {
                    validators: {
                        notEmpty: {
                            message: '宿舍不能为空'
                        },
                        stringLength: {
                            min: 5,
                            max: 30,
                            message: '宿舍长度应该是8-30之间'
                        }
                    }
                },
                stuid: {
                    validators: {
                        notEmpty: {
                            message: '学号不能为空'
                        },
                        stringLength: {
                            min: 8,
                            max: 15,
                            message: '学号长度应该是8-15之间'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: '学号只能由数字组成'
                        }
                    }
                },
                facultyid: {
                    validators: {
                        notEmpty: {
                            message: '学院不能为空'
                        }
                    }
                },
                classes:{
                    validators: {
                        notEmpty: {
                            message: '班级不能为空'
                        },
                        stringLength: {
                            min: 8,
                            max: 15,
                            message: '班级长度应该是8-15之间'
                        }
                    }
                }
            }
        });
	});