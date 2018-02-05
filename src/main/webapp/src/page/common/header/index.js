require('./index.css');

var _wh     = require('util/wh.js');

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
