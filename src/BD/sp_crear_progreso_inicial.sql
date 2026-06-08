DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_crear_progreso_inicial`(
    IN p_id_estudiante INT,
    IN p_id_contenido INT
)
BEGIN

    INSERT INTO progreso(
        id_estudiante,
        id_contenido,
        completada
    )
    VALUES(
        p_id_estudiante,
        p_id_contenido,
        2
    );

END$$
DELIMITER ;
