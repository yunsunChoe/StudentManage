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
 $state = $_REQUEST['state'];


 $sql = "update attendance set attendance_state='$state' where attendance_id='$id'";

 //쿼리 실행 결과 저장
 mysql_query($sql, $connect);
 echo "success";
 ?>