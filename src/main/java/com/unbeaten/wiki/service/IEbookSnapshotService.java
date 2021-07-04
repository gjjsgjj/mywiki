package com.unbeaten.wiki.service;

import com.sun.org.glassfish.external.statistics.Statistic;
import com.unbeaten.wiki.domain.EbookSnapshot;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 电子书快照表 服务类
 * </p>
 *
 * @author unbeaten
 * @since 2021-07-04
 */
public interface IEbookSnapshotService{
    void genSnapshot();

    List<Statistic> getStatistic();
}
