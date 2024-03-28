<%@page import="principal.ayuntamiento.logica.Tramite"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>


<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <h1 class="h3 mb-2 text-gray-800">Ver Trámites</h1>
            <p class="mb-4">A continuación podrá visualizar la lista completa de Trámites</p>
            <div class="card mb-4">
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th style="width: 210px">Acción</th>
                            </tr>
                        </thead>

                        <% List<Tramite> listaTramites = (List) request.getSession().getAttribute("listaTramites"); %>

                        <tbody>
                            <%
                                for (Tramite tramite : listaTramites) {
                            %>
                            <tr>
                                <td><%=tramite.getId()%></td>
                                <td><%=tramite.getNombre()%></td>
                                <td><%=tramite.getDescipcion()%></td>
                                <td style="display:flex; width: 230px;">

                                    <div style="display: flex;">
                                        <form name="eliminar" action="SvEliminarTramite" method="POST" style="margin-right: 5px;">
                                            <button type="submit" class="btn btn-primary btn-user" style="background-color: red; display: flex; align-items: center;">
                                                <i class="fas fa-trash-alt" style="margin-right: 5px;"></i> 
                                                Eliminar
                                            </button>
                                            <input type="hidden" name="id" value="<%=tramite.getId()%>">
                                        </form>

                                        <form name="editar" action="SvEditarTramite" method="GET">
                                            <button type="submit" class="btn btn-primary btn-user" style="margin-left:5px; display: flex; align-items: center;">
                                                <i class="fas fa-pen-alt" style="margin-right: 5px;"></i> 
                                                Editar
                                            </button>
                                            <input type="hidden" name="id" value="<%=tramite.getId()%>">
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
