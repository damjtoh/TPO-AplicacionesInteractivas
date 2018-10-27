INSERT INTO APIDB.Usuarios
(usuarioId, nombre, email, password, nombreUsuario, dni, fechaNacimiento, domicilio)
VALUES 
(null, 'Damian', 'damian@pepe.com', '123', 'dami', 37356201, '1984-02-14', 'Calle siempre viva 123');

INSERT INTO APIDB.Roles (rolId, descripcion) VALUES (null, 'Agente comercial');

INSERT INTO APIDB.UsuarioRol (usuarioRolId, rolId, usuarioId) VALUES (null, 1, 1);