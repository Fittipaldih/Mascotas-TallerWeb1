
INSERT INTO Usuario(idUsuario, email, password, rol, activo) VALUES(1, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

INSERT INTO MASCOTA (COLOR, DESCRIPCION, ESTADO, FOTO, LATITUD, LONGITUD, NOMBRE, RAZA, TIEMPOBUSCADO, TIPOMASCOTA, ZONA, IDUSUARIO) VALUES
    ('DORADO', '3 años, se perdió en el Parque San Martín. Es muy cariñoso', 'BUSCADO_POR_DUENIO', 'https://img.freepik.com/', '46.833333', '-6.833333', 'Toby', 'LABRADOR', 1, 'PERRO', 1, 1);

INSERT INTO MASCOTA (COLOR, DESCRIPCION, ESTADO, FOTO, LATITUD, LONGITUD, NOMBRE, RAZA, TIEMPOBUSCADO, TIPOMASCOTA, ZONA, IDUSUARIO) VALUES
    ('BLANCO', 'Tiene 2 años. Es muy tranquila y le encanta acurrucarse.', 'EN_ADOPCION', 'https://img.freepik.com/', '46.833333', '-6.833333', 'Luna', 'PERSA', 1, 'GATO', 1, 1);
