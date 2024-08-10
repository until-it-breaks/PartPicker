-- Inserimento Produttori
INSERT INTO Produttori (CodiceProduttore, NomeProduttore, PaeseProduttore) VALUES
(1, 'CoolerMaster', 'Taiwan'),        -- Known for PC cases, cooling solutions, and power supplies
(2, 'Noctua', 'Austria'),             -- Renowned for high-performance CPU coolers and fans
(3, 'Corsair', 'USA'),                -- Specializes in memory, storage solutions, PC cases, and cooling
(4, 'DeepCool', 'China'),             -- Produces PC cases, cooling solutions, and power supplies
(5, 'Arctic', 'Switzerland'),         -- Known for cooling solutions and thermal pastes
(6, 'Lian Li', 'Taiwan'),             -- Famous for high-quality aluminum PC cases
(7, 'Fractal Design', 'Sweden'),      -- Produces PC cases, cooling solutions, and accessories
(8, 'Thermaltake', 'Taiwan'),         -- Known for PC cases, cooling solutions, and power supplies
(9, 'Be Quiet!', 'Germany'),          -- Specializes in silent PC cooling solutions and power supplies
(10, 'Super Flower', 'Taiwan'),       -- Known for high-quality power supplies
(11, 'NZXT', 'USA'),                  -- Produces PC cases, cooling solutions, and accessories
(12, 'Phanteks', 'Taiwan'),           -- Known for PC cases, cooling solutions, and accessories
(13, 'Gigabyte', 'Taiwan'),           -- Produces motherboards, graphics cards, and other PC components
(14, 'MSI', 'Taiwan'),                -- Known for motherboards, graphics cards, and gaming laptops
(15, 'ASUS', 'Taiwan'),               -- Specializes in motherboards, graphics cards, and gaming products
(16, 'ASRock', 'Taiwan'),             -- Produces motherboards and other PC components
(17, 'Crucial', 'USA'),               -- Known for memory and storage solutions
(18, 'Kingston', 'USA'),              -- Specializes in memory and storage products
(19, 'G.Skill', 'Taiwan'),            -- Known for high-performance memory modules
(20, 'EVGA', 'USA'),                  -- Produces graphics cards, motherboards, and power supplies
(21, 'Seasonic', 'Taiwan'),           -- Known for high-quality power supplies
(22, 'Intel', 'USA'),                 -- Specializes in CPUs and other semiconductor products
(23, 'AMD', 'USA'),                   -- Known for CPUs and GPUs
(24, 'Western Digital', 'USA'),       -- Known for storage solutions including HDDs and SSDs
(25, 'Seagate', 'USA'),               -- Specializes in hard drives and storage solutions
(26, 'Samsung', 'South Korea'),       -- Produces memory, storage solutions, and displays
(27, 'ADATA', 'Taiwan'),              -- Specializes in memory and storage solutions
(28, 'Toshiba', 'Japan'),             -- Known for storage solutions, especially HDDs and SSDs
(29, 'Inno3D', 'Hong Kong'),          -- Produces graphics cards
(30, 'ZOTAC', 'Hong Kong'),           -- Known for graphics cards and mini-PCs
(31, 'PowerColor', 'Hong Kong'),      -- Specializes in graphics cards
(32, 'Palit', 'Hong Kong'),           -- Known for graphics cards
(33, 'NVIDIA', 'USA'),                -- Leading manufacturer of GPUs and graphics technologies
(34, 'Sapphire', 'Hong Kong');        -- Specializes in AMD graphics cards

-- Inserimento Socket
INSERT INTO `socket` (NomeSocket) VALUES
('LGA 1200'), -- Introduced in 2020, used by Intel 10th and 11th Gen Core processors
('LGA 1700'), -- Introduced in 2021, used by Intel 12th Gen Core processors and newer
('AM4'),      -- Introduced in 2017, still widely used with AMD Ryzen processors from 1000 to 5000 series
('AM5');      -- Introduced in 2022, used by AMD Ryzen 7000 series processors

-- Inserimento GenerazioniRam
INSERT INTO GenerazioniRam (NomeGenerazioneRam) VALUES
('DDR4'),
('DDR5');

-- Inserimento CPU
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(1, 'Core i3-10100', 'Cpu', '2020', 122.00, 22),
(2, 'Core i5-10400', 'Cpu', '2020', 157.00, 22),
(3, 'Core i7-10700K', 'Cpu', '2020', 374.00, 22),
(4, 'Core i7-12700K', 'Cpu', '2021', 409.00, 22),
(5, 'Core i9-12900K', 'Cpu', '2021', 589.00, 22),
(6, 'Core i5-13400', 'Cpu', '2022', 229.00, 22),
(7, 'Core i7-13700K', 'Cpu', '2022', 439.00, 22),
(8, 'Ryzen 3 1200', 'Cpu', '2017', 109.00, 23),
(9, 'Ryzen 5 1600', 'Cpu', '2017', 219.00, 23),
(10, 'Ryzen 5 3600', 'Cpu', '2019', 199.00, 23),
(11, 'Ryzen 7 3700X', 'Cpu', '2019', 329.00, 23),
(12, 'Ryzen 5 5600X', 'Cpu', '2020', 299.00, 23),
(13, 'Ryzen 7 5800X', 'Cpu', '2020', 449.00, 23),
(14, 'Ryzen 5 7600X', 'Cpu', '2022', 299.00, 23),
(15, 'Ryzen 9 7950X3D', 'Cpu', '2023', 700.00, 23);

INSERT INTO Cpu (CodiceCpu, FamigliaCpu, NumeroCore, FrequenzaCpu, Tdp, Smt, NomeSocket) VALUES
(1, 'Comet Lake', 4, 4.3, 65, TRUE, 'LGA 1200'),
(2, 'Comet Lake', 6, 4.3, 65, TRUE, 'LGA 1200'),
(3, 'Comet Lake', 8, 5.1, 125, TRUE, 'LGA 1200'),
(4, 'Alder Lake', 12, 5.0, 125, TRUE, 'LGA 1700'),
(5, 'Alder Lake', 16, 5.2, 125, TRUE, 'LGA 1700'),
(6, 'Raptor Lake', 10, 4.6, 125, TRUE, 'LGA 1700'),
(7, 'Raptor Lake', 16, 5.2, 125, TRUE, 'LGA 1700'),
(8, 'Summit Ridge', 4, 3.1, 65, TRUE, 'AM4'),
(9, 'Summit Ridge', 6, 3.2, 65, TRUE, 'AM4'),
(10, 'Matisse', 6, 3.6, 65, TRUE, 'AM4'),
(11, 'Matisse', 8, 3.6, 65, TRUE, 'AM4'),
(12, 'Vermeer', 6, 3.7, 65, TRUE, 'AM4'),
(13, 'Vermeer', 8, 3.8, 105, TRUE, 'AM4'),
(14, 'Raphael', 6, 4.7, 105, TRUE, 'AM5'),
(15, 'Raphael', 16, 5.7, 120, TRUE, 'AM5');

-- Inserimento CompatibilitaRamCpu
INSERT INTO CompatibilitaRamCpu (NomeGenerazioneRam, CodiceCpu) VALUES
('DDR4', 1),
('DDR4', 2),
('DDR4', 3),
('DDR4', 4),
('DDR5', 4),
('DDR4', 5),
('DDR5', 5),
('DDR4', 6),
('DDR5', 6),
('DDR4', 7),
('DDR5', 7),
('DDR4', 8),
('DDR4', 9),
('DDR4', 10),
('DDR4', 11),
('DDR4', 12),
('DDR4', 13),
('DDR5', 14),
('DDR5', 15);

-- Inserimento Gpu
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(16, 'GeForce RTX 3060', 'Gpu', '2021', 329.00, 33),
(17, 'GeForce RTX 3070', 'Gpu', '2020', 499.00, 33),
(18, 'GeForce RTX 3080', 'Gpu', '2020', 699.00, 33),
(19, 'GeForce RTX 4060', 'Gpu', '2023', 299.00, 33),
(20, 'GeForce RTX 4070', 'Gpu', '2023', 599.00, 33),
(21, 'GeForce RTX 4080', 'Gpu', '2023', 1199.00, 33),
(22, 'Radeon RX 6700 XT', 'Gpu', '2021', 479.00, 23),
(23, 'Radeon RX 6800 XT', 'Gpu', '2020', 649.00, 23),
(24, 'Arc A750', 'Gpu', '2022', 289.00, 22),
(25, 'Arc A770', 'Gpu', '2022', 329.00, 22),
(26, 'GeForce RTX 2070', 'Gpu', '2018', 499.00, 33),
(27, 'GeForce RTX 3060 ZOTAC Twin Edge', 'Gpu', '2021', 339.00, 30),
(28, 'GeForce RTX 3070 PowerColor Red Devil', 'Gpu', '2020', 509.00, 31),
(29, 'GeForce RTX 3080 Palit GamingPro', 'Gpu', '2020', 719.00, 32),
(30, 'Radeon RX 6700 XT ZOTAC Gaming', 'Gpu', '2021', 489.00, 30),
(31, 'Radeon RX 6800 XT PowerColor Hellhound', 'Gpu', '2020', 659.00, 31);

INSERT INTO Gpu (CodiceGpu, FamigliaGpu, TipoMemoriaGpu, QuantitaMemoriaGpu, FrequenzaGpu, Tgp) VALUES
(16, 'Ampere', 'GDDR6', 12, 1777, 170),
(17, 'Ampere', 'GDDR6', 8, 1730, 220),
(18, 'Ampere', 'GDDR6X', 10, 1710, 320),
(19, 'Ada Lovelace', 'GDDR6', 8, 2535, 115),
(20, 'Ada Lovelace', 'GDDR6X', 12, 2475, 200),
(21, 'Ada Lovelace', 'GDDR6X', 16, 2610, 320),
(22, 'Navi 22', 'GDDR6', 12, 2581, 230),
(23, 'Navi 21', 'GDDR6', 16, 2250, 300),
(24, 'Alchemist', 'GDDR6', 8, 2500, 150),
(25, 'Alchemist', 'GDDR6', 16, 2550, 225),
(26, 'Turing', 'GDDR6', 8, 1770, 200),
(27, 'Ampere', 'GDDR6', 12, 1777, 170),
(28, 'Ampere', 'GDDR6', 8, 1730, 220),
(29, 'Ampere', 'GDDR6X', 10, 1710, 320),
(30, 'Navi 22', 'GDDR6', 12, 2581, 230),
(31, 'Navi 21', 'GDDR6', 16, 2250, 300);

-- Inserimento Case
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(32, 'Cooler Master MasterBox Q300L', 'Case', '2018', 49.99, 1),
(33, 'Fractal Design Meshify C', 'Case', '2017', 89.99, 7),
(34, 'Thermaltake Core P3', 'Case', '2016', 149.99, 8),
(35, 'NZXT H510', 'Case', '2019', 69.99, 11);

INSERT INTO `Case` (CodiceCase, FattoreFormaCase) VALUES
(32, 'MicroATX'),
(33, 'ATX'),
(34, 'ATX'),
(35, 'ATX');

-- Inserimento Psu
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(36, 'EVGA SuperNOVA 650 G3', 'Psu', '2017', 89.99, 20),
(37, 'Seasonic Prime Ultra Titanium 750W', 'Psu', '2018', 179.99, 21),
(38, 'Corsair RM850x', 'Psu', '2021', 134.99, 3),
(39, 'Fractal Design Ion+ 760P', 'Psu', '2019', 139.99, 7),
(40, 'Arctic P12 Silent', 'Psu', '2020', 49.99, 5);

INSERT INTO Psu (CodicePsu, FattoreFormaPsu, Efficienza, Wattaggio, Modularita) VALUES
(36, 'ATX', 'Gold', 650, 'Full'),
(37, 'ATX', 'Titanium', 750, 'Full'),
(38, 'ATX', 'Gold', 850, 'Full'),
(39, 'ATX', 'Platinum', 760, 'Full'),
(40, 'ATX', 'Bronze', 500, 'No');

-- Inserimento Cooler
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(41, 'CoolerMaster Hyper 212', 'Cooler', '2015', 29.99, 1),
(42, 'Noctua NH-D15', 'Cooler', '2017', 89.99, 2),
(43, 'Corsair H100i RGB Platinum', 'Cooler', '2019', 139.99, 3),
(44, 'DeepCool Assassin III', 'Cooler', '2020', 79.99, 4),
(45, 'Arctic Freezer 34 eSports DUO', 'Cooler', '2018', 44.99, 5);

INSERT INTO Cooler (CodiceCooler, RpmCooler, LivelloRumore, TipoCooler) VALUES
(41, 2000, 26.0, 'Aria'),
(42, 1500, 24.6, 'Aria'),
(43, 2400, 37.0, 'AIO'),
(44, 1400, 29.5, 'Aria'),
(45, 2100, 28.1, 'Aria');

-- Inserimento Storage
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(46, 'Crucial MX500', 'Storage', '2018', 79.99, 17),
(47, 'Kingston A2000', 'Storage', '2019', 99.99, 18),
(48, 'Samsung 970 EVO Plus', 'Storage', '2019', 149.99, 26),
(49, 'WD Blue 1TB HDD', 'Storage', '2017', 49.99, 24),
(50, 'Seagate Barracuda 2TB', 'Storage', '2016', 69.99, 25);

INSERT INTO Storage (CodiceStorage, CapienzaStorage, RpmStorage, QuantitaCache, TipoStorage) VALUES
(46, 500, NULL, 256, 'Ssd'),
(47, 1000, NULL, 512, 'Ssd'),
(48, 1000, NULL, 1024, 'Ssd'),
(49, 1000, 7200, 64, 'Hdd'),
(50, 2000, 7200, 256, 'Hdd');

-- Inserimento Ram
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(51, 'Kingston HyperX Fury DDR4 8GB', 'RAM', '2020', 39.99, 18),
(52, 'Corsair Vengeance LPX DDR4 16GB', 'RAM', '2021', 79.99, 3),
(53, 'G.Skill Ripjaws V DDR4 8GB', 'RAM', '2019', 37.99, 19),
(54, 'Crucial Ballistix DDR4 16GB', 'RAM', '2020', 82.99, 17),
(55, 'Corsair Dominator Platinum DDR4 32GB', 'RAM', '2021', 199.99, 3),
(56, 'ADATA XPG Lancer DDR5 16GB', 'RAM', '2022', 84.99, 27);

INSERT INTO Ram (CodiceRam, FrequenzaRam, CapienzaRam, Latenza, Ecc, NomeGenerazioneRam) VALUES
(51, 3200, 8, 'CL16', 0, 'DDR4'),
(52, 3600, 16, 'CL18', 0, 'DDR4'),
(53, 3000, 8, 'CL15', 0, 'DDR4'),
(54, 3200, 16, 'CL16', 0, 'DDR4'),
(55, 3600, 32, 'CL18', 0, 'DDR4'),
(56, 5200, 16, 'CL40', 0, 'DDR5');

-- Inserimento Motherboard
INSERT INTO Componenti (CodiceComponente, NomeComponente, TipoComponente, AnnoLancio, PrezzoListino, CodiceProduttore) VALUES
(57, 'ASUS ROG Strix Z490-E Gaming', 'Motherboard', '2020', 299.99, 15),
(58, 'MSI MPG Z490 Gaming Edge WiFi', 'Motherboard', '2020', 199.99, 14),
(59, 'Gigabyte Z590 AORUS Elite AX', 'Motherboard', '2021', 229.99, 13),
(60, 'ASRock Z590 Steel Legend', 'Motherboard', '2021', 189.99, 16),
(61, 'ASUS ROG Strix B550-F Gaming', 'Motherboard', '2020', 179.99, 15),
(62, 'MSI MAG B550 TOMAHAWK', 'Motherboard', '2020', 159.99, 14),
(63, 'Gigabyte B660M DS3H DDR4', 'Motherboard', '2022', 129.99, 13),
(64, 'ASRock B660M Pro4', 'Motherboard', '2022', 139.99, 16),
(65, 'ASUS ROG Strix B650-A Gaming WiFi', 'Motherboard', '2023', 199.99, 15);

INSERT INTO Motherboard (CodiceMotherboard, FattoreFormaMotherboard, NomeChipset, SlotRam, SlotGpu, Wifi, NomeSocket, NomeGenerazioneRam) VALUES
(57, 'ATX', 'Z490', 4, 2, 1, 'LGA 1200', 'DDR4'),
(58, 'ATX', 'Z490', 4, 2, 1, 'LGA 1200', 'DDR4'),
(59, 'ATX', 'Z590', 4, 2, 1, 'LGA 1200', 'DDR4'),
(60, 'ATX', 'Z590', 4, 2, 0, 'LGA 1200', 'DDR4'),
(61, 'ATX', 'B550', 4, 2, 0, 'AM4', 'DDR4'),
(62, 'ATX', 'B550', 4, 2, 0, 'AM4', 'DDR4'),
(63, 'MicroATX', 'B660', 4, 1, 0, 'LGA 1700', 'DDR4'),
(64, 'MicroATX', 'B660', 4, 1, 0, 'LGA 1700', 'DDR4'),
(65, 'ATX', 'B650', 4, 1, 1, 'AM5', 'DDR5');

INSERT INTO Utenti (Username, Password, DataRegistrazione, Email, Moderatore) VALUES
('mario_rossi', 'password123', '2024-07-27', 'mario.rossi@example.com', 0),
('giulia_bianchi', 'giulia2024', '2024-07-27', 'giulia.bianchi@example.com', 0),
('luca_verdi', 'lucaVerdi2024', '2024-07-27', 'luca.verdi@example.com', 1),
('anna_neri', 'annaNeri2024', '2024-07-27', 'anna.neri@example.com', 0),
('alessandro_gialli', 'alessandro2024', '2024-07-27', 'alessandro.gialli@example.com', 1);

INSERT INTO Build (CodiceBuild, CodiceCooler, CodiceCase, CodicePsu, CodiceCpu, CodiceMotherboard) VALUES
(1, 41, 32, 40, 1, 58), -- cheap overall
(2, 43, 34, 37, 15, 65), -- expensive amd + AMD GPU
(3, 43, 34, 37, 5, 63); -- expensive intel + NVIDIA GPU

INSERT INTO usiGpu (CodiceBuild, CodiceGpu, Quantita) VALUES
(1, 24, 1),
(2, 23, 1),
(3, 21, 1);

INSERT INTO usiRam (CodiceBuild, CodiceRam, Quantita) VALUES
(1, 52, 2),
(2, 54, 4),
(3, 56, 4);

INSERT INTO usiStorage (CodiceBuild, CodiceStorage, Quantita) VALUES
(1, 47, 1),
(2, 48, 2),
(3, 48, 2);

INSERT INTO Pubblicazioni (CodiceBuild, DataModificaBuild, Username) VALUES
(1, '2024-07-27', 'anna_neri'),
(2, '2024-07-27', 'alessandro_gialli'),
(3, '2024-07-27', 'alessandro_gialli');

INSERT INTO Ban (UsernameAssegnatario, DataInizioBan, DataFineBan, DescrizioneBan, UsernameAssegnatore) VALUES
('mario_rossi', '2024-07-01', '2024-07-15', 'Ban temporaneo per comportamento scorretto', 'luca_verdi'),
('giulia_bianchi', '2024-07-10', NULL, 'Ban permanente per violazione delle regole', 'alessandro_gialli');

INSERT INTO Recensioni (CodiceBuild, Username, RatingRecensione, Commento, DataModificaRecensione) VALUES
(1, 'mario_rossi', 5, "One could do better than this", '2024-07-27'),
(1, 'giulia_bianchi', 1, "Awful, would not recommend at all", '2024-07-27'),
(1, 'luca_verdi', 6, "There are definitely some better picks but I guess it's good enough", '2024-07-27'),
(2, 'mario_rossi', 6, "Eh, could be better I guess", '2024-07-27'),
(2, 'giulia_bianchi', 1, "You should abandon pc building, my friend", '2024-07-27'),
(2, 'luca_verdi', 8, "I love this build, 10/10 would recommend", '2024-07-27'),
(3, 'anna_neri', 7, "Solid build", '2024-07-27'),
(3, 'giulia_bianchi', 7, "This one is definitely better than your previous build", '2024-07-27'),
(3, 'luca_verdi', 8, "You went with Intel this time? Interesting!", '2024-07-27');

INSERT INTO PrezziComponenti (CodiceComponente, NomeRivenditore, PrezzoComponente, DataRilevamentoPrezzo) VALUES
-- DAY 1 AMAZON
(1, 'Amazon', 109.80, '2024-07-27'),
(2, 'Amazon', 141.30, '2024-07-27'),
(3, 'Amazon', 336.60, '2024-07-27'),
(4, 'Amazon', 368.10, '2024-07-27'),
(5, 'Amazon', 530.10, '2024-07-27'),
(6, 'Amazon', 206.10, '2024-07-27'),
(7, 'Amazon', 395.10, '2024-07-27'),
(8, 'Amazon', 98.10, '2024-07-27'),
(9, 'Amazon', 197.10, '2024-07-27'),
(10, 'Amazon', 179.10, '2024-07-27'),
(11, 'Amazon', 296.10, '2024-07-27'),
(12, 'Amazon', 269.10, '2024-07-27'),
(13, 'Amazon', 404.10, '2024-07-27'),
(14, 'Amazon', 269.10, '2024-07-27'),
(15, 'Amazon', 630.00, '2024-07-27'),
(16, 'Amazon', 296.10, '2024-07-27'),
(17, 'Amazon', 449.10, '2024-07-27'),
(18, 'Amazon', 629.10, '2024-07-27'),
(19, 'Amazon', 269.10, '2024-07-27'),
(20, 'Amazon', 539.10, '2024-07-27'),
(21, 'Amazon', 1079.10, '2024-07-27'),
(22, 'Amazon', 431.10, '2024-07-27'),
(23, 'Amazon', 584.10, '2024-07-27'),
(24, 'Amazon', 260.10, '2024-07-27'),
(25, 'Amazon', 296.10, '2024-07-27'),
(26, 'Amazon', 449.10, '2024-07-27'),
(27, 'Amazon', 305.10, '2024-07-27'),
(28, 'Amazon', 458.10, '2024-07-27'),
(29, 'Amazon', 647.10, '2024-07-27'),
(30, 'Amazon', 440.10, '2024-07-27'),
(31, 'Amazon', 593.10, '2024-07-27'),
(32, 'Amazon', 44.99, '2024-07-27'),
(33, 'Amazon', 80.99, '2024-07-27'),
(34, 'Amazon', 134.99, '2024-07-27'),
(35, 'Amazon', 62.99, '2024-07-27'),
(36, 'Amazon', 80.99, '2024-07-27'),
(37, 'Amazon', 161.99, '2024-07-27'),
(38, 'Amazon', 121.49, '2024-07-27'),
(39, 'Amazon', 125.99, '2024-07-27'),
(40, 'Amazon', 44.99, '2024-07-27'),
(41, 'Amazon', 26.99, '2024-07-27'),
(42, 'Amazon', 80.99, '2024-07-27'),
(43, 'Amazon', 125.99, '2024-07-27'),
(44, 'Amazon', 71.99, '2024-07-27'),
(45, 'Amazon', 40.49, '2024-07-27'),
(46, 'Amazon', 71.99, '2024-07-27'),
(47, 'Amazon', 89.99, '2024-07-27'),
(48, 'Amazon', 134.99, '2024-07-27'),
(49, 'Amazon', 44.99, '2024-07-27'),
(50, 'Amazon', 62.99, '2024-07-27'),
(51, 'Amazon', 35.99, '2024-07-27'),
(52, 'Amazon', 71.99, '2024-07-27'),
(53, 'Amazon', 34.19, '2024-07-27'),
(54, 'Amazon', 74.69, '2024-07-27'),
(55, 'Amazon', 179.99, '2024-07-27'),
(56, 'Amazon', 76.49, '2024-07-27'),
(57, 'Amazon', 269.99, '2024-07-27'),
(58, 'Amazon', 179.99, '2024-07-27'),
(59, 'Amazon', 206.99, '2024-07-27'),
(60, 'Amazon', 170.99, '2024-07-27'),
(61, 'Amazon', 161.99, '2024-07-27'),
(62, 'Amazon', 143.99, '2024-07-27'),
(63, 'Amazon', 116.99, '2024-07-27'),
(64, 'Amazon', 125.99, '2024-07-27'),
(65, 'Amazon', 179.99, '2024-07-27'),

-- DA'Amazon' 2 AMAZON
(1, 'Amazon', 115.90, '2024-07-28'),
(2, 'Amazon', 149.15, '2024-07-28'),
(3, 'Amazon', 355.30, '2024-07-28'),
(4, 'Amazon', 388.55, '2024-07-28'),
(5, 'Amazon', 559.55, '2024-07-28'),
(6, 'Amazon', 217.05, '2024-07-28'),
(7, 'Amazon', 418.05, '2024-07-28'),
(8, 'Amazon', 103.05, '2024-07-28'),
(9, 'Amazon', 208.05, '2024-07-28'),
(10, 'Amazon', 189.05, '2024-07-28'),
(11, 'Amazon', 316.55, '2024-07-28'),
(12, 'Amazon', 284.05, '2024-07-28'),
(13, 'Amazon', 472.55, '2024-07-28'),
(14, 'Amazon', 284.05, '2024-07-28'),
(15, 'Amazon', 665.00, '2024-07-28'),
(16, 'Amazon', 311.10, '2024-07-28'),
(17, 'Amazon', 473.55, '2024-07-28'),
(18, 'Amazon', 663.05, '2024-07-28'),
(19, 'Amazon', 281.05, '2024-07-28'),
(20, 'Amazon', 568.55, '2024-07-28'),
(21, 'Amazon', 1139.05, '2024-07-28'),
(22, 'Amazon', 455.10, '2024-07-28'),
(23, 'Amazon', 620.55, '2024-07-28'),
(24, 'Amazon', 276.05, '2024-07-28'),
(25, 'Amazon', 311.10, '2024-07-28'),
(26, 'Amazon', 473.55, '2024-07-28'),
(27, 'Amazon', 320.10, '2024-07-28'),
(28, 'Amazon', 486.10, '2024-07-28'),
(29, 'Amazon', 662.55, '2024-07-28'),
(30, 'Amazon', 460.10, '2024-07-28'),
(31, 'Amazon', 622.55, '2024-07-28'),
(32, 'Amazon', 47.99, '2024-07-28'),
(33, 'Amazon', 85.99, '2024-07-28'),
(34, 'Amazon', 142.99, '2024-07-28'),
(35, 'Amazon', 66.99, '2024-07-28'),
(36, 'Amazon', 85.99, '2024-07-28'),
(37, 'Amazon', 170.99, '2024-07-28'),
(38, 'Amazon', 127.24, '2024-07-28'),
(39, 'Amazon', 131.99, '2024-07-28'),
(40, 'Amazon', 52.99, '2024-07-28'),
(41, 'Amazon', 28.49, '2024-07-28'),
(42, 'Amazon', 85.49, '2024-07-28'),
(43, 'Amazon', 132.99, '2024-07-28'),
(44, 'Amazon', 75.99, '2024-07-28'),
(45, 'Amazon', 42.74, '2024-07-28'),
(46, 'Amazon', 75.99, '2024-07-28'),
(47, 'Amazon', 94.99, '2024-07-28'),
(48, 'Amazon', 142.99, '2024-07-28'),
(49, 'Amazon', 47.49, '2024-07-28'),
(50, 'Amazon', 66.99, '2024-07-28'),
(51, 'Amazon', 37.99, '2024-07-28'),
(52, 'Amazon', 75.99, '2024-07-28'),
(53, 'Amazon', 35.99, '2024-07-28'),
(54, 'Amazon', 78.69, '2024-07-28'),
(55, 'Amazon', 189.99, '2024-07-28'),
(56, 'Amazon', 79.99, '2024-07-28'),
(57, 'Amazon', 284.99, '2024-07-28'),
(58, 'Amazon', 189.99, '2024-07-28'),
(59, 'Amazon', 217.99, '2024-07-28'),
(60, 'Amazon', 180.99, '2024-07-28'),
(61, 'Amazon', 170.99, '2024-07-28'),
(62, 'Amazon', 151.99, '2024-07-28'),
(63, 'Amazon', 123.99, '2024-07-28'),
(64, 'Amazon', 131.99, '2024-07-28'),
(65, 'Amazon', 189.99, '2024-07-28'),

-- DAY 3 AMAZON
(1, 'Amazon', 112.64, '2024-07-29'),
(2, 'Amazon', 144.64, '2024-07-29'),
(3, 'Amazon', 344.88, '2024-07-29'),
(4, 'Amazon', 374.72, '2024-07-29'),
(5, 'Amazon', 541.68, '2024-07-29'),
(6, 'Amazon', 213.92, '2024-07-29'),
(7, 'Amazon', 398.08, '2024-07-29'),
(8, 'Amazon', 99.04, '2024-07-29'),
(9, 'Amazon', 199.84, '2024-07-29'),
(10, 'Amazon', 183.84, '2024-07-29'),
(11, 'Amazon', 302.88, '2024-07-29'),
(12, 'Amazon', 275.99, '2024-07-29'),
(13, 'Amazon', 412.72, '2024-07-29'),
(14, 'Amazon', 275.99, '2024-07-29'),
(15, 'Amazon', 644.00, '2024-07-29'),
(16, 'Amazon', 303.10, '2024-07-29'),
(17, 'Amazon', 459.08, '2024-07-29'),
(18, 'Amazon', 641.28, '2024-07-29'),
(19, 'Amazon', 272.12, '2024-07-29'),
(20, 'Amazon', 551.32, '2024-07-29'),
(21, 'Amazon', 1109.28, '2024-07-29'),
(22, 'Amazon', 439.04, '2024-07-29'),
(23, 'Amazon', 597.68, '2024-07-29'),
(24, 'Amazon', 269.90, '2024-07-29'),
(25, 'Amazon', 303.10, '2024-07-29'),
(26, 'Amazon', 459.08, '2024-07-29'),
(27, 'Amazon', 295.20, '2024-07-29'),
(28, 'Amazon', 485.20, '2024-07-29'),
(29, 'Amazon', 661.60, '2024-07-29'),
(30, 'Amazon', 458.80, '2024-07-29'),
(31, 'Amazon', 606.80, '2024-07-29'),
(32, 'Amazon', 45.99, '2024-07-29'),
(33, 'Amazon', 82.79, '2024-07-29'),
(34, 'Amazon', 137.99, '2024-07-29'),
(35, 'Amazon', 64.79, '2024-07-29'),
(36, 'Amazon', 82.79, '2024-07-29'),
(37, 'Amazon', 165.99, '2024-07-29'),
(38, 'Amazon', 124.79, '2024-07-29'),
(39, 'Amazon', 127.99, '2024-07-29'),
(40, 'Amazon', 46.39, '2024-07-29'),
(41, 'Amazon', 27.59, '2024-07-29'),
(42, 'Amazon', 82.79, '2024-07-29'),
(43, 'Amazon', 128.79, '2024-07-29'),
(44, 'Amazon', 73.59, '2024-07-29'),
(45, 'Amazon', 41.16, '2024-07-29'),
(46, 'Amazon', 73.99, '2024-07-29'),
(47, 'Amazon', 91.99, '2024-07-29'),
(48, 'Amazon', 137.99, '2024-07-29'),
(49, 'Amazon', 45.99, '2024-07-29'),
(50, 'Amazon', 64.39, '2024-07-29'),
(51, 'Amazon', 36.79, '2024-07-29'),
(52, 'Amazon', 73.99, '2024-07-29'),
(53, 'Amazon', 35.99, '2024-07-29'),
(54, 'Amazon', 76.79, '2024-07-29'),
(55, 'Amazon', 183.99, '2024-07-29'),
(56, 'Amazon', 78.99, '2024-07-29'),
(57, 'Amazon', 275.99, '2024-07-29'),
(58, 'Amazon', 183.99, '2024-07-29'),
(59, 'Amazon', 207.99, '2024-07-29'),
(60, 'Amazon', 174.39, '2024-07-29'),
(61, 'Amazon', 165.99, '2024-07-29'),
(62, 'Amazon', 147.99, '2024-07-29'),
(63, 'Amazon', 119.99, '2024-07-29'),
(64, 'Amazon', 130.39, '2024-07-29'),
(65, 'Amazon', 183.99, '2024-07-29'),

-- DAY 1 EBAY
(1, 'Ebay', 125.66, '2024-07-27'),
(2, 'Ebay', 161.11, '2024-07-27'),
(3, 'Ebay', 385.22, '2024-07-27'),
(4, 'Ebay', 421.27, '2024-07-27'),
(5, 'Ebay', 606.27, '2024-07-27'),
(6, 'Ebay', 236.87, '2024-07-27'),
(7, 'Ebay', 452.17, '2024-07-27'),
(8, 'Ebay', 112.27, '2024-07-27'),
(9, 'Ebay', 225.57, '2024-07-27'),
(10, 'Ebay', 204.97, '2024-07-27'),
(11, 'Ebay', 339.87, '2024-07-27'),
(12, 'Ebay', 308.79, '2024-07-27'),
(13, 'Ebay', 462.27, '2024-07-27'),
(14, 'Ebay', 308.79, '2024-07-27'),
(15, 'Ebay', 607.70, '2024-07-27'),
(16, 'Ebay', 339.87, '2024-07-27'),
(17, 'Ebay', 514.97, '2024-07-27'),
(18, 'Ebay', 719.97, '2024-07-27'),
(19, 'Ebay', 307.27, '2024-07-27'),
(20, 'Ebay', 615.97, '2024-07-27'),
(21, 'Ebay', 1239.97, '2024-07-27'),
(22, 'Ebay', 493.97, '2024-07-27'),
(23, 'Ebay', 669.97, '2024-07-27'),
(24, 'Ebay', 298.97, '2024-07-27'),
(25, 'Ebay', 339.87, '2024-07-27'),
(26, 'Ebay', 514.97, '2024-07-27'),
(27, 'Ebay', 349.87, '2024-07-27'),
(28, 'Ebay', 525.87, '2024-07-27'),
(29, 'Ebay', 698.97, '2024-07-27'),
(30, 'Ebay', 504.87, '2024-07-27'),
(31, 'Ebay', 679.87, '2024-07-27'),
(32, 'Ebay', 51.49, '2024-07-27'),
(33, 'Ebay', 92.89, '2024-07-27'),
(34, 'Ebay', 154.99, '2024-07-27'),
(35, 'Ebay', 72.89, '2024-07-27'),
(36, 'Ebay', 92.89, '2024-07-27'),
(37, 'Ebay', 185.69, '2024-07-27'),
(38, 'Ebay', 138.99, '2024-07-27'),
(39, 'Ebay', 137.59, '2024-07-27'),
(40, 'Ebay', 51.49, '2024-07-27'),
(41, 'Ebay', 30.89, '2024-07-27'),
(42, 'Ebay', 92.89, '2024-07-27'),
(43, 'Ebay', 144.99, '2024-07-27'),
(44, 'Ebay', 76.79, '2024-07-27'),
(45, 'Ebay', 46.29, '2024-07-27'),
(46, 'Ebay', 82.79, '2024-07-27'),
(47, 'Ebay', 102.99, '2024-07-27'),
(48, 'Ebay', 154.99, '2024-07-27'),
(49, 'Ebay', 51.49, '2024-07-27'),
(50, 'Ebay', 72.09, '2024-07-27'),
(51, 'Ebay', 41.19, '2024-07-27'),
(52, 'Ebay', 82.79, '2024-07-27'),
(53, 'Ebay', 39.17, '2024-07-27'),
(54, 'Ebay', 85.68, '2024-07-27'),
(55, 'Ebay', 205.99, '2024-07-27'),
(56, 'Ebay', 82.99, '2024-07-27'),
(57, 'Ebay', 308.99, '2024-07-27'),
(58, 'Ebay', 205.79, '2024-07-27'),
(59, 'Ebay', 236.99, '2024-07-27'),
(60, 'Ebay', 195.99, '2024-07-27'),
(61, 'Ebay', 185.99, '2024-07-27'),
(62, 'Ebay', 155.99, '2024-07-27'),
(63, 'Ebay', 133.99, '2024-07-27'),
(64, 'Ebay', 146.99, '2024-07-27'),
(65, 'Ebay', 185.99, '2024-07-27'),

-- DAY 2 EBAY

(1, 'Ebay', 112.64, '2024-07-31'),
(2, 'Ebay', 144.64, '2024-07-31'),
(3, 'Ebay', 344.88, '2024-07-31'),
(4, 'Ebay', 374.72, '2024-07-31'),
(5, 'Ebay', 541.68, '2024-07-31'),
(6, 'Ebay', 213.92, '2024-07-31'),
(7, 'Ebay', 398.08, '2024-07-31'),
(8, 'Ebay', 99.04, '2024-07-31'),
(9, 'Ebay', 199.84, '2024-07-31'),
(10, 'Ebay', 183.84, '2024-07-31'),
(11, 'Ebay', 302.88, '2024-07-31'),
(12, 'Ebay', 275.99, '2024-07-31'),
(13, 'Ebay', 412.72, '2024-07-31'),
(14, 'Ebay', 275.99, '2024-07-31'),
(15, 'Ebay', 644.00, '2024-07-31'),
(16, 'Ebay', 303.10, '2024-07-31'),
(17, 'Ebay', 459.08, '2024-07-31'),
(18, 'Ebay', 641.28, '2024-07-31'),
(19, 'Ebay', 272.12, '2024-07-31'),
(20, 'Ebay', 551.32, '2024-07-31'),
(21, 'Ebay', 1109.28, '2024-07-31'),
(22, 'Ebay', 439.04, '2024-07-31'),
(23, 'Ebay', 597.68, '2024-07-31'),
(24, 'Ebay', 269.90, '2024-07-31'),
(25, 'Ebay', 303.10, '2024-07-31'),
(26, 'Ebay', 459.08, '2024-07-31'),
(27, 'Ebay', 295.20, '2024-07-31'),
(28, 'Ebay', 485.20, '2024-07-31'),
(29, 'Ebay', 661.60, '2024-07-31'),
(30, 'Ebay', 458.80, '2024-07-31'),
(31, 'Ebay', 606.80, '2024-07-31'),
(32, 'Ebay', 45.99, '2024-07-31'),
(33, 'Ebay', 82.79, '2024-07-31'),
(34, 'Ebay', 137.99, '2024-07-31'),
(35, 'Ebay', 64.79, '2024-07-31'),
(36, 'Ebay', 82.79, '2024-07-31'),
(37, 'Ebay', 165.99, '2024-07-31'),
(38, 'Ebay', 124.79, '2024-07-31'),
(39, 'Ebay', 127.99, '2024-07-31'),
(40, 'Ebay', 46.39, '2024-07-31'),
(41, 'Ebay', 27.59, '2024-07-31'),
(42, 'Ebay', 82.79, '2024-07-31'),
(43, 'Ebay', 128.79, '2024-07-31'),
(44, 'Ebay', 73.59, '2024-07-31'),
(45, 'Ebay', 41.16, '2024-07-31'),
(46, 'Ebay', 73.99, '2024-07-31'),
(47, 'Ebay', 91.99, '2024-07-31'),
(48, 'Ebay', 137.99, '2024-07-31'),
(49, 'Ebay', 45.99, '2024-07-31'),
(50, 'Ebay', 64.39, '2024-07-31'),
(51, 'Ebay', 36.79, '2024-07-31'),
(52, 'Ebay', 73.99, '2024-07-31'),
(53, 'Ebay', 35.99, '2024-07-31'),
(54, 'Ebay', 76.79, '2024-07-31'),
(55, 'Ebay', 183.99, '2024-07-31'),
(56, 'Ebay', 78.99, '2024-07-31'),
(57, 'Ebay', 275.99, '2024-07-31'),
(58, 'Ebay', 183.99, '2024-07-31'),
(59, 'Ebay', 207.99, '2024-07-31'),
(60, 'Ebay', 174.39, '2024-07-31'),
(61, 'Ebay', 165.99, '2024-07-31'),
(62, 'Ebay', 147.99, '2024-07-31'),
(63, 'Ebay', 133.99, '2024-07-31'),
(64, 'Ebay', 146.99, '2024-07-31'),
(65, 'Ebay', 185.99, '2024-07-31');