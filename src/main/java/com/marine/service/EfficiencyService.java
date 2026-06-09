package com.marine.service;

import com.marine.entity.*;

import java.util.List;

public interface EfficiencyService {

    List<AhuEfficiency> getAhuList();

    List<BallastWaterTreatmentEfficiency> getBallastWaterTreatmentList();

    List<ChillerEfficiency> getChillerList();

    List<Co2CaptureEfficiency> getCo2CaptureList();

    List<CraneEfficiency> getCraneList();

    List<DiscSeparatorEfficiency> getDiscSeparatorList();

    List<EngineEfficiency> getEngineList(String engineType);

    List<GeneratorEfficiency> getGeneratorList(String generatorType);

    List<IncineratorEfficiency> getIncineratorList(String incineratorType);

    List<InertGasEfficiency> getInertGasList();

    List<SteamTurbineEfficiency> getSteamTurbineList();

    List<WasteHeatOrganicRankineEfficiency> getOrganicRankineList();

    List<WindlassEfficiency> getWindlassList(String windlassType);
}
