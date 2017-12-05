$(document).ready(function () {

    $.getJSON("http://localhost:8084/Restful/webresources/ADO2/cliente/listarClientes", function (result) {

        $.each(result, function (i, value) {

            $("#lista").append("<tr>" + "<td>" + '<input type="checkbox" value="' + value.codigoCliente + '"/>' + "</td>"
                    + "<td>" + value.nomeCliente
                    + "</td>" + "<td>" + value.login
                    + "</td>" + "<td>" + value.senhaCliente
                    + "</td>" + "</tr>");
        });
    });

    $("#salvar").on('click', function () {
        var cliente = new Object();
        cliente.nomeCliente = $("#nome").val();
        cliente.login = $("#login").val();
        cliente.senhaCliente = $("#senha").val();
        $("#nome").val('');
        $("#login").val('');
        $("#senha").val('');
        $.ajax({
            url: 'http://localhost:8084/Restful/webresources/ADO2/cliente/adicionarCliente',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(cliente),
            dataType: 'json'
        });
        $.getJSON("http://localhost:8084/Restful/webresources/ADO2/cliente/listarClientes", function (result) {
            $("#lista").empty();
            $.each(result, function (i, value) {

                $("#lista").append("<tr>" + "<td>" + '<input type="checkbox" value="' + value.codigoCliente + '"/>' + "</td>"
                        + "<td>" + value.nomeCliente
                        + "</td>" + "<td>" + value.login
                        + "</td>" + "<td>" + value.senhaCliente
                        + "</td>" + "</tr>");
            });
        });
    });

    $("#excluir").on('click', function () {
        var ids = $("#lista input:checkbox:checked").map(function () {
            return $(this).val();
        }).get();
        var cont = 0;

        $.each(ids, function () {

            $.ajax({
                url: 'http://localhost:8084/Restful/webresources/ADO2/cliente/deletar/' + ids[cont],
                type: 'DELETE'
            });

            cont++;
        });
        $.getJSON("http://localhost:8084/Restful/webresources/ADO2/cliente/listarClientes", function (result) {
            $("#lista").empty();
            $.each(result, function (i, value) {

                $("#lista").append("<tr>" + "<td>" + '<input type="checkbox" value="' + value.codigoCliente + '"/>' + "</td>"
                        + "<td>" + value.nomeCliente
                        + "</td>" + "<td>" + value.login
                        + "</td>" + "<td>" + value.senhaCliente
                        + "</td>" + "</tr>");
            });
        });
    });

    $("#edit").on('click', function () {
        var ids = $("#lista input:checkbox:checked").map(function () {
            return $(this).val();
        }).get();
        if (ids.length != 1) {
            return false;
        }
        $.getJSON("http://localhost:8084/Restful/webresources/ADO2/cliente/obter/" + ids[0], function (data) {
            $("#nome-editar").val(data.nomeCliente);
            $("#login-editar").val(data.login);
            $("#senha-editar").val(data.senhaCliente);
            $("#id-salvar").val(data.codigoCliente);

            $("#salvar-editar").on('click', function () {
                data.nomeCliente = $("#nome-editar").val();
                data.login = $("#login-editar").val();
                data.senhaCliente = $("#senha-editar").val();
                data.codigoCliente = $("#id-salvar").val();
                $.ajax({
                    url: 'http://localhost:8084/Restful/webresources/ADO2/cliente/editarCliente',
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    dataType: 'json'
                });
                $.getJSON("http://localhost:8084/Restful/webresources/ADO2/cliente/listarClientes", function (result) {
                    $("#lista").empty();
                    $.each(result, function (i, value) {

                        $("#lista").append("<tr>" + "<td>" + '<input type="checkbox" value="' + value.codigoCliente + '"/>' + "</td>"
                                + "<td>" + value.nomeCliente
                                + "</td>" + "<td>" + value.login
                                + "</td>" + "<td>" + value.senhaCliente
                                + "</td>" + "</tr>");
                    });
                });
            });
        });
    });
});
