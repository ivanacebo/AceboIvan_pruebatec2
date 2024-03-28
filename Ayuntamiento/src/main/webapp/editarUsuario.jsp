<%@page import="principal.ayuntamiento.logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<h1>Editar Usuarios</h1>
<p>Este apartado es para modificar un usuario del sistema</p>

<%Usuario usu = (Usuario) request.getSession().getAttribute("usuarioEditar");

    String nombre = usu.getNombreUsuario();
    String password = usu.getPassword();
    String rol = usu.getRol();

%>

<form class="user" action="SvEditarUsuario" method="POST">
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre_usuario" name="nombreUsuario"
                   placeholder="Nombre" value="<%=nombre%>">
        </div>
        <div class="col-sm-6 mb-3" >
            <input type="password" class="form-control form-control-user" id="password" name="password"
                   placeholder="password" value="<%=password%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="rol" class="form-control form-control-user" id="rol" name="rol"
                   placeholder="Rol" value="<%=rol%>">
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar Cambios
    </button>


</form>

<%@include file="componentes/parteAbajo.jsp"%>
