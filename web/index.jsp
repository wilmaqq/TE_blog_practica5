<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1 align="center">Login</h1>
        <form action="Login" method="POST">
            <input type="hidden" name="id" value="${usu.id}" />
            <table border="0" cellpadding="2" cellspacing="0" align="center">
                <tr>
                    <td>Usuario</td>
                    <td><input type="text" name="usuarioin" value="" size="40"/></td>
                    <td><input type="hidden" name="usuariosql" value="${usu.usuarios}" /></td>
                </tr>
                <tr>
                    <td>Clave</td>
                    <td><input type="password" name="passwordin" value="" size="40"/></td>
                    <td><input type="hidden" name="passwordsql" value="${usu.password}" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="Ingresar" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
