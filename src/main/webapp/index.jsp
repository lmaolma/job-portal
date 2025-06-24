<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Job Portal Home</title>
  
  <style type="text/css">
*{
	padding: 0px;
	margin: 0px;
	box-sizing: border-box;
}
    /* search bar */
.search-wrapper {
	background: linear-gradient(135deg, #6366f1 0%, #a855f7 100%);
	height:70vh;
	display: flex;
	align-items: center;
	justify-content: center;
}
.search-container {
	
	background: rgba(255, 255, 255, 0.95);
	backdrop-filter: blur(10px);
	border-radius: 10px;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
	padding: 2rem;
	width: 90%;
	max-width: 500px;
}


</style>
</head>

<body>
	<br>
    <h3>Welcome to Job Portal</h3>
	<br>
	<div class="homepageinfo">
		<h2 class="heading" align="center">Get Your Dream Job!</h2>
	</div>
	<div class="search-wrapper" align="center">
	    <div class="search-container">
	        <h4 class="mb-4 text-center">Search & Discover Amazing Job Content</h4><br>
	
		    <form action="searchjob" method="post">
		        <input type="text" name="keyword" placeholder="Search jobs..." required />
		        <button type="submit" value="SearchJobServlet">Search</button>
		    </form>
			<br>
    		<p><a href="register.jsp">Register</a> | <a href="login.jsp">Login</a></p>
    		   </div>
	</div>
</body>
</html>       
	 