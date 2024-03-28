<%@page import="principal.ayuntamiento.logica.Funcionario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/parteArriba.jsp"%>


<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <h1 class="h3 mb-2 text-gray-800">Ver Funcionarios</h1>
            <p class="mb-4">A continuación podrá visualizar la lista completa de Funcionarios</p>
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
                                <th>Cargo</th>
                                <th>Departamento</th>
                                <th style="width: 210px">Acción</th>
                            </tr>
                        </thead>

                        <% List<Funcionario> listaFuncionarios = (List) request.getSession().getAttribute("listaFuncionarios");
                        %>

                        <tbody>
                            <%
                                for (Funcionario funcionario : listaFuncionarios) {
                            %>
                            <tr>
                                <td><%=funcionario.getId()%></td>
                                <td><%=funcionario.getDni()%></td>
                                <td><%=funcionario.getNombre()%></td>
                                <td><%=funcionario.getApellido()%></td>
                                <td><%=funcionario.getTelefono()%></td>
                                <td><%=funcionario.getDireccion()%></td>
                                <td><%=funcionario.getFechaFormateada()%></td>
                                <td><%=funcionario.getCargo()%></td>
                                <td><%=funcionario.getDepartamento()%></td>

                                <td style="display:flex; width: 230px;">

                                    <div style="display: flex;">
                                        <form name="eliminar" action="SvEliminarFuncionario" method="POST" style="margin-right: 5px;">
                                            <button type="submit" class="btn btn-primary btn-user" style="background-color: red; display: flex; align-items: center;">
                                                <i class="fas fa-trash-alt" style="margin-right: 5px;"></i> 
                                                Eliminar
                                            </button>
                                            <input type="hidden" name="id" value="<%=funcionario.getId()%>">
                                        </form>

                                        <form name="editar" action="SvEditarFuncionario" method="GET">
                                            <button type="submit" class="btn btn-primary btn-user" style="margin-left:5px; display: flex; align-items: center;">
                                                <i class="fas fa-pen-alt" style="margin-right: 5px;"></i> 
                                                Editar
                                            </button>
                                            <input type="hidden" name="id" value="<%=funcionario.getId()%>">
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
