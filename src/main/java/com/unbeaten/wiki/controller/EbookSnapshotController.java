package com.unbeaten.wiki.controller;


import com.sun.org.glassfish.external.statistics.Statistic;
import com.unbeaten.wiki.resp.CommonResp;
import com.unbeaten.wiki.resp.StatisticResp;
import com.unbeaten.wiki.service.impl.EbookSnapshotServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 电子书快照表 前端控制器
 * </p>
 *
 * @author unbeaten
 * @since 2021-07-04
 */
@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {
    @Resource
    private EbookSnapshotServiceImpl ebookSnapshotService;

    @GetMapping("/get-statistic")
    public CommonResp<List<StatisticResp>> getStatistic() {
        List<StatisticResp> statisticResp = ebookSnapshotService.getStatistic();
        CommonResp<List<StatisticResp>> commonResp = new CommonResp<>();
        commonResp.setContent(statisticResp);
        return commonResp;
    }

    @GetMapping("/get-30-statistic")
    public CommonResp<List<StatisticResp>> get30Statistic() {
        List<StatisticResp> statisticResp = ebookSnapshotService.get30Statistic();
        CommonResp<List<StatisticResp>> commonResp = new CommonResp<>();
        commonResp.setContent(statisticResp);
        return commonResp;
    }


}

