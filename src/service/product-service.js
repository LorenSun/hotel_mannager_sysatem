var _wh = require('util/wh.js');

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