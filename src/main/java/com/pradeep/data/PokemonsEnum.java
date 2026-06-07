package com.pradeep.data;

import lombok.Getter;

@Getter
public enum PokemonsEnum {

    UNKNOWN(0, "???", 0, 0, 0, 0, 0, 0, "Medium_Slow"),

    BULBASAUR(1, "BULBASAUR", 45, 49, 49, 65, 65, 45, "Medium_Slow"),
    IVYSAUR(2, "IVYSAUR", 60, 62, 63, 80, 80, 60, "Medium_Slow"),
    VENUSAUR(3, "VENUSAUR", 80, 82, 83, 100, 100, 80, "Medium_Slow"),

    CHARMANDER(4, "CHARMANDER", 39, 52, 43, 60, 50, 65, "Medium_Slow"),
    CHARMELEON(5, "CHARMELEON", 58, 64, 58, 80, 65, 80, "Medium_Slow"),
    CHARIZARD(6, "CHARIZARD", 78, 84, 78, 109, 85, 100, "Medium_Slow"),

    SQUIRTLE(7, "SQUIRTLE", 44, 48, 65, 50, 64, 43, "Medium_Slow"),
    WARTORTLE(8, "WARTORTLE", 59, 63, 80, 65, 80, 58, "Medium_Slow"),
    BLASTOISE(9, "BLASTOISE", 79, 83, 100, 85, 105, 78, "Medium_Slow"),

    CATERPIE(10, "CATERPIE", 45, 30, 35, 20, 20, 45, "Medium_Fast"),
    METAPOD(11, "METAPOD", 50, 20, 55, 25, 25, 30, "Medium_Fast"),
    BUTTERFREE(12, "BUTTERFREE", 60, 45, 50, 80, 80, 70, "Medium_Fast"),

    WEEDLE(13, "WEEDLE", 40, 35, 30, 20, 20, 50, "Medium_Fast"),
    KAKUNA(14, "KAKUNA", 45, 25, 50, 25, 25, 35, "Medium_Fast"),
    BEEDRILL(15, "BEEDRILL", 65, 80, 40, 45, 80, 75, "Medium_Fast"),

    PIDGEY(16, "PIDGEY", 40, 45, 40, 35, 35, 56, "Medium_Slow"),
    PIDGEOTTO(17, "PIDGEOTTO", 63, 60, 55, 50, 50, 71, "Medium_Slow"),
    PIDGEOT(18, "PIDGEOT", 83, 80, 75, 70, 70, 91, "Medium_Slow"),

    RATTATA(19, "RATTATA", 30, 56, 35, 25, 35, 72, "Medium_Fast"),
    RATICATE(20, "RATICATE", 55, 81, 60, 50, 70, 97, "Medium_Fast"),

    SPEAROW(21, "SPEAROW", 40, 60, 30, 31, 31, 70, "Medium_Fast"),
    FEAROW(22, "FEAROW", 65, 90, 65, 61, 61, 100, "Medium_Fast"),

    EKANS(23, "EKANS", 35, 60, 44, 40, 54, 55, "Medium_Fast"),
    ARBOK(24, "ARBOK", 60, 85, 69, 65, 79, 80, "Medium_Fast"),

    PIKACHU(25, "PIKACHU", 35, 55, 30, 50, 40, 90, "Medium_Fast"),
    RAICHU(26, "RAICHU", 60, 90, 55, 90, 80, 100, "Medium_Fast"),

    SANDSHREW(27, "SANDSHREW", 50, 75, 85, 20, 30, 40, "Medium_Fast"),
    SANDSLASH(28, "SANDSLASH", 75, 100, 110, 45, 55, 65, "Medium_Fast"),

    NIDORAN_F(29, "NIDORAN♀", 55, 47, 52, 40, 40, 41, "Medium_Slow"),
    NIDORINA(30, "NIDORINA", 70, 62, 67, 55, 55, 56, "Medium_Slow"),
    NIDOQUEEN(31, "NIDOQUEEN", 90, 82, 87, 75, 85, 76, "Medium_Slow"),

    NIDORAN_M(32, "NIDORAN♂", 46, 57, 40, 40, 40, 50, "Medium_Slow"),
    NIDORINO(33, "NIDORINO", 61, 72, 57, 55, 55, 65, "Medium_Slow"),
    NIDOKING(34, "NIDOKING", 81, 92, 77, 85, 75, 85, "Medium_Slow"),

    CLEFAIRY(35, "CLEFAIRY", 70, 45, 48, 60, 65, 35, "Fast"),
    CLEFABLE(36, "CLEFABLE", 95, 70, 73, 85, 90, 60, "Fast"),

    VULPIX(37, "VULPIX", 38, 41, 40, 50, 65, 65, "Medium_Fast"),
    NINETALES(38, "NINETALES", 73, 76, 75, 81, 100, 100, "Medium_Fast"),

    JIGGLYPUFF(39, "JIGGLYPUFF", 115, 45, 20, 45, 25, 20, "Fast"),
    WIGGLYTUFF(40, "WIGGLYTUFF", 140, 70, 45, 75, 50, 45, "Fast"),

    ZUBAT(41, "ZUBAT", 40, 45, 35, 20, 20, 50, "Medium_Fast"),
    GOLBAT(42, "GOLBAT", 75, 80, 70, 65, 75, 90, "Medium_Fast"),

    ODDISH(43, "ODDISH", 45, 50, 55, 75, 65, 30, "Medium_Slow"),
    GLOOM(44, "GLOOM", 60, 65, 70, 85, 75, 40, "Medium_Slow"),
    VILEPLUME(45, "VILEPLUME", 75, 80, 85, 100, 90, 50, "Medium_Slow"),

    PARAS(46, "PARAS", 35, 70, 55, 45, 55, 25, "Medium_Fast"),
    PARASECT(47, "PARASECT", 60, 95, 80, 60, 80, 30, "Medium_Fast"),

    VENONAT(48, "VENONAT", 60, 55, 50, 40, 55, 45, "Medium_Fast"),
    VENOMOTH(49, "VENOMOTH", 70, 65, 60, 90, 75, 90, "Medium_Fast"),

    DIGLETT(50, "DIGLETT", 10, 55, 25, 35, 45, 95, "Medium_Fast"),
    DUGTRIO(51, "DUGTRIO", 35, 80, 50, 50, 70, 120, "Medium_Fast"),

    MEOWTH(52, "MEOWTH", 40, 45, 35, 40, 40, 90, "Medium_Fast"),
    PERSIAN(53, "PERSIAN", 65, 70, 60, 65, 65, 115, "Medium_Fast"),

    PSYDUCK(54, "PSYDUCK", 50, 52, 48, 65, 50, 55, "Medium_Fast"),
    GOLDUCK(55, "GOLDUCK", 80, 82, 78, 95, 80, 85, "Medium_Fast"),

    MANKEY(56, "MANKEY", 40, 80, 35, 35, 45, 70, "Medium_Fast"),
    PRIMEAPE(57, "PRIMEAPE", 65, 105, 60, 60, 70, 95, "Medium_Fast"),

    GROWLITHE(58, "GROWLITHE", 55, 70, 45, 70, 50, 60, "Slow"),
    ARCANINE(59, "ARCANINE", 90, 110, 80, 100, 80, 95, "Slow"),

    POLIWAG(60, "POLIWAG", 40, 50, 40, 40, 40, 90, "Medium_Slow"),
    POLIWHIRL(61, "POLIWHIRL", 65, 65, 65, 5, 50, 90, "Medium_Slow"),
    POLIWRATH(62, "POLIWRATH", 90, 85, 95, 70, 90, 70, "Medium_Slow"),

    ABRA(63, "ABRA", 25, 20, 15, 105, 55, 90, "Medium_Slow"),
    KADABRA(64, "KADABRA", 40, 35, 30, 120, 70, 105, "Medium_Slow"),
    ALAKAZAM(65, "ALAKAZAM", 55, 50, 45, 135, 85, 120, "Medium_Slow"),

    MACHOP(66, "MACHOP", 70, 80, 50, 35, 35, 35, "Medium_Slow"),
    MACHOKE(67, "MACHOKE", 80, 100, 70, 50, 60, 45, "Medium_Slow"),
    MACHAMP(68, "MACHAMP", 90, 130, 80, 65, 85, 55, "Medium_Slow"),

    BELLSPROUT(69, "BELLSPROUT", 50, 75, 35, 70, 30, 40, "Medium_Slow"),
    WEEPINBELL(70, "WEEPINBELL", 65, 90, 50, 85, 45, 55, "Medium_Slow"),
    VICTREEBEL(71, "VICTREEBEL", 80, 105, 65, 100, 70, 70, "Medium_Slow"),

    TENTACOOL(72, "TENTACOOL", 40, 40, 35, 50, 100, 70, "Slow"),
    TENTACRUEL(73, "TENTACRUEL", 80, 70, 65, 80, 120, 100, "Slow"),

    GEODUDE(74, "GEODUDE", 40, 80, 100, 30, 30, 20, "Medium_Slow"),
    GRAVELER(75, "GRAVELER", 55, 95, 115, 45, 45, 35, "Medium_Slow"),
    GOLEM(76, "GOLEM", 80, 110, 130, 55, 65, 45, "Medium_Slow"),

    PONYTA(77, "PONYTA", 50, 85, 55, 65, 65, 90, "Medium_Fast"),
    RAPIDASH(78, "RAPIDASH", 65, 100, 70, 80, 80, 105, "Medium_Fast"),

    SLOWPOKE(79, "SLOWPOKE", 90, 65, 65, 40, 40, 15, "Medium_Fast"),
    SLOWBRO(80, "SLOWBRO", 95, 75, 110, 100, 80, 30, "Medium_Fast"),

    MAGNEMITE(81, "MAGNEMITE", 25, 35, 70, 95, 55, 45, "Medium_Fast"),
    MAGNETON(82, "MAGNETON", 50, 60, 95, 120, 70, 70, "Medium_Fast"),

    FARFETCHD(83, "FARFETCHD", 52, 65, 55, 58, 62, 60, "Medium_Fast"),

    DODUO(84, "DODUO", 35, 85, 45, 35, 35, 75, "Medium_Fast"),
    DODRIO(85, "DODRIO", 60, 110, 70, 60, 60, 100, "Medium_Fast"),

    SEEL(86, "SEEL", 65, 45, 55, 45, 70, 45, "Medium_Fast"),
    DEWGONG(87, "DEWGONG", 90, 70, 80, 70, 95, 70, "Medium_Fast"),

    GRIMER(88, "GRIMER", 80, 80, 50, 40, 50, 25, "Medium_Fast"),
    MUK(89, "MUK", 105, 105, 75, 65, 100, 50, "Medium_Fast"),

    SHELLDER(90, "SHELLDER", 30, 65, 100, 45, 25, 40, "Slow"),
    CLOYSTER(91, "CLOYSTER", 50, 95, 180, 85, 45, 70, "Slow"),

    GASTLY(92, "GASTLY", 30, 35, 30, 100, 35, 80, "Medium_Slow"),
    HAUNTER(93, "HAUNTER", 45, 50, 45, 115, 55, 95, "Medium_Slow"),
    GENGAR(94, "GENGAR", 60, 65, 60, 130, 75, 110, "Medium_Slow"),

    ONIX(95, "ONIX", 35, 45, 160, 30, 45, 70, "Medium_Fast"),

    DROWZEE(96, "DROWZEE", 60, 48, 45, 43, 90, 42, "Medium_Fast"),
    HYPNO(97, "HYPNO", 85, 73, 70, 73, 115, 67, "Medium_Fast"),

    KRABBY(98, "KRABBY", 30, 105, 90, 25, 25, 50, "Medium_Fast"),
    KINGLER(99, "KINGLER", 55, 130, 115, 50, 50, 75, "Medium_Fast"),

    VOLTORB(100, "VOLTORB", 40, 30, 50, 55, 55, 100, "Medium_Fast"),
    ELECTRODE(101, "ELECTRODE", 60, 50, 70, 80, 80, 140, "Medium_Fast"),

    EXEGGCUTE(102, "EXEGGCUTE", 60, 40, 80, 60, 45, 40, "Slow"),
    EXEGGUTOR(103, "EXEGGUTOR", 95, 95, 85, 125, 65, 55, "Slow"),

    CUBONE(104, "CUBONE", 50, 50, 95, 40, 50, 35, "Medium_Fast"),
    MAROWAK(105, "MAROWAK", 60, 80, 110, 50, 80, 45, "Medium_Fast"),

    HITMONLEE(106, "HITMONLEE", 50, 120, 53, 35, 110, 87, "Medium_Fast"),
    HITMONCHAN(107, "HITMONCHAN", 50, 105, 79, 35, 110, 76, "Medium_Fast"),

    LICKITUNG(108, "LICKITUNG", 90, 55, 75, 60, 75, 30, "Medium_Fast"),

    KOFFING(109, "KOFFING", 40, 65, 95, 60, 45, 35, "Medium_Fast"),
    WEEZING(110, "WEEZING", 65, 90, 120, 85, 70, 60, "Medium_Fast"),

    RHYHORN(111, "RHYHORN", 80, 85, 95, 30, 30, 25, "Slow"),
    RHYDON(112, "RHYDON", 105, 130, 120, 45, 45, 40, "Slow"),

    CHANSEY(113, "CHANSEY", 250, 5, 5, 35, 105, 50, "Fast"),

    TANGELA(114, "TANGELA", 65, 55, 115, 100, 40, 60, "Medium_Fast"),

    KANGASKHAN(115, "KANGASKHAN", 105, 95, 80, 40, 80, 90, "Medium_Fast"),

    HORSEA(116, "HORSEA", 30, 40, 70, 70, 25, 60, "Medium_Fast"),
    SEADRA(117, "SEADRA", 55, 65, 95, 95, 45, 85, "Medium_Fast"),

    GOLDEEN(118, "GOLDEEN", 45, 67, 60, 35, 50, 63, "Medium_Fast"),
    SEAKING(119, "SEAKING", 80, 92, 65, 65, 80, 68, "Medium_Fast"),

    STARYU(120, "STARYU", 30, 45, 55, 70, 55, 85, "Slow"),
    STARMIE(121, "STARMIE", 60, 75, 85, 100, 85, 115, "Slow"),

    MR_MIME(122, "MR. MIME", 40, 45, 65, 100, 120, 90, "Medium_Fast"),
    SCYTHER(123, "SCYTHER", 70, 110, 80, 55, 80, 105, "Medium_Fast"),
    JYNX(124, "JYNX", 65, 50, 35, 115, 95, 95, "Medium_Fast"),
    ELECTABUZZ(125, "ELECTABUZZ", 65, 83, 57, 95, 85, 105, "Medium_Fast"),
    MAGMAR(126, "MAGMAR", 65, 95, 57, 100, 85, 93, "Medium_Fast"),
    PINSIR(127, "PINSIR", 65, 125, 100, 55, 70, 85, "Slow"),
    TAUROS(128, "TAUROS", 75, 100, 95, 40, 70, 110, "Slow"),
    MAGIKARP(129, "MAGIKARP", 20, 10, 55, 15, 20, 80, "Slow"),
    GYARADOS(130, "GYARADOS", 95, 125, 79, 60, 100, 81, "Slow"),
    LAPRAS(131, "LAPRAS", 130, 85, 80, 85, 95, 60, "Slow"),
    DITTO(132, "DITTO", 48, 48, 48, 48, 48, 48, "Medium_Fast"),
    EEVEE(133, "EEVEE", 55, 55, 50, 45, 65, 55, "Medium_Fast"),
    VAPOREON(134, "VAPOREON", 130, 65, 60, 110, 95, 65, "Medium_Fast"),
    JOLTEON(135, "JOLTEON", 65, 65, 60, 110, 95, 130, "Medium_Fast"),
    FLAREON(136, "FLAREON", 65, 130, 60, 95, 110, 65, "Medium_Fast"),
    PORYGON(137, "PORYGON", 65, 60, 70, 85, 75, 40, "Medium_Fast"),

    OMANYTE(138, "OMANYTE", 35, 40, 100, 90, 55, 35, "Medium_Fast"),
    OMASTAR(139, "OMASTAR", 70, 60, 125, 115, 70, 55, "Medium_Fast"),
    KABUTO(140, "KABUTO", 30, 80, 90, 55, 45, 55, "Medium_Fast"),
    KABUTOPS(141, "KABUTOPS", 60, 115, 105, 65, 70, 80, "Medium_Fast"),

    AERODACTYL(142, "AERODACTYL", 80, 105, 65, 60, 75, 130, "Slow"),
    SNORLAX(143, "SNORLAX", 160, 110, 65, 65, 110, 30, "Slow"),

    ARTICUNO(144, "ARTICUNO", 90, 85, 100, 95, 125, 85, "Slow"),
    ZAPDOS(145, "ZAPDOS", 90, 90, 85, 125, 90, 100, "Slow"),
    MOLTRES(146, "MOLTRES", 90, 100, 90, 125, 85, 90, "Slow"),

    DRATINI(147, "DRATINI", 41, 64, 45, 50, 50, 50, "Slow"),
    DRAGONAIR(148, "DRAGONAIR", 61, 84, 65, 70, 70, 70, "Slow"),
    DRAGONITE(149, "DRAGONITE", 91, 134, 95, 100, 100, 80, "Slow"),

    MEWTWO(150, "MEWTWO", 106, 110, 90, 154, 90, 130, "Slow"),
    MEW(151, "MEW", 100, 100, 100, 100, 100, 100, "Medium_Slow"),

    CHIKORITA(152, "CHIKORITA", 45, 49, 65, 49, 65, 45, "Medium_Slow"),
    BAYLEEF(153, "BAYLEEF", 60, 62, 80, 63, 80, 60, "Medium_Slow"),
    MEGANIUM(154, "MEGANIUM", 80, 82, 100, 83, 100, 80, "Medium_Slow"),

    CYNDAQUIL(155, "CYNDAQUIL", 39, 52, 43, 60, 50, 65, "Medium_Slow"),
    QUILAVA(156, "QUILAVA", 58, 64, 58, 80, 65, 80, "Medium_Slow"),
    TYPHLOSION(157, "TYPHLOSION", 78, 84, 78, 109, 85, 100, "Medium_Slow"),

    TOTODILE(158, "TOTODILE", 50, 65, 64, 44, 48, 43, "Medium_Slow"),
    CROCONAW(159, "CROCONAW", 65, 80, 80, 59, 63, 58, "Medium_Slow"),
    FERALIGATR(160, "FERALIGATR", 85, 105, 100, 79, 83, 78, "Medium_Slow"),

    SENTRET(161, "SENTRET", 35, 46, 34, 35, 45, 20, "Medium_Fast"),
    FURRET(162, "FURRET", 85, 76, 64, 45, 55, 90, "Medium_Fast"),

    HOOTHOOT(163, "HOOTHOOT", 60, 30, 30, 36, 56, 50, "Medium_Fast"),
    NOCTOWL(164, "NOCTOWL", 100, 50, 50, 76, 96, 70, "Medium_Fast"),

    LEDYBA(165, "LEDYBA", 40, 20, 30, 40, 80, 55, "Fast"),
    LEDIAN(166, "LEDIAN", 55, 35, 50, 55, 110, 85, "Fast"),

    SPINARAK(167, "SPINARAK", 40, 60, 40, 40, 40, 30, "Fast"),
    ARIADOS(168, "ARIADOS", 70, 90, 70, 60, 60, 40, "Fast"),

    CROBAT(169, "CROBAT", 85, 90, 80, 70, 80, 130, "Medium_Fast"),

    CHINCHOU(170, "CHINCHOU", 75, 38, 38, 56, 56, 67, "Slow"),
    LANTURN(171, "LANTURN", 125, 58, 58, 76, 76, 67, "Slow"),

    PICHU(172, "PICHU", 20, 40, 15, 35, 35, 60, "Medium_Fast"),
    CLEFFA(173, "CLEFFA", 50, 25, 28, 45, 55, 15, "Fast"),
    IGGLYBUFF(174, "IGGLYBUFF", 90, 30, 15, 40, 20, 15, "Fast"),

    TOGEPI(175, "TOGEPI", 35, 20, 65, 40, 65, 20, "Fast"),
    TOGETIC(176, "TOGETIC", 55, 40, 85, 80, 105, 40, "Fast"),

    NATU(177, "NATU", 40, 50, 45, 70, 45, 70, "Medium_Fast"),
    XATU(178, "XATU", 65, 75, 70, 95, 70, 95, "Medium_Fast"),

    MAREEP(179, "MAREEP", 55, 40, 40, 65, 45, 35, "Medium_Slow"),
    FLAAFFY(180, "FLAAFFY", 70, 55, 55, 80, 60, 45, "Medium_Slow"),
    AMPHAROS(181, "AMPHAROS", 90, 75, 75, 115, 90, 55, "Medium_Slow"),
    BELLOSSOM(182, "BELLOSSOM", 75, 80, 85, 90, 100, 50, "Medium_Slow"),
    MARILL(183, "MARILL", 70, 20, 50, 20, 50, 40, "Fast"),
    AZUMARILL(184, "AZUMARILL", 100, 50, 80, 50, 80, 50, "Fast"),

    SUDOWOODO(185, "SUDOWOODO", 70, 100, 115, 30, 65, 30, "Medium_Fast"),
    POLITOED(186, "POLITOED", 90, 75, 75, 90, 100, 70, "Medium_Slow"),

    HOPPIP(187, "HOPPIP", 35, 35, 40, 35, 55, 50, "Medium_Slow"),
    SKIPLOOM(188, "SKIPLOOM", 55, 45, 50, 45, 65, 80, "Medium_Slow"),
    JUMPLUFF(189, "JUMPLUFF", 75, 55, 70, 55, 85, 110, "Medium_Slow"),

    AIPOM(190, "AIPOM", 55, 70, 55, 40, 55, 85, "Fast"),
    SUNKERN(191, "SUNKERN", 30, 30, 30, 30, 30, 30, "Medium_Slow"),
    SUNFLORA(192, "SUNFLORA", 75, 75, 55, 105, 85, 30, "Medium_Slow"),

    YANMA(193, "YANMA", 65, 65, 45, 75, 45, 95, "Medium_Fast"),
    WOOPER(194, "WOOPER", 55, 45, 45, 25, 25, 15, "Medium_Fast"),
    QUAGSIRE(195, "QUAGSIRE", 95, 85, 85, 65, 65, 35, "Medium_Fast"),

    ESPEON(196, "ESPEON", 65, 65, 60, 130, 95, 110, "Medium_Fast"),
    UMBREON(197, "UMBREON", 95, 65, 110, 60, 130, 65, "Medium_Fast"),
    MURKROW(198, "MURKROW", 60, 85, 42, 85, 42, 91, "Medium_Slow"),

    SLOWKING(199, "SLOWKING", 95, 75, 80, 100, 80, 30, "Medium_Fast"),
    MISDREAVUS(200, "MISDREAVUS", 60, 60, 60, 85, 85, 85, "Fast"),
    UNOWN(201, "UNOWN", 48, 72, 48, 72, 48, 48, "Medium_Fast"),

    WOBBUFFET(202, "WOBBUFFET", 190, 33, 58, 33, 58, 33, "Medium_Fast"),
    GIRAFARIG(203, "GIRAFARIG", 70, 80, 65, 90, 65, 85, "Medium_Fast"),

    PINECO(204, "PINECO", 50, 65, 90, 35, 35, 15, "Medium_Fast"),
    FORRETRESS(205, "FORRETRESS", 75, 90, 140, 60, 60, 40, "Medium_Fast"),
    DUNSPARCE(206, "DUNSPARCE", 100, 70, 70, 65, 65, 45, "Medium_Fast"),

    GLIGAR(207, "GLIGAR", 65, 75, 105, 35, 65, 85, "Medium_Slow"),
    STEELIX(208, "STEELIX", 75, 85, 200, 55, 65, 30, "Medium_Fast"),

    SNUBBULL(209, "SNUBBULL", 60, 80, 50, 40, 40, 30, "Fast"),
    GRANBULL(210, "GRANBULL", 90, 120, 75, 60, 60, 45, "Fast"),

    QWILFISH(211, "QWILFISH", 65, 95, 75, 55, 55, 85, "Medium_Fast"),
    SCIZOR(212, "SCIZOR", 70, 130, 100, 55, 80, 65, "Medium_Fast"),
    SHUCKLE(213, "SHUCKLE", 20, 10, 230, 10, 230, 5, "Medium_Slow"),
    HERACROSS(214, "HERACROSS", 80, 125, 75, 40, 95, 85, "Slow"),

    SNEASEL(215, "SNEASEL", 55, 95, 55, 35, 75, 115, "Medium_Slow"),
    URSARING(216, "URSARING", 90, 130, 75, 75, 75, 55, "Medium_Fast"),

    SLUGMA(217, "SLUGMA", 40, 40, 40, 70, 40, 20, "Medium_Fast"),
    MAGCARGO(218, "MAGCARGO", 50, 50, 120, 80, 80, 30, "Medium_Fast"),

    SWINUB(219, "SWINUB", 50, 50, 40, 30, 30, 50, "Slow"),
    PILOSWINE(220, "PILOSWINE", 100, 100, 80, 60, 60, 50, "Slow"),

    CORSOLA(221, "CORSOLA", 55, 55, 85, 65, 85, 35, "Fast"),
    REMORAID(222, "REMORAID", 35, 65, 35, 65, 35, 65, "Medium_Fast"),
    OCTILLERY(223, "OCTILLERY", 75, 105, 75, 105, 75, 45, "Medium_Fast"),

    DELIBIRD(224, "DELIBIRD", 45, 55, 45, 65, 45, 75, "Fast"),
    MANTINE(225, "MANTINE", 65, 40, 70, 80, 140, 70, "Slow"),

    SKARMORY(226, "SKARMORY", 65, 80, 140, 40, 70, 70, "Slow"),

    HOUNDOUR(227, "HOUNDOUR", 45, 60, 30, 80, 50, 65, "Slow"),
    HOUNDOOM(228, "HOUNDOOM", 75, 90, 50, 110, 80, 95, "Slow"),

    KINGDRA(229, "KINGDRA", 75, 95, 95, 95, 95, 85, "Medium_Fast"),

    PHANPY(230, "PHANPY", 90, 60, 60, 40, 40, 40, "Medium_Fast"),
    DONPHAN(231, "DONPHAN", 90, 120, 120, 60, 60, 50, "Medium_Fast"),

    PORYGON2(232, "PORYGON2", 85, 80, 90, 105, 95, 60, "Medium_Fast"),

    STANTLER(233, "STANTLER", 73, 95, 62, 85, 65, 85, "Slow"),

    SMEARGLE(234, "SMEARGLE", 55, 20, 35, 20, 45, 75, "Fast"),

    TYROGUE(235, "TYROGUE", 35, 35, 35, 35, 35, 35, "Medium_Fast"),
    HITMONTOP(236, "HITMONTOP", 50, 95, 95, 35, 110, 70, "Medium_Fast"),

    SMOOCHUM(237, "SMOOCHUM", 45, 30, 15, 85, 65, 65, "Medium_Fast"),
    ELEKID(238, "ELEKID", 45, 63, 37, 65, 55, 95, "Medium_Fast"),
    MAGBY(239, "MAGBY", 45, 75, 37, 70, 55, 83, "Medium_Fast"),
    MILTANK(241, "MILTANK", 95, 80, 105, 40, 70, 100, "Slow"),

    BLISSEY(242, "BLISSEY", 255, 10, 10, 75, 135, 55, "Fast"),

    RAIKOU(243, "RAIKOU", 90, 85, 75, 115, 100, 115, "Slow"),
    ENTEI(244, "ENTEI", 115, 115, 85, 90, 75, 100, "Slow"),
    SUICUNE(245, "SUICUNE", 100, 75, 115, 90, 115, 85, "Slow"),

    LARVITAR(246, "LARVITAR", 50, 64, 50, 45, 50, 41, "Slow"),
    PUPITAR(247, "PUPITAR", 70, 84, 70, 65, 70, 51, "Slow"),
    TYRANITAR(248, "TYRANITAR", 100, 134, 110, 95, 100, 61, "Slow"),

    LUGIA(249, "LUGIA", 106, 90, 130, 90, 154, 110, "Slow"),
    HOOH(250, "HOOH", 106, 130, 90, 110, 154, 90, "Slow"),

    CELEBI(251, "CELEBI", 100, 100, 100, 100, 100, 100, "Medium_Slow");

    private final int id;
    private final String name;
    private final int hp;
    private final int attack;
    private final int defense;
    private final int spAttack;
    private final int spDefense;
    private final int speed;
    private final String growthRate;

    PokemonsEnum(int id,
                 String name,
                 int hp,
                 int attack,
                 int defense,
                 int spAttack,
                 int spDefense,
                 int speed,
                 String growthRate) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.growthRate = growthRate;
    }

    private static final PokemonsEnum[] LOOKUP = new PokemonsEnum[256];

    static {
        for (PokemonsEnum move : values()) {
            if (move.id >= 0 && move.id < LOOKUP.length) {
                LOOKUP[move.id] = move;
            }
        }
    }

    public static PokemonsEnum fromId(int id) {
        if (id < 0 || id >= LOOKUP.length) {
            return null;
        }
        return LOOKUP[id];
    }

}
