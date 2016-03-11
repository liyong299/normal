<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影院结算金额总表</title>
<link href="<%=request.getContextPath()%>/resources/css/common/common.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/resources/scripts/jquery-1.11.1.min.js"></script>
</head>
<body>
 <table class="print" cellpadding="0" cellspacing="0">
            <thead>
                <tr>
                    <th>影院编码</th>
                    <th>影院名称</th>
                    <th>地区</th>
                    <th>平台结算金额</th>
                    <th>对账金额</th>
                    <th>调账</th>
                    <th>付款金额</th>
                    <th>结算月份</th>
                    <th>备注</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list}">
                     <tr>
                         <td>${item.cinemano}</td>
                         <td>${item.cinemaName}</td>
                         <td>${item.cityName}</td>
                         <td>${item.settlemntAmount}</td>
                         <td>${item.checkAmount}</td>
                         <td>${item.manualAmount}</td>
                         <td>${item.payAmount}</td>
                         <td>${item.settlmentDate}</td>
                         <td>${item.remark}</td>
                     </tr>
                </c:forEach>
            </tbody>
</table>
 <script>
            $(function () {
                if ("${fn:length(list)}" > 0){
                    window.print();
                }
                else{
                    parent.Tips.Error("没有数据可打印");
                }
            });
        </script>
 
</body>
</html>