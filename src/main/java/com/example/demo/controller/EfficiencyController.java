package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 能效等级控制器
 * 提供能效等级和能效基值查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/efficiency")
public class EfficiencyController {

    @Autowired
    private EngineEfficiencyService engineEfficiencyService;

    @Autowired
    private WasteHeatOrganicRankineEfficiencyService organicRankineEfficiencyService;

    @Autowired
    private SteamTurbineEfficiencyService steamTurbineEfficiencyService;

    @Autowired
    private IncineratorEfficiencyService incineratorEfficiencyService;

    @Autowired
    private DiscSeparatorEfficiencyService discSeparatorEfficiencyService;

    @Autowired
    private BallastWaterTreatmentEfficiencyService ballastWaterTreatmentEfficiencyService;

    @Autowired
    private WindlassEfficiencyService windlassEfficiencyService;

    @Autowired
    private CraneEfficiencyService craneEfficiencyService;

    @Autowired
    private GeneratorEfficiencyService generatorEfficiencyService;

    @Autowired
    private AhuEfficiencyService ahuEfficiencyService;

    @Autowired
    private ChillerEfficiencyService chillerEfficiencyService;

    @Autowired
    private InertGasEfficiencyService inertGasEfficiencyService;

    @Autowired
    private Co2CaptureEfficiencyService co2CaptureEfficiencyService;

    /**
     * 查询船用发动机能效等级列表
     * 支持按发动机类型筛选
     *
     * @param engineType 发动机类型
     * @return 能效等级列表
     */
    @GetMapping("/engine/list")
    public Result<List<EngineEfficiency>> getEngineList(@RequestParam(required = false) String engineType) {
        List<EngineEfficiency> list = engineEfficiencyService.getList(engineType);
        return Result.success(list, list.size());
    }

    /**
     * 查询有机朗肯循环发电装置能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/organic-rankine/list")
    public Result<List<WasteHeatOrganicRankineEfficiency>> getOrganicRankineList() {
        List<WasteHeatOrganicRankineEfficiency> list = organicRankineEfficiencyService.getList();
        return Result.success(list, list.size());
    }

    /**
     * 查询蒸汽透平发电装置能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/steam-turbine/list")
    public Result<List<SteamTurbineEfficiency>> getSteamTurbineList() {
        List<SteamTurbineEfficiency> list = steamTurbineEfficiencyService.getList();
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
        List<IncineratorEfficiency> list = incineratorEfficiencyService.getList(incineratorType);
        return Result.success(list, list.size());
    }

    /**
     * 查询碟式分离机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/disc-separator/list")
    public Result<List<DiscSeparatorEfficiency>> getDiscSeparatorList() {
        List<DiscSeparatorEfficiency> list = discSeparatorEfficiencyService.getList();
        return Result.success(list, list.size());
    }

    /**
     * 查询压载水处理设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/ballast-water-treatment/list")
    public Result<List<BallastWaterTreatmentEfficiency>> getBallastWaterTreatmentList() {
        List<BallastWaterTreatmentEfficiency> list = ballastWaterTreatmentEfficiencyService.getList();
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
        List<WindlassEfficiency> list = windlassEfficiencyService.getList(windlassType);
        return Result.success(list, list.size());
    }

    /**
     * 查询吊机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/crane/list")
    public Result<List<CraneEfficiency>> getCraneList() {
        List<CraneEfficiency> list = craneEfficiencyService.getList();
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
        List<GeneratorEfficiency> list = generatorEfficiencyService.getList(generatorType);
        return Result.success(list, list.size());
    }

    /**
     * 查询组合式空调机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/ahu/list")
    public Result<List<AhuEfficiency>> getAhuList() {
        List<AhuEfficiency> list = ahuEfficiencyService.getList();
        return Result.success(list, list.size());
    }

    /**
     * 查询冷水机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/chiller/list")
    public Result<List<ChillerEfficiency>> getChillerList() {
        List<ChillerEfficiency> list = chillerEfficiencyService.getList();
        return Result.success(list, list.size());
    }

    /**
     * 查询惰性气体系统能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/inert-gas/list")
    public Result<List<InertGasEfficiency>> getInertGasList() {
        List<InertGasEfficiency> list = inertGasEfficiencyService.getList();
        return Result.success(list, list.size());
    }

    /**
     * 查询二氧化碳捕集设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/co2-capture/list")
    public Result<List<Co2CaptureEfficiency>> getCo2CaptureList() {
        List<Co2CaptureEfficiency> list = co2CaptureEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
