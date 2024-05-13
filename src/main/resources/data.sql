
INSERT INTO Usuario(idUsuario, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

INSERT INTO MASCOTA (COLOR, DESCRIPCION, ESTADO, FOTO, LATITUD, LONGITUD, NOMBRE, RAZA, TIEMPOBUSCADO, TIPOMASCOTA, ZONA, IDUSUARIO) VALUES
    ('DORADO', '3 años, se perdió en el Parque San Martín. Es muy cariñoso', 'BUSCANDO_DUENIO', 'https://img.freepik.com/', '46.833333', '-6.833333', 'Toby', 'LABRADOR', 1, 'PERRO', 1, 1);

INSERT INTO MASCOTA (COLOR, DESCRIPCION, ESTADO, FOTO, LATITUD, LONGITUD, NOMBRE, RAZA, TIEMPOBUSCADO, TIPOMASCOTA, ZONA, IDUSUARIO) VALUES
    ('BLANCO', 'Tiene 2 años. Es muy tranquila y le encanta acurrucarse.', 'EN_ADOPCION', 'https://img.freepik.com/', '46.833333', '-6.833333', 'Luna', 'PERSA', 1, 'GATO', 1, 1);



/*
 INSERT INTO TipoMascota(idTipoMascota, nombreTipoMascota) VALUES (1, 'perro'), (2, 'gato');
>>>>>>> 534d3cb (controlador mascota new)
INSERT INTO Estado(idEstado, nombreEstado) VALUES (1, 'conSuDueño'), (2, 'perdidoPorDueño'), (3, 'encontradoPorX'), (4, 'enAdopcion');
INSERT INTO Raza (idRaza, nombreRaza) VALUES
                                          (1, 'Golden Retriever'),
                                          (2, 'Labrador Retriever'),
                                          (3, 'Pastor Aleman'),
                                          (4, 'Beagle'),
                                          (5, 'Bulldog Frances'),
                                          (6, 'Boxer'),
                                          (7, 'Rottweiler'),
                                          (8, 'Chihuahua'),
                                          (9, 'Cocker'),
                                          (10, 'Salchicha'),
                                          (11, 'Siamés'),
                                          (12, 'Persa'),
                                          (13, 'Ragdoll'),
                                          (14, 'Maine Coon'),
                                          (15, 'Bengalí'),
                                          (16, 'Abisinio'),
                                          (17, 'Esfinge'),
                                          (18, 'British'),
                                          (19, 'Savannah'),
                                          (20, 'Russian Blue');

INSERT INTO Color(idColor, nombre) VALUES (1, 'blanco'), (2, 'negro'), (3, 'marron'), (4, 'gris');

INSERT INTO Mascota (idMascota, descripcion, foto, latitud, longitud, nombre, tiempoBuscado, color_idColor, estado_idEstado, raza_idRaza, tipoMascota_idTipoMascota, usuario_idUsuario, zona) VALUES
                                                                                                                                                                                            (1, 'Es un perro que amo con todo mi ser', 'imagenes/toby.jpg', '-34.724171', '-58.560544', 'Toby', 30, 1, 3, 1, 1, 1, 'SUR'),
                                                                                                                                                                                            (2, 'Es una gata llamada luna', 'imagenes/luna.jpg', '-34.711944', '-58.563139', 'Luna', 15, 2, 3, 11, 2, 1, 'NORTE'),
                                                                                                                                                                                            (3, 'Es un Pastor. Se perdió hace 4 días', 'imagenes/max.jpg', '-34.728522', '-58.557833', 'Max', 4, 3, 2, 3, 1, 1, 'NORTE'),
                                                                                                                                                                                            (4, 'Es una perrita sociable. Se lleva bien con otros perros', 'imagenes/lola.jpg', '-34.709722', '-58.565734', 'Lola', 2, 4, 3, 4, 1, 1, 'OESTE'),
                                                                                                                                                                                            (5, 'Gatito bebé, Muy cariñoso', 'imagenes/gatito.jpg', '-34.726111', '-58.559533', 'Gatito', 0, 1, 1, 12, 2, 1, 'OESTE'),
                                                                                                                                                                                            (6, 'Es un perro juguetón y cariñoso, le gusta mucho la gente. Tiene 2 años', 'imagenes/ciro.jpg', '-34.724171', '-58.560544', 'Ciro', 30, 1, 3, 2, 1, 1, 'OESTE'),
                                                                                                                                                                                            (7, 'Es una gatita tímida pero muy dulce, ayudame por favor', 'imagenes/ramona.jpg', '-34.711944', '-58.563139', 'Ramona', 15, 2, 3, 13, 2, 1, 'NORTE'),
                                                                                                                                                                                            (8, 'Es un Pastor Alemán adulto, dócil y bien educado. Se perdió hace 5 días', 'imagenes/terry.jpg', '-34.728522', '-58.557833', 'Terry', 5, 3, 2, 5, 1, 1, 'SUR'),
                                                                                                                                                                                            (9, 'Es una perrita juguetona. Se lleva bien con los nenes', 'imagenes/mandala.jpg', '-34.709722', '-58.565734', 'Mandala', 2, 4, 3, 6, 1, 1, 'OESTE');


INSERT INTO veterinaria(idVeterinaria, direccion, nombre, open24, telefono) VALUES
                                                                                (1, 'Primera Junta 176', 'Veterinaria1', true, 1122334455),
                                                                                (2, 'Florencio Varela 123', 'Veterinaria2', true, 1234567890),
                                                                                (3, 'Calle Falsa 123', 'Veterinaria3', false, 12312312);

