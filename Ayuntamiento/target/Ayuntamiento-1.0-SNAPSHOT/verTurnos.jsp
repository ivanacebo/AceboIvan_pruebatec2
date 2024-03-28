<%@page import="principal.ayuntamiento.logica.Turno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>


<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <h1 class="h3 mb-2 text-gray-800">Ver Turnos</h1>
            <p class="mb-4">A continuación podrá visualizar la lista completa de Turnos</p>
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
                                <th style="width: 210px">Acción</th>
                            </tr>
                        </thead>

                        <%    // Llamar a los servlets para obtener las listas de Funcionarios, Tr�mites y Ciudadanos
                            request.getRequestDispatcher("SvTramite").include(request, response);
                            request.getRequestDispatcher("SvFuncionario").include(request, response);
                            request.getRequestDispatcher("SvCiudadano").include(request, response);
                            request.getRequestDispatcher("SvTurno").include(request, response);
                        %>

                        <% List<Turno> listaTurnos = (List) request.getSession().getAttribute("listaTurnos"); %>

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

                                <td style="display:flex; width: 230px;">

                                    <div style="display: flex;">
                                        <form name="eliminar" action="SvEliminarTurno" method="POST" style="margin-right: 5px;">
                                            <button type="submit" class="btn btn-primary btn-user" style="background-color: red; display: flex; align-items: center;">
                                                <i class="fas fa-trash-alt" style="margin-right: 5px;"></i> 
                                                Eliminar
                                            </button>
                                            <input type="hidden" name="id" value="<%=turno.getId()%>">
                                        </form>

                                        <form name="editar" action="SvEditarTurno" method="GET">
                                            <button type="submit" class="btn btn-primary btn-user" style="margin-left:5px; display: flex; align-items: center;">
                                                <i class="fas fa-pen-alt" style="margin-right: 5px;"></i> 
                                                Editar
                                            </button>
                                            <input type="hidden" name="id" value="<%=turno.getId()%>">
                                        </form>
                                    </div>
                                </td>

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
