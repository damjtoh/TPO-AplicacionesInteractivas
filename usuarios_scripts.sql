INSERT INTO APIDB.Usuarios
(usuarioId, nombre, email, password, nombreUsuario, dni, fechaNacimiento, domicilio)
VALUES 
(null, 'Damian', 'damian@pepe.com', '123', 'dami', 37356201, '1984-02-14', 'Calle siempre viva 123');

INSERT INTO APIDB.Roles (rolId, descripcion) VALUES (null, 'Agente comercial');
INSERT INTO APIDB.Roles (rolId, descripcion) VALUES (null, 'Vendedor');

INSERT INTO APIDB.UsuarioRol (usuarioRolId, rolId, usuarioId) VALUES (null, 4, 1);

SELECT Usuarios.*, UsuarioRol.rolId
FROM Usuarios 
INNER JOIN UsuarioRol ON Usuarios.usuarioId = UsuarioRol.usuarioId
INNER JOIN Roles ON UsuarioRol.rolId = Roles.rolId
WHERE nombreUsuario = 'dami';

SELECT LAST_INSERT_ID();

DELETE FROM UsuarioRol WHERE usuarioId = 5;DELETE FROM Usuarios WHERE usuarioId = 5;