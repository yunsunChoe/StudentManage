<?php
 $connect=mysql_connect("localhost","root","apmsetup")or
 	die("SQL server�� ������ �� �����ϴ�.");
 
 mysql_query("set names utf8");
 

 mysql_query("set session character_set_connection=utf8;");
 mysql_query("set session character_set_results=utf8;");
 mysql_query("set session character_set_client=utf8;"); 
 //������ ���̽� ����
 mysql_select_db("studentdb", $connect);

//�Ѿ�� �Ķ���� �� �޴� ���
 $id = -1;
 $id = $_REQUEST['id'];
// $pwd = -1;
// $pwd = $_REQUEST['pwd'];
// $admin = $_REQUEST['admin'];
// $class_name = $_REQUEST['class_name'];

 
 $sql = "select class_id, class_name from class where class_id in (select class_id from class where teacher_id = $id)";

 //���� ���� ��� ����
 $result=mysql_query($sql, $connect) or die("Data not found.");
 $xml_output="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
 $xml_output.="<stuInfo>\n";

 //�޾ƿ� ����� xml������ ���� - �̺κ��� �ǵ帮�� �ʰ� �״�� ��� *sql���� ����
 while($record=mysql_fetch_object($result))
 {
	$xml_output.="\t<item>\n";
	for($i=0;$i<mysql_num_fields($result); $i++)
	{
		$fieldName=mysql_field_name($result, $i);
		$xml_output.="\t\t<".$fieldName.">";
		
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