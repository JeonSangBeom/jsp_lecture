<%@page import="java.util.ArrayList"%>
<%@page import="com.jjang051.model.MarioDao"%>
<%@page import="com.jjang051.model.MarioDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	MarioDao marioDao = new MarioDao();
	ArrayList<MarioDto> marioList = marioDao.getAllList(); // ArrayList<MarioDto>을 반환하는 marioDao.getAllList() 호출
	request.setAttribute("marioList", marioList);
	System.out.println(marioList.get(0).toString());
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=PT+Serif:wght@400;700&family=Raleway:wght@100;200;300;400;500;600;700;800;900&family=Titillium+Web:wght@200;300;400;600;700;900&display=swap"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/splitting.css" />
<link rel="stylesheet" href="css/swiper-bundle.css" />
<link rel="stylesheet" href="css/fullpage.css" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/main02.css" />
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/js.cookie.min.js"></script>
<script src="js/splitting.min.js"></script>
<script src="js/swiper-bundle.min.js"></script>
<script src="js/gsap/gsap.min.js"></script>
<script src="js/gsap/CSSRulePlugin.min.js"></script>
<script src="js/fullpage.min.js"></script>
<script src="js/fullpage.extensions.min.js"></script>
<script src="js/axios.min.js"></script>
<script src="js/particles.min.js"></script>
<script src="js/main.js" defer></script>
</head>
<body>
	<header id="header">
		<h1 id="logo">
			<img src="images/logo.png" alt="" />
		</h1>
		<nav id="gnb">
			<ul>
				<li data-json="data/mario.json" class="selected"><a href="/chap09_file_upload">SUPER
						MARIO</a></li>
				<li data-json="data/monster.json"><a href="/chap09_file_upload/monster.jsp">MONSTER</a></li>
			</ul>
		</nav>
		<div class="util">
			<a href="">NINTENDO MALL</a> <a href=""><span
				class="material-icons">favorite_border</span></a> <a href=""><span
				class="material-icons">search</span></a>
		</div>
	</header>
	<main id="main">
		<!-- <div class="section">
        <div class="info">
          <p class="category">BIG BANG</p>
          <h2 class="title">UNICO BERLUTI COLD BROWN</h2>
          <p class="depth">45MM</p>
          <p class="price">CHF 24,800</p>
        </div>
      </div> -->
		<ul class="swiper-wrapper" id="itemList">
			<c:forEach items="${marioList }" var="marioDto" varStatus="loop"> <%-- // ${marioList }로 반복 키워드는 marioDto로 쓰겠다 --%> 
				<li style="background:${marioDto.bg}" class="swiper-slide">
					<div class="info">
						<h2 class="title" data-splitting>${marioDto.title }</h2>
						<p class="desc" data-splitting>${marioDto.contents }</p>
						<a href="${marioDto.link}" target="">MORE</a>
					</div>
					<div class="img">
						<img src="upload_mario/${marioDto.marioRealImg }">
					</div>
				</li>
			</c:forEach>
		</ul>
		<div class="pagination"></div>
	</main>
	<div class="pagination"></div>
	<div id="bgParticle"></div>
	<!-- <div id="loop">
      <img src="images/supermario/supermario01.png" alt="" />
    </div> -->
</body>
</html>