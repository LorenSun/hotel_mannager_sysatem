webpackJsonp([1],{

/***/ 1:
/***/ (function(module, exports, __webpack_require__) {

var _wh = __webpack_require__(0);

var _user = {
    // 用户登录
    login : function(userInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/login.do'),
            data    : userInfo,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 检查用户名
    checkUsername : function(username, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/check_valid.do'),
            data    : {
                type    : 'username',
                str     : username
            },
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 用户注册
    register : function(userInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/register.do'),
            data    : userInfo,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 检查登录状态
    checkLogin : function(resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/get_user_info.do'),
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 检查用户信息
    checkAnswer : function(userInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/forget_check_answer.do'),
            data    : userInfo,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 重置密码
    resetPassword : function(userInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/forget_reset_password.do'),
            data    : userInfo,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 获取用户信息
    getUserInfo : function(resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/get_information.do'),
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 更新个人信息
    updateUserInfo : function(userInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/update_information.do'),
            data    : userInfo,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 登录状态下更新密码
    updatePassword : function(userInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/reset_password.do'),
            data    : userInfo,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    // 登出
    logout : function(resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/hotel/user/logout.do'),
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    }
}
module.exports = _user;


/***/ }),

/***/ 2:
/***/ (function(module, exports, __webpack_require__) {

var _wh = __webpack_require__(0);

var _cart = {
    // 添加到购物车
    addToCart : function(productInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/add.do'),
            data    : productInfo,
            success : resolve,
            error   : reject
        });
    },
    // 获取购物车列表
    getCartList : function(resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/list.do'),
            success : resolve,
            error   : reject
        });
    },
    // 选择购物车商品
    selectProduct : function(productId, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/select.do'),
            data    : {
                productId : productId
            },
            success : resolve,
            error   : reject
        });
    },
    // 取消选择购物车商品
    unselectProduct : function(productId, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/un_select.do'),
            data    : {
                productId : productId
            },
            success : resolve,
            error   : reject
        });
    },
    // 选中全部商品
    selectAllProduct : function(resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/select_all.do'),
            success : resolve,
            error   : reject
        });
    },
    // 取消选中全部商品
    unselectAllProduct : function(resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/un_select_all.do'),
            success : resolve,
            error   : reject
        });
    },
    // 更新购物车商品数量
    updateProduct : function(productInfo, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/update.do'),
            data    : productInfo,
            success : resolve,
            error   : reject
        });
    },
    // 删除指定商品
    deleteProduct : function(productIds, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/cart/delete_product.do'),
            data    : {
                productIds : productIds
            },
            success : resolve,
            error   : reject
        });
    },
}
module.exports = _cart;


/***/ }),

/***/ 3:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(4);
var _wh     = __webpack_require__(0);
var _user   = __webpack_require__(1);
var _cart   = __webpack_require__(2);
// 导航
var nav = {
    init : function(){
        this.bindEvent();
        this.loadUserInfo();
        return this;
    },
    bindEvent : function(){
        // 登录点击事件
        $('.js-login').click(function(){
            _wh.doLogin();
        });
        // 注册点击事件
        $('.js-register').click(function(){
            window.location.href = './user-register.html';
        });
        // 退出点击事件
        $('.js-logout').click(function(){
            _user.logout(function(res){
                window.location.reload();
            }, function(errMsg){
                _wh.errorTips(errMsg);
            });
        });
    },
    // 加载用户信息
    loadUserInfo : function(){
        _user.checkLogin(function(res){
            $('.user-info .not-login').hide().siblings('.user-info .login').show()
                .find('.username').text(res.username);
        }, function(errMsg){
            // do nothing
        });
    }
};

module.exports = nav.init();


/***/ }),

/***/ 4:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 47:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(48);


/***/ }),

/***/ 48:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(49);
__webpack_require__(3);
__webpack_require__(5);
var navSide         = __webpack_require__(7);
var _wh             = __webpack_require__(0);
var _user           = __webpack_require__(1);
var templateIndex   = __webpack_require__(50);

// page 逻辑部分
var page = {
    init: function(){
        this.onLoad();
    },
    onLoad : function(){
        // 初始化左侧菜单
        navSide.init({
            name: 'user-center'
        });
        // 加载用户信息
        this.loadUserInfo();
    },
    // 加载用户信息
    loadUserInfo : function(){
        var userHtml = '';
        _user.getUserInfo(function(res){
            userHtml = _wh.renderHtml(templateIndex, res);
            $('.panel-body').html(userHtml);
        }, function(errMsg){
            _wh.errorTips(errMsg);
        });
    }
};
$(function(){
    page.init();
});


/***/ }),

/***/ 49:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 5:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(6);

var _wh     = __webpack_require__(0);

// 通用页面头部
var header = {
    init : function(){
        this.bindEvent();
    },
    onload : function(){
      var keyword = _wh.getUrlParam('keyword');
      //keyword存在，则回填输入框
      if(keyword){
        $('#search-input').value(keyword);
      }
    },
    bindEvent : function(){
      var _this = this;
      //点击搜索按钮之后，搜索提交
      $('#search-btn').click(function(){
        _this.searchSubmit();
      });
      //输入回车后，也做搜索提交
      $('#search-input').keyup(function(e){
        //13是回车键的keycode
        if(e.keyCode === 13){
          _this.searchSubmit();
        }
      });
      },
    // 搜索的提交
    searchSubmit: function(){
      var keyword = $.trim($('#search-input').val());
      //如果提交的时候有keyword，正常跳转到list页
      if(keyword){
        window.location.href = './list.html?keyword=' + keyword;
      }
      //如果keyword为空，直接返回首页
      else{
        _wh.goHome();
      }
    }
};

header.init();


/***/ }),

/***/ 50:
/***/ (function(module, exports) {

module.exports = "<div class=\"user-info\">\n    <div class=\"form-line\">\n        <span class=\"label\">用户名：</span>\n        <span class=\"text\">{{username}}</span>\n    </div>\n    <div class=\"form-line\">\n        <span class=\"label\">电  话：</span>\n        <span class=\"text\">{{phone}}</span>\n    </div>\n    <div class=\"form-line\">\n        <span class=\"label\">邮  箱：</span>\n        <span class=\"text\">{{email}}</span>\n    </div>\n    <div class=\"form-line\">\n        <span class=\"label\">身份证：</span>\n        <span class=\"text\">{{id_card}}</span>\n    </div>\n    <a class=\"btn btn-submit\" href=\"./user-center-update.html\">编辑</a>\n</div>\n";

/***/ }),

/***/ 6:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 7:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(8);
var _wh             = __webpack_require__(0);
var templateIndex   = __webpack_require__(9);
// 侧边导航
var navSide = {
    option : {
        name : '',
        navList : [
            {name : 'user-center', desc : '个人中心', href: './user-center.html'},
            {name : 'order-list', desc : '我的订单', href: './order-list.html'},
            {name : 'user-pass-update', desc : '修改密码', href: './user-pass-update.html'},
            {name : 'about', desc : '关于WindHotel', href: './about.html'}
        ]
    },
    init : function(option){
        // 合并选项
        $.extend(this.option, option);
        this.renderNav();
    },
    // 渲染导航菜单
    renderNav : function(){
        // 计算active数据
        for(var i = 0, iLength = this.option.navList.length; i < iLength; i++){
            if(this.option.navList[i].name === this.option.name){
                this.option.navList[i].isActive = true;
            }
        };
        // 渲染list数据
        var navHtml = _wh.renderHtml(templateIndex, {
            navList : this.option.navList
        });
        // 把html放入容器
        $('.nav-side').html(navHtml);
    }
};

module.exports = navSide;


/***/ }),

/***/ 8:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 9:
/***/ (function(module, exports) {

module.exports = "{{#navList}}\r\n{{#isActive}}\r\n<li class=\"nav-item active\">\r\n{{/isActive}}\r\n{{^isActive}}\r\n<li class=\"nav-item\">\r\n{{/isActive}}\r\n    <a class=\"link\" href=\"{{href}}\">{{desc}}</a>\r\n</li>\r\n{{/navList}}\r\n";

/***/ })

},[47]);