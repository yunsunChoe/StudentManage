-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 15-12-11 02:48 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `studentdb`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `attendance`
--

CREATE TABLE IF NOT EXISTS `attendance` (
  `attendance_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `attendance_date` date NOT NULL DEFAULT '2015-12-02',
  `teacher_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `student_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `attendance_state` varchar(10) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `student_id` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 테이블의 덤프 데이터 `attendance`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `class_id` varchar(7) COLLATE utf8_bin NOT NULL,
  `class_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `teacher_id` varchar(7) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `teacher_id` (`teacher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 테이블의 덤프 데이터 `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `teacher_id`) VALUES
('cid0001', '고등영어', 'tid0001'),
('cid0002', '고등수학', 'tid0002');

-- --------------------------------------------------------

--
-- 테이블 구조 `classmate_list`
--

CREATE TABLE IF NOT EXISTS `classmate_list` (
  `classmate_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `class_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `student_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`classmate_id`),
  KEY `class_id` (`class_id`),
  KEY `student_id` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 테이블의 덤프 데이터 `classmate_list`
--

INSERT INTO `classmate_list` (`classmate_id`, `class_id`, `student_id`) VALUES
('clid001', 'cid0001', 'st00001'),
('clid002', 'cid0001', 'st00002'),
('clid003', 'cid0001', 'st00003'),
('clid004', 'cid0001', 'st00004'),
('clid005', 'cid0001', 'st00005'),
('clid011', 'cid0002', 'st00001'),
('clid012', 'cid0002', 'st00002'),
('clid013', 'cid0002', 'st00003'),
('clid014', 'cid0002', 'st00004'),
('clid015', 'cid0002', 'st00005');

-- --------------------------------------------------------

--
-- 테이블 구조 `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `writer_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `post_id` varchar(7) CHARACTER SET latin1 NOT NULL,
  `comment_content` varchar(300) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 테이블의 덤프 데이터 `comment`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `grade`
--

CREATE TABLE IF NOT EXISTS `grade` (
  `grade_id` varchar(7) NOT NULL,
  `student_id` varchar(7) NOT NULL,
  `class_name` varchar(20) NOT NULL,
  `grade_test_score` int(11) DEFAULT NULL,
  `grade_test_name` varchar(20) DEFAULT NULL,
  `grade_test_date` date DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  KEY `student_id` (`student_id`),
  KEY `class_name` (`class_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `grade`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `homework`
--

CREATE TABLE IF NOT EXISTS `homework` (
  `homework_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `homework_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `homework_content` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `homework_deadline` date NOT NULL,
  `teacher_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `class_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`homework_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `class_id` (`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `homework`
--

INSERT INTO `homework` (`homework_id`, `homework_name`, `homework_content`, `homework_deadline`, `teacher_id`, `class_id`) VALUES
('hid0001', NULL, '2013년도 10월 교육청 모의고사 풀기', '2015-12-07', 'tid0001', 'cid0001'),
('hid0002', NULL, '쎈 1200번부터 1300번 풀어오기', '2015-12-07', 'tid0002', 'cid0002'),
('hid0003', NULL, 'iwantgohome', '0000-00-00', 'tid0001', 'cid0001'),
('hid0004', NULL, 'iwantgohome', '0000-00-00', 'tid0001', 'cid0001'),
('hid0005', NULL, 'iwantgohome', '0000-00-00', 'tid0001', 'cid0001'),
('hid0006', NULL, 'gdyfyfgydcjff', '0000-00-00', 'tid0001', 'cid0001'),
('hid0007', NULL, 'shdjdbaksvdj', '0000-00-00', 'tid0001', 'cid0002'),
('hid0008', NULL, 'guvhcydd', '0000-00-00', 'tid0001', 'cid0002'),
('hid0009', NULL, 'home', '0000-00-00', 'tid0001', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `login_id` varchar(7) NOT NULL,
  `login_password` varchar(20) NOT NULL,
  `login_account` varchar(7) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `login`
--

INSERT INTO `login` (`login_id`, `login_password`, `login_account`) VALUES
('ererer', 'eeeerrrr', 'st00005'),
('gdgdgd', 'ggggdddd', 'pcid005'),
('tdtdtd', 'ttttdddd', 'tid0001');

-- --------------------------------------------------------

--
-- 테이블 구조 `notice`
--

CREATE TABLE IF NOT EXISTS `notice` (
  `notice_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `notice_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `writer_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `notice_date` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `notice`
--

INSERT INTO `notice` (`notice_id`, `notice_content`, `writer_id`, `notice_date`) VALUES
('nid0001', '매월 25일은 학원비 결제일입니다.', 'tid0001', '2015-'),
('nid0002', '영어 숙제 7일까지 해와야합니다!', 'tid0001', '2015-'),
('nid0003', '수학 숙제 7일까지 해와야합니다!', 'tid0002', '2015-'),
('nid0004', '수학_쪽지시험 14일에 봅니다.', 'tid0002', '2015-');

-- --------------------------------------------------------

--
-- 테이블 구조 `parent`
--

CREATE TABLE IF NOT EXISTS `parent` (
  `parent_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `parent_tel` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `parent`
--

INSERT INTO `parent` (`parent_id`, `parent_tel`) VALUES
('pcid001', '010-2943-9847'),
('pcid002', '010-2748-3984'),
('pcid003', '010-2392-5984'),
('pcid004', '010-2988-5594'),
('pcid005', '010-2394-5594');

-- --------------------------------------------------------

--
-- 테이블 구조 `pay`
--

CREATE TABLE IF NOT EXISTS `pay` (
  `pay_id` varchar(7) NOT NULL,
  `pay_method` varchar(10) DEFAULT 'card',
  `pay_amount` int(11) DEFAULT '0',
  `pay_month` date DEFAULT '2015-12-02',
  `pay_reg_date` date DEFAULT '2015-12-02',
  `student_id` varchar(7) NOT NULL,
  PRIMARY KEY (`pay_id`),
  KEY `student_id` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `pay`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `question_id` varchar(7) NOT NULL,
  `question_content` varchar(1000) DEFAULT NULL,
  `writer_id` varchar(7) NOT NULL,
  `question_date` varchar(7) NOT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `question`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `student_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `student_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `student_name` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `student_tel` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `student_tuition` int(4) NOT NULL,
  `student_age` int(11) DEFAULT NULL,
  `student_schoolname` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `parent_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `student`
--

INSERT INTO `student` (`student_id`, `student_address`, `student_name`, `student_tel`, `student_tuition`, `student_age`, `student_schoolname`, `parent_id`) VALUES
('st00001', '서울시 서대문구 북아현동 25-22', '홍진이', '010-1234-5678', 2000000, 18, '풍문여고', 'pcid001'),
('st00002', '서울시 은평구 신사2동 25-22', '김유리', '010-1234-5555', 2000000, 18, '선일여고', 'pcid002'),
('st00003', '서울시 은평구 구산동 25-22', '전미리', '010-1444-5687', 2000000, 18, '예일여고', 'pcid003'),
('st00004', '서울시 서대문구 북가좌동 25-42', '박상현', '010-1246-5437', 2000000, 18, '충암고', 'pcid004'),
('st00005', '서울시 은평구 구산1동 1-1', '전화리', '010-1542-5277', 2000000, 18, '예일여고', 'pcid005'),
('st00006', '서울시 은평구 구산1동 1-1', '이지영', '010-1542-5277', 2000000, 18, '예일여고', 'pcid005');

-- --------------------------------------------------------

--
-- 테이블 구조 `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `teacher_name` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `teacher_tel` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `teacher_email` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `teacher_name`, `teacher_tel`, `teacher_email`) VALUES
('tid0001', '최지희', '010-9855-9853', 'abdkje@naver.com'),
('tid0002', '김진성', '010-7849-3656', 'kjs6703@naver.com');
