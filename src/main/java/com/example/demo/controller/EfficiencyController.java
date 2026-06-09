package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 能效等级控制器
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/efficiency")
public class EfficiencyController {

    private final EfficiencyService efficiencyService;

    /**
     * 查询船用发动机能效等级列表
     * 支持按发动机类型筛选
     *
     * @param engineType 发动机类型
     * @return 能效等级列表
     */
    @GetMapping("/engine/list")
    public Result<List<EngineEfficiency>> getEngineList(@RequestParam(required = false) String engineType) {
        List<EngineEfficiency> list = efficiencyService.getEngineList(engineType);
        return Result.success(list, list.size());
    }

    /**
     * 查询有机朗肯循环发电装置能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/organic-rankine/list")
    public Result<List<WasteHeatOrganicRankineEfficiency>> getOrganicRankineList() {
        List<WasteHeatOrganicRankineEfficiency> list = efficiencyService.getOrganicRankineList();
        return Result.success(list, list.size());
    }

    /**
     * 查询蒸汽透平发电装置能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/steam-turbine/list")
    public Result<List<SteamTurbineEfficiency>> getSteamTurbineList() {
        List<SteamTurbineEfficiency> list = efficiencyService.getSteamTurbineList();
        return Result.success(list, list.size());
    }

    /**
     * 查询焚烧炉能效等级列表
     *
     * @param incineratorType 焚烧炉类型
     * @return 能效等级列表
     */
    @GetMapping("/incinerator/list")
    public Result<List<IncineratorEfficiency>> getIncineratorList(@RequestParam(required = false) String incineratorType) {
        List<IncineratorEfficiency> list = efficiencyService.getIncineratorList(incineratorType);
        return Result.success(list, list.size());
    }

    /**
     * 查询碟式分离机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/disc-separator/list")
    public Result<List<DiscSeparatorEfficiency>> getDiscSeparatorList() {
        List<DiscSeparatorEfficiency> list = efficiencyService.getDiscSeparatorList();
        return Result.success(list, list.size());
    }

    /**
     * 查询压载水处理设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/ballast-water-treatment/list")
    public Result<List<BallastWaterTreatmentEfficiency>> getBallastWaterTreatmentList() {
        List<BallastWaterTreatmentEfficiency> list = efficiencyService.getBallastWaterTreatmentList();
        return Result.success(list, list.size());
    }

    /**
     * 查询锚绞机能效等级列表
     *
     * @param windlassType 锚绞机类型
     * @return 能效等级列表
     */
    @GetMapping("/windlass/list")
    public Result<List<WindlassEfficiency>> getWindlassList(@RequestParam(required = false) String windlassType) {
        List<WindlassEfficiency> list = efficiencyService.getWindlassList(windlassType);
        return Result.success(list, list.size());
    }

    /**
     * 查询吊机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/crane/list")
    public Result<List<CraneEfficiency>> getCraneList() {
        List<CraneEfficiency> list = efficiencyService.getCraneList();
        return Result.success(list, list.size());
    }

    /**
     * 查询发电机能效等级列表
     *
     * @param generatorType 发电机类型
     * @return 能效等级列表
     */
    @GetMapping("/generator/list")
    public Result<List<GeneratorEfficiency>> getGeneratorList(@RequestParam(required = false) String generatorType) {
        List<GeneratorEfficiency> list = efficiencyService.getGeneratorList(generatorType);
        return Result.success(list, list.size());
    }

    /**
     * 查询组合式空调机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/ahu/list")
    public Result<List<AhuEfficiency>> getAhuList() {
        List<AhuEfficiency> list = efficiencyService.getAhuList();
        return Result.success(list, list.size());
    }

    /**
     * 查询冷水机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/chiller/list")
    public Result<List<ChillerEfficiency>> getChillerList() {
        List<ChillerEfficiency> list = efficiencyService.getChillerList();
        return Result.success(list, list.size());
    }

    /**
     * 查询惰性气体系统能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/inert-gas/list")
    public Result<List<InertGasEfficiency>> getInertGasList() {
        List<InertGasEfficiency> list = efficiencyService.getInertGasList();
        return Result.success(list, list.size());
    }

    /**
     * 查询二氧化碳捕集设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/co2-capture/list")
    public Result<List<Co2CaptureEfficiency>> getCo2CaptureList() {
        List<Co2CaptureEfficiency> list = efficiencyService.getCo2CaptureList();
        return Result.success(list, list.size());
    }
}
