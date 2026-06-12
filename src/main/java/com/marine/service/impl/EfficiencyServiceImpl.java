package com.marine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.marine.entity.*;
import com.marine.mapper.*;
import com.marine.service.EfficiencyService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EfficiencyServiceImpl implements EfficiencyService {

    private final AhuEfficiencyMapper ahuEfficiencyMapper;

    private final BallastWaterTreatmentEfficiencyMapper ballastWaterTreatmentEfficiencyMapper;

    private final ChillerEfficiencyMapper chillerEfficiencyMapper;

    private final Co2CaptureEfficiencyMapper co2CaptureEfficiencyMapper;

    private final CraneEfficiencyMapper craneEfficiencyMapper;

    private final DiscSeparatorEfficiencyMapper discSeparatorEfficiencyMapper;

    private final EngineEfficiencyMapper engineEfficiencyMapper;

    private final GeneratorEfficiencyMapper generatorEfficiencyMapper;

    private final IncineratorEfficiencyMapper incineratorEfficiencyMapper;

    private final InertGasEfficiencyMapper inertGasEfficiencyMapper;

    private final SteamTurbineEfficiencyMapper steamTurbineEfficiencyMapper;

    private final WasteHeatOrganicRankineEfficiencyMapper wasteHeatOrganicRankineEfficiencyMapper;

    private final WindlassEfficiencyMapper windlassEfficiencyMapper;

    private final DenitrificationEfficiencyMapper denitrificationEfficiencyMapper;

    private final DesulfurizationEfficiencyMapper desulfurizationEfficiencyMapper;

    @Override
    public List<AhuEfficiency> getAhuList() {
        LambdaQueryWrapper<AhuEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AhuEfficiency::getIsDeleted, 0)
                .orderByAsc(AhuEfficiency::getAirFlowMin)
                .orderByAsc(AhuEfficiency::getAirFlowMax)
                .orderByAsc(AhuEfficiency::getStaticPressure)
                .orderByAsc(AhuEfficiency::getEfficiencyLevel);
        return ahuEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<BallastWaterTreatmentEfficiency> getBallastWaterTreatmentList() {
        LambdaQueryWrapper<BallastWaterTreatmentEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BallastWaterTreatmentEfficiency::getIsDeleted, 0)
                .orderByAsc(BallastWaterTreatmentEfficiency::getEfficiencyLevel);
        return ballastWaterTreatmentEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<ChillerEfficiency> getChillerList() {
        LambdaQueryWrapper<ChillerEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChillerEfficiency::getIsDeleted, 0)
                .orderByAsc(ChillerEfficiency::getEvaluationType)
                .orderByAsc(ChillerEfficiency::getCoolingCapacityMin)
                .orderByAsc(ChillerEfficiency::getCoolingCapacityMax)
                .orderByAsc(ChillerEfficiency::getEfficiencyLevel);
        return chillerEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<Co2CaptureEfficiency> getCo2CaptureList() {
        LambdaQueryWrapper<Co2CaptureEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Co2CaptureEfficiency::getIsDeleted, 0)
                .orderByAsc(Co2CaptureEfficiency::getEfficiencyLevel);
        return co2CaptureEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<CraneEfficiency> getCraneList() {
        LambdaQueryWrapper<CraneEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CraneEfficiency::getIsDeleted, 0)
                .orderByAsc(CraneEfficiency::getId);
        return craneEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<DiscSeparatorEfficiency> getDiscSeparatorList() {
        LambdaQueryWrapper<DiscSeparatorEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DiscSeparatorEfficiency::getIsDeleted, 0)
                .orderByAsc(DiscSeparatorEfficiency::getEfficiencyLevel);
        return discSeparatorEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<EngineEfficiency> getEngineList(String engineType) {
        LambdaQueryWrapper<EngineEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EngineEfficiency::getIsDeleted, 0)
                .eq(StringUtils.isNotBlank(engineType), EngineEfficiency::getEngineType, engineType)
                .orderByAsc(EngineEfficiency::getEmissionLevel)
                .orderByAsc(EngineEfficiency::getPowerRangeMin)
                .orderByAsc(EngineEfficiency::getPowerRangeMax)
                .orderByAsc(EngineEfficiency::getEfficiencyLevel);
        return engineEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<GeneratorEfficiency> getGeneratorList(String generatorType) {
        LambdaQueryWrapper<GeneratorEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GeneratorEfficiency::getIsDeleted, 0)
                .eq(StringUtils.isNotBlank(generatorType), GeneratorEfficiency::getGeneratorType, generatorType)
                .orderByAsc(GeneratorEfficiency::getRatedCapacity)
                .orderByAsc(GeneratorEfficiency::getRatedPower)
                .orderByAsc(GeneratorEfficiency::getRotorPoles)
                .orderByAsc(GeneratorEfficiency::getEfficiencyLevel);
        return generatorEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<IncineratorEfficiency> getIncineratorList(String incineratorType) {
        LambdaQueryWrapper<IncineratorEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(IncineratorEfficiency::getIsDeleted, 0)
                .eq(StringUtils.isNotBlank(incineratorType), IncineratorEfficiency::getIncineratorType, incineratorType)
                .orderByAsc(IncineratorEfficiency::getEfficiencyLevel);
        return incineratorEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<InertGasEfficiency> getInertGasList() {
        LambdaQueryWrapper<InertGasEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InertGasEfficiency::getIsDeleted, 0)
                .orderByAsc(InertGasEfficiency::getEfficiencyLevel);
        return inertGasEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<SteamTurbineEfficiency> getSteamTurbineList() {
        LambdaQueryWrapper<SteamTurbineEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SteamTurbineEfficiency::getIsDeleted, 0)
                .orderByAsc(SteamTurbineEfficiency::getSteamPressureMin)
                .orderByAsc(SteamTurbineEfficiency::getSteamPressureMax)
                .orderByAsc(SteamTurbineEfficiency::getSteamType)
                .orderByAsc(SteamTurbineEfficiency::getEfficiencyLevel);
        return steamTurbineEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<WasteHeatOrganicRankineEfficiency> getOrganicRankineList() {
        LambdaQueryWrapper<WasteHeatOrganicRankineEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WasteHeatOrganicRankineEfficiency::getIsDeleted, 0)
                .orderByAsc(WasteHeatOrganicRankineEfficiency::getHeatSourceTempMin)
                .orderByAsc(WasteHeatOrganicRankineEfficiency::getHeatSourceTempMax)
                .orderByAsc(WasteHeatOrganicRankineEfficiency::getPowerOutputMin)
                .orderByAsc(WasteHeatOrganicRankineEfficiency::getPowerOutputMin)
                .orderByAsc(WasteHeatOrganicRankineEfficiency::getEfficiencyLevel);
        return wasteHeatOrganicRankineEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<WindlassEfficiency> getWindlassList(String windlassType) {
        LambdaQueryWrapper<WindlassEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WindlassEfficiency::getIsDeleted, 0)
                .eq(StringUtils.isNotBlank(windlassType), WindlassEfficiency::getWindlassType, windlassType)
                .orderByAsc(WindlassEfficiency::getEfficiencyLevel);
        return windlassEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<DenitrificationEfficiency> getDenitrificationList() {
        LambdaQueryWrapper<DenitrificationEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DenitrificationEfficiency::getIsDeleted, 0)
                .orderByAsc(DenitrificationEfficiency::getEmissionTier)
                .orderByAsc(DenitrificationEfficiency::getEfficiencyLevel);
        return denitrificationEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<DesulfurizationEfficiency> getDesulfurizationList() {
        LambdaQueryWrapper<DesulfurizationEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DesulfurizationEfficiency::getIsDeleted, 0)
                .orderByAsc(DesulfurizationEfficiency::getDeviceType)
                .orderByAsc(DesulfurizationEfficiency::getOperationMode)
                .orderByAsc(DesulfurizationEfficiency::getEfficiencyLevel);
        return desulfurizationEfficiencyMapper.selectList(wrapper);
    }
}
