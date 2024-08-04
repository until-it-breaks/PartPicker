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
        SELECT componenti.*, cpu.*
        FROM cpu, componenti
        WHERE CodiceCpu = ? AND cpu.CodiceCpu = componenti.CodiceComponente
        """;

    public static final String FIND_GPU =
        """
        SELECT componenti.*, gpu.*
        FROM gpu, componenti
        WHERE CodiceGpu = ? AND gpu.CodiceGpu = componenti.CodiceComponente
        """;

    public static final String FIND_RAM =
        """
        SELECT componenti.*, ram.*
        FROM ram, componenti
        WHERE CodiceRam = ? AND ram.CodiceRam = componenti.CodiceComponente
        """;

    public static final String FIND_STORAGE =
        """
        SELECT componenti.*, storage.*
        FROM storage, componenti
        WHERE CodiceStorage = ? AND storage.CodiceStorage = componenti.CodiceComponente
        """;

    public static final String FIND_MOTHERBOARD =
        """
        SELECT componenti.*, motherboard.*
        FROM motherboard, componenti
        WHERE CodiceMotherboard = ? AND motherboard.CodiceMotherboard = componenti.CodiceComponente
        """;

    public static final String FIND_PSU =
        """
        SELECT componenti.*, psu.*
        FROM psu, componenti
        WHERE CodicePsu = ? AND psu.CodicePsu = componenti.CodiceComponente
        """;

    public static final String FIND_COOLER =
        """
        SELECT componenti.*, cooler.*
        FROM cooler, componenti
        WHERE CodiceCooler = ? AND cooler.CodiceCooler = componenti.CodiceComponente
        """;

    public static final String FIND_CASE =
        """
        SELECT componenti.*, `case`.*
        FROM `case`, componenti
        WHERE CodiceCase = ? AND `case`.CodiceCase = componenti.CodiceComponente
        """;
}
