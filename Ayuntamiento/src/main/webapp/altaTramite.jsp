<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<h1>Alta Trámites</h1>

<form class="tramite" action="SvTramite" method="POST">
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" required>
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name="descripcion"
                   placeholder="Descripcion" required>
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Trámite
    </button>

</form>



<%@include file="componentes/parteAbajo.jsp"%>
