<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>--%>

<html>
<head>
    <title><tiles:getAsString name="title" ignore="true" /></title>
</head>

<body>
<table border="1" width="100%">
    <tr valign="top">
        <td colspan="5"><insert attribute="header" ignore="true" /></td>
    </tr>
    <tr valign="top">
        <td width="100"><tiles:insert attribute="menu"/></td>
        <td>&nbsp;</td>
        <td><tiles:insert attribute="body"/></td>
        <td>&nbsp;</td>
        <td width="100"><tiles:insert attribute="sidebar"/></td>
    <tr>
        <td colspan=5 height=20%><tiles:insert attribute="footer" /></td>
    </tr>
</table>
</body>
</html>