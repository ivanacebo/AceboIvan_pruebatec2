<%@page import="principal.ayuntamiento.logica.Tramite"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<h1>Editar Trámites</h1>

<% Tramite tramite = (Tramite) request.getSession().getAttribute("tramiteEditar");

    String nombre = tramite.getNombre();
    String descripcion = tramite.getDescipcion();

%>

<form class="tramite" action="SvEditarTramite" method="POST">
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" value="<%=nombre%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name="descripcion"
                   placeholder="Descripcion" value="<%=descripcion%>">
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar Trámite
    </button>

</form>

<%@include file="componentes/parteAbajo.jsp"%>
