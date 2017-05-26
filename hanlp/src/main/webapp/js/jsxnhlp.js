function initwordmarkdoc(data) {

	 var htmlstr = "";
     for (var i = 0; i < data.length; i++) {
         var word = data[i];
         var str = word[0];
         var nature = word[1];
         var start = nature.substring(0, 1);
         switch (start) {
             case 'c': htmlstr += '<h3><sapn class="b1 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'd': htmlstr += '<h3><sapn class="b2 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'e': htmlstr += '<h3><sapn class="b3 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'h': htmlstr += '<h3><sapn class="b4 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'k': htmlstr += '<h3><sapn class="b5 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'm': htmlstr += '<h3><sapn class="b6 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'n': htmlstr += '<h3><sapn class="b7 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'p': htmlstr += '<h3><sapn class="b8 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'q': htmlstr += '<h3><sapn class="b9 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'r': htmlstr += '<h3><sapn class="b10 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'u': htmlstr += '<h3><sapn class="b11 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'v': htmlstr += '<h3><sapn class="b12 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'a': htmlstr += '<h3><sapn class="b13 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'b': htmlstr += '<h3><sapn class="b14 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'f': htmlstr += '<h3><sapn class="b15 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'l': htmlstr += '<h3><sapn class="b16 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'o': htmlstr += '<h3><sapn class="b17 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 't': htmlstr += '<h3><sapn class="b18 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'y': htmlstr += '<h3><sapn class="b19 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'z': htmlstr += '<h3><sapn class="b20 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'g': htmlstr += '<h3><sapn class="b21 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
             case 'w': htmlstr += '<h3><sapn class="b22 label col-md-3">' + str + '/' + nature + '</sapn></h3>'; break;
         }
     }
     $('#marktypes').html(htmlstr);
     $('#marktypespic').hide();
     $('#marktypes').show();
     
	
}
function initwordmarkpic(data) {

	var legend=['名词','形容词','动词','副词','连词','介词','助词','代词','类别'];
	var categories=[{name:'名词'},{name:'形容词'},{name:'动词'},{name:'副词'}
	                 ,{name:'连词'},{name:'介词'},{name:'助词'},{name:'代词'},{name:'类别'}];
	
	var datas=[];
	var links=[];
	
	for(var i=0;i<8;i++){
		var dataitem={name:legend[i],value:20,category:8};
		datas.push(dataitem);
	}
	var pos=0;
	for(var i=0;i<data.length;i++){
		var word=data[i];
		var str=word[0];
		var nature=word[1];
		var naturestart=nature.substring(0,1);
		switch (naturestart){
		case 'n':{
			var dataitem={name:str,value:1,category:0};
			datas.push(dataitem);
			var link={source:pos+8,target:0};
			links.push(link);
			pos++;
			break;
		}
		case 'a':{
			var dataitem={name:str,value:1,category:1};
			datas.push(dataitem);
			var link={source:pos+8,target:1};
			links.push(link);
			pos++;
			break;
		}
		case 'v':{
			var dataitem={name:str,value:1,category:2};
			datas.push(dataitem);
			var link={source:pos+8,target:2};
			links.push(link);
			pos++;
			break;
		}
		case 'd':{
			var dataitem={name:str,value:1,category:3};
			datas.push(dataitem);
			var link={source:pos+8,target:3};
			links.push(link);
			pos++;
			break;
		}
		case 'c':{
			var dataitem={name:str,value:1,category:4};
			datas.push(dataitem);
			var link={source:pos+8,target:4};
			links.push(link);
			pos++;
			break;
		}
		case 'p':{
			var dataitem={name:str,value:1,category:5};
			datas.push(dataitem);
			var link={source:pos+8,target:5};
			links.push(link);
			pos++;
			break;
		}
		case 'u':{
			var dataitem={name:str,value:1,category:6};
			datas.push(dataitem);
			var link={source:pos+8,target:6};
			links.push(link);
			pos++;
			break;
		}
		case 'r':{
			var dataitem={name:str,value:1,category:7};
			datas.push(dataitem);
			var link={source:pos+8,target:7};
			links.push(link);
			pos++;
			break;
		}
		}
	}
	
	
	var myChart=echarts.init(document.getElementById('marktypespicdiv'));
	option = {
            legend: {
                data:legend,
            },
            series: [{
                type: 'graph',
                layout: 'force',
                animation: false,
                label: {
                    normal: {
                        position: 'right',
                        formatter: '{b}'
                    }
                },
                draggable: true,
                nodes:datas.map(function (node, idx) {
                    node.id = idx;
                    return node;
                }),
                categories:categories,
                force: {
                     initLayout: 'circular',
                     //repulsion: 20,
                    edgeLength: 5,
                    repulsion: 50,
                    gravity: 0.2
                },
               edges:links
            }]
        };
	myChart.setOption(option);
	
	$('#marktypespic').show();
}
function wordmarkdoc() {
	$('#marktypesint').show();
    $('#marktypespic').hide();
	$('#wordfrequencydiv').hide();
	$('#wordclouddiv').hide();
	$('#wordmarkbt').text('显示图像');
    getJSON('wordmarkdoc', function (data) {
        initwordmarkdoc(data);
    });
}
function wordmarkpic() {

	$('#marktypes').hide();
	$('#wordmarkbt').text('显示文本');
	$('#marktypesint').hide();
	getJSON('wordmarkpic',function(data){
		initwordmarkpic(data);
	});
}
function initwordfrequencydoc(data) {

}
function initwordfrequencypic(data) {

}
function wordfrequencydoc() {

}
function wordfrequencypic() {

}
function wordcloud() {

}
            $(document).ready(function () {
                // $('#fencidiv').hide();
                //$('#wordmarkdiv').hide();
                $('#wordfrequencydiv').hide();
                $('#wordclouddiv').hide();
                $('#marktypespic').hide();
                $('#analyse').click(function () {
                    var context = $('#input').val();
                    postJSON('analysefile', context, function (data) {
                    	initwordmarkdoc(data);
                    });
                });

                $('#wordmark').click(function () {
                	wordmarkdoc();
                });
                $('#wordmarkbt').click(function(){
                	if($('#wordmarkbt').text()=="显示文本"){
                		wordmarkdoc();
                	}else{
                		wordmarkpic();
                	}
                });

                $('.file').on("change","input[type='file']",function(){
                    
              	    var formData = new FormData();
                	formData.append('file',$('#file')[0].files[0]);
                	var data=$('#file')[0].files[0];
                	$.ajax({
                	    url: 'fileupload',
                	    type: 'POST',
                	    cache: false,
                	    data:formData,
                	    processData: false,
                	    contentType: false
                	}).done(function(res) {
                		$('#input').val(res);
                	}).fail(function(res) {});
                	
            });               
               
            });
