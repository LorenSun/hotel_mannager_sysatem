var Hogan = require('hogan.js');
var conf = {
  serverHost : ''
};
var _wh = {
  request :function(param){
    var _this = this;
    $.ajax({
      type    : param.method || 'get' ,
      url     : param.url    || '',
      dataType: param.type   || 'json',
      data    : param.data   || '',
      xhrFields: {
                    withCredentials: true
                },  
      crossDomain: true,
      //cache: false, //设置为 false 将不会从浏览器缓存中加载请求信息。
      //async: true, //(默认: true)，所有请求均为异步请求。发送同步请求，请将此选项设置为 false。同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
      //timeout: 1500, //设置请求超时时间（毫秒）。此设置将覆盖全局设置。
      success : function(res){
                alert(res.status+' '+res.msg);
                // 请求成功
                if(0 === res.status){
                    typeof param.success === 'function' && param.success(res.data, res.msg);
                }
                // 没有登录状态，需要强制登录
                else if(10 === res.status){
                    _this.doLogin();
                }
                // 请求数据错误
                else if(1 === res.status){
                    typeof param.error === 'function' && param.error(res.msg);
                }
      },
      error   : function(err){
          typeof param.error() === 'function' && param.error(err.statusText);
      }
    });
  },
  //获取服务器地址
  getServerUrl : function(path){
    return conf.serverHost + path;
  },
  //获取url参数
  getUrlParam : function(name){
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
    var result = window.location.search.substr(1).match(reg);
    return result ? decodeURIComponent(result[2]) : null;
  },
  //渲染html模板
  renderHtml :function(htmlTemplate,data){
    var template = Hogan.compile(htmlTemplate);
    var result = template.render(data);
    return result;
  },
  // 成功提示
  successTips : function(msg){
      alert(msg || '操作成功！');
  },
  // 错误提示
  errorTips : function(msg){
      alert(msg || '哪里不对了~');
  },
  // 字段的验证，支持非空、手机、邮箱的判断
  validate : function(value, type){
      var value = $.trim(value);
      // 非空验证
      if('require' === type){
          return !!value;
      }
      // 手机号验证
      if('phone' === type){
          return /^1\d{10}$/.test(value);
      }
      // 邮箱格式验证
      if('email' === type){
          return /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(value);
      }
      if('idcard' === type){
          return /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\d)|(3[0-1]))((\d{4})|(\d{3}[Xx]))$/.test(value);
      }
  },
  //统一登录处理
  doLogin : function(){
    window.location.href = './user-login.html?redirect='+ encodeURIComponent(window.location.href);
  },
  //返回首页
  goHome :function(){
    window.location.href = './index.html';
  }
};
module.exports = _wh;
