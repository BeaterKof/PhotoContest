function showUserMng(){
	document.getElementById("user_mng").style.display = "block";
	document.getElementById("user_mng").style.visibility = "visible";
	document.getElementById("admin_mng").style.display = "none";
	document.getElementById("admin_mng").style.visibility = "hidden";
	document.getElementById("contest_mng").style.display = "none";
	document.getElementById("contest_mng").style.visibility = "hidden";
	document.getElementById("reports_mng").style.display = "none";
	document.getElementById("reports_mng").style.visibility = "hidden";
}

function showAdminMng(){
	document.getElementById("user_mng").style.display = "none";
	document.getElementById("user_mng").style.visibility = "hidden";
	document.getElementById("admin_mng").style.display = "block";
	document.getElementById("admin_mng").style.visibility = "visible";
	document.getElementById("contest_mng").style.display = "none";
	document.getElementById("contest_mng").style.visibility = "hidden";
	document.getElementById("reports_mng").style.display = "none";
	document.getElementById("reports_mng").style.visibility = "hidden";
}

function showContestMng(){
	document.getElementById("user_mng").style.display = "none";
	document.getElementById("user_mng").style.visibility = "hidden";
	document.getElementById("admin_mng").style.display = "none";
	document.getElementById("admin_mng").style.visibility = "hidden";
	document.getElementById("contest_mng").style.display = "block";
	document.getElementById("contest_mng").style.visibility = "visible";
	document.getElementById("reports_mng").style.display = "none";
	document.getElementById("reports_mng").style.visibility = "hidden";
}

function showReportsMng(){
	document.getElementById("user_mng").style.display = "none";
	document.getElementById("user_mng").style.visibility = "hidden";
	document.getElementById("admin_mng").style.display = "none";
	document.getElementById("admin_mng").style.visibility = "hidden";
	document.getElementById("contest_mng").style.display = "none";
	document.getElementById("contest_mng").style.visibility = "hidden";
	document.getElementById("reports_mng").style.display = "block";
	document.getElementById("reports_mng").style.visibility = "visible";
}