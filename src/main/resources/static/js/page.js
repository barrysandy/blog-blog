;(function($,window,document,undefined){
	//自定义对象构造器
	var SimplePaging = function(ele,opt){
		this.$element = ele;
		this.defaults = {
			'test':"aaa",
			//必须参数
			'cPage':1,  //当前页码
			'tPage':1,  //总页数
			'clickFun':null,  //点击调用的函数名
			//可选参数
			'space':12,  //页面间距
			'showLast':true,  //是否显示第一页和最后页按钮组       true为显示
			'showNext':true,  //是否显示下一页和上一页按钮组	 true为显示
			'div':{
				'float':'right',
				'font-size':'0.75rem',				
				'-moz-user-select': 'none',
				'-webkit-user-select':'none',
				'-ms-user-select':'none',
				'-khtml-user-select':'none',
				'user-select':'none'
			},
			'btn':{
				'display':'inline-block',
				'position': 'relative',
				'padding':'0 1.5%',
				'letter-spacing':'0px',
				'cursor':'pointer',
			},
			'btnOver':{
				'top':'-1px',
			},
			'btnOut':{
				'top':'0px',
			},
			'btnAction':{
				'display':'inline-block',
				'position': 'relative',
				'padding':'0 1.5%',
				'letter-spacing':'0px',
				'color':'#EA8010',				
			    'top':'-1px',
			    'cursor':'default',
			},
		};
		this.options = $.extend({},this.defaults,opt);
	};
	
	//自定义对象方法
	SimplePaging.prototype = {
		test:function(){
			this.$element.html(this.options.test);
			return this.$element;
		},
		writePageBtn:function(){   //   1  10  20  30 31 32 33 34 35 36 37 38 39 40 50 60 ...
			var cPage = this.options.cPage;
			var tPage = this.options.tPage;
			var space = this.options.space;
			var funName = this.options.clickFun;
			var c = Math.ceil(cPage/space);
			//var l = Math.ceil(tPage/space);
			var id = this.$element.prop("id");
			var pageDiv = '';
			//第一页
			if(this.options.showLast){
				pageDiv = pageDiv + '<span class="pageSpan_'+id+'" onclick="'+funName+'(1)">|<</span>';
			}
			//上一页
			if(this.options.showNext){
				if(cPage>1){
					pageDiv = pageDiv + '<span class="pageSpan_'+id+'" onclick="'+funName+'('+(cPage-1)+')"><<</span>';
				}else{
					pageDiv = pageDiv + '<span class="pageSpan_'+id+'" ><<</span>';
				}
			}
			//前期页区间前部分
		 	for(var i=1;i<c;i++){
		 		pageDiv = pageDiv +  '<span class="pageSpan_'+id+'" onclick="'+funName+'('+(i*space)+')">'+(i*space)+'</span>';	
			}
			//当前页区间部分
			for(var i=(c-1)*space+1;i<=c*space&&i<=tPage;i++){
				//i = (i==0)?1:i;  //i=0时从1开始
				if(cPage==i){
					pageDiv = pageDiv +  '<span class="actionPageSpan_'+id+'">'+i+'</span>';
				}else{
					pageDiv = pageDiv +  '<span class="pageSpan_'+id+'" onclick="'+funName+'('+i+')">'+i+'</span>';	
				}
			}
			//当前页区间后部分
			for(var i=c+1;i*space<=tPage;i++){
				pageDiv = pageDiv +  '<span class="pageSpan_'+id+'" onclick="'+funName+'('+(i*space)+')">'+(i*space)+'</span>';
			}
			//下一页
			if(this.options.showNext){
				if(cPage<tPage){
					pageDiv = pageDiv + '<span class="pageSpan_'+id+'" onclick="'+funName+'('+(parseInt(cPage) + 1)+')">>></span>';
				}else{
					pageDiv = pageDiv + '<span class="pageSpan_'+id+'" >>></span>';
				}
			}
			//最后页
			if(this.options.showLast){
				pageDiv = pageDiv + '<span class="pageSpan_'+id+'" onclick="'+funName+'('+tPage+')">>|</span>';	
			}
			this.$element.html(pageDiv);
			
			//设置相关样式
			var div = $.extend({},this.defaults.div,this.options.div);
			this.$element.css(div);
			var btn = $.extend({},this.defaults.btn,this.options.btn);
			this.$element.find(".pageSpan_"+id).css(btn);
			var obj = this;
			this.$element.find(".pageSpan_"+id).hover(function(){
				var btnOver =  $.extend({},obj.defaults.btnOver,obj.options.btnOver);
				$(this).css(btnOver);
			},function(){
				var btnOut =  $.extend({},obj.defaults.btnOut,obj.options.btnOut);
				$(this).css(btnOut);
			});
			this.$element.find(".actionPageSpan_"+id).css(this.options.btnAction);
		}
	};
	
	//添加插件到jQuery
	$.fn.simplePaging = function(options){
		var simplePaging = new SimplePaging(this,options);
		return simplePaging.writePageBtn();
	};
	
})(jQuery,window,document);
