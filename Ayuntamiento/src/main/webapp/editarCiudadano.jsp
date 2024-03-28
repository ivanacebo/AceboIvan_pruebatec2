<%@page import="principal.ayuntamiento.logica.Ciudadano"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<h1>Editar Ciudadano</h1>

<% Ciudadano ciudadano = (Ciudadano) request.getSession().getAttribute("ciudadanoEditar");

    String nombre = ciudadano.getNombre();
    String apellido = ciudadano.getApellido();
    String dni = ciudadano.getDni();
    String telefono = ciudadano.getTelefono();
    String direccion = ciudadano.getDireccion();
    String fecha = ciudadano.getFechaFormateada();

%>

<form class="user" action="SvEditarCiudadano" method="POST">
    <div class="form-group col">

        <% if (request.getAttribute("errorDNI") != null) {%>
        <div class="col-sm-6 mb-3" style=" color: red" role="alert">
            <strong><%= request.getAttribute("errorDNI")%></strong>
        </div>
        <% }%>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" value="<%=dni%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" value="<%=nombre%>">
        </div>
        <div class="col-sm-6 mb-3" >
            <input type="text" class="form-control form-control-user" id="password" name="apellido"
                   placeholder="Apellido" value="<%=apellido%>">
        </div>
        <% if (request.getAttribute("errorTelefono") != null) {%>
        <div class="col-sm-6 mb-3" style=" color: red" role="alert">
            <strong><%= request.getAttribute("errorTelefono")%></strong>
        </div>
        <% }%>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Telefono" value="<%=telefono%>">
        </div>
        <div class="col-sm-6 mb-3" >
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion"
                   placeholder="Direccion" value="<%=direccion%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
                   placeholder="Fecha" value="<%=fecha%>">
        </div>

        <button class="btn btn-primary btn-user btn-block" type="submit">
            Guardar Ciudadano
        </button>

</form>

<%@include file="componentes/parteAbajo.jsp"%>