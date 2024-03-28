<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<h1>Alta Usuarios</h1>

<form class="user" action="SvUsuario" method="POST">
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombreUsuario" name="nombreUsuario"
                   placeholder="Nombre" required>
        </div>
        <div class="col-sm-6 mb-3" >
            <input type="password" class="form-control form-control-user" id="password" name="password"
                   placeholder="Password" required>
        </div>
        <div class="col-sm-6 mb-3">
            <input type="rol" class="form-control form-control-user" id="rol" name="rol"
                   placeholder="Rol" required>
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Usuario
    </button>


</form>

<%@include file="componentes/parteAbajo.jsp"%>