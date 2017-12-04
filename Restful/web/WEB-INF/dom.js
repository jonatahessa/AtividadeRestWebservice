$(document).ready(function() {
  $("button").click(function(){

      $.getJSON("http://localhost:8080/Restful/webresources/ADO2/cliente/listarClientes", function(result){

          $.each(result, function(i, value){

              $("ul").append("<li>" + value.nomeCliente + "</li>");
          });
      });
  });
});
