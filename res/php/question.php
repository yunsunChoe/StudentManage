<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
<body>

<!--php ���ۺκ�-->
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
// $id = -1;
// $id = $_REQUEST['id'];
// $pwd = -1;
// $pwd = $_REQUEST['pwd'];
// $admin = $_REQUEST['admin'];
// $class_name = $_REQUEST['class_name'];

 
 //$sql = "select class_name from class where class_id in (select class_id from class where teacher_id = $id)";

 // ��� ù ȭ��� ������ ��
 
 $sql = "select * from comment all (select question_id from question)";

 //$sql = "select a.attendance_date, class.class_name, a.attendance_state, s.student_name from attendance a, student s, classmate_list c, class where a.student_id = s.student_id and s.student_id = c.student_id and c.class_id = class.class_id 
 				and class_id in (select class_id from teacher, class where class.teacher_id = teacher.teacher_id and teacher_id = $id)";

 //$sql = "select a.attendance_date, a.attendance_state, s.student_name from attendance a, student s, classmate_list c, class where a.student_id = s.student_id and s.student_id = c.student_id and c.class_id = class.class_id 
 				and class_id in select class_id from class, teacher class.teacher_id = teacher.teacher_id and class_name='.$class_name' and teacher_id = $id";
 
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
</body>
</html>