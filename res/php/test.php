<?php
 $connect=mysql_connect("localhost","root","apmsetup")or
 	die("SQL server�� ������ �� �����ϴ�.");
 
 mysql_query("set names utf8");
 
 mysql_query("set session character_set_connection=utf8;");
 mysql_query("set session character_set_results=utf8;");
 mysql_query("set session character_set_client=utf8;"); 
 //������ ���̽� ����
 mysql_select_db("studentdb", $connect);
 
 //���� ����
 //session_start();

 $id = -1;
 $id = $_REQUEST['id'];

 $sql = "select test_id, test_name from test where class_id = '$id'";

 //���� ���� ��� ����
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