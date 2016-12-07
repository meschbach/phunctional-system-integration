<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Are you lost?</title>
    </head>
    <body>
        <h1>Are you lost</h1>
        <div>
            <h2>Humans</h2>
            <p><a href="<%= request.getContextPath() + "/hcwi"%>">Human Computer Web Interface</a></p>
        </div>
        <div>
            <h2>Distributed Computing Interface</h2>
            <p>The RESTful style web service is exported at <a href="<%= request.getContextPath() +"/dci" %>"><%= request.getContextPath() + "/dci"%></a></p>
        </div>
    </body>
</html>
