package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.service.EfficiencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EfficiencyServiceImpl implements EfficiencyService {

    private  final AhuEfficiencyMapper ahuEfficiencyMapper;

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

    @Override
    public List<AhuEfficiency> getAhuList() {
        LambdaQueryWrapper<AhuEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AhuEfficiency::getIsDeleted, 0)
                .orderByAsc(AhuEfficiency::getAirFlowMin, AhuEfficiency::getAirFlowMax, AhuEfficiency::getStaticPressure, AhuEfficiency::getEfficiencyLevel);
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
                .orderByAsc(ChillerEfficiency::getEvaluationType, ChillerEfficiency::getCoolingCapacityMin, ChillerEfficiency::getCoolingCapacityMax, ChillerEfficiency::getEfficiencyLevel);
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
                .orderByAsc(CraneEfficiency::getSort);
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
        wrapper.eq(EngineEfficiency::getIsDeleted, 0);

        if (engineType != null && !engineType.trim().isEmpty()) {
            wrapper.eq(EngineEfficiency::getEngineType, engineType);
        }

        wrapper.orderByAsc(EngineEfficiency::getEmissionLevel, EngineEfficiency::getPowerRangeMin, EngineEfficiency::getPowerRangeMax, EngineEfficiency::getEfficiencyLevel);
        return engineEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<GeneratorEfficiency> getGeneratorList(String generatorType) {
        LambdaQueryWrapper<GeneratorEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GeneratorEfficiency::getIsDeleted, 0);

        if (generatorType != null && !generatorType.trim().isEmpty()) {
            wrapper.eq(GeneratorEfficiency::getGeneratorType, generatorType);
        }

        wrapper.orderByAsc(GeneratorEfficiency::getRatedCapacity, GeneratorEfficiency::getRatedPower,
                GeneratorEfficiency::getRotorPoles, GeneratorEfficiency::getEfficiencyLevel);
        return generatorEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<IncineratorEfficiency> getIncineratorList(String incineratorType) {
        LambdaQueryWrapper<IncineratorEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(IncineratorEfficiency::getIsDeleted, 0);

        if (incineratorType != null && !incineratorType.trim().isEmpty()) {
            wrapper.eq(IncineratorEfficiency::getIncineratorType, incineratorType);
        }

        wrapper.orderByAsc(IncineratorEfficiency::getEfficiencyLevel);
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
                .orderByAsc(SteamTurbineEfficiency::getSteamPressureMin, SteamTurbineEfficiency::getSteamPressureMax,
                        SteamTurbineEfficiency::getSteamType, SteamTurbineEfficiency::getEfficiencyLevel);
        return steamTurbineEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<WasteHeatOrganicRankineEfficiency> getOrganicRankineList() {
        LambdaQueryWrapper<WasteHeatOrganicRankineEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WasteHeatOrganicRankineEfficiency::getIsDeleted, 0)
                .orderByAsc(WasteHeatOrganicRankineEfficiency::getHeatSourceTempMin, WasteHeatOrganicRankineEfficiency::getHeatSourceTempMax,
                        WasteHeatOrganicRankineEfficiency::getPowerOutputMin, WasteHeatOrganicRankineEfficiency::getPowerOutputMin,
                        WasteHeatOrganicRankineEfficiency::getEfficiencyLevel);
        return wasteHeatOrganicRankineEfficiencyMapper.selectList(wrapper);
    }

    @Override
    public List<WindlassEfficiency> getWindlassList(String windlassType) {
        LambdaQueryWrapper<WindlassEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WindlassEfficiency::getIsDeleted, 0);

        if (windlassType != null && !windlassType.trim().isEmpty()) {
            wrapper.eq(WindlassEfficiency::getWindlassType, windlassType);
        }

        wrapper.orderByAsc(WindlassEfficiency::getEfficiencyLevel);
        return windlassEfficiencyMapper.selectList(wrapper);
    }
}
