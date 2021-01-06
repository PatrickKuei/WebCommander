<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="myprefix" uri="WEB-INF/message.tld"%>
<%@ taglib prefix="test" uri="WEB-INF/message.tld"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>WEB Commander</title>
	<style>
		table,
		th,
		td {
			border: 1px solid black;
		}

		.inline {
			display: inline;
		}

		.link-button {
			background: none;
			border: none;
			color: blue;
			text-decoration: underline;
			cursor: pointer;
			font-family: serif;
			padding: 0;
		}

		.link-button:focus {
			outline: none;
		}

		.link-button:active {
			color: red;
		}

		.flex {
			width: 100%;
			display: flex;
		}

		.flex>div {
			width: 50%;
			margin: 10px;
		}
	</style>
</head>

<body>
	<%
		String FOLDER_PATH_LEFT = (String) request.getAttribute("FOLDER_PATH_LEFT");
	String FOLDER_PATH_RIGHT = (String) request.getAttribute("FOLDER_PATH_RIGHT");
	String requestUri = (String) request.getAttribute("requestUri");
	String[] fileListLeft = (String[]) request.getAttribute("fileListLeft");
	String[] fileListRight = (String[]) request.getAttribute("fileListRight");
	%>
	<div class="container" style="position: relative">
		<h1>Custom Tag</h1>
		<div class="flex">
			<myprefix:MyMsg FOLDER_PATH="<%=FOLDER_PATH_LEFT%>" requestUri="<%=requestUri%>"
				fileList="<%=fileListLeft%>" direction="left" />
			<div style="text-align: center; width: 60px">
				<div>
					<button onclick="handleInputAppend('copy')">copy</button>
				</div>
				<div>
					<button>move</button>
				</div>
				<div>
					<button onclick="handleInputAppend('delete')">delete</button>
				</div>
			</div>
			<myprefix:MyMsg FOLDER_PATH="<%=FOLDER_PATH_RIGHT%>" requestUri="<%=requestUri%>"
				fileList="<%=fileListRight%>" direction="right" />
		</div>
		<div id='copyDiv' style="display: none;">
			<div
				style="border: 1px solid black; position: absolute; background: rgb(249, 249, 255); top: 30%; left: 31%;">
				<button onclick="handleCopyDivDisplay()">X</button>
				<div style="margin: 20px">
					<h4>Check your destination to paste files</h4>
					<form id="copyForm" class='inline' method='post' action=<%=requestUri%>>
						<input type="text" value="<%=FOLDER_PATH_RIGHT%>" size="60" />
						<button type='submit'>paste</button>
					</form>
				</div>
			</div>
		</div>
		<div id='deleteDiv' style="display: none;">
			<div
				style="border: 1px solid black; position: absolute; background: rgb(249, 249, 255); top: 30%; left: 43%;">
				<button onclick="handleDeleteDivDisplay()">X</button>
				<div style="margin: 20px">
					<h4>Do you really want to do it?</h4>
					<form id="deleteForm" class='inline' method='post' action="/TomcatTest/Delete">
						<input type="hidden" name='path' value="<%=FOLDER_PATH_LEFT%>" size="60" />
						<button type='submit'>delete anyway</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		var checkedList = [];
		const handleCheckBoxClick = (id) => {
			checkedList = document.getElementById(id).checked
				? [...checkedList, id]
				: checkedList.filter((i) => i !== id);
			console.log(checkedList);
		};
		const handleCheckAllLeftBox = () => {
			checkedList = []
			const checkBoxesLeft = document.getElementsByName('checkBoxLeft');
			const checkBoxesRight = document.getElementsByName('checkBoxRight');
			const rightAll = document.getElementById("rightAll");
			document.getElementById('leftAll').checked
				? (checkBoxesLeft.forEach(checkBox => {
					checkBox.checked = true
					checkedList.push(checkBox.value)
				}), rightAll.checked = false,
					checkBoxesRight.forEach(checkBox => checkBox.checked = false))
				: checkBoxesLeft.forEach(checkBox => checkBox.checked = false)
			console.log(checkedList);
		}
		const handleCheckAllRightBox = () => {
			checkedList = []
			const checkBoxesLeft = document.getElementsByName('checkBoxLeft');
			const checkBoxesRight = document.getElementsByName('checkBoxRight');
			const leftAll = document.getElementById("leftAll");
			document.getElementById('rightAll').checked
				? (checkBoxesRight.forEach(checkBox => {
					checkBox.checked = true
					checkedList.push(checkBox.value)
				}), leftAll.checked = false,
					checkBoxesLeft.forEach(checkBox => checkBox.checked = false))
				: checkBoxesRight.forEach(checkBox => checkBox.checked = false)
			console.log(checkedList);
		}
		const handleInputAppend = (funcName) => {
			checkedList.forEach(item => {
				const input = document.createElement('input');
				input.type = 'hidden';
				input.name = 'submitCopy[]'
				input.value = item;
				switch (funcName) {
					case "copy":
						document.getElementById("copyForm").appendChild(input);
						break;

					case "delete":
						document.getElementById("deleteForm").appendChild(input);
						break;
				}
			})
			switch (funcName) {
				case "copy":
					document.getElementById('copyDiv').style.display = 'block';
					break;

				case "delete":
					document.getElementById('deleteDiv').style.display = 'block';
					break;
			}
		}
		const handleCopyDivDisplay = () => {
			document.getElementById('copyDiv').style.display = 'none';
		}
		const handleDeleteDivDisplay = () => {
			document.getElementById('deleteDiv').style.display = 'none';
		}
	</script>
</body>

</html>