package com.marine.service;

import com.marine.entity.*;

import java.util.List;

public interface EfficiencyService {

    /**
     * 获取空调机组（AHU）能效列表
     *
     * @return 空调机组能效数据列表
     */
    List<AhuEfficiency> getAhuList();

    /**
     * 获取压载水处理系统能效列表
     *
     * @return 压载水处理系统能效数据列表
     */
    List<BallastWaterTreatmentEfficiency> getBallastWaterTreatmentList();

    /**
     * 获取制冷机能效列表
     *
     * @return 制冷机能效数据列表
     */
    List<ChillerEfficiency> getChillerList();

    /**
     * 获取二氧化碳捕获系统能效列表
     *
     * @return 二氧化碳捕获系统能效数据列表
     */
    List<Co2CaptureEfficiency> getCo2CaptureList();

    /**
     * 获取起重机能效列表
     *
     * @return 起重机能效数据列表
     */
    List<CraneEfficiency> getCraneList();

    /**
     * 获取分油机能效列表
     *
     * @return 分油机能效数据列表
     */
    List<DiscSeparatorEfficiency> getDiscSeparatorList();

    /**
     * 获取发动机能效列表
     *
     * @param engineType 发动机类型
     * @return 发动机能效数据列表
     */
    List<EngineEfficiency> getEngineList(String engineType);

    /**
     * 获取发电机能效列表
     *
     * @param generatorType 发电机类型
     * @return 发电机能效数据列表
     */
    List<GeneratorEfficiency> getGeneratorList(String generatorType);

    /**
     * 获取焚烧炉能效列表
     *
     * @param incineratorType 焚烧炉类型
     * @return 焚烧炉能效数据列表
     */
    List<IncineratorEfficiency> getIncineratorList(String incineratorType);

    /**
     * 获取惰性气体系统能效列表
     *
     * @return 惰性气体系统能效数据列表
     */
    List<InertGasEfficiency> getInertGasList();

    /**
     * 获取蒸汽轮机能效列表
     *
     * @return 蒸汽轮机能效数据列表
     */
    List<SteamTurbineEfficiency> getSteamTurbineList();

    /**
     * 获取废热有机朗肯循环系统能效列表
     *
     * @return 废热有机朗肯循环系统能效数据列表
     */
    List<WasteHeatOrganicRankineEfficiency> getOrganicRankineList();

    /**
     * 获取锚机能效列表
     *
     * @param windlassType 锚机类型
     * @return 锚机能效数据列表
     */
    List<WindlassEfficiency> getWindlassList(String windlassType);

    /**
     * 获取脱硝设备能效列表
     *
     * @return 脱硝设备能效数据列表
     */
    List<DenitrificationEfficiency> getDenitrificationList();

    /**
     * 获取脱硫设备能效列表
     *
     * @return 脱硫设备能效数据列表
     */
    List<DesulfurizationEfficiency> getDesulfurizationList();
}
