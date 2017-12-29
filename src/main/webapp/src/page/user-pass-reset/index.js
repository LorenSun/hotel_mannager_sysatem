require('./index.css');
require('page/common/nav-simple/index.js');
var _user   = require('service/user-service.js');
var _wh     = require('util/wh.js');

// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.err-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.err-msg').text('');
    }
};

// page 逻辑部分
var page = {
    data : {
        username    : '',
        phone       : '',
        id_card     : '',
        token       : ''
    },
    init: function(){
        this.onLoad();
        this.bindEvent();
    },
    onLoad : function(){
        this.loadStepUsername();
    },
    bindEvent : function(){
        var _this = this;
        // 输入用户名后下一步按钮的点击
        $('#submit-username').click(function(){
            var username = $.trim($('#username').val());
            var phone = $.trim($('#phone').val());
            var id_card = $.trim($('#id_card').val());
            // 用户名存在
            if(username){
              _user.checkAnswer({
                  username : username,
                  phone : phone,
                  id_card   : id_card
              }, function(res){
                  _this.data.username    = username;
                  _this.data.phone    = phone;
                  _this.data.id_card  = id_card;
                  _this.data.token    = '2116418c-bec6-4b76-aca2-8617400be773';
                  _this.loadStepPassword();
              }, function(errMsg){
                  formError.show(errMsg);
              });
            }
            // 用户名不存在
            else{
                formError.show('用户名不存在');
            }
        });
        // 输入新密码后的按钮点击
        $('#submit-password').click(function(){
            var password = $.trim($('#password').val());
            // 密码不为空
            if(password && password.length >= 6){
                // 检查密码提示问题答案
                _user.resetPassword({
                    username        : _this.data.username,
                    passwordNew     : password,
                    forgetToken     : _this.data.token
                }, function(res){
                    window.location.href = './result.html?type=pass-reset';
                }, function(errMsg){
                    formError.show(errMsg);
                });
            }
            // 密码为空
            else{
                formError.show('请输入不少于6位的新密码');
            }
        });

    },
    // 加载输入用户名的一步
    loadStepUsername : function(){
        $('.step-username').show();
    },
    // 加载输入password的一步
    loadStepPassword : function(){
        // 清除错误提示
        formError.hide();
        // 做容器的切换
        $('.step-username').hide()
            .siblings('.step-password').show();
    }

};
$(function(){
    page.init();
});
