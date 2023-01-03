package TJCore.api.material.materials;

import static TJCore.api.TJOreDictionaryLoader.GENERATE_NANOFOIL;
import static TJCore.api.TJOreDictionaryLoader.GENERATE_NANOWIRE;
import static gregicality.science.api.unification.materials.GCYSMaterials.*;

import TJCore.api.material.materials.properties.RotationPipeProperties;
import TJCore.api.material.materials.properties.TJPropertyKey;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.*;
import org.apache.http.impl.conn.Wire;
import scala.sys.Prop;



import static TJCore.api.material.TJMaterials.*;


import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class TJFirstDegreeMaterials {

    /* for fluidpipeproperties, throughput is in ticks


    */


    public static void registerMaterials(){
        Birmabright = new Material.Builder(25000, "birmabright")
                .ingot().fluid()
                .color(1755371).iconSet(MaterialIconSet.DULL)
                .flags(SetMaterialFlags(STANDARDPLATE, STANDARDROD, STANDARDROTOR, STANDARDGEAR, STANDARDSPRING, STANDARDCASING))
                .components(Aluminium, 5, Magnesium, 1, Manganese, 1)
                .build();

        BT6 = new Material.Builder(25001, "bt_6")
                .ingot().fluid()
                .colorAverage().iconSet(MaterialIconSet.SHINY)
                .flags(SetMaterialFlags(STANDARDPLATE, STANDARDROD, STANDARDROTOR, STANDARDGEAR, STANDARDSPRING))
                .components(Iron, 3, Carbon, 1, Vanadium, 5, Titanium, 40, Aluminium, 6)
                .blastTemp(3400)
                .build();



        TriphenylPhosphine  = new Material.Builder(25002,"triphenylphosphine")
                .dust()
                .fluid()
                .colorAverage()
                .components(Phosphorus, 1, Carbon, 18, Hydrogen, 15)
                .build()
                .setFormula("P(C6H5)3", true);

        MolybdenumSulfide = new Material.Builder(25004, "molybdenumsulfide")
                .fluid()
                .colorAverage()
                .components(Molybdenum, 1, Sulfur, 2)
                .build()
                .setFormula("MoS2", true);

        PhenylmagnesiumBromide = new Material.Builder(25005,"phenylmagnesiumbromide")
                .fluid()
                .colorAverage()
                .components(Carbon, 6, Hydrogen, 5, Magnesium, 1, Bromine, 1)
                .build()
                .setFormula("C6H5MgBr", true);

        Bromobenzene = new Material.Builder(25006,"bromobenzene")
                .fluid()
                .colorAverage()
                .components(Carbon, 6, Hydrogen, 4, Bromine, 1)
                .build()
                .setFormula("C6H5Br",true);

        Draconium = new Material.Builder(25007, "draconium")
                .ingot().fluid()
                .color(0x573d85).iconSet(MaterialIconSet.DULL)
                .flags(SetMaterialFlags(STANDARDPLATE, STANDARDCASING))
                .build();

        SilicaCeramic = new Material.Builder(25008,"silicaceramic")
                .ingot()
                .blastTemp(1000)
                .color(0x8c7a50).iconSet(MaterialIconSet.SHINY)
                .flags(SetMaterialFlags(STANDARDPLATE,STANDARDROD,STANDARDFOIL,STANDARDROUND))
                .build();

        NickelPlatedTin = new Material.Builder(25009, "nickelplatedtin")
                .ingot()
                .color(0x8fb7c4).iconSet(MaterialIconSet.SHINY)
                .build();

        //TODO: CARBON make the formula for ladder poly-p-phenylene
        Ladder_Poly_P_Phenylene = new Material.Builder(25011, "polypphenylene")
                .fluid()
                .ingot()
                .color(0xbfb393)
                .flags(SetMaterialFlags(STANDARDWIREFINE,STANDARDFOIL))
                .build();

        HydrogenSilsesquioxane = new Material.Builder(25012, "hydrogensilsesquioxane")
                .fluid()
                .color(0x471525)
                .build()
                .setFormula("[HSiO3/2]", true);

        //TODO: CARBON make the formula for SU-8 Photoresist
        SU8_Photoresist = new Material.Builder(25013, "su_photoresist")
                .fluid()
                .color(0x0e242b)
                .build();
        Fiberglass = new Material.Builder(25014, "fiberglass")
                .ingot()
                .color(0x99c0cf)
                .build();

        LuminescentSiliconNanocrystals = new Material.Builder(25015,"luminescentsiliconnanocrystals")
                .dust()
                .color(0x363636)
                .build();

        SeleniumMonobromide = new Material.Builder(25016, "seleniummonobromide")
                .fluid()
                .color(0x472a1a)
                .build();

        DiamondCVDSolution = new Material.Builder(25017, "diamondcvdsolution")
                .fluid()
                .color(0x65b1fc)
                .build();

        Starlight = new Material.Builder(25019, "starlight")
                .fluid()
                .color(0xebfafc)
                .iconSet(MaterialIconSet.SHINY)
                .build();

        SilverLeadOxide = new Material.Builder(25020, "silverleadoxide")
                .dust()
                .colorAverage()
                .components(Silver, 1, Lead, 1, Oxygen, 1)
                .iconSet(MaterialIconSet.SHINY)
                .build();

        PalladiumChloride = new Material.Builder(25021, "palladiumchloride")
                .dust()
                .colorAverage()
                .components(Palladium, 1, Chlorine, 2)
                .build()
                .setFormula("PdCl2",true);

        TetrakisPDCatalyst = new Material.Builder(25022, "tretrakispdcatalyst")
                .dust()
                .color(0x9bd1e8)
                .iconSet(MaterialIconSet.SHINY)
                .build();

        HydraziniumChloride = new Material.Builder(25023, "hydraziniumchloride")
                .fluid()
                .colorAverage()
                .components(Nitrogen, 2, Hydrogen, 5, Chlorine, 1)
                .build()
                .setFormula("N2H4HCl", true);

        DibromoisophthalicAcid = new Material.Builder(25024, "dibromoisophthalicacid")
                .fluid()
                .colorAverage()
                .components(Carbon, 8, Hydrogen, 5, Bromine, 1, Oxygen, 4)
                .build()
                .setFormula("C8H5BrO4",true);

        Dibromoterephthaloyldichloride = new Material.Builder(25025, "dibromoterephthaloyldichloride")
                .fluid()
                .colorAverage()
                .components(Carbon, 8, Hydrogen, 2, Bromine, 2, Chlorine, 2, Oxygen, 2)
                .build()
                .setFormula("C8H2Br2Cl2O2",true);

        P1Solution = new Material.Builder(25026, "p_one_solution")
                .fluid()
                .color(0x6b0c05)
                .build();

        HafniumSilicate = new Material.Builder(25027, "hafnium_silicate")
                .dust()
                .colorAverage()
                .components(Hafnium, 1, Oxygen, 4, Silicon, 1)
                .build()
                .setFormula("HfO4Si", true);

        Cobalt60 = new Material.Builder(25028,"cobalt_sixty")
                .dust()
                .iconSet(MaterialIconSet.SHINY)
                .color(Cobalt.getMaterialRGB())
                .build()
                .setFormula("Co-60", false);

        FritSolderMix = new Material.Builder(25035,"glass_frit_solder_mixture")
                .dust()
                .colorAverage()
                .components(Glass, 36, Yttrium, 1, Copper, 3)
                .build()
                .setFormula("(SiO2)36Cu3Y1", true);

        SolderVPrep = new Material.Builder(25036, "solder_v_prep")
                .dust()
                .colorAverage()
                .components(Tin, 2, Gold, 1, Bismuth, 1)
                .build()
                .setFormula("Sn2AuBi",true);

        SolderVPrepClean = new Material.Builder(25037, "solder_v_prep_clean")
                .dust()
                .colorAverage()
                .components(SolderVPrep, 1)
                .build()
                .setFormula("Sn2AuBi", true);

        SolderVStruct = new Material.Builder(25038, "solder_v_prestructure")
                .dust()
                .colorAverage()
                .components(SolderVPrepClean, 9, Lanthanum, 1, Cerium, 1)
                .build()
                .setFormula("(Sn2AuBi)9LaCe",true);

        IndiumRadium = new Material.Builder(25039, "indium_radium")
                .dust()
                .colorAverage()
                .components(Indium, 6, Radium, 2)
                .build()
                .setFormula("In6Ra4", true);

        IndiumRadiumRadon = new Material.Builder(25040, "indium_radium_radon")
                .dust()
                .colorAverage()
                .components(Indium, 6, Radium, 2, Radon, 2)
                .build()
                .setFormula("In6Ra2Rn2",true);

        PilledFoilMix = new Material.Builder(25041, "pilled_foil_mix")
                .dust()
                .colorAverage()
                .components(Ruthenium, 2, Gold, 2, Copper, 2, Lutetium, 2)
                .build()
                .setFormula("Ru2Au2Cu2Lu2", true);

        IrradiatingMix = new Material.Builder(25042, "irradiating_mixture")
                .dust()
                .colorAverage()
                .components(IndiumRadiumRadon, 2, PilledFoilMix, 2, Cobalt60, 3)
                .build();

        SolderVIPrep = new Material.Builder(25043, "solder_vi_prep")
                .dust()
                .colorAverage()
                .components(IrradiatingMix, 8, Tritanium, 1, Moscovium, 1)
                .build();


        Silane = new Material.Builder(25045, "silane")
                .fluid()
                .colorAverage()
                .components(Silicon, 1, Hydrogen, 4)
                .build()
                .setFormula("SiH4", true);

        ArgonSilane = new Material.Builder(25044, "argon_silane")
                .fluid()
                .plasma()
                .components(Argon, 1, Silane, 1)
                .color(0x24BB18)
                .build();

        SolderingAlloyI = new Material.Builder(25029, "soldering_alloy_i")
                .fluid()
                .ingot()
                .colorAverage()
                .components(Lead, 6, Tin, 4)
                .build()
                .setFormula("Pb4Sn4", true);
        SolderingAlloyII = new Material.Builder(25030, "soldering_alloy_ii")
                .fluid()
                .ingot()
                .colorAverage()
                .components(Tin,6,Silver,3,Antimony,1)
                .build()
                .setFormula("Sn6Ag3Sb", true);
        SolderingAlloyIII = new Material.Builder(25031, "soldering_alloy_iii")
                .fluid()
                .ingot()
                .colorAverage()
                .components(FritSolderMix, 9, Zinc, 1)
                .build()
                .setFormula("((SiO2)36Cu3Y1)9Zn", true);
        SolderingAlloyIV = new Material.Builder(25032, "soldering_alloy_iv")
                .fluid()
                .ingot()
                .colorAverage()
                .components(IndiumGalliumPhosphide, 6, Bismuth, 2, Tellurium, 1, Platinum, 1)
                .build()
                .setFormula("", true);
        SolderingAlloyV = new Material.Builder(25033, "soldering_alloy_v")
                .fluid()
                .ingot()
                .colorAverage()
                .iconSet(MaterialIconSet.BRIGHT)
                .components(SolderVStruct, 11, SiliconCarbide, 3, NiobiumTitanium, 2)
                .build()
                .setFormula("((Sn2AuBi)9LaCe)11(SiC)3(Nb3Ti)2", true);
        SolderingAlloyVI = new Material.Builder(25034, "soldering_alloy_vi")
                .fluid()
                .ingot()
                .colorAverage()
                .iconSet(MaterialIconSet.SHINY)
                .components(SolderVIPrep, 1, ArgonSilane, 4)
                .build();

        CarbonNanotubePolymer = new Material.Builder(25046, "carbon_nanotube_polymer")
                .dust()
                .ingot()
                .color(0x0d0d0d)
                .iconSet(MaterialIconSet.SHINY)
                .components(CarbonNanotube, 5, Kevlar, 2)
                .build();

        DegenerateRhenium = new Material.Builder(25047, "degenerate_rhenium")
                .dust()
                .ingot()
                .color(0xc9c9c9)
                .iconSet(MaterialIconSet.METALLIC)
                .components(Rhenium, 1)
                .build();

        NihoniumTriiodide = new Material.Builder(25048, "nihonium_triiodide")
                .dust()
                .ingot()
                .color(0x5986a8)
                .iconSet(MaterialIconSet.SHINY)
                .components(Nihonium, 1, Iodine, 3)
                .build();

        SuperheavyL = new Material.Builder(25049, "superheavy_l")
                .dust()
                .ingot()
                .color(0x000a8f)
                .iconSet(MaterialIconSet.SHINY)
                .components(Nobelium, 1, Lawrencium, 1, Meitnerium, 1, Darmstadtium, 1, Roentgenium, 1, Copernicium, 1)
                .build();

        SuperheavyH = new Material.Builder(25050, "superheavy_h")
                .dust()
                .ingot()
                .colorAverage()
                .iconSet(MaterialIconSet.SHINY)
                .components(Nihonium, 1, Flerovium, 1, Moscovium, 1, Livermorium, 1, Tennessine, 1, Oganesson, 1)
                .build();

        Gluons = new Material.Builder(25051, "gluons")
                .fluid()
                .color(0xffffff)
                .build();

        LightQuarks = new Material.Builder(25052, "light_quarks")
                .fluid()
                .color(0x59ff7d)
                .build();

        HeavyQuarks = new Material.Builder(25053, "heavy_quarks")
                .fluid()
                .color(0x4a080b)
                .build();

        BismuthTelluride = new Material.Builder(25054, "bismuth_telluride")
                .ingot()
                .colorAverage()
                .components(Bismuth, 2, Tellurium, 3)
                .build();

        SynthDiamond = new Material.Builder(25055, "synthetic_diamond")
                .dust()
                .gem()
                .color(0x8fbaff)
                .components(Carbon, 8)
                .build();

        GraphenePQD = new Material.Builder(25056, "photoluminescent_graphene_quantum_dots")
                .dust()
                .color(0x616161)
                .iconSet(MaterialIconSet.SHINY)
                .components(Carbon, 6)
                .build();

        HeavyQuarkDegenerate = new Material.Builder(25057, "heavy_quark_degenerate_matter")
                .fluid()
                .ingot()
                .color(0x171717)
                .components(Gluons, 1, HeavyQuarks, 4, LightQuarks, 1)
                .build();

        BismuthIridiumOxide = new Material.Builder(25058, "bismuth_iridium_oxide")
                .ingot()
                .colorAverage()
                .components(Bismuth, 2, Iridium, 2, Oxygen, 7)
                .iconSet(MaterialIconSet.DULL)
                .build();

        IndiumFluoride = new Material.Builder(25059, "indium_fluoride")
                .fluid()
                .color(0x2d5c53)
                .components(Indium, 1, Fluorine, 3)
                .build();

        EnrichedNaqAlloy = new Material.Builder(25060, "enriched_naquadah_alloy")
                .ingot()
                .fluid()
                .colorAverage()
                .components(NaquadahEnriched, 16, Einsteinium, 4, Rhodium, 4, Technetium, 4, Astatine, 2, Erbium, 2)
                .blastTemp(9700)
                .iconSet(MaterialIconSet.SHINY)
                .build();

        SodiumPotassiumNiobate = new Material.Builder(25061, "sodium_potassium_niobate")
                .ingot()
                .colorAverage()
                .components(Sodium, 1, Potassium, 1, Niobium, 2, Oxygen, 6)
                .blastTemp(3600)
                .build();

        TriniumSteel = new Material.Builder(25062, "trinium_steel")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(10200)
                .colorAverage()
                .components(Trinium, 18, Tungsten, 6, Vanadium, 4, Chrome, 2, Tantalum, 1, Cobalt, 1)
                .build();

        LeadZirconateTitanate = new Material.Builder(25063, "lead_zirconate_titanate")
                .ingot()
                .iconSet(MaterialIconSet.DULL)
                .blastTemp(6100)
                .color(0x355232)
                .components(Lead, 12, Titanium, 1, Zirconium, 1, Oxygen, 16)
                .build();

        XenonFluorideSupercondiveMix = new Material.Builder(25064, "xenon_fluoride_mix")
                .fluid()
                .color(0x251a33)
                .build();


        XenonOxyTetraFluoride = new Material.Builder(25065, "xenon_oxytetrafluoride")
                .fluid()
                .colorAverage()
                .components(Xenon, 1, Oxygen, 1, Fluorine, 4)
                .build();

        XenonDioxide = new Material.Builder(25066, "xenon_dioxide")
                .fluid()
                .colorAverage()
                .components(Xenon, 1, Oxygen, 2)
                .build();

        XenonTetraFluoride = new Material.Builder(25067, "xenon_tetrafluoride")
                .fluid()
                .color(0x3d1e42)
                .components(Xenon, 1, Fluorine, 4)
                .build();
        XenonHexaFluoride = new Material.Builder(25068, "xenon_hexafluoride")
                .fluid()
                .color(0x8d5cff)
                .components(Xenon, 1, Fluorine, 6)
                .build();

        TerfenolD_H = new Material.Builder(25069, "terfenol_d_heavy")
                .ingot()
                .blastTemp(10200)
                .components(Osmium, 12, Iron, 6, Dysprosium, 2, Terbium, 1)
                .color(0x4d4d4d)
                .build();

        TerfenolD_L = new Material.Builder(25070, "terfenol_d_light")
                .ingot()
                .blastTemp(10200)
                .components(Lithium7, 12, Iron, 6, Dysprosium, 2, Terbium, 1)
                .color(0x9c9c9c)
                .build();

        SuspendedPGQD = new Material.Builder(25071, "suspended_pgqd")
                .fluid()
                .color(0x65ad95)
                .components(Krypton, 1, GraphenePQD, 1)
                .build();

        Leptons = new Material.Builder(25072, "leptons")
                .fluid()
                .color(0x5500ff)
                .build();

        NeonFluoride = new Material.Builder(25073, "neon_fluoride")
                .fluid()
                .colorAverage()
                .components(Neon, 1, Fluorine, 1)
                .build();

        ExcitedNeonFluoride = new Material.Builder(25074, "excited_neon_fluoride")
                .fluid()
                .colorAverage()
                .color(NeonFluoride.getMaterialRGB())
                .components(NeonFluoride, 1)
                .build();

        ArgonFluorine = new Material.Builder(25075, "argon_fluorine")
                .fluid()
                .color(0x00ff88)
                .components(Argon, 1, Fluorine, 1)
                .build();

        SilverGalliumSelenide = new Material.Builder(25076, "silver_gallium_selenide")
                .dust()
                .colorAverage()
                .components(Silver, 1, Gallium, 1, Selenium, 2)
                .build();

        BismuthPhosphomolybdate = new Material.Builder(25077, "bismuth_phosphomolybdate")
                .dust()
                .colorAverage()
                .components(Bismuth, 9, Phosphorus, 1, Molybdenum, 12, Oxygen, 52)
                .build();

        Acrylonitrile = new Material.Builder(25078, "acrylonitrile")
                .fluid()
                .color(0x565734)
                .components(Carbon, 3, Hydrogen, 3, Nitrogen, 1)
                .build()
                .setFormula("CH2CHCN", true);

        SodiumThiocyanate = new Material.Builder(25079, "sodium_thiocyanate")
                .dust()
                .colorAverage()
                .components(SodiumCyanide, 1, Sulfur, 1)
                .build();

        SodiumThiocyanatePolymerizationSolution = new Material.Builder(25080, "sodiumthiocyanatepolymerizationsolution")
                .fluid()
                .colorAverage()
                .components(Water, 1, SodiumThiocyanate, 1)
                .build();

        Polyacrylonitrile = new Material.Builder(25081, "polyacrylonitrile")
                .dust()
                .color(0x854218)
                .build();

        CFCoatingSolution = new Material.Builder(25082, "cf_coating_solution")
                .fluid()
                .colorAverage()
                .components(PolyvinylChloride, 1, Polyethylene, 1)
                .build();

        GalvanizedSteel = new Material.Builder(25083, "galvanized_steel")
                .ingot()
                .color(0xb5b5b5)
                .components(Iron, 9, Zinc, 1)
                .iconSet(MaterialIconSet.SHINY)
                .arcSmeltInto(Steel)
                .flags(DISABLE_DECOMPOSITION, NO_WORKING, NO_SMASHING, NO_SMELTING)
                .build();

        Manasteel = new Material.Builder(25084, "manasteel")
                .ingot()
                .color(0x4772ff)
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("Fe4Sx", true);

        ManasteelAlloy = new Material.Builder(25085, "manasteel_alloy")
                .ingot()
                .color(0x152352)
                .iconSet(MaterialIconSet.SHINY)
                .components(Naquadria, 1, Rhodium, 1, Palladium, 3, Manasteel, 4)
                .build();

        Polyetheretherketone = new Material.Builder(25086, "peek")
                .ingot()
                .fluid()
                .iconSet(MaterialIconSet.DULL)
                .color(0x2b2b2b)
                .build()
                .setFormula("C20H12O3", true);

        ProgrammableMatter = new Material.Builder(25087, "programmable_matter")
                .ingot()
                .fluid()
                .color(0x8196a3)
                .iconSet(MaterialIconSet.SHINY)
                .build()
                .setFormula("robots!", false);

        LutetiumTantalate = new Material.Builder(25088, "lutetium_tantalite")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .components(Lutetium, 1, Tantalum, 1, Oxygen, 4)
                .color(0xaccde6)
                .build();

        Iridrhodruthenium = new Material.Builder(25089, "iridrhodruthenium")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .components(Ruthenium, 8, Rhodium, 1, Iridium, 1)
                .colorAverage()
                .build();

        HEA_1 = new Material.Builder(25090, "high_entropy_alloy_1")
                .ingot()
                .iconSet(MaterialIconSet.METALLIC)
                .components(Chrome, 5, Niobium, 8, Silicon, 7, Titanium, 3, Zirconium, 5)
                .colorAverage()
                .build();

        HEA_2 = new Material.Builder(25091, "high_entropy_alloy_2")
                .ingot()
                .fluid()
                .iconSet(MaterialIconSet.METALLIC)
                .components(Zirconium, 6, Tungsten, 4, Vanadium, 5, Cobalt, 3, Manganese, 4)
                .colorAverage()
                .build();

        HEA_3 = new Material.Builder(25092, "high_entropy_alloy_3")
                .ingot()
                .fluid()
                .iconSet(MaterialIconSet.SHINY)
                .components(Aluminium, 5, Chrome, 5, Molybdenum, 7, Tantalum, 9, Titanium, 6, Zirconium, 4, Nitrogen, 21)
                .colorAverage()
                .build();

        HDCS_1 = new Material.Builder(25093, "high_durability_compound_steel_1")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .color(0x2e216e)
                .components(TungstenCarbide, 10, Americium, 4, ChromiumGermaniumTellurideMagnetic, 4, Vanadium, 3, Rhenium, 2, Lithium, 2)
                .build();

        HDCS_2 = new Material.Builder(25094, "high_durability_compound_steel_2")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .color(0x3d0b0e)
                .components(Adamantium, 8, Fermium, 4, EnrichedNaqAlloy, 4, Germanium, 3, BismuthChalcogenide, 3, Arsenic, 3, Beryllium, 2)
                .build();

        HDCS_3 = new Material.Builder(25095, "high_durability_compound_steel_3")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .color(0x1f0126)
                .components(Vibranium, 12, TriniumSteel, 8, Rutherfordium, 6, Promethium, 4, Scandium, 4, Hassium, 1)
                .build();

        Pikyonium = new Material.Builder(25096, "pikyonium")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .color(0x3770bf)
                .components(HSSE, 12, Seaborgium, 7, Lead, 5, Molybdenum, 5, Beryllium, 3, Gallium, 3, Iodine, 2, Mercury, 2)
                .build();

        HafniumCarbide = new Material.Builder(25097, "hafnium_carbide")
                .dust()
                .iconSet(MaterialIconSet.METALLIC)
                .colorAverage()
                .components(Hafnium, 1, Carbon, 1)
                .build();

        TantalumCarbide = new Material.Builder(25098, "tantalum_carbide")
                .dust()
                .iconSet(MaterialIconSet.METALLIC)
                .colorAverage()
                .components(Tantalum, 1, Carbon, 1)
                .build();

        SeaborgiumCarbide = new Material.Builder(25099, "seaborgium_carbide")
                .dust()
                .iconSet(MaterialIconSet.METALLIC)
                .colorAverage()
                .components(Seaborgium, 1, Carbon, 1)
                .build();

        TantalumHafniumSeaborgiumCarbide = new Material.Builder(25100, "tantalum_hafnium_seaborgium_carbide")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .colorAverage()
                .components(TantalumCarbide, 3, HafniumCarbide, 1, SeaborgiumCarbide, 3)
                .build();

        TantalumHafniumSeaborgiumCarboNitride = new Material.Builder(25101, "tantalum_hafnium_seaborgium_carbonitide")
                .ingot()
                .iconSet(MaterialIconSet.SHINY)
                .color(0x1c1c1c)
                .components(TantalumHafniumSeaborgiumCarbide, 1, Nitrogen, 1)
                .build()
                .setFormula("4TaHf3Sg10C7N");

        OganessonTetraTennesside = new Material.Builder(25102, "oganessontetratennesside")
                .ingot()
                .iconSet(MaterialIconSet.METALLIC)
                .colorAverage()
                .components(Oganesson, 1, Tennessine, 4)
                .build();
    }

    public static void registerProperties() {

        // Glass + Ceramics
        SilicaCeramic.addFlags(NO_WORKING, NO_SMELTING, NO_SMASHING, GENERATE_RING, GENERATE_FOIL);
        Fiberglass.addFlags(GENERATE_FINE_WIRE, GENERATE_FOIL);
        BismuthTelluride.addFlags(GENERATE_FOIL);
        BismuthIridiumOxide.addFlags(NO_WORKING, NO_SMASHING, GENERATE_PLATE);
        SodiumPotassiumNiobate.addFlags(GENERATE_FOIL, GENERATE_ROUND);
        LeadZirconateTitanate.addFlags(GENERATE_PLATE, GENERATE_RING);

        // Polymers
        Polybenzimidazole.addFlags(GENERATE_FINE_WIRE);
        PolyphenyleneSulfide.addFlags(GENERATE_FINE_WIRE);
        Polytetrafluoroethylene.addFlags(GENERATE_FINE_WIRE, GENERATE_LONG_ROD);
        PolyvinylChloride.addFlags(GENERATE_FINE_WIRE);
        Polyethylene.addFlags(GENERATE_FINE_WIRE);
        ReinforcedEpoxyResin.addFlags(GENERATE_FOIL);
        PedotPSS.addFlags(GENERATE_FRAME);
        PedotTMA.addFlags(GENERATE_FINE_WIRE);
        CarbonNanotubePolymer.addFlags(GENERATE_FRAME, GENERATE_PLATE, NO_WORKING, NO_SMASHING, NO_SMELTING);
        PolyvinylButyral.addFlags(GENERATE_FOIL, GENERATE_RING);
        Epoxy.addFlags(GENERATE_FOIL);

        // Metals
        NickelPlatedTin.addFlags(GENERATE_FOIL);
        Silicon.addFlags(GENERATE_ROD);
        Thulium.addFlags(GENERATE_FOIL, GENERATE_ROD);
        Chrome.addFlags(GENERATE_FOIL);
        Brass.addFlags(GENERATE_FOIL, GENERATE_LONG_ROD);
        Terbium.addFlags(GENERATE_ROD, GENERATE_NANOFOIL);
        Steel.addFlags(GENERATE_ROUND);
        HSSG.addFlags(GENERATE_ROUND);
        Pikyonium.addFlags(GENERATE_FINE_WIRE);
        OganessonTetraTennesside.addFlags(GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_SPRING);
        GalvanizedSteel.addFlags(GENERATE_ROUND, GENERATE_FRAME, GENERATE_ROTOR, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_PLATE, GENERATE_SPRING_SMALL, GENERATE_SPRING, GENERATE_RING);
        VanadiumGallium.addFlags(GENERATE_FINE_WIRE);
        TinAlloy.addFlags(GENERATE_FINE_WIRE);
        Dysprosium.addFlags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_FRAME);
        Vibranium.addFlags(GENERATE_ROUND, GENERATE_FRAME, GENERATE_BOLT_SCREW, GENERATE_NANOFOIL, GENERATE_ROTOR);
        RhodiumPlatedPalladium.addFlags(GENERATE_FRAME);
        Duranium.addFlags(GENERATE_FRAME, GENERATE_NANOFOIL);
        NihoniumTriiodide.addFlags(GENERATE_FINE_WIRE, GENERATE_RING);
        Nihonium.addFlags(GENERATE_PLATE, GENERATE_NANOWIRE);
        SuperheavyH.addFlags(GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW);
        SuperheavyL.addFlags(GENERATE_LONG_ROD, GENERATE_FRAME, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING, GENERATE_BOLT_SCREW);
        Neutronium.addFlags(GENERATE_ROTOR, GENERATE_FINE_WIRE, GENERATE_FOIL, GENERATE_ROUND);
        HeavyQuarkDegenerate.addFlags(GENERATE_ROUND, GENERATE_ROTOR, GENERATE_FRAME, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_GEAR, GENERATE_LONG_ROD, GENERATE_NANOWIRE, DISABLE_DECOMPOSITION, NO_SMELTING, NO_SMASHING, NO_WORKING);
        Taranium.addFlags(GENERATE_BOLT_SCREW, GENERATE_LONG_ROD, GENERATE_SPRING_SMALL, GENERATE_NANOWIRE);
        TriniumSteel.addFlags(GENERATE_ROUND, GENERATE_FRAME, GENERATE_SPRING, GENERATE_PLATE, GENERATE_BOLT_SCREW);
        Fermium.addFlags(GENERATE_FOIL, GENERATE_SPRING_SMALL, GENERATE_NANOWIRE);
        Dubnium.addFlags(GENERATE_FOIL, GENERATE_ROUND, GENERATE_FINE_WIRE, GENERATE_NANOWIRE);
        TerfenolD_L.addFlags(GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING, GENERATE_SPRING, GENERATE_BOLT_SCREW, GENERATE_ROTOR);
        TerfenolD_H.addFlags(GENERATE_FOIL, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_DENSE);
        EnrichedNaqAlloy.addFlags(GENERATE_LONG_ROD, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_ROUND, GENERATE_FRAME);
        PolystyreneSulfonate.addFlags(GENERATE_FOIL);
        Nichrome.addFlags(GENERATE_FINE_WIRE, GENERATE_RING);
        PlutoniumPhosphide.addFlags(GENERATE_NANOFOIL);
        ZBLANGlass.addFlags(GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW);
        ErbiumDopedZBLANGlass.addFlags(GENERATE_FOIL, GENERATE_FINE_WIRE);
        PraseodymiumDopedZBLANGlass.addFlags(GENERATE_FOIL, GENERATE_FINE_WIRE);
        Cobalt.addFlags(GENERATE_FINE_WIRE);
        Carbon.addFlags(GENERATE_FINE_WIRE);
        Osmium.addFlags(GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_SPRING_SMALL);
        NeodymiumMagnetic.addFlags(GENERATE_FOIL, GENERATE_RING);
        Rutherfordium.addFlags(GENERATE_FOIL);
        Aluminium.addFlags(GENERATE_ROTOR);
        Draconium.addFlags(GENERATE_ROTOR);
        LutetiumTantalate.addFlags(GENERATE_PLATE, GENERATE_FRAME, GENERATE_RING, GENERATE_ROD);
        Iridrhodruthenium.addFlags(GENERATE_PLATE, GENERATE_FRAME, GENERATE_RING, GENERATE_ROD);
        HEA_1.addFlags(GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_DENSE, GENERATE_ROUND, GENERATE_LONG_ROD);
        HEA_2.addFlags(GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_DENSE, GENERATE_ROUND, GENERATE_LONG_ROD);
        HEA_3.addFlags(GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_DENSE, GENERATE_ROUND, GENERATE_LONG_ROD);
        HDCS_1.addFlags(GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_DENSE, GENERATE_ROUND, GENERATE_SMALL_GEAR, GENERATE_GEAR, GENERATE_ROD, GENERATE_FRAME, GENERATE_LONG_ROD);
        HDCS_2.addFlags(GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_DENSE, GENERATE_ROUND, GENERATE_SMALL_GEAR, GENERATE_GEAR, GENERATE_ROD, GENERATE_FRAME, GENERATE_LONG_ROD);
        HDCS_3.addFlags(GENERATE_BOLT_SCREW, GENERATE_RING, GENERATE_DENSE, GENERATE_ROUND, GENERATE_SMALL_GEAR, GENERATE_GEAR, GENERATE_ROD, GENERATE_FRAME, GENERATE_LONG_ROD);
        Seaborgium.addFlags(GENERATE_BOLT_SCREW, GENERATE_PLATE, GENERATE_NANOFOIL, GENERATE_FOIL);
        Bohrium.addFlags(GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FOIL, GENERATE_FRAME, GENERATE_DENSE);

        //Properties
        Thulium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Terbium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Bohrium.setProperty(PropertyKey.INGOT, new IngotProperty());
        HeavyQuarkDegenerate.setProperty(PropertyKey.ITEM_PIPE, new ItemPipeProperties());
        Rutherfordium.setProperty(PropertyKey.ITEM_PIPE, new ItemPipeProperties());
        Oganesson.setProperty(PropertyKey.INGOT, new IngotProperty());
        Tennessine.setProperty(PropertyKey.INGOT, new IngotProperty());
        WireProperties OgTs4 = new WireProperties(536870912, 2, 256);
        OganessonTetraTennesside.setProperty(PropertyKey.WIRE, OgTs4);
        Livermorium.setProperty(PropertyKey.FLUID, new FluidProperty());
        Livermorium.setProperty(PropertyKey.INGOT, new IngotProperty());
        PlasmaProperty HQDPlasmaProp = new PlasmaProperty();
        HeavyQuarkDegenerate.setProperty(PropertyKey.PLASMA, HQDPlasmaProp);
        IngotProperty TaraniumIngotProp = new IngotProperty();
        WireProperties TaraniumWireProp = new WireProperties(134217728, 2, 64);
        Taranium.setProperty(PropertyKey.INGOT, TaraniumIngotProp);
        Taranium.setProperty(PropertyKey.WIRE, TaraniumWireProp);
        WireProperties NihoniumTriiodideProp = new WireProperties(33554432, 2, 64);
        NihoniumTriiodide.setProperty(PropertyKey.WIRE, NihoniumTriiodideProp);
        WireProperties SuperheavyHProp = new WireProperties(536870912, 4, 128);
        SuperheavyH.setProperty(PropertyKey.WIRE, SuperheavyHProp);
        WireProperties NeutroniumProp = new WireProperties(2147483647, 4,0);
        Neutronium.setProperty(PropertyKey.WIRE, NeutroniumProp);
        FluidPipeProperties bt6FluidPipeProps = new FluidPipeProperties(1500, 36, false, true, false, false);
        BT6.setProperty(PropertyKey.FLUID_PIPE, bt6FluidPipeProps);
        WireProperties BlueSteelWireProps = new WireProperties(512, 2, 6);
        BlueSteel.setProperty(PropertyKey.WIRE, BlueSteelWireProps);
        FluidPipeProperties bluesteelFluidPipeProps = new FluidPipeProperties(1500, 30, false, true, false, false);
        BlueSteel.setProperty(PropertyKey.FLUID_PIPE, bluesteelFluidPipeProps);
        WireProperties EnrichedNaqAlloyProps = new WireProperties(2097152, 2, 48);
        EnrichedNaqAlloy.setProperty(PropertyKey.WIRE, EnrichedNaqAlloyProps);
        Dysprosium.setProperty(PropertyKey.FLUID, new FluidProperty());
        FluidPipeProperties birmabrightFluidPipeProps = new FluidPipeProperties(1500, 24, false, true, false, false);
        Birmabright.setProperty(PropertyKey.FLUID_PIPE, birmabrightFluidPipeProps);
        FluidPipeProperties silicaCeramicFluidPipeProps = new FluidPipeProperties(350, 18, false, false, false, false);
        SilicaCeramic.setProperty(PropertyKey.FLUID_PIPE, silicaCeramicFluidPipeProps);
        FluidPipeProperties PZTPipeProps = new FluidPipeProperties(3200, 46,false,true,true,false);
        LeadZirconateTitanate.setProperty(PropertyKey.FLUID_PIPE, PZTPipeProps);
        Fermium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Dubnium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Einsteinium.setProperty(PropertyKey.DUST, new DustProperty());
        Technetium.setProperty(PropertyKey.DUST, new DustProperty());
        Actinium.setProperty(PropertyKey.DUST, new DustProperty());
        Astatine.setProperty(PropertyKey.DUST, new DustProperty());
        Carbon.setProperty(PropertyKey.INGOT, new IngotProperty());
        Seaborgium.setProperty(PropertyKey.INGOT, new IngotProperty());
        FluidPipeProperties TaHfSgCNPipeProps = new FluidPipeProperties(3950, 45,true,true, true, true);
        TantalumHafniumSeaborgiumCarboNitride.setProperty(PropertyKey.FLUID_PIPE, TaHfSgCNPipeProps);
        WireProperties PikyoniumWireProps = new WireProperties(2097152, 2,12);
        Pikyonium.setProperty(PropertyKey.WIRE, PikyoniumWireProps);

        FluidPipeProperties HDCS1PipeProps = new FluidPipeProperties(2100, 85,true,true, true, false);
        FluidPipeProperties HDCS2PipeProps = new FluidPipeProperties(2540, 115,true,true, true, false);
        FluidPipeProperties HDCS3PipeProps = new FluidPipeProperties(3280, 145,true,true, true, false);
        HDCS_1.setProperty(PropertyKey.FLUID_PIPE, HDCS1PipeProps);
        HDCS_2.setProperty(PropertyKey.FLUID_PIPE, HDCS2PipeProps);
        HDCS_3.setProperty(PropertyKey.FLUID_PIPE, HDCS3PipeProps);
        // becuase of techs foolishness and thinking graphenes are a foile
        Graphene.addFlags(GENERATE_FOIL);

        Steel.setProperty(TJPropertyKey.ROTATION_PIPE, new RotationPipeProperties(100.0f));

    }

}
