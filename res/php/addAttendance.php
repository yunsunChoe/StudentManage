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
 $state = $_REQUEST['state'];


 $sql = "update attendance set attendance_state='$state' where attendance_id='$id'";

 //���� ���� ��� ����
 mysql_query($sql, $connect);
 echo "success";
 ?>