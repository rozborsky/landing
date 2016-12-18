<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>SQLCmd</title>
        <style>
           <%@include file='style.css' %>
        </style>
	</head>

	<body>
        <h1 class="startH1">first</h1>
        <div class="logForm">
            <form:form action="confirmation" modelAttribute="employee" enctype="multipart/form-data" method="post">
                <table>
                    <tr>
                        <td>name</td>
                        <td>
                            <form:input type="text" path="name"/>
                            <form:errors path="name" id="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>surname</td>
                        <td>
                            <form:input type="text" path="secondName"/>
                            <form:errors path="secondName" id="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>e-mail</td>
                        <td>
                            <form:input type="text" path="eMail"/>
                            <form:errors path="eMail" id="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>remarks</td>
                        <td>
                            <form:input type="text" path="remarks"/>
                            <form:errors path="remarks" id="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td><input type="submit" value="registration"/></td>
                    </tr>
                </table>
            </form:form>
        </div>
	</body>
</html>