<%@page import="java.time.LocalDate"%>
<%@page import="principal.ayuntamiento.logica.Turno"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalTime"%>
<%@page import="principal.ayuntamiento.logica.Tramite"%>
<%@page import="principal.ayuntamiento.logica.Ciudadano"%>
<%@page import="principal.ayuntamiento.logica.Funcionario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<h1>Editar Turno</h1>

<%    // Llamar a los servlets para obtener las listas de Funcionarios, Ciudadanos y Tr�mites
    request.getRequestDispatcher("SvFuncionario").include(request, response);
    request.getRequestDispatcher("SvCiudadano").include(request, response);
    request.getRequestDispatcher("SvTramite").include(request, response);
%>

<%
    Turno turno = (Turno) request.getSession().getAttribute("turnoEditar");

    LocalDate fecha = turno.getFechaTurno();
    LocalTime hora = turno.getHoraTurno();

%>

<form class="turno" action="SvEditarTurno" method="POST">

    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
                   placeholder="Fecha">
        </div>
        <div class="col-sm-6 mb-3" >
            <input type="time" class="form-control form-control-user" id="hora" name="hora"
                   placeholder="Hora">
        </div>

        <div class="col-sm-6 mb-3">
            <label for="funcionario">Seleccionar Funcionario</label>
            <select class="form-control" id="funcionario" name="funcionario">
                <% List<Funcionario> listaFuncionarios = (List<Funcionario>) request.getSession().getAttribute("listaFuncionarios");
                    for (Funcionario funcionario : listaFuncionarios) {%>
                <option value="<%= funcionario.getId()%>"><%= funcionario.getDepartamento() + " -- " + funcionario.getNombre()%></option>
                <% }%>
            </select>
        </div>

        <div class="col-sm-6 mb-3">
            <label for="ciudadano">Seleccionar Ciudadano</label>
            <select class="form-control" id="ciudadano" name="ciudadano">
                <% List<Ciudadano> listaCiudadanos = (List<Ciudadano>) request.getSession().getAttribute("listaCiudadanos");
                    for (Ciudadano ciudadano : listaCiudadanos) {%>
                <option value="<%= ciudadano.getId()%>"><%=ciudadano.getDni() + "-" + ciudadano.getNombre() + " " + ciudadano.getApellido()%></option>
                <% }%>
            </select>
        </div>

        <div class="col-sm-6 mb-3">
            <label for="tramite">Seleccionar Trámite</label>
            <select class="form-control" id="tramite" name="tramite">
                <% List<Tramite> listaTramites = (List<Tramite>) request.getSession().getAttribute("listaTramites");
                    for (Tramite tramite : listaTramites) {%>
                <option value="<%= tramite.getId()%>"><%= tramite.getNombre()%></option>
                <% }%>
            </select>
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar Cambios
    </button>


</form>

<%@include file="componentes/parteAbajo.jsp"%>
