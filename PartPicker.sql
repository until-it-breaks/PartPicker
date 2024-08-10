create database PartPicker;
use PartPicker;

create table Ban (
	UsernameAssegnatario varchar(255) not null,
	DataInizioBan date not null,
	DataFineBan date,
	DescrizioneBan varchar(255) not null,
	UsernameAssegnatore varchar(255) not null,
	constraint primary key (UsernameAssegnatario, DataInizioBan)
);

create table Build (
	CodiceBuild int auto_increment,
	CodiceCooler int not null,
	CodiceCase int not null,
	CodicePsu int not null,
	CodiceCpu int not null,
	CodiceMotherboard int not null,
	constraint primary key (CodiceBuild)
);

create table `Case` (
	CodiceCase int not null,
	FattoreFormaCase enum("ATX","MicroATX", "MiniITX") not null,
	constraint primary key (CodiceCase)
);

create table CompatibilitaRamCpu (
	NomeGenerazioneRam varchar(255) not null,
	CodiceCpu int not null,
	constraint primary key (NomeGenerazioneRam, CodiceCpu)
);

create table Componenti (
	CodiceComponente int not null,
	NomeComponente varchar(255) not null,
	TipoComponente enum("Gpu", "Cpu", "Motherboard", "Ram", "Psu", "Case", "Storage", "Cooler") not null,
	AnnoLancio year not null,
	PrezzoListino float not null,
    CodiceProduttore int not null,
	constraint primary key (CodiceComponente)
);

create table Cooler (
	CodiceCooler int not null,
	RpmCooler int,
	LivelloRumore float,
	TipoCooler enum("Aria","AIO") not null,
	constraint primary key (CodiceCooler)
);

create table `Cpu` (
	CodiceCpu int not null,
	FamigliaCpu varchar(255) not null,
	NumeroCore int not null,
	FrequenzaCpu float not null,
	Tdp int not null,
	Smt boolean not null,
	NomeSocket varchar(255) not null,
	constraint primary key (CodiceCpu)
);

create table GenerazioniRam (
	NomeGenerazioneRam varchar(255) not null,
	constraint primary key (NomeGenerazioneRam)
);

create table Gpu (
	CodiceGpu int not null,
	FamigliaGpu varchar(255) not null,
	TipoMemoriaGpu varchar(255) not null,
	QuantitaMemoriaGpu int not null,
	FrequenzaGpu int not null,
	Tgp int not null,
	constraint primary key (CodiceGpu)
);

create table Motherboard (
	CodiceMotherboard int not null,
	FattoreFormaMotherboard varchar(255) not null,
	NomeChipset varchar(255) not null,
	SlotRam int not null,
	SlotGpu int not null,
	Wifi boolean not null,
	NomeSocket varchar(255) not null,
	NomeGenerazioneRam varchar(255) not null,
	constraint primary key (CodiceMotherboard)
);

create table PrezziComponenti (
	CodiceComponente int not null,
	NomeRivenditore varchar(255) not null,
	PrezzoComponente float not null,
	DataRilevamentoPrezzo date not null,
	constraint primary key (NomeRivenditore, CodiceComponente, DataRilevamentoPrezzo)
);

create table Produttori (
	CodiceProduttore int auto_increment,
	NomeProduttore varchar(255) not null,
	PaeseProduttore varchar(255) not null,
	constraint primary key (CodiceProduttore)
);

create table Psu (
	CodicePsu int not null,
	FattoreFormaPsu varchar(255) not null,
	Efficienza enum("Bronze", "Silver", "Gold", "Platinum", "Titanium") not null,
	Wattaggio int not null,
	Modularita enum("Full", "Semi", "No") not null,
	constraint primary key (CodicePsu)
);

create table Pubblicazioni (
	CodiceBuild int auto_increment,
	DataModificaBuild date not null,
	Username varchar(255) not null,
	constraint primary key (CodiceBuild)
);

create table Ram (
	CodiceRam int not null,
	FrequenzaRam int not null,
	CapienzaRam int not null,
	Latenza varchar(255) not null,
	Ecc boolean not null,
	NomeGenerazioneRam varchar(255) not null,
	constraint primary key (CodiceRam)
);

create table Recensioni (
	CodiceBuild int auto_increment,
	Username varchar(255) not null,
	RatingRecensione int not null,
	Commento varchar(255),
	DataModificaRecensione date not null,
	constraint primary key (CodiceBuild, Username),
    constraint chk_rating check (RatingRecensione between 1 and 10)
);

create table `Socket` (
	NomeSocket varchar(255) not null,
	constraint primary key (NomeSocket)
);

create table `Storage` (
	CodiceStorage int not null,
	CapienzaStorage int not null,
	RpmStorage int,
	QuantitaCache int,
	TipoStorage enum("Hdd", "Ssd") not null,
	constraint primary key (CodiceStorage)
);

create table UsiGpu (
	CodiceBuild int not null,
	CodiceGpu int not null,
	Quantita int not null,
	constraint primary key (CodiceBuild, CodiceGpu)
);

create table UsiRam (
	CodiceBuild int not null,
	CodiceRam int not null,
	Quantita int not null,
	constraint primary key (CodiceBuild, CodiceRam)
);

create table UsiStorage (
	CodiceBuild int not null,
	CodiceStorage int not null,
	Quantita int not null,
	constraint primary key (CodiceBuild, CodiceStorage)
);

create table Utenti (
	Username varchar(255) not null,
	`Password` varchar(255) not null,
	DataRegistrazione date not null,
	Email varchar(255) not null,
	Moderatore boolean not null,
	constraint primary key (Username)
);

-- Constraints Section
-- ___________________ 

alter table Ban
	add constraint foreign key (UsernameAssegnatore) references Utenti (Username),
	add constraint foreign key (UsernameAssegnatario) references Utenti (Username);

alter table Build 
	add constraint foreign key (CodiceCooler) references Cooler (CodiceCooler),
	add constraint foreign key (CodiceCase) references `Case` (CodiceCase),
	add constraint foreign key (CodicePsu) references Psu (CodicePsu),
	add constraint foreign key (CodiceCpu) references `Cpu` (CodiceCpu),
	add constraint foreign key (CodiceMotherboard) references Motherboard (CodiceMotherboard);

alter table `Case`
	add constraint foreign key (CodiceCase) references Componenti (CodiceComponente);

alter table CompatibilitaRamCpu
	add constraint foreign key (CodiceCpu) references Cpu (CodiceCpu),
	add constraint foreign key (NomeGenerazioneRam) references GenerazioniRam (NomeGenerazioneRam);

alter table Componenti
	add constraint foreign key (CodiceProduttore) references Produttori (CodiceProduttore);

alter table Cooler
	add constraint foreign key (CodiceCooler) references Componenti (CodiceComponente);

alter table `Cpu`
	add constraint foreign key (CodiceCpu) references Componenti (CodiceComponente),
	add constraint foreign key (NomeSocket) references `Socket` (NomeSocket);

alter table Gpu
	add constraint FK_Gpu foreign key (CodiceGpu) references Componenti (CodiceComponente);

alter table Motherboard
	add constraint foreign key (NomeSocket) references `Socket` (NomeSocket),
	add constraint foreign key (NomeGenerazioneRam) references GenerazioniRam (NomeGenerazioneRam),
	add constraint foreign key (CodiceMotherboard) references Componenti (CodiceComponente);

alter table PrezziComponenti
	add constraint foreign key (CodiceComponente) references Componenti (CodiceComponente);

alter table Psu
	add constraint foreign key (CodicePsu) references Componenti (CodiceComponente);

alter table Pubblicazioni
	add constraint foreign key (Username) references Utenti (Username),
	add constraint foreign key (CodiceBuild) references Build (CodiceBuild);

alter table Ram
	add constraint foreign key (CodiceRam) references Componenti (CodiceComponente),
	add constraint foreign key (NomeGenerazioneRam) references GenerazioniRam (NomeGenerazioneRam);

alter table Recensioni
	add constraint foreign key (Username) references Utenti (Username),
	add constraint foreign key (CodiceBuild) references Build (CodiceBuild);

alter table `Storage`
	add constraint foreign key (CodiceStorage) references Componenti (CodiceComponente);

alter table UsiGpu
    add constraint foreign key (CodiceBuild) references Build (CodiceBuild),
    add constraint foreign key (CodiceGpu) references Gpu (CodiceGpu);

alter table UsiRam
	add constraint foreign key (CodiceBuild) references Build (CodiceBuild),
	add constraint foreign key (CodiceRam) references Ram (CodiceRam);

alter table UsiStorage
	add constraint foreign key (CodiceBuild) references Build (CodiceBuild),
	add constraint foreign key (CodiceStorage) references `Storage` (CodiceStorage);