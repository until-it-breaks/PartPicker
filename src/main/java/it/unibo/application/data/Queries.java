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
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, cpu.*
        FROM componenti, cpu, produttori
        WHERE CodiceCpu = ? and cpu.CodiceCpu = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String GET_CPUS =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, cpu.*
        FROM componenti, cpu, produttori
        WHERE cpu.CodiceCpu = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_GPU =
    """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, gpu.*
        FROM componenti, gpu, produttori
        WHERE CodiceGpu = ?  and gpu.CodiceGpu = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;
    
    public static final String GET_GPUS =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, gpu.*
        FROM componenti, gpu, produttori
        WHERE gpu.CodiceGpu = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_RAM =
    """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, ram.*
        FROM componenti, ram, produttori
        WHERE CodiceRam = ? and ram.CodiceRam = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String GET_RAMS =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, ram.*
        FROM componenti, ram, produttori
        WHERE ram.CodiceRam = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_STORAGE =
    """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, storage.*
        FROM componenti, storage, produttori
        WHERE CodiceStorage = ? and storage.CodiceStorage = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String GET_STORAGE =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, storage.*
        FROM componenti, storage, produttori
        WHERE storage.CodiceStorage = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_MOTHERBOARD =
    """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, motherboard.*
        FROM componenti, motherboard, produttori
        WHERE CodiceMotherboard = ? and motherboard.CodiceMotherboard = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;
    
    public static final String GET_MOTHERBOARDS =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, motherboard.*
        FROM componenti, motherboard, produttori
        WHERE motherboard.CodiceMotherboard = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_PSU =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, psu.*
        FROM componenti, psu, produttori
        WHERE CodicePsu = ? and psu.CodicePsu = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String GET_PSU =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, psu.*
        FROM componenti, psu, produttori
        WHERE psu.CodicePsu = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_COOLER =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, cooler.*
        FROM cooler, componenti, produttori
        WHERE CodiceCooler = ? AND cooler.CodiceCooler = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String GET_COOLER =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, cooler.*
        FROM componenti, cooler, produttori
        WHERE cooler.CodiceCooler = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_CASE =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, `case`.*
        FROM `case`, componenti, produttori
        WHERE CodiceCase = ? AND `case`.CodiceCase = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String GET_CASES =
        """
        SELECT componenti.CodiceComponente, componenti.NomeComponente, componenti.AnnoLancio, componenti.PrezzoListino, NomeProduttore, `case`.*
        FROM componenti, `case`, produttori
        WHERE `case`.CodiceCase = componenti.CodiceComponente and produttori.CodiceProduttore = componenti.CodiceProduttore
        """;

    public static final String FIND_MANUFACTURER =
        """
        SELECT *
        FROM Produttori
        WHERE Produttori.CodiceProduttore = ?
        """;

    public static final String GET_BUILDS =
        """
        SELECT utenti.Username, pubblicazioni.DataModificaBuild, build.*, usiRam.CodiceRam, usiRam.Quantita, usiGpu.CodiceGpu, usiGpu.Quantita, usiStorage.CodiceStorage, usiStorage.Quantita
        FROM build, utenti, pubblicazioni, usiRam, usiGpu, usiStorage
        WHERE build.CodiceBuild = pubblicazioni.CodiceBuild AND pubblicazioni.Username = utenti.Username
            AND usiRam.CodiceBuild = build.CodiceBuild AND usiGpu.CodiceBuild = build.CodiceBuild AND usiStorage.CodiceBuild = build.CodiceBuild
        """;

    public static final String FIND_BUILD =
        """
        SELECT utenti.Username, pubblicazioni.DataModificaBuild, build.*
        FROM build, utenti, pubblicazioni
        WHERE build.CodiceBuild = pubblicazioni.CodiceBuild AND utenti.Username = pubblicazioni.Username AND build.CodiceBuild = ?
        """;

    public static final String FIND_USED_GPUS =
        """
        SELECT *
        FROM usiGpu
        WHERE usiGpu.CodiceBuild = ?
        """;
    
    public static final String FIND_USED_RAMS =
        """
        SELECT *
        FROM usiRam
        WHERE usiRam.CodiceBuild = ?
        """;

    public static final String FIND_USED_STORAGE =
        """
        SELECT *
        FROM usiStorage
        WHERE usiStorage.CodiceBuild = ?
        """;
    public static final String FIND_USER_DETAILS = 
        """
        SELECT 
            u.Username, 
            u.DataRegistrazione, 
            u.Email, 
            u.Moderatore, 
            AVG(r.RatingRecensione) AS RatingMedio, 
            COUNT(DISTINCT p.CodiceBuild) AS NumeroBuild
        FROM 
            Utenti u,
            Pubblicazioni p,
            Recensioni r
        WHERE
            p.Username = ?    
            AND u.Username = p.Username 
            AND p.CodiceBuild = r.CodiceBuild
        GROUP BY 
            u.Username
        """;

    public static final String INSERT_BAN =
        """
        INSERT INTO ban (UsernameAssegnatario, DataInizioBan, DataFineBan, DescrizioneBan, UsernameAssegnatore)
        VALUES (?, ?, ?, ?, ?)
        """;

    public static final String GET_REVIEWS =
        """
        SELECT *
        FROM recensioni
        WHERE CodiceBuild = ?
        """;

    public static final String INSERT_REVIEW =
        """
        INSERT INTO recensioni (CodiceBuild, Username, RatingRecensione, Commento, DataModificaRecensione)
        VALUES (?, ?, ?, ?, ?)
        """;

    public static final String UPDATE_REVIEW =
        """
        UPDATE recensioni
        SET RatingRecensione = ?, Commento = ?, DataModificaRecensione = ?
        WHERE CodiceBuild = ? AND Username = ?
        """;

    public static final String INSERT_UPLOAD =
        """
        INSERT INTO pubblicazioni (CodiceBuild, Username, DataModificaBuild)
        VALUES (?, ?, ?)
        """;

    public static final String INSERT_GPU_USAGE =
        """
        INSERT INTO usiGpu (CodiceBuild, CodiceGpu, Quantita)
        VALUES (?, ?, ?)
        """;
    
    public static final String INSERT_RAM_USAGE =
        """
        INSERT INTO usiRam (CodiceBuild, CodiceRam, Quantita)
        VALUES (?, ?, ?)
        """;

    public static final String INSERT_STORAGE_USAGE =
        """
        INSERT INTO usiStorage (CodiceBuild, CodiceStorage, Quantita)
        VALUES (?, ?, ?)
        """;
    public static final String GET_LATEST_BUILD_ID =
        """
            SELECT MAX(CodiceBuild) as Max
            FROM build;
        """;
    public static final String INSERT_BUILD = 
    """
        INSERT INTO build (CodiceBuild, CodiceCooler, CodiceCase, CodicePsu, CodiceCpu, CodiceMotherboard)
        VALUES (?, ?, ?, ?, ?, ?);
    """;
}
