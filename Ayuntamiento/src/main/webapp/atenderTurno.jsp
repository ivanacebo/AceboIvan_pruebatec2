<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<style> 
    h1 {
        margin-bottom: 20px;
    }
    
    .btn-custom {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
        padding: 10px 20px;
        font-size: 18px;
        border-radius: 5px;
        transition: all 0.3s ease;
    }
    
    .btn-custom:hover {
        background-color: #007bff;
        border-color: #0056b3;
    }
</style>
<div class="container">
    <h1>Usted va a atender el siguiente turno</h1>
    <form action="SvAtenderTurno" method="post">
        <button type="submit" class="btn btn-custom">Atender siguiente turno</button>
    </form>
</div>

<%@include file="componentes/parteAbajo.jsp"%>