<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<h1>Alta Ciudadano</h1>
<p>Ingrese los datos solicitados para dar de alta al nuevo Ciudadano</p>

<form class="user" action="SvCiudadano" method="POST">
    <div class="form-group col">

        <% if (request.getAttribute("errorDNI") != null) {%>
        <div class="col-sm-6 mb-3" style=" color: red" role="alert">
            <strong><%= request.getAttribute("errorDNI")%></strong>
        </div>
        <% } %>

        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" required>
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" required>
        </div>
        <div class="col-sm-6 mb-3" >
            <input type="text" class="form-control form-control-user" id="password" name="apellido"
                   placeholder="Apellido" required>
        </div>
        <% if (request.getAttribute("errorTelefono") != null) {%>
        <div class="col-sm-6 mb-3" style=" color: red" role="alert">
            <strong><%= request.getAttribute("errorTelefono")%></strong>
        </div>
        <% }%>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Telefono" required>
        </div>
        <div class="col-sm-6 mb-3" >
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion"
                   placeholder="Direccion" required>
        </div>
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
                   placeholder="Fecha" required>
        </div>

    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Ciudadano
    </button>

</form>




<%@include file="componentes/parteAbajo.jsp"%>
