<%@page import="principal.ayuntamiento.logica.Turno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<style>
    .custom-date-input {
        width: 200px;
    }

    .custom-select-estado {
        width: 200px;
    }

    .custom-btn-filtrar:hover {
        background-color: #0056b3;
    }
</style>

<div class="container mt-5">
    <h1 class="mb-4">Filtrar Turnos por Fecha y Estado</h1>
    <form class="form-inline" action="SvFiltroTurno" method="GET">
        <div class="d-flex justify-content-between">
            <div class="form-group">
                <label for="estado"><strong>Estado:</strong></label><br>
                <select class="form-control" name="estado" id="estado">
                    <option value="En espera">En espera</option>
                    <option value="Ya atendido">Ya atendido</option>
                </select>
            </div>
            <div class="form-group">
                <label for="fecha"><strong>Fecha:</strong></label><br>
                <input type="date" class="form-control custom-date-input" id="fecha" name="fecha" value="<%= java.time.LocalDate.now()%>">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary custom-btn-filtrar">FILTRAR</button>
            </div>
        </div>
    </form>
</div>


<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <h1 class="h3 mb-2 text-gray-800">Ver Turnos Filtrados</h1>
            <p class="mb-4">A continuación podrá visualizar la lista por Turnos Filtrados</p>
            <div class="card mb-4">
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                            <tr>
                                <th>Nº</th>
                                <th>Fecha</th>
                                <th>Hora</th>
                                <th>Estado</th>
                                <th>Trámite</th>
                                <th>Ciudadano</th>
                                <th>Departamento</th>
                                <th>Funcionario</th>
                            </tr>
                        </thead>

                        <%    // Llamar a los servlets para obtener las listas de Funcionarios, Trámites y Ciudadanos
                            request.getRequestDispatcher("SvTramite").include(request, response);
                            request.getRequestDispatcher("SvFuncionario").include(request, response);
                            request.getRequestDispatcher("SvCiudadano").include(request, response);
                            request.getRequestDispatcher("SvTurno").include(request, response);
                        %>

                        <% List<Turno> listaTurnos = (List) request.getSession().getAttribute("listadoTurnosFiltrados"); %>

                        <tbody>
                            <%
                                for (Turno turno : listaTurnos) {
                            %>

                            <tr>
                                <td><%=turno.getId()%></td>
                                <td><%=turno.getFechaTurno()%></td>
                                <td><%=turno.getHoraTurno()%></td>
                                <td><%=turno.getEstado()%></td>
                                <td><%=turno.getTramite().getNombre()%></td>
                                <td><%=turno.getCiudadano().getDni()%></td>
                                <td><%=turno.getFuncionario().getDepartamento()%></td>
                                <td><%=turno.getFuncionario().getNombre()%></td>
                            </tr>

                            <% }%>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </main>
</div>


<%@include file="componentes/parteAbajo.jsp"%>
