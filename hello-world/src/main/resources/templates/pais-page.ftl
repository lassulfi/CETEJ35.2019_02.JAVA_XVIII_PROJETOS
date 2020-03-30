<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Hello, World!</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container-fuild">
       <div class="jumbotron">
           <h1>Gerencimento de Países</h1>
           <p>Nesta página você pode listar, criar, alterar ou excluir países...</p>
       </div>

       <form action="/hello/form" method="POST">
           <div class="form-group">
               <label for="nome">Nome:</label>
               <input type="text" class="form-control" placeholder="Informe o nome do país" id="nome">
           </div>
           <div class="form-group">
               <label for="sigla">Sigla:</label>
               <input type="text" class="form-control" placeholder="Informe a sigla do país" id="sigla">
           </div>
           <input type="submit" class="btn btn-primary" value="Cadastrar" />
       </form>

       <div class="mt-5">
           <table class="table table-striped">
               <thead>
                   <tr>
                       <th>País</th>
                       <th>Sigla</th>
                       <th>Ações</th>
                   </tr>
               </thead>
               <tbody>
                    <#list listaPaises as pais>
                        <tr>
                            <td>${pais.nome}</td>
                            <td>${pais.sigla}</td>
                            <td>
                                <button type="button" class="btn btn-warning">Editar</button>
                                <button type="button" class="btn btn-danger">Excluir</button>
                            </td>
                        </tr>
                    </#list>
               </tbody>
           </table>
       </div>
    </div>



     <!-- jQuery library -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

     <!-- Popper JS -->
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
 
     <!-- Latest compiled JavaScript -->
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>