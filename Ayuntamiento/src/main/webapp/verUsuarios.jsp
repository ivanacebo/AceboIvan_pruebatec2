<%@page import="principal.ayuntamiento.logica.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>

<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <h1 class="h3 mb-2 text-gray-800">Ver Usuarios</h1>
            <p class="mb-4">A continuación podrá visualizar la lista completa de Usuarios</p>
            <div class="card mb-4">
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>Nombre</th>
                                <th>Rol</th>
                                <th style="width: 210px">Acción</th>
                            </tr>
                        </thead>

                        <% List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios"); %>

                        <tbody>
                            <%
                                for (Usuario usuario : listaUsuarios) {
                            %>
                            <tr>
                                <td><%=usuario.getIdUsuario()%></td>
                                <td><%=usuario.getNombreUsuario()%></td>
                                <td><%=usuario.getRol()%></td>

                                <td style="display:flex; width: 230px;">

                                    <form name="eliminar" action="SvEliminarUsuario" method="POST" style="display: inline-block;">
                                        <button type="submit" class="btn btn-primary btn-user" style="background-color: red; margin-right: 5px;">
                                            <i class="fas fa-trash-alt"></i>Eliminar
                                        </button>
                                        <input type="hidden" name="id" value="<%=usuario.getIdUsuario()%>">
                                    </form>

                                    <form name="editar" action="SvEditarUsuario" method="GET" style="display: inline-block;"> 
                                        <button type="submit" class="btn btn-primary btn-user" style="margin-left:5px;">
                                            <i class="fas fa-pen-alt"></i>Editar
                                        </button>
                                        <input type="hidden" name="id" value="<%=usuario.getIdUsuario()%>">
                                    </form>
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
