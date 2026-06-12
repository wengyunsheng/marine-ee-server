package com.marine.controller;

import com.marine.entity.*;
import com.marine.entity.vo.ResultVO;
import com.marine.service.EfficiencyService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/efficiency")
public class EfficiencyController {

    private final EfficiencyService efficiencyService;

    /**
     * 查询船用发动机能效等级列表
     *
     * @param engineType 发动机类型
     * @return 能效等级列表
     */
    @GetMapping("/engine/list")
    public ResultVO<List<EngineEfficiency>> getEngineList(@RequestParam(required = false) String engineType) {
        try {
            List<EngineEfficiency> list = efficiencyService.getEngineList(engineType);
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询有机朗肯循环发电装置能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/organic-rankine/list")
    public ResultVO<List<WasteHeatOrganicRankineEfficiency>> getOrganicRankineList() {
        try {
            List<WasteHeatOrganicRankineEfficiency> list = efficiencyService.getOrganicRankineList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询蒸汽透平发电装置能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/steam-turbine/list")
    public ResultVO<List<SteamTurbineEfficiency>> getSteamTurbineList() {
        try {
            List<SteamTurbineEfficiency> list = efficiencyService.getSteamTurbineList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询焚烧炉能效等级列表
     *
     * @param incineratorType 焚烧炉类型
     * @return 能效等级列表
     */
    @GetMapping("/incinerator/list")
    public ResultVO<List<IncineratorEfficiency>> getIncineratorList(@RequestParam(required = false) String incineratorType) {
        try {
            List<IncineratorEfficiency> list = efficiencyService.getIncineratorList(incineratorType);
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询碟式分离机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/disc-separator/list")
    public ResultVO<List<DiscSeparatorEfficiency>> getDiscSeparatorList() {
        try {
            List<DiscSeparatorEfficiency> list = efficiencyService.getDiscSeparatorList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询压载水处理设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/ballast-water-treatment/list")
    public ResultVO<List<BallastWaterTreatmentEfficiency>> getBallastWaterTreatmentList() {
        try {
            List<BallastWaterTreatmentEfficiency> list = efficiencyService.getBallastWaterTreatmentList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询锚绞机能效等级列表
     *
     * @param windlassType 锚绞机类型
     * @return 能效等级列表
     */
    @GetMapping("/windlass/list")
    public ResultVO<List<WindlassEfficiency>> getWindlassList(@RequestParam(required = false) String windlassType) {
        try {
            List<WindlassEfficiency> list = efficiencyService.getWindlassList(windlassType);
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询吊机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/crane/list")
    public ResultVO<List<CraneEfficiency>> getCraneList() {
        try {
            List<CraneEfficiency> list = efficiencyService.getCraneList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询发电机能效等级列表
     *
     * @param generatorType 发电机类型
     * @return 能效等级列表
     */
    @GetMapping("/generator/list")
    public ResultVO<List<GeneratorEfficiency>> getGeneratorList(@RequestParam(required = false) String generatorType) {
        try {
            List<GeneratorEfficiency> list = efficiencyService.getGeneratorList(generatorType);
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询组合式空调机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/ahu/list")
    public ResultVO<List<AhuEfficiency>> getAhuList() {
        try {
            List<AhuEfficiency> list = efficiencyService.getAhuList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询冷水机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/chiller/list")
    public ResultVO<List<ChillerEfficiency>> getChillerList() {
        try {
            List<ChillerEfficiency> list = efficiencyService.getChillerList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询惰性气体系统能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/inert-gas/list")
    public ResultVO<List<InertGasEfficiency>> getInertGasList() {
        try {
            List<InertGasEfficiency> list = efficiencyService.getInertGasList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询二氧化碳捕集设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/co2-capture/list")
    public ResultVO<List<Co2CaptureEfficiency>> getCo2CaptureList() {
        try {
            List<Co2CaptureEfficiency> list = efficiencyService.getCo2CaptureList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询脱硝设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/denitrification/list")
    public ResultVO<List<DenitrificationEfficiency>> getDenitrificationList() {
        try {
            List<DenitrificationEfficiency> list = efficiencyService.getDenitrificationList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询脱硫设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/desulfurization/list")
    public ResultVO<List<DesulfurizationEfficiency>> getDesulfurizationList() {
        try {
            List<DesulfurizationEfficiency> list = efficiencyService.getDesulfurizationList();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }
}
