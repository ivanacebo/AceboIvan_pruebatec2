<%@page import="principal.ayuntamiento.logica.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<% Funcionario funcionario = (Funcionario) request.getSession().getAttribute("funcionarioEditar");

    String nombre = funcionario.getNombre();
    String apellido = funcionario.getApellido();
    String dni = funcionario.getDni();
    String cargo = funcionario.getCargo();
    String departamento = funcionario.getDepartamento();
    String telefono = funcionario.getTelefono();
    String direccion = funcionario.getDireccion();
    String fecha = funcionario.getFechaFormateada();

%>

<h1>Editar Funcionario</h1>

<form class="funcionario" action="SvEditarFuncionario" method="POST">
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
                   placeholder="nombre" value="<%=nombre%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
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
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion"
                   placeholder="Direccion" value="<%=direccion%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="cargo" name="cargo"
                   placeholder="Cargo" value="<%=cargo%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name="departamento"
                   placeholder="Departamento" value="<%=departamento%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
                   placeholder="Fecha" value="<%=fecha%>">
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar Cambios
    </button>

</form>

<%@include file="componentes/parteAbajo.jsp"%>