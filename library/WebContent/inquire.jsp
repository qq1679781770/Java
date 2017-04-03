<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询</title>
<link rel="stylesheet" href="css/uikit.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/uikit.min.js"></script>
<script type="text/javascript" src="js/vue.min.js"></script>
<script type="text/javascript" src="js/jsxnh.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//$("#bookmodel").hide();
	//document.getElementById('tablethread').style.visibility='hidden';
	var vm=new Vue({
		el:'#inquire',
		data:{
			subjects:'book_name',
			val:''
			},
		methods:{
			begin:function(){
				getJSON('api/inquire',{subjectsname:this.subjects,subjectsvalue:this.val},function(data){
			                 //data=JSON.stringify(data);
			                 //alert(data);
			                 document.getElementById("uk-list").innerHTML="";
			                 
		                     bookvm.books=data;                 
					});
			   }}
		
		
	      });
    var bookvm=new Vue({
        el:'#bookmodel',
        data:{
            books:[{}],
        	seen:false
            },
    filters:{
		  is_borrowed:function(value){
			   if(value)
				   return "是";
			   else if(value===0)
				   return "否";
			  }
		}
        });
    $('#input').on('input propertychange',function(){
    	$('.uk-list').html("");
         var val=$('#input').val();
         //alert(val);
         var name=$('.uk-select').val();
         if(val==="")
             return;
         getJSON('api/inquiresubjects',{subjectsname:name,subjectsvalue:val},function(data){
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



<nav class="uk-navbar-container uk-text-center uk-text-large uk-flex-center">
<ul class="uk-navbar-nav uk-margin uk-grid" id="inquire">

<li>
<label>选择类型 </label>
</li>

<li>
<select class="uk-select" v-model="subjects">
<option name="subjects" value="subjects" v-model="subjects">类型</option>
<option name="book_name" value="book_name" v-model="subjects" >书名</option>
<option name="author" value="author" v-model="subjects">作者</option>
<option name="isbn" value="isbn" v-model="subjects">isbn</option>
</select>
</li>

<li>
<input class="uk-input" type="text" placeholder="请输入关键词" v-model="val" id="input" />
<ul style="position: absolute;border-style:solid;border-color:#FFFFFF;background-color:#FFFFFF" class="uk-list" id="uk-list"></ul>
</li>

<li>
<button class="uk-button uk-button-primary"  v-on="click:begin()">开始</button>
</li>
</ul>
</nav>
<table id="bookmodel"  class="uk-table">
<thread v-show="seen">
   <tr>
      <th>编号</th>
      <th>书名</th>
      <th>作者</th>
      <th>类型</th>
      <th>书架编号</th>
      <th>国家书籍编号</th>
      <th>出版社</th>
      <th>是否借出</th>
      <th>备注</th>
   </tr>
</thread>
<tbody>
   <tr  v-repeat="book:books">
     <td v-text="book.book_id"></td>
     <td v-text="book.book_name"></td>
     <td v-text="book.author"></td>
     <td v-text="book.subjects"></td>
     <td v-text="book.bookshelf_id" ></td>
     <td v-text="book.isbn"></td>
     <td v-text="book.publisher"></td>
     <td v-text="book.is_borrowed | is_borrowed"></td>
     <td v-text="book.message"></td>
   </tr>
</tbody>
</table>
</body>
</html>