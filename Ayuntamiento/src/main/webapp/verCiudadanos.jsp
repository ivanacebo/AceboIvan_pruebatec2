<%@page import="principal.ayuntamiento.logica.Ciudadano"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <h1 class="h3 mb-2 text-gray-800">Ver Ciudadanos</h1>
            <p class="mb-4">A continuación podrá visualizar la lista completa de Ciudadanos</p>
            <div class="card mb-4">
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>DNI</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Telefono</th>
                                <th>Dirección</th>
                                <th>Fecha Nacimiento</th>
                                <th style="width: 210px">Acción</th>
                            </tr>
                        </thead>

                        <%
                            List<Ciudadano> listaCiudadanos = (List) request.getSession().getAttribute("listaCiudadanos");
                        %>

                        <tbody>
                            <%
                                for (Ciudadano ciudadano : listaCiudadanos) {
                            %>
                            <tr>
                                <td><%=ciudadano.getId()%></td>
                                <td><%=ciudadano.getDni()%></td>
                                <td><%=ciudadano.getNombre()%></td>
                                <td><%=ciudadano.getApellido()%></td>
                                <td><%=ciudadano.getTelefono()%></td>
                                <td><%=ciudadano.getDireccion()%></td>
                                <td><%=ciudadano.getFechaFormateada()%></td>

                                <td style="display:flex; width: 230px;">

                                    <div style="display: flex; align-items: center;">
                                        <form name="eliminar" action="SvEliminarCiudadano" method="POST" style="margin-right: 5px;">
                                            <button type="submit" class="btn btn-primary btn-user" style="background-color: red; display: flex; align-items: center;">
                                                <i class="fas fa-trash-alt" style="margin-right: 5px;"></i> 
                                                Eliminar
                                            </button>
                                            <input type="hidden" name="id" value="<%=ciudadano.getId()%>">
                                        </form>

                                        <form name="editar" action="SvEditarCiudadano" method="GET">
                                            <button type="submit" class="btn btn-primary btn-user" style="display: flex; align-items: center;">
                                                <i class="fas fa-pen-alt" style="margin-right: 5px;"></i> 
                                                Editar
                                            </button>
                                            <input type="hidden" name="id" value="<%=ciudadano.getId()%>">
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