<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>

<!-- Basic -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <!-- Mobile Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Site Metas -->
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <link rel="shortcut icon" href="images/favicon.png" type="">

  <title> Feane </title>

  <!-- bootstrap core css -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

  <!--owl slider stylesheet -->
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
  <!-- nice select  -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css" integrity="sha512-CruCP+TD3yXzlvvijET8wV5WxxEh5H8P4cmz0RFbKK6FlZ2sYl3AEsKlLPHbniXKSrDdFewhbmBK5skbdsASbQ==" crossorigin="anonymous" />
  <!-- font awesome style -->
  <link href="css/font-awesome.min.css" rel="stylesheet" />

  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet" />
  <!-- responsive style -->
  <link href="css/responsive.css" rel="stylesheet" />
  
</head>
<body class="sub_page">

  
<div class="hero_area">
    <div class="bg-box">
        <img src="images/hero-bg.jpg" alt="">
    </div>

    <!-- header include -->
    <jsp:include page="/header.jsp" />
</div>

<!-- 본문 컨테이너 -->
<div class="content-wrap" style="max-width: 900px; margin: 50px auto; padding: 20px;">
    
    <h1 style="font-size: 32px; color: #B22222; font-weight: bold; margin-bottom: 20px;">
        ${ dto.title }
    </h1>

    <div style="border-bottom: 1px solid #ccc; padding-bottom: 10px; margin-bottom: 20px;">
        <span style="font-weight: bold;">${ dto.id }</span> |
        <span>${ dto.postdate }</span> |
        <span>조회수: ${ dto.visitcount }</span>
    </div>

    <div style="white-space: pre-wrap; font-size: 18px; line-height: 1.7;">
        ${ dto.content }
    </div>
</div>
</body>
</html>
