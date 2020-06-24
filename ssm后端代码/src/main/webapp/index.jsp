<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>file upload</title>
</head>
<body>
<h1>   该页面仅做测试使用，若想启动项目，需要启动前端，后端只提供接口</h1>
<form action="/user/upload" enctype="multipart/form-data" method="post">
    <input type="file" name="upload"><br><br>
    <input type="submit" value="上传">
</form>

<a href="/blog/findAll">博文所有</a>


<form
        action="/comment/addComment"
>
    name:<input name="name" type="text">
    bid:<input name="bid" type="text">

    context:<input name="context" type="text">
    <input type="submit" value="提交">
</form>

<hr/>
<form action="/comment/findCommentById">
   bid <input name="id" type="text"/>
    <input type="submit" value="查询">
</form>


<hr/>
<form action="/blog/delBlog">
    删除id <input name="id" type="number"/>
    <input type="submit" value="删除">
</form>
</body>
</html>
