package it.unibo.application.data;

public final class Queries {
    
    public static final String FIND_USER =
        """
        SELECT *
        FROM utenti
        WHERE Username = ?
        """;
    
    public static final String CHECK_BAN =
        """
        SELECT *
        FROM ban
        WHERE UsernameAssegnatario = ? AND (DataFineBan IS NULL OR DataFineBan > NOW());
        """;
    
    public static final String REGISTER_USER =
        """
        INSERT INTO utenti (Username, Password, DataRegistrazione, Email, Moderatore)
        VALUES (?, ?, ?, ?, ?)
        """;

    public static final String FIND_LATEST_COMPONENTS =
        """
        SELECT *
        FROM Componenti
        WHERE TipoComponente = ?
        ORDER BY CodiceComponente DESC
        LIMIT 10;
        """;

    public static final String FIND_CPU =
        """
        SELECT *
        FROM cpu
        WHERE CodiceCpu = ?
        """;
    public static final String FIND_GPU =
        """
        SELECT *
        FROM gpu
        WHERE CodiceGpu = ?
        """;
    public static final String FIND_RAM =
        """
        SELECT *
        FROM ram
        WHERE CodiceRam = ?
        """;
    public static final String FIND_STORAGE =
        """
        SELECT *
        FROM storage
        WHERE CodiceStorage = ?
        """;
    public static final String FIND_MOTHERBOARD =
        """
        SELECT *
        FROM motherboard
        WHERE CodiceMotherboard = ?
        """;
    public static final String FIND_PSU =
        """
        SELECT *
        FROM psu
        WHERE CodicePsu = ?
        """;
    public static final String FIND_COOLER =
        """
        SELECT *
        FROM cooler
        WHERE CodiceCooler = ?
        """;
    public static final String FIND_CASE =
        """
        SELECT *
        FROM case
        WHERE CodiceCase = ?
        """;
}
