
INSERT INTO Usuario(idUsuario, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);
INSERT INTO TipoMascota(idTipoMascota, nombreTipoMascota) VALUES (null, 'perro'), (null, 'gato');
INSERT INTO Estado(idEstado, nombreEstado) VALUES (null, 'conSuDueño'), (null, 'perdidoPorDueño'), (null, 'encontradoPorX'), (null, 'enAdopcion');
INSERT INTO Raza (idRaza, nombreRaza) VALUES
    (null, 'Golden Retriever'),
    (null, 'Labrador Retriever'),
    (null, 'Pastor Aleman'),
    (null, 'Beagle'),
    (null, 'Bulldog Frances'),
    (null, 'Boxer'),
    (null, 'Rottweiler'),
    (null, 'Chihuahua'),
    (null, 'Cocker'),
    (null, 'Salchicha');
ALTER TABLE Raza ADD CONSTRAINT fk_raza_tipoMascota FOREIGN KEY (tipoAnimal) REFERENCES TipoMascota(idTipoMascota);

INSERT INTO Raza (idRaza, nombreRaza) VALUES
    (null, 'Siamés'),
    (null, 'Persa'),
    (null, 'Ragdoll'),
    (null, 'Maine Coon'),
    (null, 'Bengalí'),
    (null, 'Abisinio'),
    (null, 'Esfinge'),
    (null, 'British'),
    (null, 'Savannah'),
    (null, 'Russian Blue');

INSERT INTO Color(idColor, nombreColor) VALUES (null, 'blanco'), (null, 'negro'), (null, 'marron'), (null, 'gris')

INSERT INTO Mascota(idMascota, idTipoMascota, idRaza, idEstado, foto, descripcion, nombre, tiempoBuscado, idColor) VALUES
    (null, 1, 1, 2, '', 'Es juguetón y cariñoso, le gusta mucho la gente', 'Toby', 0, 1),
    (null, 1, 2, 2, '', 'Es miedosa, no tiene contacto con otros perros pero no muerde', 'Luna', 10, 2),
    (null, 1, 3, 2, '', 'Es Pastor Aleman, nunca estuvo solo en la calle no conoce ', 'Max', 5, 3),
    (null, 1, 4, 2, '', 'Lola se lleva muy bien con otros perros pero nunca estuvo sola', 'Lola', 2, 4);

INSERT INTO Mascota(idMascota, idTipoMascota, idRaza, idEstado, foto, descripcion, nombre, tiempoBuscado, idColor, idDueño,)
VALUES
    (null, 2, 1, 2, '', 'Es muy cariñoso', 'Siam', 0, 1),
    (null, 2, 2, 2, '', 'Es desconfiaza, tiene un collar rosa', 'Bella', 10, 2),
    (null, 2, 3, 2, '', 'Es relajado pero no conoce la calle siempre esta en casa', 'Manchas', 5, 3),
    (null, 2, 4, 2, '', 'Es gordito y de verdad se parece a garfield', 'Garfield', 2, 4);
