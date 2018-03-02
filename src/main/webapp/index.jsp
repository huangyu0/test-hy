<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="js/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/icon.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="js/easyui.css"/>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	选择表<select id=cc name="state"  style="width:200px;">
		
    </select>
    <br>
    选择字段
    <a href="#" onclick="return getFieldId()">选中项</a>
    <table id="tt" title="表字段" class="easyui-datagrid" style="width:650px;height:250px"
           url="/queryFiledByTable"
           idField="itemid" pagination="true"
           iconCls="icon-save">
        <thead>
        <tr>
            <th field="ck" checkbox="true"></th>
            <th field="fieldId" width="100">fieldId</th>
            <th field="code" width="250">code</th>
            <th field="name" width="200" align="right">name</th>
        </tr>
        </thead>
    </table>

  </body>
  
  <script>

  	$('#cc').combobox({    
	    url:"/test",
	    valueField:'id',   //相当于option的value值 
	    textField:'code'   //相当于<option></option>之间的显示值
	});


  	var text;
    $('#cc').combobox({
        onChange: function(){
            var id = $('#cc').combobox('getValue');
            text = $('#cc').combobox('getText');
            // alert(id);
            alert(text);
            $.post("/queryFiledByTable",{id:id},function(data){
                // console.log(data);
                $('#tt').datagrid('loadData', data);
            }, "json");


        }
    });

    //取得选中行的字段名
  function getFieldId() {

      var  filedCodes;
      var rows = $('#tt').datagrid('getChecked');
      for(var i=0; i<rows.length; i++){
          ids.push(rows[i].fieldId);
          codes.push(rows[i].code);
          filedCodes = JSON.stringify(codes);
      }

      // rows.each(function () {
      //     codes += $(this).attr("codes")+",";
      //     alert(codes);
      // })


      // console.info(filedCodes)
      // alert(ids.join());
      // alert(codes.join());
      $.post("/queryFiledDate",{filedCodes:filedCodes, tableCode:text},function(data){



      }, "json");


  }

  </script>

</html>


