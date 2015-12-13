<?php
 $connect=mysql_connect("localhost","root","apmsetup")or
 	die("SQL server에 연결할 수 없습니다.");
 
 mysql_query("set names utf8");
 
 mysql_query("set session character_set_connection=utf8;");
 mysql_query("set session character_set_results=utf8;");
 mysql_query("set session character_set_client=utf8;"); 
 //데이터 베이스 선택
 mysql_select_db("studentdb", $connect);
 
 //세션 시작
 //session_start();

 $id = -1;
 $id = $_REQUEST['id'];

 $sql = "select h.homework_id, h.homework_deadline, h.homework_content from homework h, class c where h.class_id = c.class_id and c.class_id = '$id'";

 //쿼리 실행 결과 저장
 $result = mysql_query($sql, $connect) or die("Data not found.");
 $xml_output = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
 $xml_output .= "<stuInfo>\n";

 while($record=mysql_fetch_object($result))
 {
	$xml_output .= "\t<item>\n";
	for($i=0;$i<mysql_num_fields($result); $i++)
	{
		$fieldName = mysql_field_name($result, $i);
		$xml_output .= "\t\t<".$fieldName.">";
		
		if(!empty($record->$fieldName))
			$xml_output.=$record->$fieldName;
		else
			$xml_output.="null";

		$xml_output.="\t\t</".$fieldName.">\n";
	}
	$xml_output.="\t</item>\n";
 }
$xml_output.="</stuInfo>\n";

echo $xml_output;
 ?>