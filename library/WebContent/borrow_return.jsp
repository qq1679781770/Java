<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/uikit.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/uikit.min.js"></script>
<script type="text/javascript" src="js/vue.min.js"></script>
<script type="text/javascript" src="js/jsxnh.js"></script>
<script>
$(document).ready(function(){
    var vm=new Vue({
        el:'#borrow_return',
        data:{
            val:''
            },
        methods:{
            begin:function(){
                   getJSON('api/inquireborrow',{student_id:this.val},function(data){
                	      document.getElementById("uk-list").innerHTML="";
                          borrowvm.borrows=data;
                       });
                }
            }
        });

    var borrowvm=new Vue({
        el:'#borrowbook',
        data:{borrows:[{}]}
        });

	
	$("#borrow").click(function(){
		var book_id=$("#book_id").val();
		var student_id=$("#student_id").val();
		if(typeof book_id==="string")
			//book_id=book_id.toString();
			book_id=parseInt(book_id);
		if(typeof student_id==="number")
			student_id=student_id.toString();
		alert(book_id+student_id);
		postJSON("api/borrow",{"book_id":book_id,"student_id":student_id},function(data){
			   data=JSON.stringify(data);
			   alert(data);
			});
		});
	$("#return").click(function(){
		var book_id=$("#return_book_id").val();
		var student_id=$("#return_student_id").val();
		if(typeof book_id==="string")
			//book_id=book_id.toString();
			book_id=parseInt(book_id);
		if(typeof student_id==="number")
			student_id=student_id.toString();
		alert(book_id+student_id);
		postJSON("api/return",{"book_id":book_id,"student_id":student_id},function(data){
			   data=JSON.stringify(data);
			   alert(data);
			});
		});

	$('#input').on('input propertychange',function(){
    	$('.uk-list').html("");
         var val=$('#input').val();
         //alert(val);
         getJSON('api/vagueinquireborrow',{student_id:val},function(data){
               //alert(data.length);
               var str="";
               if(data.length>4){
                  for(var i=0;i<4;i++){
                    str+='<li onclick="choose(this)" onmouseover="onit(this)" onmouseout="outit(this)">'+data[i]+'</li>';
                   }
               }
               else{
            	   for(var i=0;i<data.length;i++){
                       str+='<li onclick="choose(this)" onmouseover="onit(this)" onmouseout="outit(this)">'+data[i]+'</li>';
                      }
               }
               $('.uk-list').html(str);
               });
    });
});
 
</script>
</head>
<body>
<nav class="uk-navbar-container " uk-sticky="bottom: #offset" uk-navbar>
        <div class="uk-navbar-left  uk-card-body uk-card-small uk-text-center uk-text-large uk-text-bold">
            简史小男孩
        </div>
    <div class="uk-navbar-center ">
       
        <ul class="uk-navbar-nav uk-text-primary">
            <li>
                <a href="inquire">inquire</a>
            </li>
            <li><a href="borrow_return">borrow_return</a></li>
            <li><a href="information" >information</a></li>
        </ul>
    </div>
    <div class="uk-navbar-right">
    
    </div>
</nav>
<hr class="uk-divider-icon">

<div  uk-grid>
<div class="uk-width-1-4">
<div class="uk-card uk-text-large uk-text-center uk-margin">借书登记</div> 
<div>
<form uk-grid>
  <div class="uk-margin uk-width-1-1"><input class="uk-input"  type="text" id="book_id" placeholder="图书编号"></div>
  <div class="uk-margin uk-width-1-1"><input class="uk-input"  type="text" id="student_id" placeholder="学生编号"></div>
  <div class="uk-margin uk-width-1-1"><button class="uk-button-primary uk-button" id="borrow" type="button">提交</button></div>
</form>
</div>
</div>
<div class="uk-width-1-2">
<div class="uk-card uk-text-large uk-text-center uk-margin">借书记录查询</div> 
<ul class="uk-navbar-nav uk-margin uk-grid" id="borrow_return">

<li>
<input type="input" id="input" class="uk-input" placeholder="输入学号" v-model="val">
<ul style="position: absolute;border-style:solid;border-color:#FFFFFF;background-color:#FFFFFF" class="uk-list" id="uk-list"></ul>
</li>
<li>
<button class="uk-button uk-button-primary"  v-on="click:begin()">开始</button>
</li>
</ul>

<table class="uk-table" id="borrowbook">
<thead>
<tr>
<th>图书编号</th>
<th>学生编号</th>
<th>借书时间</th>
<th>超期罚钱</th>
<th>还书时间</th>
</tr>
</thead>
<tbody>
<tr v-repeat="borrow:borrows">
<td v-text="borrow.book_id"></td>
<td v-text="borrow.student_id"></td>
<td v-text="borrow.borrow_time"></td>
<td v-text="borrow.amerce"></td>
<td v-text="borrow.return_time"></td>
</tr>
</tbody>
</table>

</div>
<div class="uk-width-1-4">
<div class="uk-card uk-text-large uk-text-center uk-margin">还书登记</div> 
<div>
<form uk-grid>
  <div class="uk-margin uk-width-1-1"><input class="uk-input"  type="text" id="return_book_id" placeholder="图书编号"></div>
  <div class="uk-margin uk-width-1-1"><input class="uk-input"  type="text" id="return_student_id" placeholder="学生编号"></div>
  <div class="uk-margin uk-width-1-1"><button class="uk-button-primary uk-button" id="return" type="button">提交</button></div>
</form>
</div>
</div>
</div>
</body>
</html>