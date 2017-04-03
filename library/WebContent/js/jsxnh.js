function refresh(){
	var url=location.pathname;
	//var t=new Date().getTime();
	if(location.search)
		{
		   //url=url+location.search+'&t='+t;
		}
	//else
		//url=url+'?t='+t;
	location.assign(url);
}
function getJSON(url,data,callback){
	if (arguments.length===2) {
        callback = data;
        data = {};
    }
    if (typeof (data)==='object') {
        var arr = [];
        $.each(data, function (k, v) {
            arr.push(k + '=' + encodeURIComponent(v));
        });
        data = arr.join('&');
    }
    var opt={
    		type:'GET',
    		dataType:'json',
    		contentType:'application/json;charset=utf-8'
    };
    opt.url=url+'?'+data;
    $.ajax(opt).done(function(r){
    	//alert(r);
    	return callback(r);
    }).fail(function(jqXHR,textStatus){
    	return callback({'error': 'http_bad_response', 'data': '' + jqXHR.status, 'message': '网络好像出问题了 (HTTP ' + jqXHR.status + ')'});
    });
}
function postJSON(url,data,callback){
	if(arguments.length===2){
		callback=data;
		data={};
	}
	var opt={
		type:'POST',
		url:url,
		dataType:'json',
		data:JSON.stringify(data),
		contentType:'application/json;charset=utf-8'
	};
	$.ajax(opt).done(function(r){
    	//alert(r);
    	return callback(r);
    }).fail(function(jqXHR,textStatus){
    	return callback({'error': 'http_bad_response', 'data': '' + jqXHR.status, 'message': '网络好像出问题了 (HTTP ' + jqXHR.status + ')'});
    });
}
function choose(obj){
	document.getElementById("input").innerHTML=obj.innerHTML;
	$(".uk-list").html("");
 }
function onit(obj){
 obj.style.backgroundColor="#B0B0B0";//选中时的颜色
 }
function outit(obj){
 obj.style.backgroundColor="#FFFFFF";//离开选中时的颜色
 }