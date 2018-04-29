/*系统后台交互效果*/
var bdyc = {
	/*系统首页初始化*/
	init:function(){
		//初始化顶部菜单userinfo箭头切换效果
		this.toggleArrow();
		
		this.initHeight();
		
		this.toggleNavList();
		
		this.toggleNavActive();
	},
	/*切换顶部工具条三角方向方法*/
	toggleArrow:function(){
		//获取userinfo元素对象
		var userInfoObj = document.getElementsByClassName("user-info")[0];
		var userInfoArrowObj = document.getElementById("user-info-arrow");
		//绑定鼠标移入事件
		userInfoObj.onmouseover = function(){
			userInfoArrowObj.setAttribute("class","fa fa-caret-up");
		}
		//绑定鼠标移出事件
		userInfoObj.onmouseout = function(){
			userInfoArrowObj.setAttribute("class","fa fa-caret-down");
		}
	},
	/*修改左侧导航高度*/
	initHeight:function(){
		/*获取当前屏幕高度*/
		var leftHeight = document.body.scrollHeight-60;
		var bdContentNavObj = document.getElementsByClassName("bd-content-nav")[0];
		//设置左侧高度
		bdContentNavObj.style["min-height"] = (leftHeight + 80)+"px";
		
		var bdContentRight = document.getElementsByClassName("bd-content-right")[0];
		//设置右侧高度
		bdContentRight.style["min-height"] = (leftHeight) + "px";
		
		//设置iframe最小高度
		var mainObj = document.getElementById("main");
		mainObj.style['min-height'] = (leftHeight-45) +"px";
		
	},
	/*左侧导航折叠效果*/
	toggleNavList:function(){
		//获取所有顶级菜单
		var parentListItems = document.getElementsByClassName("parent-list-item");
		for(var i=0; i<parentListItems.length;i++){
			parentListItems[i].onclick = function(){
				//从当前a标签的父元素li中查询类名为son-list
				var sonList = this.parentElement.getElementsByClassName("son-list")[0];
				//从当前a标签中获取对应的三角箭头
				var sanjiao = this.getElementsByClassName("sanjiao")[0];
				
				//如果sonList的display状态为none,应该显示，反之隐藏
				if(sonList.style.display == "none"){
					sonList.style.display = "block";
					//将箭头转换为向下方向箭头
					sanjiao.setAttribute("class","fa fa-angle-down sanjiao");
				}else{
					sonList.style.display = "none";
					//将箭头转换为向左方向箭头
					sanjiao.setAttribute("class","fa fa-angle-left sanjiao");
				}
				
			}
		}
	},
	/*切换左侧菜单活动状态*/
	toggleNavActive:function(){
		//获取所有sonList 的 ul
		var sonList = document.getElementsByClassName("son-list");
		for(var i=0; i<sonList.length;i++){
			//获取到sonList内部的所有a
			var sonListAList = sonList[i].getElementsByTagName("a");
			for(var j=0;j<sonListAList.length;j++){
				sonListAList[j].onclick=function(){
					//去掉所有a标签的活动状态
					bdyc.clearActive();
					
					//切换活动状态
					this.setAttribute("class","active");
					
					//改变右侧内容区内容
					var mainObj = document.getElementById("main");
					var src = this.getAttribute("alt");
					mainObj.setAttribute("src",src);
					
					//改变右侧title内容
					var text = this.getAttribute("title");
					var appendLi = '<li><a href="#"><i class="fa fa-home"></i> 首页 </a></li>';
					    appendLi += '<li><a href="#"> &nbsp; > &nbsp; </a></li>';
						appendLi += '<li><a href="#"> '+text+' </a></li>';
					//追加到ul后面
					var ul = document.getElementById("content-right-title-ul");
					ul.innerHTML = appendLi;
				}
			}
		}
	},
	/*清除所有sonList中a标签的活动状态*/
	clearActive:function(){
		//获取所有sonList 的 ul
		var sonList = document.getElementsByClassName("son-list");
		for(var i=0; i<sonList.length;i++){
			//获取到sonList内部的所有a
			var sonListAList = sonList[i].getElementsByTagName("a");
			for(var j=0;j<sonListAList.length;j++){
				sonListAList[j].removeAttribute("class");
			}
		}
	},
	/*设置tablebox动态高度*/
	setTableBoxHeight:function(){
		//获取到当前屏幕高度：
		var height = document.body.scrollHeight - 85;
		var tableBox = document.getElementsByClassName("table-box")[0];
		tableBox.style['min-height'] = height +"px";
		
		var tableList = document.getElementsByClassName("table-list")[0];
		tableList.style['min-height'] = (height - 42)+"px";
	},
	/*table表格全选效果*/
	tableCheckAll:function(){
		var tableCheckAll = document.getElementsByClassName("table-check-all")[0];
		var tableCheckItems = document.getElementsByClassName("table-check-item");
		tableCheckAll.onclick = function(){
			//全选框状态值
			var flag = this.checked;
			for(var i=0; i<tableCheckItems.length;i++){
				//自选择框的checked值应该和全选框状态一致
				tableCheckItems[i].checked = flag;
				if(flag){
					//为所有的tr添加背景色
					var table = document.getElementsByClassName("content-list-table")[0];
					var trs = table.getElementsByTagName("tr");
					for(var j=1; j<trs.length;j++){
						//改变当前行的背景颜色
						trs[j].style['background-color']="#dff0d8";
					}
				}else{
					//为所有的tr添加背景色
					var table = document.getElementsByClassName("content-list-table")[0];
					var trs = table.getElementsByTagName("tr");
					for(var j=1; j<trs.length;j++){
						//改变当前行的背景颜色
						trs[j].style['background-color']="#fff";
					}
				}
			}
		}
	},
	/*单击每一行选中复选框效果*/
	tableTrClick:function(){
		var table = document.getElementsByClassName("content-list-table")[0];
		var trs = table.getElementsByTagName("tr");
		for(var i=1; i<trs.length;i++){
			trs[i].onclick=function(e){
				//选中当前行内部的复选框
				var inputObj = this.getElementsByClassName("table-check-item")[0];
				if(inputObj.checked){
					inputObj.checked = false;
					//改变当前行的背景颜色
					this.style['background-color']="#fff";
				}else{
					inputObj.checked = true;
					//改变当前行的背景颜色
					this.style['background-color']="#dff0d8";
				}
			}
		}
	},
	/*单击每一行复选框选中效果*/
	tableTrCheckboxClick:function(){
		var checkboxs = document.getElementsByClassName("table-check-item");
		for(var i=0;i<checkboxs.length; i++){
			//为所有的复选框重置单击事件:e event（当前事件对象）
			checkboxs[i].onclick=function(e){
				//阻止tr的单击事件冒泡，tr的单击事件当单击复选框是就不会触发
				e.stopPropagation();
				if(!this.checked){
					this.checked = false;
					//改变当前行的背景颜色
					this.parentElement.parentElement.style['background-color']="#fff";
				}else{
					this.checked = true;
					//改变当前行的背景颜色
					this.parentElement.parentElement.style['background-color']="#dff0d8";
				}
			}
		}
	}

};
