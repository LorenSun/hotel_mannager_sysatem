webpackJsonp([4],[
/* 0 */,
/* 1 */
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
/* 2 */
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
/* 3 */
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
/* 4 */
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),
/* 5 */
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
/* 6 */
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),
/* 7 */,
/* 8 */,
/* 9 */,
/* 10 */,
/* 11 */,
/* 12 */
/***/ (function(module, exports, __webpack_require__) {

var _wh = __webpack_require__(0);

var _product = {
    // 获取商品列表
    getProductList : function(listParam, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/product/list.do'),
            data    : listParam,
            success : resolve,
            error   : reject
        });
    },
    // 获取商品详细信息
    getProductDetail : function(productId, resolve, reject){
        _wh.request({
            url     : _wh.getServerUrl('/product/detail.do'),
            data    : {
                productId : productId
            },
            success : resolve,
            error   : reject
        });
    }
}
module.exports = _product;


/***/ }),
/* 13 */,
/* 14 */,
/* 15 */,
/* 16 */,
/* 17 */,
/* 18 */,
/* 19 */,
/* 20 */,
/* 21 */,
/* 22 */,
/* 23 */,
/* 24 */,
/* 25 */,
/* 26 */,
/* 27 */,
/* 28 */,
/* 29 */,
/* 30 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(31);


/***/ }),
/* 31 */
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(32);
__webpack_require__(3);
__webpack_require__(5);
var _wh             = __webpack_require__(0);
var _product        = __webpack_require__(12);
var _cart           = __webpack_require__(2);
var templateIndex   = __webpack_require__(33);

var page = {
    data : {
        productId : _wh.getUrlParam('productId') || '',
    },
    init : function(){
        this.onLoad();
        this.bindEvent();
    },
    onLoad : function(){
        // 如果没有传productId, 自动跳回首页
        if(!this.data.productId){
            _wh.goHome();
        }
        this.loadDetail();
    },
    bindEvent : function(){
        var _this = this;
        // 图片预览
        $(document).on('mouseenter', '.p-img-item', function(){
            var imageUrl   = $(this).find('.p-img').attr('src');
            $('.main-img').attr('src', imageUrl);
        });
        // count的操作
        $(document).on('click', '.p-count-btn', function(){
            var type        = $(this).hasClass('plus') ? 'plus' : 'minus',
                $pCount     = $('.p-count'),
                currCount   = parseInt($pCount.val()),
                minCount    = 1,
                maxCount    = _this.data.detailInfo.stock || 1;
            if(type === 'plus'){
                $pCount.val(currCount < maxCount ? currCount + 1 : maxCount);
            }
            else if(type === 'minus'){
                $pCount.val(currCount > minCount ? currCount - 1 : minCount);
            }
        });
        // 加入购物车
        $(document).on('click', '.cart-add', function(){
            _cart.addToCart({
                productId   : _this.data.productId,
                count       : $('.p-count').val()
            }, function(res){
                window.location.href = './result.html?type=cart-add';
            }, function(errMsg){
                _wh.errorTips(errMsg);
            });
        });
    },
    // 加载商品详情的数据
    loadDetail : function(){
        var _this       = this,
            html        = '',
            $pageWrap   = $('.page-wrap');
        // loading
        $pageWrap.html('<div class="loading"></div>');
        // 请求detail信息
        _product.getProductDetail(this.data.productId, function(res){
            _this.filter(res);
            // 缓存住detail的数据
            _this.data.detailInfo = res;
            // render
            html = _wh.renderHtml(templateIndex, res);
            $pageWrap.html(html);
        }, function(errMsg){
            $pageWrap.html('<p class="err-tip">此商品太淘气，找不到了</p>');
        });
    },
    // 数据匹配
    filter : function(data){
        data.subImages = data.subImages.split(',');
    }
};
$(function(){
    page.init();
})


/***/ }),
/* 32 */
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),
/* 33 */
/***/ (function(module, exports) {

module.exports = "<div class=\"intro-wrap\">\n    <div class=\"p-img-con\">\n        <div class=\"main-img-con\">\n            <img class=\"main-img\" src=\"{{imageHost}}{{mainImage}}\" alt=\"{{name}}\"/>\n        </div>\n        <ul class=\"p-img-list\">\n            {{#subImages}}\n            <li class=\"p-img-item\">\n                <img class=\"p-img\" src=\"{{imageHost}}{{.}}\" alt=\"{{name}}\" />\n            </li>\n            {{/subImages}}\n        </ul>\n    </div>\n    <div class=\"p-info-con\">\n        <h1 class=\"p-name\">{{name}}</h1>\n        <p class=\"p-subtitle\">{{subtitle}}</p>\n        <div class=\"p-info-item p-price-con\">\n            <span class=\"label\">价格:</span>\n            <span class=\"info\">￥{{price}}</span>\n        </div>\n        <div class=\"p-info-item\">\n            <span class=\"label\">库存:</span>\n            <span class=\"info\">{{stock}}</span>\n        </div>\n        <div class=\"p-info-item p-count-con\">\n            <span class=\"label\">数量:</span>\n            <input class=\"p-count\" value=\"1\" readonly=\"\"/>\n            <span class=\"p-count-btn plus\">+</span>\n            <span class=\"p-count-btn minus\">-</span>\n        </div>\n        <div class=\"p-info-item\">\n            <a class=\"btn cart-add\">加入购物车</a>\n        </div>\n    </div>\n</div>\n<div class=\"detail-wrap\">\n    <div class=\"detail-tab-con\">\n        <ul class=\"tab-list\">\n            <li class=\"tab-item active\">详细描述</li>\n        </ul>\n    </div>\n    <div class=\"detail-con\">\n        {{{detail}}}\n    </div>\n</div>";

/***/ })
],[30]);