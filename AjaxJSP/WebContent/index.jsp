<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Demo Ajax</title>
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
	
	//output name
	function do_demo1() {
		
		var full_name = $('#full_name').val();
		
		$.ajax({
			type: "post",
			data: {
					full_name: full_name,
					action: 'demo1'
					},
			url: 'AjaxController',
			success: function(result) {
				$('#result1').html(result);	
			}
		});
	}//End
	
	function do_demo2() {
		
		var num1 = $('#number1').val();
		var num2 = $('#number2').val();
		
		$.ajax({
			type:"post",
			data: {
				number1 : num1,
				number2 : num2,
				action: 'demo2'
			},
			url: "AjaxController",
			success: function(result) {
				$('#result2').html(result);
			}
		});
	}//End
</script>

</head>
<body>

	<fieldset>
		<legend>Demo 1</legend>
			<form>
			Name: <input style="margin-right: 10px" type="text" id="full_name">
					<input type="button" value="Hello" id="btn_Hello" onclick="do_demo1()"><br>
				<span id="result1"></span>
			</form>
	</fieldset>
	
	<fieldset>
		<legend>Demo 2</legend>
		Number #1: <input style="margin-bottom: 10px" type="text" id = "number1"><br>
		Number #2: <input type="text" id = "number2"><br>
		Result: <span id="result2"></span><br>
		<input type="button" value="Sum" id="btn_sum" onclick="do_demo2()">
	</fieldset>

</body>
</html>