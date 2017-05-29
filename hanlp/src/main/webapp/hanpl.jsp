<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文本分词</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jsxnh.js"></script>
        <script src="js/echarts.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/jsxnh.css" />
        <script src="js/jsxnhlp.js"></script>
        <script src="js/cloud.js"></script>
        <script src="js/g2.js"></script>
</head>
<body>
<div class="container">
            <div class="alert alert-info" id="jsxnh"><h3>JsxnhLP</h3></div>
            <div class="row" >
                <div class="col-md-12">
                    <textarea placeholder="你好，欢迎使用汉语分词" rows="20" id="input"></textarea>
                </div>
            </div>
            <div style="margin-top:20px">
                <div class="row">
                    <div class=" col-md-3">
                        <a href="javascript:;" class="file">选择文件
                            <input type="file" name="" id="file">
                        </a>
                    </div>
                    <div class="col-md-offset-6 col-md-3">
                        <button type="button" class="btn btn-primary btn-lg btn-block" id="analyse">分析</button>
                    </div>
                </div>
            </div>
            <div style="margin-top:30px">
                <ul class="row">
                    <li  class=" col-md-4"><h3><span class="label label-info">分词标注</span></h3><a href="javascricpt:(0)"><img src="images/fenci.png" class="img-circle" id="wordmark"/></a></li>
                    <li  class="col-md-offset-1 col-md-3"><h3><span class="label label-info">词频统计</span></h3><a href="javascricpt:(0)"><img src="images/ciping.png" class="img-circle" id="wordfrequency"/></a></li>
                    <li class="col-md-offset-2 col-md-2"><h3><span class="label label-info">生成词云</span></h3><a href="javascricpt:(0)" ><img src="images/shiti.png" class="img-circle" id="wordcloud"/></a></li>
                </ul>
            </div>

            <div class="row reultdiv" id="wordmarkdiv">
                <div class="col-md-3" style="background-color:#F0F0F0">   
                    <button type="button" class="btn btn-primary btn-lg btn-block" id="wordmarkbt">显示图像</button> 
                    <div id="marktypesint">
                        <h5>词性类别</h5>
                        <ul>
                            <li class="row">
                                <h3><span class="label col-md-3" id="b1">连词</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b2">副词</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b3">叹词</span></h3>
                            </li>
                           <li class="row">
                                <h3><span class="label col-md-3" id="b4">前缀</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b5">后缀</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b6">数词</span></h3>
                            </li>
                            <li class="row">
                                <h3><span class="label col-md-3" id="b7">名词</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b8">介词</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b9">量词</span></h3>
                            </li>
                            <li class="row">
                                <h3><span class="label col-md-3" id="b10">代词</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b11">助词</span></h3>
                                <h3><span class="label col-md-3 col-md-offset-1" id="b12">动词</span></h3>
                            </li>
                            <li class="row">
                                <h3><span class="label col-md-4" id="b13">形容词</span></h3>
                                <h3><span class="label col-md-4 col-md-offset-3" id="b14">区别词</span></h3>
                                
                            </li>
                            <li class="row">
                                <h3><span class="label col-md-4" id="b15">方位词</span></h3>
                                <h3><span class="label col-md-4 col-md-offset-3" id="b16">习用词</span></h3>                                
                            </li>
                            <li class="row">
                                <h3><span class="label col-md-4" id="b17">拟声词</span></h3>
                                <h3><span class="label col-md-4 col-md-offset-3" id="b18">时间词</span></h3>
                            </li>
                            <li class="row">
                                <h3><span class="label col-md-4" id="b19">语气词</span></h3>
                                <h3><span class="label col-md-4 col-md-offset-3" id="b20">状态词</span></h3>                              
                            </li>
                            <li class="row">
                                <h3><span class="label col-md-4" id="b21">学术词汇</span></h3>
                                <h3><span class="label col-md-4 col-md-offset-3" id="b22">标点符号</span></h3>
                            </li>
                        </ul>
                    </div>
                    
                </div>
                <div class="col-md-9" id="marktypes">

                </div>
                <div class="col-md-9" id="marktypespic" style="background-color:white">
                <div id="marktypespicdiv" style="height: 800px;width:800px">
                </div>
                </div>
            </div>
            <div class="row resultdiv" id="wordfrequencydiv"  >
                <div class="col-md-2" style="background-color:#F0F0F0">
                     <button type="button" class="btn btn-primary btn-lg btn-block" id="wordfrequencydocbt" style="margin-top:180px">显示表格</button>
                     <button type="button" class="btn btn-primary btn-lg btn-block" id="wordfrequencypicbt" style="margin-top:180px">显示图片</button>
                </div>
                <div class="col-md-9" id="wordfrequencydocdiv">
                    <table class="table table-border table-hover">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>字词</th>
                                <th>频数</th>
                                <th>频率</th>
                                <th>tf-idf</th>
                            </tr>
                        </thead>
                        <tbody id="wordfrequencytable">

                        </tbody>
                    </table>
                </div>
                <div class="col-md-1" style="background-color:#F0F0F0" id="downloaddiv">
                    <button type="button" class="btn btn-primary btn-lg btn-block" id="downloadresult" style="margin-top:270px">下载</button>
                </div>
                <div class="col-md-10" id="wordfrequencypicdiv" style="background-color:white">
                   <div id="wfpd" style="height:600px;width:945px"></div>
                  
                </div>
            </div>
            <div id="wordclouddiv" class="resultdiv">
                <div id="c1"></div>
            </div>
        </div>
         <div style="background-color:#003333;margin-top:30px">
            <div class="container">
                <div style="margin-top:10px">
                <ul class="row">
                    <li class="col-md-2"><h4 style="color:white">链接</h4></li>
                    <li class="col-md-2"><h4><a href="http://v3.bootcss.com/" target="_blank">Bootstrap</a></h4></li>
                    <li class="col-md-2"><h4><a href="https://github.com/hankcs/HanLP" target="_blank">Hanpl</a></h4></li>
                     <li class="col-md-2"><h4><a href="http://echarts.baidu.com" target="_blank">Echarts</a></h4></li>
                    <li class="col-md-2"><h4><a href="https://github.com/qq1679781770/Java/tree/master/hanlp" target="_blank">源码</a></h4></li>
                     <li class="col-md-2"><h4><a href=https://antv.alipay.com/g2/doc/index.htmltarget="_blank">G2</a></h4></li>
                </ul>
                 </div>
                <div>
                <p class="text-center" style="color:white">Powered by <a href="#">jsxnh</a>. Copyright &copy; 2017. Manage</p>
                <p class="text-center" style="color:white"><a href="http://www.jianshixiaonanhai.com/" target="_blank">www.jianshixiaonanhai.com</a>. All rights reserved.</p>
                </div>
                </div>
        </div>
        <script count="300" src="js/canvas-nest.js"></script>
</body>
</html>