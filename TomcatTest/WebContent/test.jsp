<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.File,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Total Commander</title>
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
	int number = 4444;
	%>
	<div class="container" style="position: relative">
	<h1>JSP</h1>
		<div class="flex">
			<div>
				<table style="width: 100%">
					<tr>
						<th><input type="checkbox" name="leftAll" id="leftAll" onclick="handleCheckAllLeftBox()" /></th>
						<th>name</th>
						<th>ext</th>
						<th>size</th>
						<th>date</th>
					</tr>
					<tr>
						<td></td>
						<td>
							<form method='get' action=<%=requestUri%> class='inline'>
								<input type='hidden' name='submitLeft_name' value='prev'>
								<button type='submit' name='fromWhere' value='left' class='link-button'>...</button>
							</form>
						</td>
						<td></td>
						<td>DIR</td>
						<td></td>
					</tr>
					<%
						for (String file : fileListLeft) {
						File fileData = new File(FOLDER_PATH_LEFT + file);
					%>
					<tr>
						<td><input type="checkbox" name="checkBoxLeft" value="<%=fileData.getName()%>"
								id="<%=fileData.getName()%>" onclick="handleCheckBoxClick('<%=fileData.getName()%>')" />
						</td>
						<%
							if (fileData.isDirectory()) {
						%>
						<td>
							<form method='get' action=<%=requestUri%> class='inline'>
								<input type='hidden' name='submitLeft_name' value="<%=fileData.getName()%>">
								<button type='submit' name='fromWhere' value='left' class='link-button'>
									<%=fileData.getName()%>
								</button>
							</form>
						</td>
						<%
							} else {
						%>
						<td><%=fileData.getName()%></td>
						<%
							}
						%>
						<%
							String extension = "";
						int i = fileData.getName().lastIndexOf('.');
						if (i > 0) {
							extension = fileData.getName().substring(i + 1);
						}
						%>
						<td><%=extension%></td>
						<%
							if (!fileData.isDirectory()) {
						%>
						<td><%=String.format("%,d", fileData.length())%></td>
						<%
							} else {
						%>
						<td>DIR</td>
						<%
							}
						%>
						<%
							SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						%>
						<td><%=dateFormat.format(fileData.lastModified())%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			<div style="text-align: center; width: 60px">
				<div>
					<button onclick="handleInputAppend()">copy</button>
				</div>
				<div>
					<button>move</button>
				</div>
				<div>
					<button>delete</button>
				</div>
			</div>
			<div>
				<table style="width: 100%">
					<tr>
						<th><input type="checkbox" name="rightAll" id='rightAll' onclick="handleCheckAllRightBox()" />
						</th>
						<th>name</th>
						<th>ext</th>
						<th>size</th>
						<th>date</th>
					</tr>
					<tr>
						<td></td>
						<td>
							<form method='get' action=<%=requestUri%> class='inline'>
								<input type='hidden' name='submitRight_name' value='prev'>
								<button type='submit' name='fromWhere' value='right' class='link-button'>...</button>
							</form>
						</td>
						<td></td>
						<td>DIR</td>
						<td></td>
					</tr>
					<%
						for (String file : fileListRight) {
						File fileData = new File(FOLDER_PATH_RIGHT + file);
					%>
					<tr>
						<td><input type="checkbox" name="checkBoxRight" value="<%=fileData.getName()%>"
								id="<%=fileData.getName()%>" onclick="handleCheckBoxClick('<%=fileData.getName()%>')" />
						</td>
						<%
							if (fileData.isDirectory()) {
						%>
						<td>
							<form method='get' action=<%=requestUri%> class='inline'>
								<input type='hidden' name='submitRight_name' value="<%=fileData.getName()%>">
								<button type='submit' name='fromWhere' value='right' class='link-button'>
									<%=fileData.getName()%>
								</button>
							</form>
						</td>
						<%
							} else {
						%>
						<td><%=fileData.getName()%></td>
						<%
							}
						%>
						<%
							String extension = "";
						int i = fileData.getName().lastIndexOf('.');
						if (i > 0) {
							extension = fileData.getName().substring(i + 1);
						}
						%>
						<td><%=extension%></td>
						<%
							if (!fileData.isDirectory()) {
						%>
						<td><%=String.format("%,d", fileData.length())%></td>
						<%
							} else {
						%>
						<td>DIR</td>
						<%
							}
						%>
						<%
							SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						%>
						<td><%=dateFormat.format(fileData.lastModified())%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
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
			document.getElementById('leftAll').checked
				? checkBoxesLeft.forEach(checkBox => {
					checkBox.checked = true
					checkedList.push(checkBox.value)
				})
				: checkBoxesLeft.forEach(checkBox => checkBox.checked = false)
			console.log(checkedList);
		}
		const handleCheckAllRightBox = () => {
			checkedList = []
			const checkBoxesRight = document.getElementsByName('checkBoxRight');
			document.getElementById('rightAll').checked
				? checkBoxesRight.forEach(checkBox => {
					checkBox.checked = true
					checkedList.push(checkBox.value)
				})
				: checkBoxesRight.forEach(checkBox => checkBox.checked = false)
			console.log(checkedList);
		}
		const handleInputAppend = () => {
			checkedList.forEach(item => {
				const input = document.createElement('input');
				input.type = 'hidden';
				input.name = 'submitCopy[]'
				input.value = item;
				document.getElementById("copyForm").appendChild(input);
			})
			document.getElementById('copyDiv').style.display = 'block';
		}
		const handleCopyDivDisplay = () => {
			document.getElementById('copyDiv').style.display = 'none';
		}
	</script>
</body>

</html>