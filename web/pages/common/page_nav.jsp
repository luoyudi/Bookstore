<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>


    <%--页码输出开始--%>
    <c:choose>

        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            [${i}]
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--页码输出结束--%>


    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" class="pageButton" type="button" value="确定">

    <script>
        $(function () {
            //调到指定页码
            $("#searchPageBtn").click(function () {
                let pageNo = $("#pn_input").val();
                let pageTotal = ${requestScope.page.pageTotal};
                //    获取地址栏对象location
                //    获取地址href,href可读可写
                location.href = "${pageScope.bathPath}${requestScope.page.url}&pageNo=" + pageNo;
            })
        })
    </script>
</div>
