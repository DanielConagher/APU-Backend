DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_completar_contenido`(
    IN p_id_estudiante INT,
    IN p_id_contenido INT
)
BEGIN

    DECLARE v_siguiente INT;

    /*
     * Marcar contenido actual como completado
     */
    UPDATE progreso
    SET completada = 1
    WHERE id_estudiante = p_id_estudiante
      AND id_contenido = p_id_contenido;

    /*
     * Obtener siguiente contenido
     */
    SELECT c2.id_contenido
    INTO v_siguiente
    FROM contenido c1
    JOIN contenido c2
      ON c2.id_contenido > c1.id_contenido
    WHERE c1.id_contenido = p_id_contenido
    ORDER BY c2.id_contenido
    LIMIT 1;

    /*
     * Si existe siguiente contenido
     */
    IF v_siguiente IS NOT NULL THEN

        IF NOT EXISTS(
            SELECT 1
            FROM progreso
            WHERE id_estudiante = p_id_estudiante
            AND id_contenido = v_siguiente
        ) THEN

            INSERT INTO progreso(
                id_estudiante,
                id_contenido,
                completada
            )
            VALUES(
                p_id_estudiante,
                v_siguiente,
                2
            );

        END IF;

    END IF;

END$$
DELIMITER ;
