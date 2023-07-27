<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.store.DbStore" %>
<%@ page import="ru.job4j.dream.model.Post" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.model.User" %>
<%@ page import="ru.job4j.dream.model.City" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Работа мечты</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script>
    function validate() {
        const name = $('#name').val();
        const cities = $('#cities').val();
        if (name == "") {
            alert($('#name').attr('title'));
            return false;
        }
        if (cities == "") {
            alert($('#cities').attr('title'));
            return false;
        }
        return true;
    }
</script>
<script>
    function addCity() {
        $.ajax({
            type: 'POST',
            crossDomain: true,
            url: '/dreamjob/city',
            data: JSON.stringify({
                name: $('#city').val()
            }),
            dataType: 'json'
        }).done(function (data) {
            $('#cities option:last').after('<option>' + data.name + '</option>')
        }).fail(function (err) {
            console.log(err);
        });
    }

    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            crossDomain: true,
            url: '/dreamjob/city',
            dataType: 'json'
        }).done(function (data) {
            for (var city of data) {
                $('#cities option:last').after('<option>' + city.name + '</option>')
            }
        }).fail(function (err) {
            console.log(err);
        });
    });
</script>
<script>
    console.log("Start");
    $(document).ready(function () {
        $.ajax({
            cache: false,
            type: 'GET',
            crossDomain: true,
            url: '/dreamjob/city',
            dataType: 'json'
        }).done(function (data) {
            console.log("Done");
            console.log("Done:" + data);
            for (var city of data) {
                console.log(city);
                // $('#cities option:last').after('<option>' + city.name + '</option>')
            }
        }).fail(function (err) {
            console.log(err);
        });
    });
</script>
<body>
<%
    String id = request.getParameter("id");
    Candidate candidate = new Candidate(0, "");
    if (id != null) {
        candidate = DbStore.instOf().findCandidateById(Integer.valueOf(id));
    }
%>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">Вакансии</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">Кандидаты</a>
            </li>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/post/edit.jsp">Добавить вакансию</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/candidate/edit.jsp">Добавить кандидата</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.name}"/> | Выйти</a>
                </li>
            </c:if>
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.do">Войти</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новый кандидат.
                <% } else { %>
                Редактирование кандидата.
                <% } %>
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>" method="post">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" name="name" id="name"
                               title="Введите имя" value="<%=candidate.getName()%>">
                    </div>
                    <div class="form-group">
                        <label for="cities">Город</label>
                        <select class="form-control" id="cities" name="cities" title="Выберите город">
                            <option></option>
                            <%--<% System.out.println(citiesList); %>--%>
                            <c:forEach items="${citiesList}" var="city">
                                <option><c:out value="${city.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate();">Сохранить</button>
                </form>
                <br>
                <form>
                    <div class="form-group">
                        <label for="city" style="font-weight: bold">Другой город</label>
                        <input type="text" class="form-control" id="city" aria-describedby="emailHelp"
                               placeholder="Введите город">
                    </div>
                    <button type="button" class="btn btn-primary" onclick="addCity()">Submit</button>
                </form>
                <br>
            </div>
        </div>
    </div>
</div>
</body>
</html>