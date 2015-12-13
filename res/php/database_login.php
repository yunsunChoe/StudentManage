<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
<body>

<!--php 시작부분-->
<?php
 $connect=mysql_connect("localhost","root","apmsetup")or
 	die("SQL server에 연결할 수 없습니다.");
 
 mysql_query("set names utf8");
 
 mysql_query("set session character_set_connection=utf8;");
 mysql_query("set session character_set_results=utf8;");
 mysql_query("set session character_set_client=utf8;"); 
 //데이터 베이스 선택
 mysql_select_db("studentdb", $connect);

//넘어온 파라미터 값 받는 방법
// $id = -1;
// $id = $_REQUEST['id'];
// $pwd = -1;
// $pwd = $_REQUEST['pwd'];
// $admin = $_REQUEST['admin'];


//로그인 경우 짜놓은 sql문
// if($admin == 1)
//	$sql="select teacher.teacher_id, teacher.teacher_name, class.class_name, homework.homework.name, homework.homework_content, homework.homework_deadline from teacher, class, subject, homework 
//			where teacher.class_id = class.class_id and class.subject_id = subject.subject_id and teacher.teacher_id = homework.teacher_id 
//				and teacher.teacher_id = (select rf_login_id from login 
//							where login_id=$id and login_password=$pwd)";
// else if($admin == 2)
//	$sql="select student.student_id, student.student_name, class.class_name, homework.homework_name, homework.homework_content, homework.homework_deadline from student, classmate_list m, class, homework 
//			where student.student_id = m.student_id and m.class_id = class.class_id and class.class_id = homework.class_id
//				and student.student_id = (select rf_login_id from login where login_id=$id and login_password=$pwd)";
// else if($admin == 3)
//	$sql="select student.student_id, student.student_name, student.student_tuition, class.class_name, homework.homework_name, homework.homework_content, homework.homework_deadline from student, classmate_list m, class, homework, parent
//			where student.student_id = m.student_id and m.class_id = class.class_id and class.class_id = homework.class_id and parent.student_id = student.student_id
//				and parent.parent_id = (select rf_login_id from login where login_id=$id and login_password=$pwd)";

//간단한 sql문 test
 $sql = "select * from subject";
 
 //쿼리 실행 결과 저장
 $result=mysql_query($sql, $connect) or die("Data not found.");
 $xml_output="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
 $xml_output.="<stuInfo>\n";

 //받아온 결과를 xml문으로 생성 - 이부분은 건드리지 않고 그대로 사용 *sql문만 변경
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