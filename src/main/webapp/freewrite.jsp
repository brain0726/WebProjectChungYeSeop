<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>


<style>

    .write-container {
        width: 80%;
        margin: 0 auto;  /* 가운데 정렬 */
        border: 1px solid #dcdcdc;
        background: #fff;
        border-radius: 5px;
        overflow: hidden;
        box-shadow: 0px 2px 8px rgba(0,0,0,0.05);
        margin-bottom: 60px;
    }
    .title-box {
        padding: 15px;
        border-bottom: 1px solid #e5e5e5;
        display: flex;
        align-items: center;
        background: #fafafa;
    }
	.title-box label {
        margin-right: 10px;
        font-weight: bold;
        font-size: 14px;
        width: 60px;
    }

    .title-input {
        width: 100%;
        padding: 7px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    textarea {
        width: 100%;
        height: 400px;
        border: none;
        padding: 15px;
        resize: none;
        outline: none;
        font-size: 15px;
    }

    .bottom-bar {
        padding: 12px;
        display: flex;
        justify-content: flex-end;
        background: #fafafa;
        border-top: 1px solid #e5e5e5;
    }
    .btn-submit {
        background-color: #333;
        color: white;
        border: none;
        padding: 8px 18px;
        font-size: 14px;
        cursor: pointer;
    }
</style> 

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
    <jsp:include page="/header.jsp" />
  </div>
  
	<section class="food_section layout_padding">
	    <div class="container">
	      <div class="heading_container heading_center">
	        <h2>
	          자유 게시판
	        </h2>
	      </div>
		<form action="${pageContext.request.contextPath}/freeWrite.do" method="post">
		
		<div class="write-container">
		
		    <!-- 제목 영역 -->
		    <div class="title-box">
		        <label>제목 :</label>
		
		
		        <!-- 제목 입력 -->
		        <input type="text" name="title" class="title-input" placeholder="제목을 입력하세요" required>
		    </div>
		
		    <!-- 본문 -->
		    <textarea name="content" placeholder="여기에 내용을 입력하세요" required></textarea>
		
		    <!-- 하단 -->
		    <div class="bottom-bar">
		        <div></div>
		        <button class="btn-submit" type="submit">작성완료</button>
		    </div>
		
		</div>	
		
		</form>
		
</div>
</section>
</body>
</html>
