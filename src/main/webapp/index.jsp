<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Banco Alex</title>
    <link rel="stylesheet" href="style.css">
    <%--    Boostrap Injection--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<h1>Formulario Banco Alex</h1>
<div class="container d-flex align-items-center">
    <form action="crearUsuario" method="post" class="row g-3">
        <div class="col-md-6">
            <h1 class="col-md-6">Crear cuenta</h1>
            <label for="inputNombreCliente" class="form-label">Nombre Cliente</label>
            <input type="text" class="form-control" id="inputNombreCliente" name="inputNombreCliente">
        </div>
        <div class="col-md-6">
            <label for="inputIdFiscal" class="form-label">Id Fiscal (DNI,NIE)</label>
            <input type="text" class="form-control" id="inputIdFiscal" name="inputIdFiscal">
        </div>
        <div class="col-md-6">
            <label for="inputEmail" class="form-label">Email Cliente</label>
            <input type="text" class="form-control" id="inputEmail" name="inputEmail" placeholder="example@example.com">
        </div>
        <div class="col-md-6">
            <label for="inputPais" class="form-label">Pais</label>
            <input type="text" class="form-control" id="inputPais" name="inputPais">
        </div>
        <div class="col-md-6">
            <label for="inputCuenta" class="form-label">Cuenta</label>
            <input type="text" class="form-control" id="inputCuenta" name="inputCuenta">
        </div>
        <div class="col-md-2">
            <label for="inputIngresoInicial" class="form-label">Ingreso inicial (€)</label>
            <input type="text" class="form-control" id="inputIngresoInicial" name="inputIngresoInicial">
        </div>
        <div class="col-md-6 justify-content-center ">
            <button type="submit" class="btn btn-primary">Crear Cuenta</button>
        </div>
    </form>
</div>
<br></br>
<div class="container d-flex align-items-center">
    <h1 class="col-md-3">Buscar Cliente</h1>
    <form action="mostrarClientes" method="post" class="row g-3">
        <div class="col-md-6">
            <label for="dniBuscar" class="form-label">DNI Cliente</label>
            <input type="text" class="form-control" id="dniBuscar" name="dniBuscar">
        </div>
        <div class="col-md-6 justify-content-center ">
            <button type="submit" class="btn btn-primary">Mostrar cliente y sus cuentas</button>
        </div>
    </form>
</div>
<div class="container d-flex align-items-center">
    <h1 class="col-md-3">Buscar Cuenta</h1>
    <form action="MostrarCuenta" method="post" class="row g-3">
        <div class="col-md-6">
            <label for="ibanBuscar" class="form-label">IBAN Cuenta</label>
            <input type="text" class="form-control" id="ibanBuscar" name="ibanBuscar">>
        </div>
        <div class="col-md-6 justify-content-center ">
            <button type="submit" class="btn btn-primary">Mostrar cuenta y su Cliente</button>
        </div>
    </form>
</div>
</body>
</html>