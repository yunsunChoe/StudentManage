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
 $content = $_REQUEST['content'];
 $date = $_REQUEST['date'];
 $tid = $_REQUEST['tid'];
 $cid = $_REQUEST['cid'];

 $sql = "insert into homework values('$id', null,'$content', '$date', '$tid', '$cid')";

 //���� ���� ��� ����
 mysql_query($sql, $connect);
 echo "success";
 ?>