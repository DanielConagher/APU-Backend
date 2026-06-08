DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_primera_burbuja`(
    IN p_id_tipo_desastre INT
)
BEGIN
    SELECT c.id_contenido
    FROM contenido c
    INNER JOIN nivel n
        ON n.id_nivel = c.id_nivel
    INNER JOIN mapa m
        ON m.id_mapa = n.id_mapa
    WHERE m.id_tipo_desastre = p_id_tipo_desastre
    ORDER BY c.id_contenido
    LIMIT 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_progreso_aprendizaje`(
    IN p_id_estudiante INT
)
BEGIN

    SELECT
        td.id_tipo_desastre,
        td.nombre,

       ROUND(
    (
        COUNT(
            DISTINCT CASE
                WHEN p.completada = 1
                THEN c.id_contenido
            END
        )
        /
        NULLIF(COUNT(DISTINCT c.id_contenido), 0)
    ) * 100,
    2
) AS porcentaje_avance

    FROM tipo_desastre td

    INNER JOIN mapa m
        ON td.id_tipo_desastre =
           m.id_tipo_desastre

    INNER JOIN nivel n
        ON m.id_mapa =
           n.id_mapa

    INNER JOIN contenido c
        ON n.id_nivel =
           c.id_nivel

    LEFT JOIN progreso p
        ON c.id_contenido =
           p.id_contenido
        AND p.id_estudiante =
           p_id_estudiante

    GROUP BY
        td.id_tipo_desastre,
        td.nombre;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_resolver_cuestionario`(

    IN p_id_estudiante INT,
    IN p_id_cuestionario INT,
    IN p_nota INT,
    IN p_id_contenido INT

)
BEGIN

    DECLARE v_nota_actual INT;

    SELECT nota
    INTO v_nota_actual
    FROM resultado_cuestionario
    WHERE id_estudiante = p_id_estudiante
    AND id_cuestionario = p_id_cuestionario
    LIMIT 1;

    IF v_nota_actual IS NULL THEN

        INSERT INTO resultado_cuestionario(

            id_estudiante,
            id_cuestionario,
            nota,
            fecha_resolucion

        )
        VALUES(

            p_id_estudiante,
            p_id_cuestionario,
            p_nota,
            NOW()

        );

    ELSEIF p_nota > v_nota_actual THEN

        UPDATE resultado_cuestionario
        SET nota = p_nota,
            fecha_resolucion = NOW()
        WHERE id_estudiante = p_id_estudiante
        AND id_cuestionario = p_id_cuestionario;

    END IF;

    CALL sp_completar_contenido(
        p_id_estudiante,
        p_id_contenido
    );

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tiene_progreso`(
    IN p_id_estudiante INT,
    IN p_id_tipo_desastre INT
)
BEGIN

    SELECT COUNT(*) cantidad
    FROM progreso p
    INNER JOIN contenido c
        ON c.id_contenido = p.id_contenido
    INNER JOIN nivel n
        ON n.id_nivel = c.id_nivel
    INNER JOIN mapa m
        ON m.id_mapa = n.id_mapa
    WHERE p.id_estudiante = p_id_estudiante
    AND m.id_tipo_desastre = p_id_tipo_desastre;

END$$
DELIMITER ;
